package co.com.avaluo.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.IPropiedadDAO;
import co.com.avaluo.model.entity.Propiedad;


@Named
@Transactional(readOnly = true)
public class PropiedadService implements IPropiedadService {

	@Inject
	IPropiedadDAO entityDAO;

	@Transactional(readOnly = false)
	public void addEntity(Propiedad entity) {
		getEntityDAO().addEntity(entity);
	}

	@Transactional(readOnly = false)
	public void deleteEntity(Propiedad entity) {
		getEntityDAO().deleteEntity(entity);
	}

	@Transactional(readOnly = false)
	public void updateEntity(Propiedad entity) {
		getEntityDAO().updateEntity(entity);
	}

	public Propiedad getEntityById(int id) {
		return getEntityDAO().getEntity(id);
	}

	public List<Propiedad> getEntitys(String propertyType) {	
		return getEntityDAO().getEntities(propertyType);
	}

	public IPropiedadDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEntityDAO(IPropiedadDAO entityDAO) {
		this.entityDAO = entityDAO;
	}
}
