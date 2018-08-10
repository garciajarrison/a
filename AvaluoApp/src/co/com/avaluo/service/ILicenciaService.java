package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.Licencia;


public interface ILicenciaService {
	
	void addLicencia(Licencia entity);
	
	void updateLicencia(Licencia entity);

	void deleteLicencia(Licencia entity);
	
	Licencia getLicenciaById(int id);
	
	List<Licencia> getLicencias();
}
