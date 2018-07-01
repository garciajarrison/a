package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.Usuario;


public interface IEjemploService {
	
	void addEntity(Usuario entity) ;

	void deleteEntity(Usuario entity);

	void updateEntity(Usuario entity);

	Usuario getEntityById(int id);

	List<Usuario> getEntities();
		
}
