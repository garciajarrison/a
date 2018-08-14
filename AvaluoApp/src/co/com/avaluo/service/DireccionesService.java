package co.com.avaluo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.IDireccionesDAO;
import co.com.avaluo.model.entity.Direcciones;


@Service
@Transactional(readOnly = true)
public class DireccionesService implements IDireccionesService {

	@Autowired
	private IDireccionesDAO entityDAO;

	@Transactional(readOnly = false)
	public void addDirecciones(Direcciones entity) {
		getEntityDAO().addDirecciones(entity);
	}

	@Transactional(readOnly = false)
	public void deleteDirecciones(Direcciones entity) {
		getEntityDAO().deleteDirecciones(entity);
	}

	@Transactional(readOnly = false)
	public void updateDirecciones(Direcciones entity) {
		getEntityDAO().updateDirecciones(entity);
	}

	public Direcciones getDireccionesById(int id) {
		return getEntityDAO().getDireccionesById(id);
	}



	public IDireccionesDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEntityDAO(IDireccionesDAO entityDAO) {
		this.entityDAO = entityDAO;
	}

}
