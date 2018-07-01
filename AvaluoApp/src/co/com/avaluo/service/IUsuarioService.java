package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.Usuario;


public interface IUsuarioService {
	
	public void addEntity(Usuario entity);
	
	public void updateEntity(Usuario entity);

	public void deleteEntity(Usuario entity);
	
	public Usuario getEntityById(int id);
	
	public List<Usuario> getEntities();

	public Usuario login(Usuario usuario);
	
	
}
