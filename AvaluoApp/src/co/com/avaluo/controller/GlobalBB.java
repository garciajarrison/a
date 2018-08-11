package co.com.avaluo.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import co.com.avaluo.common.EnumLenguajes;
import co.com.avaluo.common.EnumSessionAttributes;
import co.com.avaluo.common.Util;
import co.com.avaluo.model.entity.Licencia;
import co.com.avaluo.model.entity.Usuario;

@ManagedBean(name = "globalBB")
@SessionScoped
public class GlobalBB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Licencia licencia;
	private Locale locale;
	private Util util;
	
	// TODO poner todos en false
	//Permisos
	private boolean administrador = true;
	private boolean camel = true;
	private boolean ant = true;
	private boolean shark = true;
	private boolean eagle = true;
	
	public GlobalBB() {
		util = Util.getInstance();
		usuario = (Usuario) Util.getInstance().getSessionAttribute(EnumSessionAttributes.USUARIO);
		if(usuario != null) {
			licencia = (Licencia) Util.getInstance().getSessionAttribute(EnumSessionAttributes.LICENCIA);
			EnumLenguajes lenguaje = (EnumLenguajes)Util.getInstance().getSessionAttribute(EnumSessionAttributes.LENGUAJE);
			if(lenguaje == null && usuario != null) {
				util.cambiarIdioma(usuario.getLenguaje());
				lenguaje = (EnumLenguajes)Util.getInstance().getSessionAttribute(EnumSessionAttributes.LENGUAJE);
			}
	    	locale = lenguaje.getLocale();
	        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	        cargarPermisos();
		}
	}
	
	@PostConstruct
	public void validarSession() {
		util.validarSession();
	}
	
	public void cargarPermisos() {
		
		if(util.getMessage("permiso.avalsoft").equals(licencia.getNombre().trim()))
			administrador = true;
		if(util.getMessage("permiso.camel").equals(licencia.getNombre().trim()))
			camel = true;
		if(util.getMessage("permiso.ant").equals(licencia.getNombre().trim()))
			ant = true;
		if(util.getMessage("permiso.shark").equals(licencia.getNombre().trim()))
			shark = true;	
		if(util.getMessage("permiso.eagle").equals(licencia.getNombre().trim()))
			eagle = true;
	}
	
	/**
	 * Método que se encarga del manejo de cierre de sesion del servicio
	 * @return Texto con la navegacion
	 * @throws IOException 
	 */
	public void cerrarSesion() throws IOException {
		util.cerrarSesion();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Locale getLocale() {
	    return locale;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	public boolean isCamel() {
		return camel;
	}

	public void setCamel(boolean camel) {
		this.camel = camel;
	}

	public boolean isAnt() {
		return ant;
	}

	public void setAnt(boolean ant) {
		this.ant = ant;
	}

	public boolean isShark() {
		return shark;
	}

	public void setShark(boolean shark) {
		this.shark = shark;
	}

	public boolean isEagle() {
		return eagle;
	}

	public void setEagle(boolean eagle) {
		this.eagle = eagle;
	}

 }