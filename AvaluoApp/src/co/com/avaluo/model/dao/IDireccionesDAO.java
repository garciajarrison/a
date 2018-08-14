package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.Direcciones;



public interface IDireccionesDAO {
	
	void addDirecciones(Direcciones entity);

	void updateDirecciones(Direcciones entity);
	
	void deleteDirecciones(Direcciones entity);
	
	Direcciones getDireccionesById(int id);



	
}
