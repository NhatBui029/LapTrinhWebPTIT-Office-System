package tacos.service;

import java.util.List;
import java.util.Optional;

import tacos.entity.Customer;
import tacos.entity.Office;

public interface IOfficeService {
	List<Office> getAllOffice();
	List<Office> getAllOfficeClient();
	Office saveOffice(Office office);
	Office getOfficeById(String id);
	void deleteOfficeById(String id);
	
	List<Office> getOfficeByAddressContain(String input);
	List<Office> getOfficeByLocationContain(String input);
	List<Office> getOfficeBySearchClient(String input);
	List<Office> getOfficeByFilterAdmin(String level,Double minPrice,Double maxPrice,Double minArea,Double maxArea);
	List<Office> getOfficeByFilterClient(String level,Double minPrice,Double maxPrice,Double minArea,Double maxArea);

	List<Office> getAllOfficeDeleted();
	List<Office> getAllOfficeByStatus(String status);
}
