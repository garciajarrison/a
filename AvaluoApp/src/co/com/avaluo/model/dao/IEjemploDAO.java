package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.Usuario;


public interface IEjemploDAO {
	public void addEntity(Usuario entity);

	public void updateEntity(Usuario entity);
	
	public void deleteEntity(Usuario entity);
	
	public Usuario getEntity(int id);

	public List<Usuario> getEntities();
}
