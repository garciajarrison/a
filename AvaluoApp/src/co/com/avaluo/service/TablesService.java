package co.com.avaluo.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.IPropertyDAO;
import co.com.avaluo.model.dao.ITablesDAO;
import co.com.avaluo.model.dao.IUsuarioDAO;
import co.com.avaluo.model.entity.PropertyType;
import co.com.avaluo.model.entity.Tables;
import co.com.avaluo.model.entity.Usuario;


@Named
@Transactional(readOnly = true)
public class TablesService implements ITablesService {

	@Inject
	ITablesDAO entityDAO;

	@Transactional(readOnly = false)
	public void addEntity(Tables entity) {
		getEntityDAO().addEntity(entity);
	}

	@Transactional(readOnly = false)
	public void deleteEntity(Tables entity) {
		getEntityDAO().deleteEntity(entity);
	}

	@Transactional(readOnly = false)
	public void updateEntity(Tables entity) {
		getEntityDAO().updateEntity(entity);
	}

	public Tables getEntityById(int id) {
		return getEntityDAO().getEntity(id);
	}

	public List<Tables> getEntitys() {	
		return getEntityDAO().getEntities();
	}

	public ITablesDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEntityDAO(ITablesDAO entityDAO) {
		this.entityDAO = entityDAO;
	}
}
