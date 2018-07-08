package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.TipoPropiedad;


public interface ITipoPropiedadService {
	
	void addEntity(TipoPropiedad entity);
	
	void updateEntity(TipoPropiedad entity);

	void deleteEntity(TipoPropiedad entity);
	
	TipoPropiedad getEntityById(int id);
	
	List<TipoPropiedad> getEntitys( String propertyType);
}
