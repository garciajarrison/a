package co.com.avaluo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.IEmpresaDAO;
import co.com.avaluo.model.entity.Empresa;

@Service
@Transactional(readOnly = true)
public class EmpresaService implements IEmpresaService {

	@Autowired
	private IEmpresaDAO entityDAO;

	@Transactional(readOnly = false)
	public void addEmpresa(Empresa entity) {
		getEntityDAO().addEmpresa(entity);
	}

	@Transactional(readOnly = false)
	public void deleteEmpresa(Empresa entity) {
		getEntityDAO().deleteEmpresa(entity);
	}

	@Transactional(readOnly = false)
	public void updateEmpresa(Empresa entity) {
		getEntityDAO().updateEmpresa(entity);
	}

	public Empresa getEmpresaById(int id) {
		return getEntityDAO().getEmpresaById(id);
	}

	public List<Empresa> getEmpresas() {	
		return getEntityDAO().getEmpresas();
	}
	
	public IEmpresaDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEmpresaDAO(IEmpresaDAO entityDAO) {
		this.entityDAO = entityDAO;
	}
}
