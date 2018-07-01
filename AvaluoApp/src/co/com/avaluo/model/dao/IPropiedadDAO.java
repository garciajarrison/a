package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.Propiedad;


public interface IPropiedadDAO {
	public void addEntity(Propiedad entity);

	public void updateEntity(Propiedad entity);
	
	public void deleteEntity(Propiedad entity);
	
	public Propiedad getEntity(int id);

	public List<Propiedad> getEntities( String propertyType);
}
