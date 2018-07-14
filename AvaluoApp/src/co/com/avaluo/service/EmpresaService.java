package co.com.avaluo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.IEmpresaDAO;
import co.com.avaluo.model.entity.Empresa;
import co.com.avaluo.model.entity.Estrato;
import co.com.avaluo.model.entity.Usuario;

@Service
@Transactional(readOnly = true)
public class EmpresaService implements IEmpresaService {

	@Autowired
	private IEmpresaDAO entityDAO;

	@Transactional(readOnly = false)
	public void addEntity(Empresa entity) {
		getEntityDAO().addEntity(entity);
	}

	@Transactional(readOnly = false)
	public void deleteEntity(Empresa entity) {
		getEntityDAO().deleteEntity(entity);
	}

	@Transactional(readOnly = false)
	public void updateEntity(Empresa entity) {
		getEntityDAO().updateEntity(entity);
	}

	public Empresa getEntityById(int id) {
		return getEntityDAO().getEntity(id);
	}

	public List<Empresa> getEntitys() {	
		return getEntityDAO().getEntities();
	}
	
	public IEmpresaDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEmpresaDAO(IEmpresaDAO entityDAO) {
		this.entityDAO = entityDAO;
	}
}
