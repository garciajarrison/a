package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.MarketCategories;


public interface IMarketDAO {
	public void addEntity(MarketCategories entity);

	public void updateEntity(MarketCategories entity);
	
	public void deleteEntity(MarketCategories entity);
	
	public MarketCategories getEntity(int id);

	public List<MarketCategories> getEntities();
}
