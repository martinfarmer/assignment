package farmer.martin.jobassignment.persistence.handler.impl.database;

import farmer.martin.jobassignment.controller.MetaData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * MetData repository
 * 
 * @author martinfarmer
 */
public interface MetaDataRepository extends JpaRepository<MetaData, String> {  
}
