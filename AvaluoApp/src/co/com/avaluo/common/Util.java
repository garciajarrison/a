package co.com.avaluo.common;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
        String messagesBaseName = "messages_" + lenguaje.getLocale().getLanguage() + "_" + lenguaje.getLocale().getCountry();
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

	public void mostrarError(String mensaje) {
		this.mostrarMsgGeneral(mensaje, FacesMessage.SEVERITY_ERROR);
	}
	
	public void mostrarMensaje(String mensaje) {
		this.mostrarMsgGeneral(mensaje, FacesMessage.SEVERITY_INFO);
	}
	
	private void mostrarMsgGeneral(String mensaje, FacesMessage.Severity severidad) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severidad, mensaje, ""));  
	}
	
	/* RequestContext context = RequestContext.getCurrentInstance();
  
  FacesContext jsfCtx= FacesContext.getCurrentInstance();
  ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msg");
  */

}
