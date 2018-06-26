package co.com.avaluo.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.IPropertyDAO;
import co.com.avaluo.model.dao.IServicesDAO;
import co.com.avaluo.model.dao.IUsuarioDAO;
import co.com.avaluo.model.entity.PropertyType;
import co.com.avaluo.model.entity.Services;
import co.com.avaluo.model.entity.Usuario;


@Named
@Transactional(readOnly = true)
public class ServicesService implements IServicesService {

	@Inject
	IServicesDAO entityDAO;

	@Transactional(readOnly = false)
	public void addEntity(Services entity) {
		getEntityDAO().addEntity(entity);
	}

	@Transactional(readOnly = false)
	public void deleteEntity(Services entity) {
		getEntityDAO().deleteEntity(entity);
	}

	@Transactional(readOnly = false)
	public void updateEntity(Services entity) {
		getEntityDAO().updateEntity(entity);
	}

	public Services getEntityById(int id) {
		return getEntityDAO().getEntity(id);
	}

	public List<Services> getEntitys() {	
		return getEntityDAO().getEntities();
	}

	public IServicesDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEntityDAO(IServicesDAO entityDAO) {
		this.entityDAO = entityDAO;
	}
}
