package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.Ciudad;


public interface ICiudadService {
	
	void addEntity(Ciudad entity);
	
	void updateEntity(Ciudad entity);

	void deleteEntity(Ciudad entity);
	
	Ciudad getEntityById(int id);
	
	List<Ciudad> getEntitys();
}
