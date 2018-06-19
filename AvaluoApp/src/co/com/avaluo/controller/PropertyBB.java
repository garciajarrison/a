package co.com.avaluo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;

import co.com.avaluo.model.entity.MarketCategories;
import co.com.avaluo.model.entity.PropertyType;
import co.com.avaluo.service.IMarketService;
import co.com.avaluo.service.IPropertyService;

@Named("propertyBB")
@Scope("session")
public class PropertyBB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private IPropertyService propertyService;
	
	private PropertyType property = new PropertyType();
	private PropertyType selectedProperty = new PropertyType();
	private List<PropertyType> entityList;
	
	
	
	
	public void addEntity() {
		try {
			/*MarketCategories entity = new MarketCategories();
			//entity.setId(market.getId());
			entity.setName(market.getName());
			entity.setValor(market.getValor());*/
			getPropertyService().addEntity(property);
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
			getPropertyService().updateEntity(selectedProperty);
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
			getPropertyService().deleteEntity(selectedProperty);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete!", "Message: "));  
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "D'oh!", "Message: ")); 
		} 	
		
	}

	public PropertyType getProperty() {
		return property;
	}

	public void setProperty(PropertyType property) {
		this.property = property;
	}

	public void reset() {
		this.property = new PropertyType();
	}

	public List<PropertyType> getEntityList() {
		entityList = new ArrayList<PropertyType>();
		entityList.addAll(getPropertyService().getEntitys());
		return entityList;
	}

	public IPropertyService getPropertyService() {
		return propertyService;
	}

	public void setPropertyService(IPropertyService propertyService) {
		this.propertyService = propertyService;
	}

	public void setEntityList(List<PropertyType> entityList) {
		this.entityList = entityList;
	}


	public PropertyType getSelectedProperty() {
		return selectedProperty;
	}


	public void setSelectedProperty(PropertyType selectedProperty) {
		this.selectedProperty = selectedProperty;
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