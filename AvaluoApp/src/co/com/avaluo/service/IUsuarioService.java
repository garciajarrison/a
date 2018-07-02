package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.Usuario;


public interface IUsuarioService {
	
	void addEntity(Usuario entity);
	
	void updateEntity(Usuario entity);

	void deleteEntity(Usuario entity);
	
	Usuario getEntityById(int id);
	
	List<Usuario> getEntities();

	Usuario login(Usuario usuario);
	
	
}
