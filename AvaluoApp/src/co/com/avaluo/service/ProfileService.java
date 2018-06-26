package co.com.avaluo.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.IProfileDAO;
import co.com.avaluo.model.dao.IPropertyDAO;
import co.com.avaluo.model.dao.IUsuarioDAO;
import co.com.avaluo.model.entity.ProfileAccount;
import co.com.avaluo.model.entity.PropertyType;
import co.com.avaluo.model.entity.Usuario;


@Named
@Transactional(readOnly = true)
public class ProfileService implements IProfileService {

	@Inject
	IProfileDAO entityDAO;

	@Transactional(readOnly = false)
	public void addEntity(ProfileAccount entity) {
		getEntityDAO().addEntity(entity);
	}

	@Transactional(readOnly = false)
	public void deleteEntity(ProfileAccount entity) {
		getEntityDAO().deleteEntity(entity);
	}

	@Transactional(readOnly = false)
	public void updateEntity(ProfileAccount entity) {
		getEntityDAO().updateEntity(entity);
	}

	public ProfileAccount getEntityById(int id) {
		return getEntityDAO().getEntity(id);
	}

	public List<ProfileAccount> getEntitys() {	
		return getEntityDAO().getEntities();
	}

	public IProfileDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEntityDAO(IProfileDAO entityDAO) {
		this.entityDAO = entityDAO;
	}
}
