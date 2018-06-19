package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.Customer;


public interface ICustomerService {
	
	public void addEntity(Customer entity);
	
	public void updateEntity(Customer entity);

	public void deleteEntity(Customer entity);
	
	public Customer getEntityById(int id);
	
	public List<Customer> getEntitys();
}
