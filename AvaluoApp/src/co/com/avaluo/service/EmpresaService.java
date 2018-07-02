package co.com.avaluo.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.IEmpresaDAO;
import co.com.avaluo.model.entity.Empresa;
import co.com.avaluo.model.entity.Estrato;
import co.com.avaluo.model.entity.Usuario;


@Named
@Transactional(readOnly = true)
public class EmpresaService implements IEmpresaService {

	@Inject
	IEmpresaDAO entityDAO;

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

	public Empresa getEmpresaPorUsuario(Usuario usuario) {
		return getEntityDAO().getEmpresaPorUsuario(usuario);
	}
	
	public IEmpresaDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEmpresaDAO(IEmpresaDAO entityDAO) {
		this.entityDAO = entityDAO;
	}
}
