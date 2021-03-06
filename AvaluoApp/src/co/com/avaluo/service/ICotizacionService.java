package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.Avaluos;
import co.com.avaluo.model.entity.Cotizacion;
import co.com.avaluo.model.entity.DetalleCotizacion;


public interface ICotizacionService {
	
	public void addEntity(Cotizacion entity);
	
	public void addAvaluo(Avaluos entity);
	
	public void updateAvaluo(Avaluos entity);
	
	public void updateEntity(Cotizacion entity);

	public void deleteEntity(Cotizacion entity);
	
	public Cotizacion getEntityById(int id);
	
	public List<Avaluos> getAvaluos();
	
	public List<Cotizacion> getEntitys(int id);
	
	public List<DetalleCotizacion> getDetCotizacion(int id);
}
