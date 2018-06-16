package co.com.avaluo.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import co.com.avaluo.model.dao.IMarketDAO;
import co.com.avaluo.model.entity.MarketCategories;


@Named
@Transactional(readOnly = true)
public class MarketService implements IMarketService {

	@Inject
	IMarketDAO entityDAO;

	@Transactional(readOnly = false)
	public void addEntity(MarketCategories entity) {
		getEntityDAO().addEntity(entity);
	}

	@Transactional(readOnly = false)
	public void deleteEntity(MarketCategories entity) {
		getEntityDAO().deleteEntity(entity);
	}

	@Transactional(readOnly = false)
	public void updateEntity(MarketCategories entity) {
		getEntityDAO().updateEntity(entity);
	}

	public MarketCategories getEntityById(int id) {
		return getEntityDAO().getEntity(id);
	}

	public List<MarketCategories> getEntitys() {	
		return getEntityDAO().getEntities();
	}

	public IMarketDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEntityDAO(IMarketDAO entityDAO) {
		this.entityDAO = entityDAO;
	}
}
