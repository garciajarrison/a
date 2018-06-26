package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.PropertyType;
import co.com.avaluo.model.entity.Usuario;


public interface IPropertyService {
	
	public void addEntity(PropertyType entity);
	
	public void updateEntity(PropertyType entity);

	public void deleteEntity(PropertyType entity);
	
	public PropertyType getEntityById(int id);
	
	public List<PropertyType> getEntitys( String propertyType);
}
