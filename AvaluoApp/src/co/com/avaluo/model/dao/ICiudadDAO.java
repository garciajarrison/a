package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.Ciudad;


public interface ICiudadDAO {
	void addEntity(Ciudad entity);

	void updateEntity(Ciudad entity);
	
	void deleteEntity(Ciudad entity);
	
	Ciudad getEntity(int id);

	List<Ciudad> getEntities();
}
