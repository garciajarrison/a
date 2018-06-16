package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.Services;


public interface IServicesService {
	
	public void addEntity(Services entity);
	
	public void updateEntity(Services entity);

	public void deleteEntity(Services entity);
	
	public Services getEntityById(int id);
	
	public List<Services> getEntitys();
}
