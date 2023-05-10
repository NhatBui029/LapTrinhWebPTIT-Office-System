package tacos.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tacos.entity.Customer;
import tacos.entity.Office;
@Repository
public interface OfficeRepository extends CrudRepository<Office, String>{
	List<Office> findByAddressContaining(String input);
	List<Office> findByLocationContaining(String input);
	
	@Query("SELECT e FROM Office e WHERE e.level LIKE %?1% AND e.priceOfficeMin >= ?2 AND e.priceOfficeMin <= ?3 AND e.priceOfficeMax >= ?2 AND e.priceOfficeMax <= ?3 AND e.area >= ?4 AND e.area<=?5 AND e.deleted = 'false'")	
	List<Office> filterOfficeAdmin(String level,Double minPrice,Double maxPrice,Double minArea,Double maxArea);
	
	@Query("SELECT e FROM Office e WHERE e.level LIKE %?1% AND e.priceOfficeMin >= ?2 AND e.priceOfficeMin <= ?3 AND e.priceOfficeMax >= ?2 AND e.priceOfficeMax <= ?3 AND e.area >= ?4 AND e.area<=?5 AND e.deleted = 'false' AND e.status != 'hired'")	
	List<Office> filterOfficeClient(String level,Double minPrice,Double maxPrice,Double minArea,Double maxArea);
	
	List<Office> findByDeleted(String string);
	List<Office> findByStatusContaining(String status);
	
	@Query("SELECT e FROM Office e WHERE e.address LIKE %?1% OR e.location LIKE %?1%")
	List<Office> searchOfficeClient(String input);
	
	@Query("SELECT e FROM Office e WHERE e.status != 'hired' AND e.deleted = 'false'")
	List<Office> consistOfficeClient();
}
