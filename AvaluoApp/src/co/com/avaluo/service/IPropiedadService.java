package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.Propiedad;
import co.com.avaluo.model.entity.TipoPropiedad;


public interface IPropiedadService {
	
	void addPropiedad(Propiedad entity);

	void updatePropiedad(Propiedad entity);
	
	void deletePropiedad(Propiedad entity);
	
	Propiedad getPropiedadById(int id);
	
	List<TipoPropiedad> getListaTipoPropiedad(int idEmpresa);
	
	void addTipoPropiedad(TipoPropiedad entity);

	void updateTipoPropiedad(TipoPropiedad entity);
	
	void deleteTipoPropiedad(TipoPropiedad entity);
	
	TipoPropiedad getTipoPropiedad(int id);
}
