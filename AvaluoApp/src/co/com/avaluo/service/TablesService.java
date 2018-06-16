package co.com.avaluo.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.IPropertyDAO;
import co.com.avaluo.model.dao.IUsuarioDAO;
import co.com.avaluo.model.entity.PropertyType;
import co.com.avaluo.model.entity.Usuario;


@Named
@Transactional(readOnly = true)
public class TablesService implements IPropertyService {

	@Inject
	IPropertyDAO entityDAO;

	@Transactional(readOnly = false)
	public void addEntity(PropertyType entity) {
		getEntityDAO().addEntity(entity);
	}

	@Transactional(readOnly = false)
	public void deleteEntity(PropertyType entity) {
		getEntityDAO().deleteEntity(entity);
	}

	@Transactional(readOnly = false)
	public void updateEntity(PropertyType entity) {
		getEntityDAO().updateEntity(entity);
	}

	public PropertyType getEntityById(int id) {
		return getEntityDAO().getEntity(id);
	}

	public List<PropertyType> getEntitys() {	
		return getEntityDAO().getEntities();
	}

	public IPropertyDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEntityDAO(IPropertyDAO entityDAO) {
		this.entityDAO = entityDAO;
	}
}
