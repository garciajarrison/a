package co.com.avaluo.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.ICotizacionDAO;
import co.com.avaluo.model.entity.Cotizacion;


@Named
@Transactional(readOnly = true)
public class CotizacionService implements ICotizacionService {

	@Inject
	ICotizacionDAO entityDAO;

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

	public List<Cotizacion> getEntitys() {	
		return getEntityDAO().getEntities();
	}

	public ICotizacionDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEntityDAO(ICotizacionDAO entityDAO) {
		this.entityDAO = entityDAO;
	}
}
