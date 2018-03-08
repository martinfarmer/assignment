package farmer.martin.jobassignment.persistence;

import farmer.martin.jobassignment.controller.MetaData;
import farmer.martin.jobassignment.persistence.exceptions.FileSaveException;
import farmer.martin.jobassignment.persistence.exceptions.MetadataSaveException;
import farmer.martin.jobassignment.persistence.handler.impl.AbstractFileHandler;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;


public class AbstractFileHandlerTests {

    private PersistenceProperties properties = new PersistenceProperties();
    private AbstractFileHandler service;

    @Before
    public void init() {
       properties.setRootLocation(".");
       service = new AbstractFileHandler() {

            @Override
            public void handleMetaDataSave(MetaData metadata) throws MetadataSaveException {
            }

           @Override
           public List<MetaData> findAll() {
               return new ArrayList<>();
           }
        };
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void configureFail() throws IOException {
        properties.setRootLocation("/yy");

        expectedException.expect(IOException.class);
        service.configure(properties);
    }
    
    @Test
    public void configure() throws IOException {
        service.configure(properties);
    }
    
    @Test
    public void configureNewDir() throws IOException {
        properties.setRootLocation("./test");
        service.configure(properties);
        
        Path checkDir = Paths.get("./test");
        Assert.assertTrue(checkDir.toFile().exists());
    }

    @Test
    public void save() throws IOException, FileSaveException{
        service.configure(properties);
        service.handleFileSave(new MockMultipartFile("test", "test.txt", MediaType.TEXT_PLAIN_VALUE,
                "test one two three".getBytes()));
        
        Path checkFile = Paths.get(properties.getRootLocation(), "test.txt");
        Assert.assertTrue(checkFile.toFile().exists());
        
        checkFile.toFile().delete();
    }
    
    @Test
    public void saveAndDelete() throws IOException, FileSaveException{
        service.configure(properties);
        service.handleFileSave(new MockMultipartFile("test", "test.txt", MediaType.TEXT_PLAIN_VALUE,
                "test one two three".getBytes()));
        
        Path checkFile = Paths.get(properties.getRootLocation(), "test.txt");
        Assert.assertTrue(checkFile.toFile().exists());
        
        service.handleRemoveFile(new MockMultipartFile("test", "test.txt", MediaType.TEXT_PLAIN_VALUE,
                "test one two three".getBytes()));
        
        Assert.assertFalse(checkFile.toFile().exists());

    }
    
    @Test
    public void saveInvalidFile() throws IOException, FileSaveException{
        service.configure(properties);
        
        expectedException.expect(FileSaveException.class);

        service.handleFileSave(new MockMultipartFile("test", "../test.txt", MediaType.TEXT_PLAIN_VALUE,
                "test one two three".getBytes()));
        
        Path checkFile = Paths.get(properties.getRootLocation(), "test.txt");
        Assert.assertFalse(checkFile.toFile().exists());
    }
    
    @Test
    public void saveEmptyFile() throws IOException, FileSaveException{
        service.configure(properties);
        
        expectedException.expect(FileSaveException.class);

        service.handleFileSave(new MockMultipartFile("test", "", MediaType.TEXT_PLAIN_VALUE,
                "test one two three".getBytes()));
    }
}
