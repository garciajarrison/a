package co.com.avaluo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.annotations.Tables;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;

import co.com.avaluo.model.entity.Cotizacion;
import co.com.avaluo.model.entity.Customer;
import co.com.avaluo.model.entity.Estrato;
import co.com.avaluo.model.entity.MarketCategories;
import co.com.avaluo.model.entity.PropertyType;
import co.com.avaluo.model.entity.Propiedad;
import co.com.avaluo.service.ICustomerService;
import co.com.avaluo.service.IEstratoService;
import co.com.avaluo.service.IPropiedadService;
import co.com.avaluo.service.ITablesService;

@Named("cotizacionBB")
@Scope("session")
public class CotizacionBB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private IEstratoService marketService;
	@Inject
	private ICustomerService customerService;
	@Inject
	private IPropiedadService propertyService;
	@Inject
	private ITablesService tablesService;
	
	
	private Estrato market = new Estrato();
	private Estrato selectedMarket = new Estrato();
	private List<Estrato> marketList;
	private List<Cotizacion> tables;
	private String table;
	private String property;
	private Map<String,String> listaTables = new HashMap<String, String>();
	private Map<String,String> listaProperty = new HashMap<String, String>();
	private List<Customer> customerList;
	private List<Propiedad> propertyList;
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
	
	public Map<String, String> getLzistaTable() {
		tables = new ArrayList<Tables>();
		tables.addAll(getTablesService().getEntitys());
		for (Tables table : tables) {
			listaTables.put(table.getName(), table.getTableType());
		}
		return listaTables;
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

	public List<Tables> getTables() {
		return tables;
	}

	public void setTables(List<Tables> tables) {
		this.tables = tables;
	}

	public Map<String,String> getListaTables() {
		tables = new ArrayList<Tables>();
		tables.addAll(getTablesService().getEntitys());
		for (Tables table : tables) {
			listaTables.put(table.getName(),table.getTableType());
		}
		return listaTables;
	}
	
	public void onTableChange(String tabla) {
        if(tabla !=null && !tabla.equals("")) {
        	propertyList = new ArrayList<PropertyType>();
			propertyList.addAll(getPropertyService().getEntitys(tabla));
			for (PropertyType property : propertyList) {
				listaProperty.put(property.getNameType(),property.getNameType());
			}
	    }
        else
        	listaProperty =  new HashMap<String,String>();
        
	}
	

	/*public void setListaTables(Map<String, String> listaTables) {
		this.listaTables = listaTables;
	}*/

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public void setListaTables(Map<String, String> listaTables) {
		this.listaTables = listaTables;
	}

	public Map<String, String> getListaProperty() {
		return listaProperty;
	}

	public void setListaProperty(Map<String, String> listaProperty) {
		this.listaProperty = listaProperty;
	}

	public IEstratoService getMarketService() {
		return marketService;
	}

	public void setMarketService(IEstratoService marketService) {
		this.marketService = marketService;
	}

	public ICustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}

	public IPropiedadService getPropertyService() {
		return propertyService;
	}

	public void setPropertyService(IPropiedadService propertyService) {
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