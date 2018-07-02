package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.Licencia;


public interface ILicenciaService {
	
	void addEntity(Licencia entity);
	
	void updateEntity(Licencia entity);

	void deleteEntity(Licencia entity);
	
	Licencia getEntityById(int id);
	
	List<Licencia> getEntitys();
}
