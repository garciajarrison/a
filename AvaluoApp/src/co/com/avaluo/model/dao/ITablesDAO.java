package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.Tables;


public interface ITablesDAO {
	public void addEntity(Tables entity);

	public void updateEntity(Tables entity);
	
	public void deleteEntity(Tables entity);
	
	public Tables getEntity(int id);

	public List<Tables> getEntities();
}
