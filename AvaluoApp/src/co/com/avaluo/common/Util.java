package co.com.avaluo.common;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

public class Util {
	
	private static Util instance;
	
	private Util(){}

	public static Util getInstance() {
		if(instance == null)
			instance = new Util();
		return instance;
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
	 * obtiene el identificador de la sesión
	 * @return devuelve el Id de la sesion
	 */
	public String getIdSesion()	{
		HttpSession sesion = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		return sesion.getId();
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
					if(id.contains("@")) {
						retorno.append(id);
					}else {
						//retorno.append(ComponentUtils.findComponentClientId(id));
						retorno.append(FacesContext.getCurrentInstance().getViewRoot().findComponent(id));
					}
					if(cantidad > i )retorno.append(" ");
					i++;
				}
			}
		}
		return retorno.toString();
	}

	/**
	 * Método para que invalida la sesion
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
        
        String resourceString;
        EnumLenguajes lenguaje = (EnumLenguajes)this.getSessionAttribute(EnumSessionAttributes.LENGUAJE);
        String messagesBaseName = "messages_" + lenguaje.getLocale().getLanguage();
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(messagesBaseName,locale);
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
	
	public void mostrarErrorKey(String key, String... params) {
		mostrarError(getMessage(key, params));
	}
	
	private void mostrarError(String mensaje) {
		this.mostrarMsgGeneral(mensaje, FacesMessage.SEVERITY_ERROR);
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

}
