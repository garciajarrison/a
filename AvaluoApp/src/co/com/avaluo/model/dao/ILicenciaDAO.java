package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.Licencia;


public interface ILicenciaDAO {
	void addLicencia(Licencia entity);

	void updateLicencia(Licencia entity);
	
	void deleteLicencia(Licencia entity);
	
	Licencia getLicenciaById(int id);

	List<Licencia> getLicencias();
}
