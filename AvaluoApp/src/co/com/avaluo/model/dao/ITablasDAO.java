package co.com.avaluo.model.dao;

import java.util.List;

import co.com.avaluo.model.entity.DetalleTabla;
import co.com.avaluo.model.entity.Tablas;


public interface ITablasDAO {
	void addTabla(Tablas entity);

	void updateTabla(Tablas entity);
	
	void deleteTabla(Tablas entity);
	
	Tablas getTablaById(int id);

	List<Tablas> getTablas(int idEmpresa);

	void updateTablaDetalle(DetalleTabla detalle);

	void addTablaDetalle(DetalleTabla detalle);
	
	void deleteTablaDetalle(DetalleTabla detalle);
}
