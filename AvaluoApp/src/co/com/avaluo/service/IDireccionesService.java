package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.Direcciones;



public interface IDireccionesService {
	
	void addDirecciones(Direcciones entity);

	void updateDirecciones(Direcciones entity);
	
	void deleteDirecciones(Direcciones entity);
	
	Direcciones getDireccionesById(int id);
	
	
}
