package co.com.avaluo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.IPropietariosDAO;
import co.com.avaluo.model.entity.Propietarios;

@Service
@Transactional(readOnly = true)
public class PropietariosService implements IPropietariosService {

	@Autowired
	private IPropietariosDAO entityDAO;

	@Transactional(readOnly = false)
	public void addEntity(Propietarios entity) {
		getEntityDAO().addEntity(entity);
	}

	@Transactional(readOnly = false)
	public void deleteEntity(Propietarios entity) {
		getEntityDAO().deleteEntity(entity);
	}

	@Transactional(readOnly = false)
	public void updateEntity(Propietarios entity) {
		getEntityDAO().updateEntity(entity);
	}

	public Propietarios getEntityById(int id) {
		return getEntityDAO().getEntity(id);
	}



	public List<Propietarios > listaPropietarios(int idVisita) {	
		return getEntityDAO().listaPropietarios(idVisita);
	}

	

	public IPropietariosDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEntityDAO(IPropietariosDAO entityDAO) {
		this.entityDAO = entityDAO;
	}
}
