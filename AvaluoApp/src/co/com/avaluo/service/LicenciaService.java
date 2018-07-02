package co.com.avaluo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.ILicenciaDAO;
import co.com.avaluo.model.entity.Licencia;

@Service
@Transactional(readOnly = true)
public class LicenciaService implements ILicenciaService {

	@Autowired
	private ILicenciaDAO entityDAO;

	@Transactional(readOnly = false)
	public void addEntity(Licencia entity) {
		getEntityDAO().addEntity(entity);
	}

	@Transactional(readOnly = false)
	public void deleteEntity(Licencia entity) {
		getEntityDAO().deleteEntity(entity);
	}

	@Transactional(readOnly = false)
	public void updateEntity(Licencia entity) {
		getEntityDAO().updateEntity(entity);
	}

	public Licencia getEntityById(int id) {
		return getEntityDAO().getEntity(id);
	}

	public List<Licencia> getEntitys() {	
		return getEntityDAO().getEntities();
	}

	public ILicenciaDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEntityDAO(ILicenciaDAO entityDAO) {
		this.entityDAO = entityDAO;
	}
}