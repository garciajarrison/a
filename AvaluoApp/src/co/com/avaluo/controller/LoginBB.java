package co.com.avaluo.controller;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.avaluo.common.EnumSessionAttributes;
import co.com.avaluo.common.EnviarCorreoUtil;
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
	private boolean mostrarCodigoSeguridad;
	private boolean mostrarCambiarClave;
	private String clave;
	private String confirClave;
	private Integer codigoPrivado;
	private Integer codigo;
	private int intentos;
	private Util util;
	
	private Usuario usuario = new Usuario();
	
	public LoginBB() {
		resetCampos();
		cerrarSession();
		util = Util.getInstance();
	}
	
	public void resetCampos() {
		intentos = 0;
		mostrarCodigoSeguridad = false;
		mostrarCambiarClave = false;
		usuario.setContrasena("");
	}
	
	private boolean validar(boolean correo, boolean clave) {
		boolean retorno = true;
		
		if(correo) {
			if(util.validaNuloVacio(usuario.getCorreo())) {
				retorno = false;
				util.mostrarErrorKey("javax.faces.component.UIInput.REQUIRED", util.getMessage("login.correo"));
			}
		}
		
		if(clave) {
			if(util.validaNuloVacio(usuario.getContrasena())) {
				retorno = false;
				util.mostrarErrorKey("javax.faces.component.UIInput.REQUIRED", util.getMessage("login.contrasena"));
			}
		}
		
		return retorno;
	}

	public void login() {
		
		Util util = Util.getInstance();
		
		try {
			if(validar(true, true)) {
				usuario = this.getUsuarioService().login(usuario);

				if(usuario != null) {
					
					//Validamos la licencia
					Licencia licenciaActual = null; 
					for(Licencia licencia : usuario.getLicencias()) {
						if(licencia.getFechaExpiracion().compareTo(new Date()) >= 0) {
							licenciaActual = licencia;
						}
					}
					
					if(licenciaActual != null) {
						this.getUsuarioService().actualizarUltimaConn(licenciaActual);
						util.setSessionAttribute(EnumSessionAttributes.LICENCIA, licenciaActual);
						util.setSessionAttribute(EnumSessionAttributes.USUARIO, usuario);
						util.cambiarIdioma(usuario.getLenguaje());
						util.mostrarMensajeKeyRedirect("login.bienvenido",true , usuario.getNombre());
						util.redirect("home.xhtml");
					}else {
						util.mostrarErrorKey("login.licencia.expiro");
					}
				} else{
					usuario = new Usuario();
					util.mostrarErrorKey("login.datos.incorrectos");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			util.mostrarErrorKey("login.datos.incorrectos");
		} 	
	}
	
	public void enviarCodigo() {
		
		EnviarCorreoUtil envCorreo = new EnviarCorreoUtil();
		Util util = Util.getInstance();
		
		try {
			if(validar(true, false)){
				//envia el codigo solo si existe un usuario con el correo diligenciado
				Usuario tmp = this.getUsuarioService().consultarUsuarioPorCorreo(usuario.getCorreo());
				if(tmp != null) {
				
					SecureRandom sr = null;
					try {
					    sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
					    sr.nextBytes(new byte[1]);
					    byte[] b = new byte[20];
					    sr.setSeed(b);
					    // Alternativamente se puede usar un long para resembrar
					    sr.setSeed(System.currentTimeMillis());
					    codigoPrivado = sr.nextInt(1000000);
					   
					    // para garantizar el caracter aleatorio generemos una nueva semilla
					} catch (NoSuchAlgorithmException e) {
					    e.printStackTrace();
					} catch (NoSuchProviderException e) {
					    e.printStackTrace();
					} 
					
					/* TODO descomentar y provar en el servidor
					 envCorreo.generateAndSendEmail(usuario.getCorreo(), null, 
								util.getMessage("avalsoft.correo.recuperar.clave.asunto"), 
								util.getMessage("avalsoft.correo.recuperar.clave.contenido", codigoPrivado));*/
					
					util.mostrarMensajeKey("login.exito.eviando.codigo");
					this.mostrarCodigoSeguridad = true;
				}else {
					util.mostrarErrorKey("login.usuario.no.existe");
					this.resetCampos();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			util.mostrarErrorKey("login.error.eviando.codigo");
		}
	}
	
	public void verificarCodigo() {
		try {
			if(codigo.equals(codigoPrivado)) {
				mostrarCambiarClave = true;
			}else {
				intentos++;
				validarBloqueo();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void validarBloqueo() {
		if(intentos >= 3) {
			this.getUsuarioService().bloquearCuenta(usuario.getCorreo());
			Util.getInstance().mostrarErrorKey("login.bloqueo.cuenta");
			resetCampos();
		}else {
			Util.getInstance().mostrarErrorKey("login.codigo.no.coincide");
		}
	}
	
	public void cambiarClave() {
		try {
			if(clave.equals(confirClave)) {
				String claveEncript = Util.getInstance().encriptarClave(clave);
				this.getUsuarioService().cambiarClave(usuario.getCorreo(), claveEncript);
				Util.getInstance().mostrarMensajeKey("login.cambio.clave.exito");
				resetCampos();
			}else {
				Util.getInstance().mostrarErrorKey("login.cambio.clave.error");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cerrarSession() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		}catch(Exception e) {}
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

	public boolean isMostrarCambiarClave() {
		return mostrarCambiarClave;
	}

	public void setMostrarCambiarClave(boolean mostrarCambiarClave) {
		this.mostrarCambiarClave = mostrarCambiarClave;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getConfirClave() {
		return confirClave;
	}

	public void setConfirClave(String confirClave) {
		this.confirClave = confirClave;
	}

	public boolean isMostrarCodigoSeguridad() {
		return mostrarCodigoSeguridad;
	}

	public void setMostrarCodigoSeguridad(boolean mostrarCodigoSeguridad) {
		this.mostrarCodigoSeguridad = mostrarCodigoSeguridad;
	}
	
 }