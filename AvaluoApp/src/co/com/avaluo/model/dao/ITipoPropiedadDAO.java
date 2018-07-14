package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.Propiedad;
import co.com.avaluo.model.entity.TipoPropiedad;


public interface ITipoPropiedadDAO {
	void addEntity(TipoPropiedad entity);

	void updateEntity(TipoPropiedad entity);
	
	void deleteEntity(TipoPropiedad entity);
	
	TipoPropiedad getEntity(int id);

	List<TipoPropiedad> getEntities( String tipo, int idEmpresa);
}
