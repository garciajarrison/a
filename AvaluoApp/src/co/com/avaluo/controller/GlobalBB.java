package co.com.avaluo.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import co.com.avaluo.common.EnumLenguajes;
import co.com.avaluo.common.EnumSessionAttributes;
import co.com.avaluo.common.Util;
import co.com.avaluo.controller.reporte.RCotizacion;
import co.com.avaluo.model.entity.Cotizacion;
import co.com.avaluo.model.entity.Usuario;

@ManagedBean(name = "globalBB")
@SessionScoped
public class GlobalBB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Locale locale;
	private Util util = Util.getInstance();
	
	public GlobalBB() {
		usuario = (Usuario) Util.getInstance().getSessionAttribute(EnumSessionAttributes.USUARIO);
		EnumLenguajes lenguaje = (EnumLenguajes)Util.getInstance().getSessionAttribute(EnumSessionAttributes.LENGUAJE);
		if(lenguaje == null && usuario != null) {
			util.cambiarIdioma(usuario.getLenguaje());
			lenguaje = (EnumLenguajes)Util.getInstance().getSessionAttribute(EnumSessionAttributes.LENGUAJE);
		}
    	locale = lenguaje.getLocale();
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}
	
	/**
	 * Método que se encarga del manejo de cierre de sesion del servicio
	 * @return Texto con la navegacion
	 * @throws IOException 
	 */
	public void cerrarSesion() throws IOException {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext extContext = fc.getExternalContext();
		extContext.redirect(util.getContextPath() + "/login.xhtml");
		util.cerrarSesionHttp();
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

 }