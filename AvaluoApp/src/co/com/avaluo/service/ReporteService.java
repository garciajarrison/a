package co.com.avaluo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.IReporteDAO;
import co.com.avaluo.model.entity.Empresa;
import co.com.avaluo.model.entity.Reporte;

@Service
@Transactional(readOnly = true)
public class ReporteService implements IReporteService {

	@Autowired
	private IReporteDAO entityDAO;
	
	public IReporteDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEntityDAO(IReporteDAO entityDAO) {
		this.entityDAO = entityDAO;
	}

	@Transactional(readOnly = false)
	public void addReporte(Reporte entity) {
		getEntityDAO().addReporte(entity);
	}

	@Transactional(readOnly = false)
	public void deleteReporte(Reporte entity) {
		getEntityDAO().deleteReporte(entity);
	}

	@Transactional(readOnly = false)
	public void updateReporte(Reporte entity) {
		getEntityDAO().updateReporte(entity);
	}

	public Reporte getReporteById(int id) {
		return getEntityDAO().getReporteById(id);
	}

	public List<Reporte> getReportes(String codigo, Empresa empresa) {	
		return getEntityDAO().getReportes(codigo, empresa);
	}

	@Transactional(readOnly = false)
	public List<Reporte> datosReporteCotizacion(String codigo, Empresa empresa) {
		return getEntityDAO().datosReporteCotizacion(codigo, empresa);
	}
}
