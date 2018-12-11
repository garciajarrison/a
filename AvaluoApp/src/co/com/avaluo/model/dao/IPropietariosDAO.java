package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.Propietarios;


public interface IPropietariosDAO {
	void addEntity(Propietarios entity);

	void updateEntity(Propietarios entity);
	
	void deleteEntity(Propietarios entity);
	
	Propietarios getEntity(int id);

	
	List<Propietarios > listaPropietarios(int idVisita);
}
