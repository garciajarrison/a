package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.Empresa;
import co.com.avaluo.model.entity.Estrato;
import co.com.avaluo.model.entity.Usuario;


public interface IEmpresaDAO {
	public void addEntity(Estrato entity);

	public void updateEntity(Estrato entity);
	
	public void deleteEntity(Estrato entity);
	
	public Estrato getEntity(int id);

	public List<Estrato> getEntities();

	public Empresa getEmpresaPorUsuario(Usuario usuario);
}
