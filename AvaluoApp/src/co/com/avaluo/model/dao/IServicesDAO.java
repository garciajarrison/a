package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.Services;


public interface IServicesDAO {
	public void addEntity(Services entity);

	public void updateEntity(Services entity);
	
	public void deleteEntity(Services entity);
	
	public Services getEntity(int id);

	public List<Services> getEntities();
}
