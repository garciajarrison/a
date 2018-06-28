package co.com.avaluo.common;

import java.io.IOException;

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
}
