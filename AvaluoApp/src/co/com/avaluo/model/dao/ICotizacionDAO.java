package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.Cotizacion;


public interface ICotizacionDAO {
	void addEntity(Cotizacion entity);

	void updateEntity(Cotizacion entity);
	
	void deleteEntity(Cotizacion entity);
	
	Cotizacion getEntity(int id);

	List<Cotizacion> getEntities();
}