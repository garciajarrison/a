package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.Licencia;


public interface ILicenciaDAO {
	public void addEntity(Licencia entity);

	public void updateEntity(Licencia entity);
	
	public void deleteEntity(Licencia entity);
	
	public Licencia getEntity(int id);

	public List<Licencia> getEntities();
}
