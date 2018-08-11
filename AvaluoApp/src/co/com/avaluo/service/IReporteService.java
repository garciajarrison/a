package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.Empresa;
import co.com.avaluo.model.entity.Reporte;


public interface IReporteService {
	
	void addReporte(Reporte entity);
	
	void updateReporte(Reporte entity);

	void deleteReporte(Reporte entity);
	
	Reporte getReporteById(int id);
	
	List<Reporte> getReportes(String codigo, Empresa empresa);

	List<Reporte> datosReporteCotizacion(String codigo, Empresa empresa);
}
