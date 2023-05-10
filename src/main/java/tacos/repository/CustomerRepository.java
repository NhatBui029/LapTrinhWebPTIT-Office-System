package tacos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tacos.entity.Admin;
import tacos.entity.Customer;
import tacos.entity.Office;
@Repository
public interface CustomerRepository extends CrudRepository<Customer, String>{
	Customer findByUser(String user);
	Customer findByUserAndPassword(String user,String password);
	List<Customer> findByDeleted(String string);
	void deleteByUser(String user);

}
