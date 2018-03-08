package farmer.martin.jobassignment.controller;

import farmer.martin.jobassignment.persistence.exceptions.FileSaveException;
import farmer.martin.jobassignment.persistence.exceptions.MetadataSaveException;
import farmer.martin.jobassignment.persistence.service.FilePersistenceService;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * Main REST Controller.
 * 
 * Offers a REST api for file uploads. Files and metadata can be uploaded via a multipart POST
 * 
 * @author martinfarmer
 */
@RestController
public class FileUploadController {

    // Main service to persist the file and metadata.
    private final FilePersistenceService service;

    @Autowired
    public FileUploadController(FilePersistenceService service) {
        this.service = service;
    }

    /**
     * Allows a file and meta data (JSON) to be uploaded to the persistent store.
     * 
     * @param file
     * @param meta
     * @return OK for success or BAD_REQUEST for failure.
     */
    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file,
            @RequestParam("meta") Map<String, String> meta) {

        MetaData metadata = new MetaData(file.getOriginalFilename(), meta);
        try {
            service.save(file, metadata);
        } catch (MetadataSaveException | FileSaveException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok().build();
    }
}
