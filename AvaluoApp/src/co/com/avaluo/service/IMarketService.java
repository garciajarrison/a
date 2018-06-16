package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.MarketCategories;


public interface IMarketService {
	
	public void addEntity(MarketCategories entity);
	
	public void updateEntity(MarketCategories entity);

	public void deleteEntity(MarketCategories entity);
	
	public MarketCategories getEntityById(int id);
	
	public List<MarketCategories> getEntitys();
}
