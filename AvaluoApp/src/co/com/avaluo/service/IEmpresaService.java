package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.Empresa;
import co.com.avaluo.model.entity.Usuario;


public interface IEmpresaService {
	
	void addEntity(Empresa entity);
	
	void updateEntity(Empresa entity);

	void deleteEntity(Empresa entity);
	
	Empresa getEntityById(int id);
	
	List<Empresa> getEntitys();
	

}
