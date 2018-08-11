package co.com.avaluo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.ICiudadDAO;
import co.com.avaluo.model.entity.Ciudad;
import co.com.avaluo.model.entity.Departamento;
import co.com.avaluo.model.entity.Pais;

@Service
@Transactional(readOnly = true)
public class CiudadService implements ICiudadService {

	@Autowired
	private ICiudadDAO entityDAO;
	
	public ICiudadDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEntityDAO(ICiudadDAO entityDAO) {
		this.entityDAO = entityDAO;
	}

	@Transactional(readOnly = false)
	public void addEntity(Ciudad entity) {
		getEntityDAO().addEntity(entity);
	}

	@Transactional(readOnly = false)
	public void deleteEntity(Ciudad entity) {
		getEntityDAO().deleteEntity(entity);
	}

	@Transactional(readOnly = false)
	public void updateEntity(Ciudad entity) {
		getEntityDAO().updateEntity(entity);
	}

	public Ciudad getEntityById(int id) {
		return getEntityDAO().getEntity(id);
	}

	public List<Ciudad> getEntitys() {	
		return getEntityDAO().getEntities();
	}

	public List<Pais> getPaises() {
		return getEntityDAO().getPaises();
	}

	public List<Departamento> getDepartamentos(int idPais) {
		return getEntityDAO().getDepartamentos(idPais);
	}

	public List<Ciudad> getCiudades(int idDepartamento) {
		return getEntityDAO().getCiudades(idDepartamento);
	}
}
