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
	
	public ILicenciaDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEntityDAO(ILicenciaDAO entityDAO) {
		this.entityDAO = entityDAO;
	}

	@Transactional(readOnly = false)
	public void addLicencia(Licencia entity) {
		getEntityDAO().addLicencia(entity);
	}

	@Transactional(readOnly = false)
	public void deleteLicencia(Licencia entity) {
		getEntityDAO().deleteLicencia(entity);
	}

	@Transactional(readOnly = false)
	public void updateLicencia(Licencia entity) {
		getEntityDAO().updateLicencia(entity);
	}

	public Licencia getLicenciaById(int id) {
		return getEntityDAO().getLicenciaById(id);
	}

	public List<Licencia> getLicencias() {	
		return getEntityDAO().getLicencias();
	}
}
