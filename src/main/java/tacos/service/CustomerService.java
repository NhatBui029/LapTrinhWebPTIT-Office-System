package tacos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tacos.entity.Customer;
import tacos.repository.CustomerRepository;

@Service
public class CustomerService implements ICustomerService{
	@Autowired
	private CustomerRepository cusReps;

	@Override
	public Customer getCustomerByUser(String user) {
		return cusReps.findByUser(user);
	}

	@Override
	public Customer getCustomerByUserAndPassword(String user, String password) {
		return cusReps.findByUserAndPassword(user, password);
	}

	@Override
	public Customer saveCustomer(Customer cus) {
		return cusReps.save(cus);
	}

	@Override
	public List<Customer> getAllCustomer() {
		return (List<Customer>) cusReps.findByDeleted("false");
	}

	@Override
	public List<Customer> getAllCustomerDeleted() {
		return (List<Customer>) cusReps.findByDeleted("true");
	}

	@Override
	 @Transactional
	public void deleteCustomerByUser(String user) {
		cusReps.deleteByUser(user);
	}



}
