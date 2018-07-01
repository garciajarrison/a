package co.com.avaluo.controller;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import co.com.avaluo.common.EnumSessionAttributes;
import co.com.avaluo.common.Util;
import co.com.avaluo.model.entity.Usuario;

@Named("globalBB")
@Scope("session")
public class GlobalBB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	
	public GlobalBB() {
		usuario = (Usuario) Util.getInstance().getSessionAttribute(EnumSessionAttributes.USUARIO);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getMessageResources() {
		return Util.getInstance().getSessionAttribute(EnumSessionAttributes.LENGUAJE).toString();
	}



 }