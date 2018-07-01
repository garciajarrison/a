package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.Licencia;


public interface ILicenciaService {
	
	public void addEntity(Licencia entity);
	
	public void updateEntity(Licencia entity);

	public void deleteEntity(Licencia entity);
	
	public Licencia getEntityById(int id);
	
	public List<Licencia> getEntitys();
}
