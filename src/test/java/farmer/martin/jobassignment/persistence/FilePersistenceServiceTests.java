package farmer.martin.jobassignment.persistence;

import farmer.martin.jobassignment.controller.MetaData;
import farmer.martin.jobassignment.persistence.exceptions.FileSaveException;
import farmer.martin.jobassignment.persistence.exceptions.MetadataSaveException;
import farmer.martin.jobassignment.persistence.handler.api.FileHandler;
import farmer.martin.jobassignment.persistence.handler.impl.AbstractFileHandler;
import farmer.martin.jobassignment.persistence.service.FilePersistenceService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

public class FilePersistenceServiceTests {

    private FileHandler fileHandler;
    private final FilePersistenceService filePersistentService = new FilePersistenceService();

    @Before
    public void init() {
        fileHandler = mock(AbstractFileHandler.class);
        filePersistentService.configureFileHandler(fileHandler);
    }

    @Test
    public void save() throws IOException, MetadataSaveException, FileSaveException {

        Map<String, String> meta = new HashMap<>();
        meta.put("test", "data");
        MetaData md = new MetaData("test.txt", meta);
        filePersistentService.save(new MockMultipartFile("test", "test.txt", MediaType.TEXT_PLAIN_VALUE,
                "test one two three".getBytes()), md);

        verify(fileHandler).handleFileSave(ArgumentMatchers.any());
        verify(fileHandler).handleMetaDataSave(ArgumentMatchers.any());
    }
}
