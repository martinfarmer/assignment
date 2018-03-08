package farmer.martin.jobassignment.persistence.database;

import farmer.martin.jobassignment.controller.MetaData;
import farmer.martin.jobassignment.persistence.exceptions.FileSaveException;
import farmer.martin.jobassignment.persistence.exceptions.MetadataSaveException;
import farmer.martin.jobassignment.persistence.service.FilePersistenceService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabasePersistenceServiceTests {

    @Autowired
    private FilePersistenceService filePersistentService;

    @Before
    public void init() {
    }

    @Test
    public void save() throws IOException, MetadataSaveException, FileSaveException {

        Map<String, String> meta = new HashMap<>();
        meta.put("test", "data");
        MetaData md = new MetaData("test.txt", meta);
        filePersistentService.save(new MockMultipartFile("test", "test.txt", MediaType.TEXT_PLAIN_VALUE,
                "test one two three".getBytes()), md);

        Assert.assertEquals(1, filePersistentService.findAll().size());
    }
}
