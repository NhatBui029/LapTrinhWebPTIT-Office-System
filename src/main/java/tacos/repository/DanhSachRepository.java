package tacos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tacos.entity.Admin;
import tacos.entity.DanhSach;
@Repository
public interface DanhSachRepository extends CrudRepository<DanhSach, String>{
	DanhSach findByOffice(String id);
	void deleteByOffice(String office);
}
