package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.Estrato;


public interface IEstratoDAO {
	void addEntity(Estrato entity);

	void updateEntity(Estrato entity);
	
	void deleteEntity(Estrato entity);
	
	Estrato getEntity(int id);

	List<Estrato> getEntities(int idEmpresa);
}
