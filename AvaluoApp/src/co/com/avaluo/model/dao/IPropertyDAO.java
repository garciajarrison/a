package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.PropertyType;


public interface IPropertyDAO {
	public void addEntity(PropertyType entity);

	public void updateEntity(PropertyType entity);
	
	public void deleteEntity(PropertyType entity);
	
	public PropertyType getEntity(int id);

	public List<PropertyType> getEntities( String propertyType);
}
