package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.Propiedad;


public interface IPropiedadDAO {
	void addEntity(Propiedad entity);

	void updateEntity(Propiedad entity);
	
	void deleteEntity(Propiedad entity);
	
	Propiedad getEntity(int id);

	List<Propiedad> getEntities( String propertyType);
}
