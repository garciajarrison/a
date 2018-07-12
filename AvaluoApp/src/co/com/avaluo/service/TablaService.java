package co.com.avaluo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.ITablasDAO;
import co.com.avaluo.model.entity.DetalleTabla;
import co.com.avaluo.model.entity.Tablas;

@Service
@Transactional(readOnly = true)
public class TablaService implements ITablasService {

	@Autowired
	private ITablasDAO entityDAO;

	@Transactional(readOnly = false)
	public void addTabla(Tablas entity) {
		getEntityDAO().addTabla(entity);
	}

	@Transactional(readOnly = false)
	public void deleteTabla(Tablas entity) {
		getEntityDAO().deleteTabla(entity);
	}

	@Transactional(readOnly = false)
	public void updateTabla(Tablas entity) {
		getEntityDAO().updateTabla(entity);
	}

	public Tablas getTablaById(int id) {
		return getEntityDAO().getTablaById(id);
	}

	public List<Tablas> getTablas(int idEmpresa) {	
		return getEntityDAO().getTablas(idEmpresa);
	}

	@Transactional(readOnly = false)
	public void updateTablaDetalle(DetalleTabla detalle) {
		getEntityDAO().updateTablaDetalle(detalle);
	}

	@Transactional(readOnly = false)
	public void addTablaDetalle(DetalleTabla detalle) {
		getEntityDAO().addTablaDetalle(detalle);
	}
	
	@Transactional(readOnly = false)
	public void deleteTablaDetalle(DetalleTabla detalle) {
		getEntityDAO().deleteTablaDetalle(detalle);
	}
	
	public ITablasDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEntityDAO(ITablasDAO entityDAO) {
		this.entityDAO = entityDAO;
	}
}
