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

import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;

import co.com.avaluo.model.entity.MarketCategories;
import co.com.avaluo.service.IMarketService;

@Named("marketBB")
@Scope("session")
public class MarketBB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private IMarketService marketService;
	
	private MarketCategories market = new MarketCategories();
	private MarketCategories selectedMarket;
	private List<MarketCategories> entityList;
	
	
	
	
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
		entityList = new ArrayList<MarketCategories>();
		entityList.addAll(getMarketService().getEntitys());
		return entityList;
	}

	public IMarketService getMarketService() {
		return marketService;
	}

	public void setMarketService(IMarketService marketService) {
		this.marketService = marketService;
	}

	public void setEntityList(List<MarketCategories> entityList) {
		this.entityList = entityList;
	}


	public MarketCategories getSelectedMarket() {
		return selectedMarket;
	}


	public void setSelectedMarket(MarketCategories selectedMarket) {
		this.selectedMarket = selectedMarket;
	}
 }