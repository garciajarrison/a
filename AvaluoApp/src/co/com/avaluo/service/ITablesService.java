package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.Tables;


public interface ITablesService {
	
	public void addEntity(Tables entity);
	
	public void updateEntity(Tables entity);

	public void deleteEntity(Tables entity);
	
	public Tables getEntityById(int id);
	
	public List<Tables> getEntitys();
}
