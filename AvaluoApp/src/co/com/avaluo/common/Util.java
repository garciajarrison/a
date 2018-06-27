package co.com.avaluo.common;

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
}
