package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.Estrato;


public interface IEstratoDAO {
	public void addEntity(Estrato entity);

	public void updateEntity(Estrato entity);
	
	public void deleteEntity(Estrato entity);
	
	public Estrato getEntity(int id);

	public List<Estrato> getEntities();
}
