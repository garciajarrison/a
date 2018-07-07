package co.com.avaluo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.avaluo.model.entity.Cotizacion;
import co.com.avaluo.model.entity.Tablas;
import co.com.avaluo.model.entity.Usuario;
import co.com.avaluo.service.ICotizacionService;


@ManagedBean(name = "cotizacionAdmBB")
@ViewScoped
public class CotizacionAdmBB extends SpringBeanAutowiringSupport implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICotizacionService cotizacionService;
	
	private Cotizacion cotizacion = new Cotizacion();
	private Cotizacion selectedCotizacion = new Cotizacion();
	private List<Cotizacion> entityList;
	private Usuario usuario;
	private List<SelectItem> listaTipoDocumentos;
	private String tabla;
	private List<Tablas> tablas;
	private Map<String,String> listaTablas = new HashMap<String, String>();
	
	
	public void addEntity() {
		try {
			getCotizacionService().addEntity(cotizacion);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Added!", "Message: "));  
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "D'oh!", "Message: ")); 
		} 	
		
	}

	public void updateEntity() {
		try {
			getCotizacionService().updateEntity(selectedCotizacion);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Update!", "Message: "));  
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "D'oh!", "Message: ")); 
		} 	
		
	}
	
	public void deleteEntity() {
		try {
			getCotizacionService().deleteEntity(selectedCotizacion);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete!", "Message: "));  
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "D'oh!", "Message: ")); 
		} 	
		
	}

	public List<Cotizacion> getEntityList() {
		entityList = getCotizacionService().getEntitys();
		return entityList;
	}
	
	public ICotizacionService getCotizacionService() {
		return cotizacionService;
	}

	public void setCotizacionService(ICotizacionService cotizacionService) {
		this.cotizacionService = cotizacionService;
	}

	public Cotizacion getSelectedCotizacion() {
		return selectedCotizacion;
	}

	public void setSelectedCotizacion(Cotizacion selectedCotizacion) {
		this.selectedCotizacion = selectedCotizacion;
	}

	public void setEntityList(List<Cotizacion> entityList) {
		this.entityList = entityList;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<SelectItem> getListaTipoDocumentos() {
		return listaTipoDocumentos;
	}

	public void setListaTipoDocumentos(List<SelectItem> listaTipoDocumentos) {
		this.listaTipoDocumentos = listaTipoDocumentos;
	}

	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public Map<String, String> getListaTablas() {
		tablas = new ArrayList<Tablas>();
		//tablas.addAll(getTablesService().getEntitys());
		for (Tablas tabla : tablas) {
			listaTablas.put(tabla.getNombre(),tabla.getTipo());
		}
		return listaTablas;
	}

	public void setListaTablas(Map<String, String> listaTablas) {
		this.listaTablas = listaTablas;
	}

	public void onRowSelect(SelectEvent event) {
    }
 
    public void onRowUnselect(UnselectEvent event) {
    }	
 }