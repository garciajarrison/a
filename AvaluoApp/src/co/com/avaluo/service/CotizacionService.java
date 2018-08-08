package co.com.avaluo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.ICotizacionDAO;
import co.com.avaluo.model.entity.Cotizacion;
import co.com.avaluo.model.entity.DetalleCotizacion;


@Service
@Transactional(readOnly = true)
public class CotizacionService implements ICotizacionService {

	@Autowired
	private ICotizacionDAO entityDAO;

	@Transactional(readOnly = false)
	public void addEntity(Cotizacion entity) {
		getEntityDAO().addEntity(entity);
	}

	@Transactional(readOnly = false)
	public void deleteEntity(Cotizacion entity) {
		getEntityDAO().deleteEntity(entity);
	}

	@Transactional(readOnly = false)
	public void updateEntity(Cotizacion entity) {
		getEntityDAO().updateEntity(entity);
	}

	public Cotizacion getEntityById(int id) {
		return getEntityDAO().getEntity(id);
	}

	public List<Cotizacion> getEntitys(int id) {	
		return getEntityDAO().getEntities(id);
	}

	public List<DetalleCotizacion> getDetCotizacion(int id) {	
		return getEntityDAO().getDetCotizacion(id);
	}



	public ICotizacionDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEntityDAO(ICotizacionDAO entityDAO) {
		this.entityDAO = entityDAO;
	}
}
