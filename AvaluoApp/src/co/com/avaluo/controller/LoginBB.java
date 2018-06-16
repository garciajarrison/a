package co.com.avaluo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.dao.DataAccessException;

import co.com.avaluo.model.entity.Usuario;
import co.com.avaluo.service.IUsuarioService;

@ManagedBean
@ViewScoped
public class LoginBB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private IUsuarioService usuarioService;
	
	private int id;
	private String attribute;
	private List<Usuario> entityList;
	

	public void addEntity() {
		try {
			Usuario entity = new Usuario();
			entity.setId(getId());
			entity.setAttribute(getAttribute());
			getUsuarioService().addEntity(entity);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Added!", "Message: "));  
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "D'oh!", "Message: ")); 
		} 	
		
	}
	

	public void reset() {
		this.setId(0);
		this.setAttribute("");
	}

	public List<Usuario> getEntityList() {
		entityList = new ArrayList<Usuario>();
		entityList.addAll(getUsuarioService().getEntitys());
		return entityList;
	}

	public IUsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(IUsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public void setEntityList(List<Usuario> entityList) {
		this.entityList = entityList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAttribute() {
		return attribute;
	}
	
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
 }