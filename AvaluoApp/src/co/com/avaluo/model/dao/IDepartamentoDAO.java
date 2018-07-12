package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.Departamento;


public interface IDepartamentoDAO {
	void addEntity(Departamento entity);

	void updateEntity(Departamento entity);
	
	void deleteEntity(Departamento entity);
	
	Departamento getEntity(int id);

	List<Departamento> getEntities();
}
