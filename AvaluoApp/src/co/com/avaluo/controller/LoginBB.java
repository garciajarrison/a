package co.com.avaluo.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import co.com.avaluo.common.EnumSessionAttributes;
import co.com.avaluo.common.Util;
import co.com.avaluo.model.entity.Usuario;
import co.com.avaluo.service.IEmpresaService;
import co.com.avaluo.service.IUsuarioService;

@Named("loginBB")
@Scope("session")
public class LoginBB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private IUsuarioService usuarioService;
	@Inject
	private IEmpresaService empresaService;
	
	private Usuario usuario = new Usuario();

	public void login() {
		try {
			usuario = this.getUsuarioService().login(usuario);
			if(usuario != null) {
				Util.getInstance().setSessionAttribute(EnumSessionAttributes.USUARIO, usuario);
				Util.getInstance().setSessionAttribute(EnumSessionAttributes.LENGUAJE, usuario.getLenguaje());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido!", usuario.getNombre()));  
				Util.getInstance().redirect("home.xhtml");
			} else{
				usuario = new Usuario();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos de ingreso incorrectos", ""));  
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "D'oh!", "Message: ")); 
		} 	
	}

	public IUsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(IUsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	public IEmpresaService getEmpresaService() {
		return empresaService;
	}

	public void setEmpresaService(IEmpresaService empresaService) {
		this.empresaService = empresaService;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
 }