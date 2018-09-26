package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.Ciudad;
import co.com.avaluo.model.entity.Departamento;
import co.com.avaluo.model.entity.Pais;


public interface ICiudadDAO {
	void addEntity(Ciudad entity);

	void updateEntity(Ciudad entity);
	
	void deleteEntity(Ciudad entity);
	
	Ciudad getEntity(int id);
	
	Departamento getDepartamento(int id);
	
	Pais getPais(int id);

	List<Ciudad> getEntities();

	List<Pais> getPaises();

	List<Departamento> getDepartamentos(int idPais);

	List<Ciudad> getCiudades(int idDepartamento);
}
