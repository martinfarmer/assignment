package farmer.martin.jobassignment.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.*;

/**
 * Entity stored in the database that holds the file reference and user
 * specified metadata.
 *
 * @author martinfarmer
 */
@Entity
public class MetaData implements Serializable{

    @Id
    @GeneratedValue
    private long id;
    
    @ElementCollection
    private Map<String, String> metadata = new HashMap<>();
    private String fileLocation;
    
    public MetaData() {}

    public MetaData(String fileLocation, Map<String, String> metadata) {
        this.metadata = metadata;
        this.fileLocation = fileLocation;
    }

    ;

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public String getFileLocation() {
        return fileLocation;
    }
}
