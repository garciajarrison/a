package co.com.avaluo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.avaluo.common.EnumSessionAttributes;
import co.com.avaluo.common.ListasGenericas;
import co.com.avaluo.common.Util;
import co.com.avaluo.model.entity.Estrato;
import co.com.avaluo.model.entity.Usuario;
import co.com.avaluo.service.IEstratoService;

@ManagedBean(name = "estratoBB")
@ViewScoped
public class EstratoBB extends SpringBeanAutowiringSupport implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private IEstratoService estratoService;
	
	private Estrato estrato = new Estrato();
	private Estrato selectedEstrato;
	private List<Estrato> entityList;
	private Usuario usuario;
	private Util util;
	private List<SelectItem> propiedad;
	
	public EstratoBB() {
		util = Util.getInstance();
		usuario = (Usuario) util.getSessionAttribute(EnumSessionAttributes.USUARIO);
		propiedad = ListasGenericas.getInstance().getListaTipoPropiedad();
		cargarListaEstratos();
	}
	
	private void cargarListaEstratos() {
		entityList = estratoService.getEntitys(usuario.getEmpresa().getId());
		if(entityList == null)
			entityList = new ArrayList<>();
	}
	
	public void addEntity() {
		try {
			boolean guardar = true;
			//Validamos que no exista un estrato con esa configuracion
			for(Estrato estr : entityList) {
				estrato.setNombre(estrato.getNombre().trim());
				if(estr.getNombre().equals(estrato.getNombre())) {
					guardar = false;
					util.mostrarErrorKey("estrato.ya.existe");
				}
			}
			
			if(guardar) {
				estrato.setEmpresa(usuario.getEmpresa());
				getEstratoService().addEntity(estrato);
				this.cargarListaEstratos();
				util.mostrarMensajeKey("exito.guardar"); 
				cargarListaEstratos();
				estrato = new Estrato();
				util.actualizarPF("formulario");
			}else {
				util.actualizarPF("growl");
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarErrorKey("error.guardar"); 
		} 	
		
	}

	public void updateEntity() {
		try {
			getEstratoService().updateEntity(selectedEstrato);
			util.mostrarMensajeKey("exito.actualizar");  
			cargarListaEstratos();
			util.actualizarPF("formulario");
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarErrorKey("error.actualizar"); 
		} 	
	}
	
	public void deleteEntity() {
		try {
			getEstratoService().deleteEntity(selectedEstrato);
			util.mostrarMensajeKey("exito.eliminar");
			cargarListaEstratos();
			util.actualizarPF("formulario");
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarErrorKey("error.eliminando");
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

	public List<SelectItem> getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(List<SelectItem> propiedad) {
		this.propiedad = propiedad;
	}
 }