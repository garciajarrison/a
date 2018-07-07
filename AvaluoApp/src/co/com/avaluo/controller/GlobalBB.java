package co.com.avaluo.controller;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import co.com.avaluo.common.EnumLenguajes;
import co.com.avaluo.common.EnumSessionAttributes;
import co.com.avaluo.common.Util;
import co.com.avaluo.model.entity.Usuario;

@ManagedBean(name = "globalBB")
@SessionScoped
public class GlobalBB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Locale locale;
	
	public GlobalBB() {
		usuario = (Usuario) Util.getInstance().getSessionAttribute(EnumSessionAttributes.USUARIO);
		EnumLenguajes lenguaje = (EnumLenguajes)Util.getInstance().getSessionAttribute(EnumSessionAttributes.LENGUAJE);
    	locale = lenguaje.getLocale();
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
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