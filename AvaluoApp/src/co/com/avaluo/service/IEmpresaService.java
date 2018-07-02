package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.Estrato;


public interface IEmpresaService {
	
	void addEntity(Estrato entity);
	
	void updateEntity(Estrato entity);

	void deleteEntity(Estrato entity);
	
	Estrato getEntityById(int id);
	
	List<Estrato> getEntitys();

}
