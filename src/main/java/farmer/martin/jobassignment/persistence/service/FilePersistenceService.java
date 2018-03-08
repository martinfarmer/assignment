package farmer.martin.jobassignment.persistence.service;

import farmer.martin.jobassignment.controller.MetaData;
import farmer.martin.jobassignment.persistence.handler.api.FileHandler;
import farmer.martin.jobassignment.persistence.exceptions.FileSaveException;
import farmer.martin.jobassignment.persistence.exceptions.MetadataSaveException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * File persistence service.
 * 
 * This service will take a file and metadata and uses an injected file handler to defer the actual handling
 * of the data. This orchestrator implements a simple command pattern
 * 
 * @author martinfarmer
 */
@Service
public class FilePersistenceService {

    protected FileHandler fileHandler;

    /**
     * DI the file handler implementation.
     * 
     * @param fileHandler 
     */
    @Autowired
    public void configureFileHandler(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    /**
     * save the file and metadata
     * 
     * @param file
     * @param metadata
     * @throws MetadataSaveException
     * @throws FileSaveException 
     */
    public final void save(MultipartFile file, MetaData metadata) throws MetadataSaveException, FileSaveException {
        fileHandler.handleFileSave(file);
        fileHandler.handleMetaDataSave(metadata);
    }
    
    public List<MetaData> findAll() {
        return fileHandler.findAll();
    }
}
