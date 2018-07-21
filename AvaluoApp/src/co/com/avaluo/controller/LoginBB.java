package co.com.avaluo.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.avaluo.common.EnumSessionAttributes;
import co.com.avaluo.common.Util;
import co.com.avaluo.model.entity.Licencia;
import co.com.avaluo.model.entity.Usuario;
import co.com.avaluo.service.IEmpresaService;
import co.com.avaluo.service.IUsuarioService;

@ManagedBean(name = "loginBB")
@ViewScoped
public class LoginBB extends SpringBeanAutowiringSupport implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private IUsuarioService usuarioService;
	private IEmpresaService empresaService;
	
	private Usuario usuario = new Usuario();
	
	public LoginBB() {
		cerrarSession();
	}

	public void login() {
		try {
			usuario = this.getUsuarioService().login(usuario);
			if(usuario != null) {
				
				//Validamos la licencia
				Licencia licenciaActual = this.getUsuarioService().cargarLicenciaActual(usuario); 
				if(licenciaActual != null) {
					Util.getInstance().setSessionAttribute(EnumSessionAttributes.LICENCIA, licenciaActual);
					Util.getInstance().setSessionAttribute(EnumSessionAttributes.USUARIO, usuario);
					Util.getInstance().cambiarIdioma(usuario.getLenguaje());
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido!", usuario.getNombre()));  
					Util.getInstance().redirect("home.xhtml");
				}else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La licencia expiro, por favor comun�quese con la entidad.", "")); 
				}
			} else{
				usuario = new Usuario();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos de ingreso incorrectos", ""));  
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos de ingreso incorrectos", "")); 
		} 	
	}
	
	public void cerrarSession() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		}catch(Exception e) {
		}
	}
	
	public IUsuarioService getUsuarioService() {
		return usuarioService;
	}

	@Autowired
	public void setUsuarioService(IUsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	public IEmpresaService getEmpresaService() {
		return empresaService;
	}

	@Autowired
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