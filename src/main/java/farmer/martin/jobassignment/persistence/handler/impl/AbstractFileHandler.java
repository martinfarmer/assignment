package farmer.martin.jobassignment.persistence.handler.impl;

import farmer.martin.jobassignment.controller.MetaData;
import farmer.martin.jobassignment.persistence.PersistenceProperties;
import farmer.martin.jobassignment.persistence.handler.api.FileHandler;
import farmer.martin.jobassignment.persistence.exceptions.FileSaveException;
import farmer.martin.jobassignment.persistence.exceptions.MetadataSaveException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * This abstract implementation provides a base service for saving files to disk but defering the storage of metadata
 * @author martinfarmer
 */
public abstract class AbstractFileHandler implements FileHandler {

    protected Path fileStorageRoot;

    private void validateFile(String filename) throws FileSaveException {
        if (filename.isEmpty()) {
            throw new FileSaveException(filename, "Failed to store empty file " + filename);
        }
        if (filename.contains("..")) {

            throw new FileSaveException(filename, 
                    "Cannot store file with relative path outside current directory ");
        }
    }

    /**
     * Configuration method to take a properties class
     * 
     * @param props
     * @throws IOException 
     */
    @Autowired
    public void configure(PersistenceProperties props) throws IOException {
        this.fileStorageRoot = Paths.get(props.getRootLocation());
        Files.createDirectories(fileStorageRoot);
    }

    /**
     * Default implementation will save to disk
     *
     * @param file
     * @throws FileSaveException
     */
    @Override
    public void handleFileSave(MultipartFile file) throws FileSaveException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            validateFile(filename);
            Files.copy(file.getInputStream(), this.fileStorageRoot.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileSaveException(file.getOriginalFilename(), "Failed to store file " + filename, e);
        }
    }

    @Override
    public abstract void handleMetaDataSave(MetaData metadata) throws MetadataSaveException;

    /**
     * removes a previously saved file.
     * 
     * @param file 
     */
    @Override
    public void handleRemoveFile(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        File ffile = Paths.get(filename).toFile();
        ffile.delete();
    }

}
