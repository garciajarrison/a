package co.com.avaluo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.ITablasDAO;
import co.com.avaluo.model.entity.Tablas;

@Service
@Transactional(readOnly = true)
public class TablaService implements ITablasService {

	@Autowired
	private ITablasDAO entityDAO;

	@Transactional(readOnly = false)
	public void addEntity(Tablas entity) {
		getEntityDAO().addEntity(entity);
	}

	@Transactional(readOnly = false)
	public void deleteEntity(Tablas entity) {
		getEntityDAO().deleteEntity(entity);
	}

	@Transactional(readOnly = false)
	public void updateEntity(Tablas entity) {
		getEntityDAO().updateEntity(entity);
	}

	public Tablas getEntityById(int id) {
		return getEntityDAO().getEntity(id);
	}

	public List<Tablas> getEntitys(int idEmpresa) {	
		return getEntityDAO().getEntities(idEmpresa);
	}

	public ITablasDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEntityDAO(ITablasDAO entityDAO) {
		this.entityDAO = entityDAO;
	}
}
