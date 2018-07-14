package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.Empresa;
import co.com.avaluo.model.entity.Usuario;


public interface IEmpresaDAO {
	void addEntity(Empresa entity);

	void updateEntity(Empresa entity);
	
	void deleteEntity(Empresa entity);
	
	Empresa getEntity(int id);

	List<Empresa> getEntities();
	

}
