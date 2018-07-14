package co.com.avaluo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.IDepartamentoDAO;
import co.com.avaluo.model.entity.Departamento;

@Service
@Transactional(readOnly = true)
public class DepartamentoService implements IDepartamentoService {

	@Autowired
	private IDepartamentoDAO entityDAO;

	@Transactional(readOnly = false)
	public void addEntity(Departamento entity) {
		getEntityDAO().addEntity(entity);
	}

	@Transactional(readOnly = false)
	public void deleteEntity(Departamento entity) {
		getEntityDAO().deleteEntity(entity);
	}

	@Transactional(readOnly = false)
	public void updateEntity(Departamento entity) {
		getEntityDAO().updateEntity(entity);
	}

	public Departamento getEntityById(int id) {
		return getEntityDAO().getEntity(id);
	}

	public List<Departamento> getEntitys() {	
		return getEntityDAO().getEntities();
	}

	public IDepartamentoDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEntityDAO(IDepartamentoDAO entityDAO) {
		this.entityDAO = entityDAO;
	}
}
