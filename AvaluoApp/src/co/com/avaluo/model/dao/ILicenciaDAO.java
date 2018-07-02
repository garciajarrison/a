package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.Licencia;


public interface ILicenciaDAO {
	void addEntity(Licencia entity);

	void updateEntity(Licencia entity);
	
	void deleteEntity(Licencia entity);
	
	Licencia getEntity(int id);

	List<Licencia> getEntities();
}
