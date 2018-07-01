package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.Estrato;


public interface IEstratoService {
	
	public void addEntity(Estrato entity);
	
	public void updateEntity(Estrato entity);

	public void deleteEntity(Estrato entity);
	
	public Estrato getEntityById(int id);
	
	public List<Estrato> getEntitys();
}
