package co.com.avaluo.common;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.primefaces.PrimeFaces;
import org.primefaces.util.ComponentUtils;

public class Util {
	
	private static Util instance;
	public static final String SRC_PATH_DOWNLOAD = System.getProperty("file.separator") + "download" + System.getProperty("file.separator");
    public static final String SEPARADOR_CARPETA = System.getProperty("file.separator");
    public static final String SEPARADOR_LINEA = System.getProperty("line.separator");
    public static final String URL_FOTO_PERFIL = System.getProperty("file.separator") + "download" + System.getProperty("file.separator") 
    			+ "images" + System.getProperty("file.separator");
	
	private Util(){}

	public static Util getInstance() {
		if(instance == null)
			instance = new Util();
		return instance;
	}
	
	public boolean validaNuloVacio(String valor) {
		if(valor == null || "".equals(valor)) {
			return true;
		}
		return false;
	}
	
	public void validarSession() {
		if(this.getSessionAttribute(EnumSessionAttributes.USUARIO) == null) {
			cerrarSesion();
		}
	}
	
	public void cerrarSesion() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext extContext = fc.getExternalContext();
		try {
			extContext.redirect(this.getContextPath() + "/login.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.cerrarSesionHttp();
	}
	
	public void setSessionAttribute(EnumSessionAttributes property, Object value){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		session.setAttribute(property.toString(), value);
	}
	
	public Object getSessionAttribute(EnumSessionAttributes property) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		return session.getAttribute(property.toString());
	}
	
	public void redirect(String page) {
		ExternalContext ec = FacesContext.getCurrentInstance()
		        .getExternalContext();
		try {
		    ec.redirect(ec.getRequestContextPath() 
		            + "/pages/" + page);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	/**
	 * Ejejcuta codigo javascript en las paginas
	 * @param codigoJS codigo javascript a ejecutar
	 * @return retorna si la peticion era ajax la ejecucion exitosa
	 */
	public boolean ejecutarPF(String codigoJS){
		PrimeFaces pf = PrimeFaces.current();
		if (pf.isAjaxRequest()) {
			pf.executeScript(codigoJS);
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Ejejcuta actualiza componentes en la vista
	 * @param componente componente a actualizar de la vista
	 * @return retorna si la peticion era ajax la actualizacion exitosa
	 */
	public boolean actualizarPF(String componente){
		PrimeFaces pf = PrimeFaces.current();
		if (pf.isAjaxRequest()) {
			pf.ajax().update(componente);
			return true;
		}else{
			return false;
		}
	}

	/**
	 * obtiene el identificador de la sesi�n
	 * @return devuelve el Id de la sesion
	 */
	public String getIdSesion()	{
		HttpSession sesion = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		return sesion.getId();
	}
	
	public void cambiarIdioma(String sigla) {
		for(EnumLenguajes lenguaje : EnumLenguajes.values()) {
			if(lenguaje.getSigla().equals(sigla)){
				FacesContext.getCurrentInstance().getViewRoot().setLocale(lenguaje.getLocale());
				Util.getInstance().setSessionAttribute(EnumSessionAttributes.LENGUAJE, lenguaje);
				break;
			}
		}
	}

	/**
	 * Obtiene la ruta relativa del contexto de la aplicacion
	 * @return Url con la ruta relativa
	 */
	public String getContextPath() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return request.getContextPath();
	}

	public String findComponentClientIdPF(String... idBotonActualizar) {
		StringBuilder retorno = new StringBuilder();
		if(idBotonActualizar != null) {
			
			int cantidad = idBotonActualizar.length;
			int i = 1;
			for (String id : idBotonActualizar) {
				if(id != null) {
					if(id.contains("@"))
						retorno.append(id);
					else 
						retorno.append(ComponentUtils.findComponentClientId(id));
					if(cantidad > i )retorno.append(" ");
					i++;
				}
			}
		}
		return retorno.toString();
	}

	/**
	 * M�todo para que invalida la sesion
	 */
	public void cerrarSesionHttp() {
		HttpSession sesion = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if(sesion != null){
			Enumeration<?> attSess = sesion.getAttributeNames();
			while( attSess.hasMoreElements() ){
				String attName = (String)attSess.nextElement();
				sesion.removeAttribute( attName );
			}
			sesion.invalidate();
		}
	}
	
	/**
	 * Obtiene el valor de un atributo desde el request
	 * @param atributo Atributo de request
	 * @return Valor del atributo o null si no existe
	 */
	public String get(EnumRequestAttributes atributo) {
		return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(atributo.toString());
	}
	
	/*
	 * Utilidades de mensajes
	 */
	public String getMessage(String key, Object... arguments) {
		 
        Locale locale = new Locale("es", "ES", ""); //ControllerUtil.getController().getUsuario().getLocale();
        FacesContext context = FacesContext.getCurrentInstance();
 
        if (locale == null || context == null) {
            locale = Locale.getDefault();
        } else {
            if (context.getViewRoot() != null) {
                locale = context.getViewRoot().getLocale();
            }
        }
        
        String messageBundleName = FacesContext.getCurrentInstance().getApplication().getMessageBundle();
        ResourceBundle bundle = ResourceBundle.getBundle(messageBundleName, locale);
        
        String resourceString;
        try {
            resourceString = bundle.getString(key);
        } catch(MissingResourceException e) {
            return key;
        }
 
        if(arguments == null) {
            return resourceString;
        }
 
        MessageFormat format = new MessageFormat(resourceString, locale);
        return format.format(arguments);
    }
	
	public void mostrarErrorKeyRedirect(String key, boolean aceptRedirect, String... params) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(aceptRedirect);
		mostrarError(getMessage(key, params));
	}
	public void mostrarErrorKey(String key, String... params) {
		mostrarError(getMessage(key, params));
	}
	
	private void mostrarError(String mensaje) {
		this.mostrarMsgGeneral(mensaje, FacesMessage.SEVERITY_ERROR);
	}
	
	public void mostrarMensajeKeyRedirect(String key, boolean aceptRedirect, String... params) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(aceptRedirect);
		mostrarMensajeKey(key, params);
	}
	
	public void mostrarMensajeKey(String key, String... params) {
		mostrarMensaje(getMessage(key, params));
	}
	
	private void mostrarMensaje(String mensaje) {
		this.mostrarMsgGeneral(mensaje, FacesMessage.SEVERITY_INFO);
	}
	
	private void mostrarMsgGeneral(String mensaje, FacesMessage.Severity severidad) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severidad, mensaje, ""));  
	}

	public String encriptarClave(String clave) {
		return BCrypt.hashpw(clave, BCrypt.gensalt());
	}
	
	public boolean verificarContrasenna(String plainPassword, String hashedPassword) {
		if (BCrypt.checkpw(plainPassword, hashedPassword))
			return true;
		else
			return false;
	}
	
	/**
	 * Permite verificar si se encuentra el directorio especificado
	 * @param directorio Ruta del directorio a validar
	 * @return boolean true si existe el directorio; false no existe el directorio
	 * @throws Exception
	 */
	public boolean existeDirectorio(String rutaDirectorio) throws Exception{
		
		boolean retorno = false;
		File directorio = new File(rutaDirectorio);
		if(directorio.exists() && directorio.isDirectory()) { 
			retorno = true;
		}else{
			retorno = false;
		}
		return retorno;
	}
	
	/**
	 * Permite crear el directorio especificado
	 * @param directorio Ruta del directorio
	 * @throws Exception
	 */
	public void crearDirectorio(String directorio) throws Exception{
		
		int j = 0;
		String directorioVer = "";
		File dirCrear = null;
		String [] campos = directorio.split("\\\\");
		String separador = System.getProperty("file.separator");

		while(j < campos.length){
			directorioVer = directorioVer + campos[j] + separador;
			
			if (!existeDirectorio(directorioVer)){
				dirCrear = new File(directorioVer);
				dirCrear.mkdirs();
			}
			j++;
		}
	}
	
	/**
	 * Permite verificar si se encuentra el archivo especificado
	 * @param directorio Ruta del directorio a validar
	 * @return boolean true si existe el archivo; false no existe el archivo
	 * @throws Exception
	 */
	public boolean existeArchivo(String rutaArchivo) throws Exception{
		boolean retorno = false;
		File archivo = new File(rutaArchivo);
		if(archivo.exists() && archivo.isFile()) { 
			retorno = true;
		}else{
			retorno = false;
		}
		return retorno;
	}
	
	public String crearFoto(String fileName, byte[] foto) {
		
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		StringBuilder realPath = new StringBuilder(((String) servletContext.getRealPath("/")))
				.append(SEPARADOR_CARPETA);
		StringBuilder url;
		
		if(foto == null){
			url = new StringBuilder(servletContext.getContextPath().replace("/", SEPARADOR_CARPETA)) 
				.append(SEPARADOR_CARPETA)
				.append("resources").append(SEPARADOR_CARPETA)
				.append("images").append(SEPARADOR_CARPETA)
				.append("user.png");
		}else {
		
			realPath.append(URL_FOTO_PERFIL);
		
			url = new StringBuilder(servletContext.getContextPath().replace("/", SEPARADOR_CARPETA))
					.append(SEPARADOR_CARPETA)
					.append(URL_FOTO_PERFIL)
					.append(fileName).append(".jpg");
			try {
				this.crearDirectorio(realPath.toString());
				// convert byte array back to BufferedImage
				InputStream in = new ByteArrayInputStream(foto);
				BufferedImage bImageFromConvert = ImageIO.read(in);
	
				ImageIO.write(bImageFromConvert, "jpg", new File(
						realPath.toString() + fileName + ".jpg"));
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return url.toString();
	}

}

