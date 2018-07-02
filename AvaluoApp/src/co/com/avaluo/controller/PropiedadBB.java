package co.com.avaluo.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.avaluo.model.entity.Propiedad;
import co.com.avaluo.service.IPropiedadService;

@ManagedBean(name = "propiedadBB")
@ViewScoped
public class PropiedadBB extends SpringBeanAutowiringSupport implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private IPropiedadService propiedadService;
	
	private Propiedad propiedad = new Propiedad();
	private Propiedad selectedPropiedad = new Propiedad();
	private List<Propiedad> entityList;
	
	public void addEntity() {
		try {
			/*MarketCategories entity = new MarketCategories();
			//entity.setId(market.getId());
			entity.setName(market.getName());
			entity.setValor(market.getValor());*/
			getPropiedadService().addEntity(propiedad);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Added!", "Message: "));  
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "D'oh!", "Message: ")); 
		} 	
		
	}

	public void updateEntity() {
		try {
			/*MarketCategories entity = new MarketCategories();
			//entity.setId(market.getId());
			entity.setName(market.getName());
			entity.setValor(market.getValor());*/
			getPropiedadService().updateEntity(selectedPropiedad);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Update!", "Message: "));  
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "D'oh!", "Message: ")); 
		} 	
		
	}
	
	public void deleteEntity() {
		try {
			/*MarketCategories entity = new MarketCategories();
			//entity.setId(market.getId());
			entity.setName(market.getName());
			entity.setValor(market.getValor());*/
			getPropiedadService().deleteEntity(selectedPropiedad);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete!", "Message: "));  
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "D'oh!", "Message: ")); 
		} 	
		
	}

	public List<Propiedad> getEntityList() {
		entityList = getPropiedadService().getEntitys("");
		return entityList;
	}
	
	public IPropiedadService getPropiedadService() {
		return propiedadService;
	}

	public void setPropiedadService(IPropiedadService propiedadService) {
		this.propiedadService = propiedadService;
	}

	public Propiedad getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(Propiedad propiedad) {
		this.propiedad = propiedad;
	}

	public Propiedad getSelectedPropiedad() {
		return selectedPropiedad;
	}

	public void setSelectedPropiedad(Propiedad selectedPropiedad) {
		this.selectedPropiedad = selectedPropiedad;
	}

	public void setEntityList(List<Propiedad> entityList) {
		this.entityList = entityList;
	}

	public void onRowSelect(SelectEvent event) {
		
        /*FacesMessage msg = new FacesMessage("Car Selected", ((Car) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);*/
    }
 
    public void onRowUnselect(UnselectEvent event) {
        /*FacesMessage msg = new FacesMessage("Car Unselected", ((Car) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);*/
    }	
 }