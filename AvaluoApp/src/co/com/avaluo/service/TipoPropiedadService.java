package co.com.avaluo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.ITipoPropiedadDAO;
import co.com.avaluo.model.entity.TipoPropiedad;

@Service
@Transactional(readOnly = true)
public class TipoPropiedadService implements ITipoPropiedadService {

	@Autowired
	private ITipoPropiedadDAO entityDAO;

	@Transactional(readOnly = false)
	public void addEntity(TipoPropiedad entity) {
		getEntityDAO().addEntity(entity);
	}

	@Transactional(readOnly = false)
	public void deleteEntity(TipoPropiedad entity) {
		getEntityDAO().deleteEntity(entity);
	}

	@Transactional(readOnly = false)
	public void updateEntity(TipoPropiedad entity) {
		getEntityDAO().updateEntity(entity);
	}

	public TipoPropiedad getEntityById(int id) {
		return getEntityDAO().getEntity(id);
	}

	public List<TipoPropiedad> getEntitys(String tipo, int id) {	
		return getEntityDAO().getEntities(tipo, id);
	}

	public ITipoPropiedadDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEntityDAO(ITipoPropiedadDAO entityDAO) {
		this.entityDAO = entityDAO;
	}
}
