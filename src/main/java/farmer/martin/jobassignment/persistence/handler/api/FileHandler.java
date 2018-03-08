package farmer.martin.jobassignment.persistence.handler.api;

import farmer.martin.jobassignment.controller.MetaData;
import farmer.martin.jobassignment.persistence.exceptions.FileSaveException;
import farmer.martin.jobassignment.persistence.exceptions.MetadataSaveException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * Interface defining the api for handling files. 
 * 
 * This service allows for different implementations of how files and the metadata are stored.
 * 
 * @author martinfarmer
 */
public interface FileHandler {

    /**
     * Method to save a file
     * 
     * @param file
     * @throws FileSaveException 
     */
    void handleFileSave(MultipartFile file) throws FileSaveException;

    /**
     * Method to save the metadata
     * 
     * @param metadata
     * @throws MetadataSaveException 
     */
    void handleMetaDataSave(MetaData metadata) throws MetadataSaveException;

    /**
     * method to remove the file
     * 
     * @param file 
     */
    void handleRemoveFile(MultipartFile file);
    
    List<MetaData> findAll();
}
