package co.com.avaluo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;

import co.com.avaluo.common.EnumSessionAttributes;
import co.com.avaluo.common.ListasGenericas;
import co.com.avaluo.common.Util;
import co.com.avaluo.model.entity.Estrato;
import co.com.avaluo.model.entity.Usuario;
import co.com.avaluo.service.IEstratoService;

@Named("estratoBB")
@Scope("session")
public class EstratoBB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private IEstratoService estratoService;
	
	private Estrato estrato = new Estrato();
	private Estrato selectedEstrato= new Estrato();
	private List<Estrato> entityList;
	private Usuario usuario;
	private Util util;
	
	private void cargarListaEstratos() {
		util = Util.getInstance();
		usuario = (Usuario) util.getSessionAttribute(EnumSessionAttributes.USUARIO);
		entityList = getEstratoService().getEntitys(usuario.getEmpresa().getId());
		if(entityList == null)
			entityList = new ArrayList<>();
		
	}
	
	public void addEntity() {
		try {
			boolean guardar = true;
			//Validamos que no exista un estrato con esa configuracion
			for(Estrato estr : entityList) {
				if(estr.getNombre().equals(estrato.getNombre())) {
					guardar = false;
					util.mostrarError("Ya existe una configuracion para este estrato.");
				}
			}
			
			if(guardar) {
				estrato.setEmpresa(usuario.getEmpresa());
				getEstratoService().addEntity(estrato);
				this.cargarListaEstratos();
				util.mostrarMensaje("Registro agregado con éxito.");  
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarError("Error registrando."); 
		} 	
		
	}

	public void updateEntity() {
		try {
			getEstratoService().updateEntity(selectedEstrato);
			util.mostrarMensaje("Registro actualizado.");  
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarError("Error registrando."); 
		} 	
		
	}
	
	public void deleteEntity() {
		try {
			getEstratoService().deleteEntity(estrato);
			util.mostrarMensaje("Registro Eliminado.");  
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarError("Error eliminando.");
		} 	
		
	}

	public Estrato getEstrato() {
		return estrato;
	}

	public void setEstrato(Estrato estrato) {
		this.estrato = estrato;
	}

	public void reset() {
		this.estrato = new Estrato();
	}

	public List<Estrato> getEntityList() {
		if(entityList == null)
			cargarListaEstratos();
		return entityList;
	}

	public IEstratoService getEstratoService() {
		return estratoService;
	}

	public void setEstratoService(IEstratoService estratoService) {
		this.estratoService = estratoService;
	}

	public Estrato getSelectedEstrato() {
		return selectedEstrato;
	}

	public void setSelectedEstrato(Estrato selectedEstrato) {
		this.selectedEstrato = selectedEstrato;
	}

	public void setEntityList(List<Estrato> entityList) {
		this.entityList = entityList;
	}

	public void onRowSelect(SelectEvent event) {
		
        /*FacesMessage msg = new FacesMessage("Car Selected", ((Car) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);*/
    }
 
    public void onRowUnselect(UnselectEvent event) {
        /*FacesMessage msg = new FacesMessage("Car Unselected", ((Car) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);*/
    }	
    
    public List<SelectItem> getListaEstratos(){
    	return ListasGenericas.getInstance().getListaEstratos();
    }
 }