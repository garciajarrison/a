package co.com.avaluo.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import co.com.avaluo.common.EnumSessionAttributes;
import co.com.avaluo.common.Util;
import co.com.avaluo.model.entity.Users;
import co.com.avaluo.service.IUsersService;

@Named("globalBB")
@Scope("session")
public class GlobalBB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Users user;
	
	public GlobalBB() {
		user = (Users) Util.getInstance().getSessionAttribute(EnumSessionAttributes.USER);
	}

	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}
	
	

 }