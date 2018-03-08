package farmer.martin.jobassignment.persistence;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Simple properties class to handle the root for file storage.
 *
 * Value is to be configured in the main application.properties file prefixed
 * with "filestorage"
 *
 * @author martinfarmer
 */
@ConfigurationProperties("filestorage")
public class PersistenceProperties {

    /**
     * Folder location for storing files
     */
    private String rootLocation = "";

    public String getRootLocation() {
        return rootLocation;
    }

    public void setRootLocation(String rootLocation) {
        this.rootLocation = rootLocation;
    }
}
