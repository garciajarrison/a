package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.Usuario;


public interface IUsuarioDAO {
	
	Usuario login(Usuario users);
	
	void addEntity(Usuario entity);

	void deleteEntity(Usuario entity);

	void updateEntity(Usuario entity);

	Usuario getEntityById(int id);

	List<Usuario> getEntities();

}
