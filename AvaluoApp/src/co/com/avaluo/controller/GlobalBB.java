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
import co.com.avaluo.model.entity.Licencia;
import co.com.avaluo.model.entity.Usuario;

@ManagedBean(name = "globalBB")
@SessionScoped
public class GlobalBB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Licencia licencia;
	private Locale locale;
	private Util util = Util.getInstance();
	
	//Permisos
	private boolean configuracion = false;
	private boolean cotizacion = false;
	
	public GlobalBB() {
		usuario = (Usuario) Util.getInstance().getSessionAttribute(EnumSessionAttributes.USUARIO);
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
	
	public void cargarPermisos() {
		configuracion = true;
		cotizacion = true;
	/*	if(cargarPermisos();,licencia.getNombre()){
			
		}*/
	}
	
	/**
	 * M�todo que se encarga del manejo de cierre de sesion del servicio
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

	public boolean isConfiguracion() {
		return configuracion;
	}

	public void setConfiguracion(boolean configuracion) {
		this.configuracion = configuracion;
	}

	public boolean isCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(boolean cotizacion) {
		this.cotizacion = cotizacion;
	}

 }