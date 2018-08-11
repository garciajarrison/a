package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.Empresa;


public interface IEmpresaService {
	
	void addEmpresa(Empresa entity);
	
	void updateEmpresa(Empresa entity);

	void deleteEmpresa(Empresa entity);
	
	Empresa getEmpresaById(int id);
	
	List<Empresa> getEmpresas();

}
