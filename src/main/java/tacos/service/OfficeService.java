package tacos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import tacos.entity.Customer;
import tacos.entity.Office;
import tacos.repository.OfficeRepository;

@Service
public class OfficeService implements IOfficeService{
	@Autowired
	private OfficeRepository officeReps;

	@Override
	public List<Office> getAllOffice() {
		return (List<Office>) officeReps.findByDeleted("false");
	}

	@Override
	public Office saveOffice(Office office) {
		return officeReps.save(office);
	}

	@Override
	public Office getOfficeById(String id) {
		Optional<Office> optionalOffice = officeReps.findById(id);
		Office office = optionalOffice.orElse(new Office());
		return office;
	}

	@Override
	public void deleteOfficeById(String id) {
		officeReps.deleteById(id);
	}

	@Override
	public List<Office> getOfficeByAddressContain(String input) {
		return officeReps.findByAddressContaining(input);
	}

	@Override
	public List<Office> getOfficeByLocationContain(String input) {
		return officeReps.findByLocationContaining(input);
	}

	@Override
	public List<Office> getOfficeByFilterAdmin(String level, Double minPrice,Double maxPrice,Double minArea,Double maxArea) {
		return officeReps.filterOfficeAdmin(level, minPrice, maxPrice,minArea,maxArea);
	}

	@Override
	public List<Office> getAllOfficeDeleted() {
		return officeReps.findByDeleted("true");
	}

	@Override
	public List<Office> getAllOfficeByStatus(String status) {
		return officeReps.findByStatusContaining(status);
	}

	@Override
	public List<Office> getOfficeByFilterClient(String level, Double minPrice, Double maxPrice, Double minArea,
			Double maxArea) {
		return officeReps.filterOfficeClient(level, minPrice, maxPrice, minArea, maxArea);
	}

	@Override
	public List<Office> getOfficeBySearchClient(String input) {
		return officeReps.searchOfficeClient(input);
	}

	@Override
	public List<Office> getAllOfficeClient() {
		return officeReps.consistOfficeClient();
	}

	
	
}
