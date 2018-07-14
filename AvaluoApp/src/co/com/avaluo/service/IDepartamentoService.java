package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.Departamento;


public interface IDepartamentoService {
	
	void addEntity(Departamento entity);
	
	void updateEntity(Departamento entity);

	void deleteEntity(Departamento entity);
	
	Departamento getEntityById(int id);
	
	List<Departamento> getEntitys();
}
