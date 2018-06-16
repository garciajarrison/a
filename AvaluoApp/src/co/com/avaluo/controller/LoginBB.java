package co.com.avaluo.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import co.com.avaluo.model.entity.Users;
import co.com.avaluo.service.IUsersService;

@Named("loginBB")
@Scope("session")
public class LoginBB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private IUsersService usersService;
	
	private Users users;
	
	public LoginBB() {
		users = new Users();
	}
	

	public void login() {
		try {
			users = this.getUsersService().login(users);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido!", users.getName()));  
			
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "D'oh!", "Message: ")); 
		} 	
		
	}
	



	public IUsersService getUsersService() {
		return usersService;
	}


	public void setUsersService(IUsersService usersService) {
		this.usersService = usersService;
	}


	public Users getUsers() {
		return users;
	}


	public void setUsers(Users users) {
		this.users = users;
	}
	
	

 }