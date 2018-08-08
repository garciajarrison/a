package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.Empresa;


public interface IEmpresaDAO {
	
	void addEmpresa(Empresa entity);

	void updateEmpresa(Empresa entity);
	
	void deleteEmpresa(Empresa entity);
	
	Empresa getEmpresa(int id);

	List<Empresa> getEmpresas();

}
