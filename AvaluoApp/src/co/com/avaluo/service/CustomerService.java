package co.com.avaluo.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.ICustomerDAO;
import co.com.avaluo.model.dao.IMarketDAO;
import co.com.avaluo.model.entity.Customer;
import co.com.avaluo.model.entity.MarketCategories;


@Named
@Transactional(readOnly = true)
public class CustomerService implements ICustomerService {

	@Inject
	ICustomerDAO entityDAO;

	@Transactional(readOnly = false)
	public void addEntity(Customer entity) {
		getEntityDAO().addEntity(entity);
	}
 
	@Transactional(readOnly = false)
	public void deleteEntity(Customer entity) {
		getEntityDAO().deleteEntity(entity);
	}

	@Transactional(readOnly = false)
	public void updateEntity(Customer entity) {
		getEntityDAO().updateEntity(entity);
	}

	public Customer getEntityById(int id) {
		return getEntityDAO().getEntity(id);
	}

	public List<Customer> getEntitys() {	
		return getEntityDAO().getEntities();
	}

	public ICustomerDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEntityDAO(ICustomerDAO entityDAO) {
		this.entityDAO = entityDAO;
	}
}
