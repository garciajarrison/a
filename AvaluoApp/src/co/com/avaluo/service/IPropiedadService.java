package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.Propiedad;


public interface IPropiedadService {
	
	void addEntity(Propiedad entity);
	
	void updateEntity(Propiedad entity);

	void deleteEntity(Propiedad entity);
	
	Propiedad getEntityById(int id);
	
	List<Propiedad> getEntitys( String propertyType);
}
