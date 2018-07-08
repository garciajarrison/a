package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.Tablas;


public interface ITablasService {
	
	void addEntity(Tablas entity);
	
	void updateEntity(Tablas entity);

	void deleteEntity(Tablas entity);
	
	Tablas getEntityById(int id);
	
	List<Tablas> getEntitys();
}
