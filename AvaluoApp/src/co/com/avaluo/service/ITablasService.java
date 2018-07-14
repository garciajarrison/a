package co.com.avaluo.service;

import java.util.List;

import co.com.avaluo.model.entity.DetalleTabla;
import co.com.avaluo.model.entity.Tablas;


public interface ITablasService {
	
	void addTabla(Tablas entity);
	
	void updateTabla(Tablas entity);

	void deleteTabla(Tablas entity);
	
	Tablas getTablaById(int id);
	
<<<<<<< HEAD
	List<Tablas> getTablas(int idEmpresa);

	void updateTablaDetalle(DetalleTabla selectedDetalle);

	void addTablaDetalle(DetalleTabla detalle);
	
	void deleteTablaDetalle(DetalleTabla detalle);
=======
	List<Tablas> getEntitys(int idEmpresa);
>>>>>>> upstream/master
}
