package farmer.martin.jobassignment.persistence.handler.impl.database;

import farmer.martin.jobassignment.controller.MetaData;
import farmer.martin.jobassignment.persistence.handler.impl.AbstractFileHandler;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Mongo implementation. Will save the metadata into a database.
 * @author martinfarmer
 */
@Service
public class DatabaseFileHandler extends AbstractFileHandler{

    // database accessor
    @Autowired
    private MetaDataRepository metaDataRepository;

    /**
     * implementation that saves the data to a database
     * @param metadata 
     */
    @Override
    public void handleMetaDataSave(MetaData metadata) {
        metaDataRepository.save(metadata);
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public List<MetaData> findAll() {
        return metaDataRepository.findAll();
    }
}
