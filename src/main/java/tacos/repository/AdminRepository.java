package tacos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tacos.entity.Admin;
import tacos.entity.Office;
@Repository
public interface AdminRepository extends CrudRepository<Admin, String>{

	Admin findByUser(String user);
	Admin findByUserAndPassword(String user,String password);

}
