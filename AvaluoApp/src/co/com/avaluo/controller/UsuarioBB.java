package co.com.avaluo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.avaluo.common.Util;
import co.com.avaluo.model.entity.Usuario;
import co.com.avaluo.service.IUsuarioService;

@ManagedBean(name = "usuarioBB")
@ViewScoped
public class UsuarioBB extends SpringBeanAutowiringSupport implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Autowired
	private IUsuarioService usuarioService;
	private List<Usuario> listaUsuarios;
	private Usuario usr = new Usuario();
	private Usuario selectedUsuario;
	private Util util;
	
	public UsuarioBB() {
		util = Util.getInstance();
		cargarListaUsuarios();
	}
	
	@PostConstruct
	public void validarSession() {
		util.validarSession();
	}
	
	private void cargarListaUsuarios() {
		listaUsuarios = getUsuarioService().getUsuarios();
		if(listaUsuarios == null)
			listaUsuarios = new ArrayList<>();
	}
	
	public void addEntity() {
		try {
			boolean guardar = true;
			//Validamos que no exista un estrato con esa configuracion
			for(Usuario estr : listaUsuarios) {
				usr.setNombre(usr.getNombre().trim());
				if(estr.getNombre().equals(usr.getNombre())) {
					guardar = false;
					util.mostrarErrorKey("usuario.ya.existe");
				}
			}
			
			if(guardar) {
				getUsuarioService().addUsuario(usr);
				this.cargarListaUsuarios();
				util.mostrarMensajeKey("exito.guardar"); 
				usr = new Usuario();
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
			getUsuarioService().updateUsuario(selectedUsuario);
			util.mostrarMensajeKey("exito.actualizar");  
			cargarListaUsuarios();
			util.actualizarPF("formulario");
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarErrorKey("error.actualizar"); 
		} 	
	}
	
	public void deleteEntity() {
		try {
			getUsuarioService().deleteUsuario(selectedUsuario);
			util.mostrarMensajeKey("exito.eliminar");
			cargarListaUsuarios();
			util.actualizarPF("formulario");
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarErrorKey("error.eliminando");
		} 	
		
	}

	public IUsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(IUsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public Usuario getUsr() {
		return usr;
	}

	public void setUsr(Usuario usr) {
		this.usr = usr;
	}

	public Usuario getSelectedUsuario() {
		return selectedUsuario;
	}

	public void setSelectedUsuario(Usuario selectedUsuario) {
		this.selectedUsuario = selectedUsuario;
	}


 }