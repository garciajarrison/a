package co.com.avaluo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.IPropiedadDAO;
import co.com.avaluo.model.entity.Propiedad;
import co.com.avaluo.model.entity.TipoPropiedad;

@Service
@Transactional(readOnly = true)
public class PropiedadService implements IPropiedadService {

	@Autowired
	private IPropiedadDAO entityDAO;

	@Transactional(readOnly = false)
	public void addPropiedad(Propiedad entity) {
		getEntityDAO().addPropiedad(entity);
	}

	@Transactional(readOnly = false)
	public void deletePropiedad(Propiedad entity) {
		getEntityDAO().deletePropiedad(entity);
	}

	@Transactional(readOnly = false)
	public void updatePropiedad(Propiedad entity) {
		getEntityDAO().updatePropiedad(entity);
	}

	public Propiedad getPropiedadById(int id) {
		return getEntityDAO().getPropiedadById(id);
	}

	public List<Propiedad> getPropiedades(String propertyType) {	
		return getEntityDAO().getPropiedades(propertyType);
	}
	
	public List<TipoPropiedad> getListaTipoPropiedad(int idEmpresa){
		return getEntityDAO().getListaTipoPropiedad(idEmpresa);
	}
	
	public void addTipoPropiedad(TipoPropiedad entity) {
		getEntityDAO().addTipoPropiedad(entity);
	}

	public void updateTipoPropiedad(TipoPropiedad entity) {
		getEntityDAO().updateTipoPropiedad(entity);
	}

	public void deleteTipoPropiedad(TipoPropiedad entity) {
		getEntityDAO().deleteTipoPropiedad(entity);
	}
	
	public TipoPropiedad getTipoPropiedad(int id) {
		return getEntityDAO().getTipoPropiedad(id);
	}

	public IPropiedadDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEntityDAO(IPropiedadDAO entityDAO) {
		this.entityDAO = entityDAO;
	}

}
