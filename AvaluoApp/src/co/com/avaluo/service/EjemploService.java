package co.com.avaluo.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.IUsuarioDAO;
import co.com.avaluo.model.entity.Usuario;


@Named
@Transactional(readOnly = true)
public class EjemploService implements IUsuarioService {

	@Inject
	IUsuarioDAO entityDAO;

	@Transactional(readOnly = false)
	public void addEntity(Usuario entity) {
		getEntityDAO().addEntity(entity);
	}

	@Transactional(readOnly = false)
	public void deleteEntity(Usuario entity) {
		getEntityDAO().deleteEntity(entity);
	}

	@Transactional(readOnly = false)
	public void updateEntity(Usuario entity) {
		getEntityDAO().updateEntity(entity);
	}

	public Usuario getEntityById(int id) {
		return getEntityDAO().getEntity(id);
	}

	public List<Usuario> getEntitys() {	
		return getEntityDAO().getEntities();
	}

	public IUsuarioDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEntityDAO(IUsuarioDAO entityDAO) {
		this.entityDAO = entityDAO;
	}
}
