package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.Propiedad;


public interface IPropiedadService {
	
	public void addEntity(Propiedad entity);
	
	public void updateEntity(Propiedad entity);

	public void deleteEntity(Propiedad entity);
	
	public Propiedad getEntityById(int id);
	
	public List<Propiedad> getEntitys( String propertyType);
}
