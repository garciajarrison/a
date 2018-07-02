package co.com.avaluo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.IEmpresaDAO;
import co.com.avaluo.model.entity.Estrato;

@Service
@Transactional(readOnly = true)
public class EmpresaService implements IEmpresaService {

	@Autowired
	private IEmpresaDAO entityDAO;

	@Transactional(readOnly = false)
	public void addEntity(Estrato entity) {
		getEntityDAO().addEntity(entity);
	}

	@Transactional(readOnly = false)
	public void deleteEntity(Estrato entity) {
		getEntityDAO().deleteEntity(entity);
	}

	@Transactional(readOnly = false)
	public void updateEntity(Estrato entity) {
		getEntityDAO().updateEntity(entity);
	}

	public Estrato getEntityById(int id) {
		return getEntityDAO().getEntity(id);
	}

	public List<Estrato> getEntitys() {	
		return getEntityDAO().getEntities();
	}
	
	public IEmpresaDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEmpresaDAO(IEmpresaDAO entityDAO) {
		this.entityDAO = entityDAO;
	}
}
