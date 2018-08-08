package co.com.avaluo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.avaluo.common.EnumSessionAttributes;
import co.com.avaluo.common.ListasGenericas;
import co.com.avaluo.common.Util;
import co.com.avaluo.model.entity.Reporte;
import co.com.avaluo.model.entity.Usuario;
import co.com.avaluo.service.IReporteService;

@ManagedBean(name = "reporteBB")
@ViewScoped
public class ReporteBB extends SpringBeanAutowiringSupport implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private IReporteService reporteService;
	
	private Reporte reporte = new Reporte();
	private Reporte selectedReporte;
	private List<Reporte> entityList;
	private Usuario usuario;
	private Util util;
	private List<SelectItem> propiedad;
	
	public ReporteBB() {
		util = Util.getInstance();
		usuario = (Usuario) util.getSessionAttribute(EnumSessionAttributes.USUARIO);
		propiedad = ListasGenericas.getInstance().getListaTipoPropiedad();
		cargarListaReportes();
	}
	
	@PostConstruct
	public void validarSession() {
		util.validarSession();
	}
	
	private void cargarListaReportes() {
		entityList = reporteService.getReportes(usuario.getEmpresa().getId());
		if(entityList == null) {
			//reporteService.cargarReporteInicial(usuario.getEmpresa().getId());
			//cargarListaReportes();
		}
	}
	
	public String contenidoKey(String codigoContenido) {
		return codigoContenido;
	}
	
	private void cambiarEstado(Reporte reporte) {
		try {
			System.out.println(reporte);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Reporte getReporte() {
		return reporte;
	}

	public void setReporte(Reporte reporte) {
		this.reporte = reporte;
	}

	public void reset() {
		this.reporte = new Reporte();
	}

	public List<Reporte> getEntityList() {
		return entityList;
	}

	public IReporteService getReporteService() {
		return reporteService;
	}

	public void setReporteService(IReporteService reporteService) {
		this.reporteService = reporteService;
	}

	public Reporte getSelectedReporte() {
		if(selectedReporte == null)
			selectedReporte = new  Reporte();
		return selectedReporte;
	}

	public void setSelectedReporte(Reporte selectedReporte) {
		this.selectedReporte = selectedReporte;
	}

	public void setEntityList(List<Reporte> entityList) {
		this.entityList = entityList;
	}

	public List<SelectItem> getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(List<SelectItem> propiedad) {
		this.propiedad = propiedad;
	}
 }