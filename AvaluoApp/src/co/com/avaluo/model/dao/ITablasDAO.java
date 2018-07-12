package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.Tablas;


public interface ITablasDAO {
	void addEntity(Tablas entity);

	void updateEntity(Tablas entity);
	
	void deleteEntity(Tablas entity);
	
	Tablas getEntity(int id);

	List<Tablas> getEntities(int idEmpresa);
}
