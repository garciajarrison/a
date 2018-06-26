package co.com.avaluo.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.IPropertyDAO;
import co.com.avaluo.model.dao.ISoftwareDAO;
import co.com.avaluo.model.dao.IUsuarioDAO;
import co.com.avaluo.model.entity.PropertyType;
import co.com.avaluo.model.entity.SoftwareAccount;
import co.com.avaluo.model.entity.Usuario;


@Named
@Transactional(readOnly = true)
public class SoftwareService implements ISoftwareService {

	@Inject
	ISoftwareDAO entityDAO;

	@Transactional(readOnly = false)
	public void addEntity(SoftwareAccount entity) {
		getEntityDAO().addEntity(entity);
	}

	@Transactional(readOnly = false)
	public void deleteEntity(SoftwareAccount entity) {
		getEntityDAO().deleteEntity(entity);
	}

	@Transactional(readOnly = false)
	public void updateEntity(SoftwareAccount entity) {
		getEntityDAO().updateEntity(entity);
	}

	public SoftwareAccount getEntityById(int id) {
		return getEntityDAO().getEntity(id);
	}

	public List<SoftwareAccount> getEntitys() {	
		return getEntityDAO().getEntities();
	}

	public ISoftwareDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEntityDAO(ISoftwareDAO entityDAO) {
		this.entityDAO = entityDAO;
	}
}
