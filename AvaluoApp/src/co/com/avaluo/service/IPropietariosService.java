package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.Propietarios;


public interface IPropietariosService {
	
	void addEntity(Propietarios entity);
	
	void updateEntity(Propietarios entity);

	void deleteEntity(Propietarios entity);
	
	Propietarios getEntityById(int id);
	

	
	List<Propietarios > listaPropietarios(int idVisita);
}
