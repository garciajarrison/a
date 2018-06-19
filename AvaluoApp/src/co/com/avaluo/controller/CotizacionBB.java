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

import co.com.avaluo.model.entity.Customer;
import co.com.avaluo.model.entity.MarketCategories;
import co.com.avaluo.model.entity.PropertyType;
import co.com.avaluo.service.ICustomerService;
import co.com.avaluo.service.IMarketService;
import co.com.avaluo.service.IPropertyService;
import co.com.avaluo.service.ITablesService;

@Named("cotizacionBB")
@Scope("session")
public class CotizacionBB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private IMarketService marketService;
	private ICustomerService customerService;
	private IPropertyService propertyService;
	private ITablesService tablesService;
	
	
	private MarketCategories market = new MarketCategories();
	private MarketCategories selectedMarket = new MarketCategories();
	private List<MarketCategories> marketList;
	private List<Customer> customerList;
	private List<PropertyType> propertyList;
	private Customer customer = new Customer();
	private PropertyType propertyType = new PropertyType();
	
	
	
	
	public void addEntity() {
		try {
			/*MarketCategories entity = new MarketCategories();
			//entity.setId(market.getId());
			entity.setName(market.getName());
			entity.setValor(market.getValor());*/
			getMarketService().addEntity(market);
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
			getMarketService().updateEntity(selectedMarket);
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
			getMarketService().deleteEntity(selectedMarket);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete!", "Message: "));  
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "D'oh!", "Message: ")); 
		} 	
		
	}

	public MarketCategories getMarket() {
		return market;
	}

	public void setMarket(MarketCategories market) {
		this.market = market;
	}

	public void reset() {
		this.market = new MarketCategories();
	}

	public List<MarketCategories> getEntityList() {
		marketList = new ArrayList<MarketCategories>();
		marketList.addAll(getMarketService().getEntitys());
		return marketList;
	}

	public List<MarketCategories> getMarketList() {
		return marketList;
	}

	public void setMarketList(List<MarketCategories> marketList) {
		this.marketList = marketList;
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	public List<PropertyType> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<PropertyType> propertyList) {
		this.propertyList = propertyList;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public PropertyType getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}

	public IMarketService getMarketService() {
		return marketService;
	}

	public void setMarketService(IMarketService marketService) {
		this.marketService = marketService;
	}

	public ICustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}

	public IPropertyService getPropertyService() {
		return propertyService;
	}

	public void setPropertyService(IPropertyService propertyService) {
		this.propertyService = propertyService;
	}

	public ITablesService getTablesService() {
		return tablesService;
	}

	public void setTablesService(ITablesService tablesService) {
		this.tablesService = tablesService;
	}

	public void setEntityList(List<MarketCategories> entityList) {
		this.marketList = entityList;
	}


	public MarketCategories getSelectedMarket() {
		return selectedMarket;
	}


	public void setSelectedMarket(MarketCategories selectedMarket) {
		this.selectedMarket = selectedMarket;
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