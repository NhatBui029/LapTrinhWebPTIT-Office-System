package tacos.service;

import java.util.List;

import tacos.entity.Customer;

public interface ICustomerService {
	List<Customer> getAllCustomer();
	List<Customer> getAllCustomerDeleted();
	Customer getCustomerByUser(String user);
	Customer getCustomerByUserAndPassword(String user,String password);
	Customer saveCustomer(Customer cus);
	void deleteCustomerByUser(String user);
}
