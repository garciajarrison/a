package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.Customer;


public interface ICustomerDAO {
	public void addEntity(Customer entity);

	public void updateEntity(Customer entity);
	
	public void deleteEntity(Customer entity);
	
	public Customer getEntity(int id);

	public List<Customer> getEntities();
	
	public Customer getCustomer(String idCustomer);
}
