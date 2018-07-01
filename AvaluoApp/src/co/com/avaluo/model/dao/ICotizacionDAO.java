package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.Cotizacion;


public interface ICotizacionDAO {
	public void addEntity(Cotizacion entity);

	public void updateEntity(Cotizacion entity);
	
	public void deleteEntity(Cotizacion entity);
	
	public Cotizacion getEntity(int id);

	public List<Cotizacion> getEntities();
}
