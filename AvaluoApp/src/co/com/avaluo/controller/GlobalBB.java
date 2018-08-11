package co.com.avaluo.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.avaluo.common.EnumLenguajes;
import co.com.avaluo.common.EnumSessionAttributes;
import co.com.avaluo.common.ListasGenericas;
import co.com.avaluo.common.Util;
import co.com.avaluo.model.entity.Licencia;
import co.com.avaluo.model.entity.Usuario;
import co.com.avaluo.service.IUsuarioService;

@ManagedBean(name = "globalBB")
@SessionScoped
public class GlobalBB extends SpringBeanAutowiringSupport implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private IUsuarioService usuarioService;
	private Usuario usuario;
	private Licencia licencia;
	private Locale locale;
	private Util util;
	private UploadedFile foto;
	private String urlFotoPerfil;
	private ListasGenericas listasGenericas;
	
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
			listasGenericas = ListasGenericas.getInstance();
			EnumLenguajes lenguaje = (EnumLenguajes)Util.getInstance().getSessionAttribute(EnumSessionAttributes.LENGUAJE);
			if(lenguaje == null && usuario != null) {
				util.cambiarIdioma(usuario.getLenguaje());
				lenguaje = (EnumLenguajes)Util.getInstance().getSessionAttribute(EnumSessionAttributes.LENGUAJE);
			}
	    	locale = lenguaje.getLocale();
	        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	        cargarPermisos();
	        cargarFotoPerfil();
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
	
	public void actualizarIdioma(){
		
		try {
			usuarioService.updateUsuario(usuario);
			util.mostrarMensajeKey("exito.idioma.actualizar");
			util.cambiarIdioma(usuario.getLenguaje());
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public void upload(FileUploadEvent event) {
		foto = event.getFile();
        if(foto != null) {
        	urlFotoPerfil = util.crearFoto(usuario.getIdentificacion() + usuario.getId(), foto.getContents());
            util.mostrarMensajeKey("exito.foto.actualizar");
        }
    }
	
	private void cargarFotoPerfil() {
		
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		StringBuilder url = new StringBuilder(servletContext.getRealPath("/").replace("/", util.SEPARADOR_CARPETA))
				.append(util.SEPARADOR_CARPETA)
				.append(util.URL_FOTO_PERFIL)
				.append(usuario.getIdentificacion()).append(usuario.getId()).append(".jpg");
		
		try {
			if(util.existeArchivo(url.toString())) {
				url = new StringBuilder(servletContext.getContextPath().replace("/", util.SEPARADOR_CARPETA))
						.append(util.SEPARADOR_CARPETA)
						.append(util.URL_FOTO_PERFIL)
						.append(usuario.getIdentificacion()).append(usuario.getId()).append(".jpg");
				urlFotoPerfil = url.toString();
			}else {
				url = new StringBuilder(servletContext.getContextPath().replace("/", util.SEPARADOR_CARPETA)) 
						.append(util.SEPARADOR_CARPETA)
						.append("resources").append(util.SEPARADOR_CARPETA)
						.append("images").append(util.SEPARADOR_CARPETA)
						.append("user.png");
				urlFotoPerfil = url.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public UploadedFile getFoto() {
		return foto;
	}

	public void setFoto(UploadedFile foto) {
		this.foto = foto;
	}

	public String getUrlFotoPerfil() {
		return urlFotoPerfil;
	}

	public void setUrlFotoPerfil(String urlFotoPerfil) {
		this.urlFotoPerfil = urlFotoPerfil;
	}

	public IUsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(IUsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public ListasGenericas getListasGenericas() {
		return listasGenericas;
	}

	public void setListasGenericas(ListasGenericas listasGenericas) {
		this.listasGenericas = listasGenericas;
	}

 }