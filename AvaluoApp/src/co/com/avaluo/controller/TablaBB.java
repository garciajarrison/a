package co.com.avaluo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.avaluo.common.EnumSessionAttributes;
import co.com.avaluo.common.ListasGenericas;
import co.com.avaluo.common.Util;
import co.com.avaluo.model.entity.Tablas;
import co.com.avaluo.model.entity.Usuario;
import co.com.avaluo.service.ITablasService;

@ManagedBean(name = "tablaBB")
@ViewScoped
public class TablaBB extends SpringBeanAutowiringSupport implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITablasService tablaService;
	
	private Tablas tablas = new Tablas();
	private Tablas selectedTablas;
	private List<Tablas> entityList;
	private Usuario usuario;
	private Util util;
	private ListasGenericas listasGenericas;
	private String tipo = "";
	
	public TablaBB() {
		util = Util.getInstance();
		usuario = (Usuario) util.getSessionAttribute(EnumSessionAttributes.USUARIO);
		listasGenericas = ListasGenericas.getInstance();
		cargarListaTablas();
	}
	
	private void cargarListaTablas() {
		entityList = tablaService.getEntitys(usuario.getEmpresa().getId());
		if(entityList == null)
			entityList = new ArrayList<>();
	}
	
	public void addEntity() {
		try {
			boolean guardar = true;
			//Validamos que no exista un estrato con esa configuracion
			for(Tablas tbl : entityList) {
				if(tbl.getTipo().equals(tablas.getTipo()) &&
						tbl.getMinimo().equals(tablas.getMinimo())
						//Falta validar que el rango no se duplique
						) {
					guardar = false;
					util.mostrarErrorKey("ya.existe");
				}
			}
			
			if(guardar) {
				tablas.setEmpresa(usuario.getEmpresa());
				tablaService.addEntity(tablas);
				this.cargarListaTablas();
				util.mostrarMensajeKey("exito.guardar"); 
				tablas = new Tablas();
				util.actualizarPF("formulario");
			}else {
				util.actualizarPF("growl");
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarErrorKey("error.guardar"); 
		} 	
		
	}

	public void updateEntity() {
		try {
			getTablaService().updateEntity(selectedTablas);
			util.mostrarMensajeKey("exito.actualizar");  
			cargarListaTablas();
			util.actualizarPF("formulario");
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarErrorKey("error.actualizar"); 
		} 	
	}
	
	public void deleteEntity() {
		try {
			getTablaService().deleteEntity(selectedTablas);
			util.mostrarMensajeKey("exito.eliminar");
			cargarListaTablas();
			util.actualizarPF("formulario");
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarErrorKey("error.eliminando");
		} 	
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Util getUtil() {
		return util;
	}

	public void setUtil(Util util) {
		this.util = util;
	}

	public ITablasService getTablaService() {
		return tablaService;
	}

	public void setTablaService(ITablasService tablaService) {
		this.tablaService = tablaService;
	}

	public ListasGenericas getListasGenericas() {
		return listasGenericas;
	}

	public Tablas getTablas() {
		return tablas;
	}

	public void setTablas(Tablas tablas) {
		this.tablas = tablas;
	}

	public Tablas getSelectedTablas() {
		return selectedTablas;
	}

	public void setSelectedTablas(Tablas selectedTablas) {
		this.selectedTablas = selectedTablas;
	}

	public List<Tablas> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<Tablas> entityList) {
		this.entityList = entityList;
	}
	
 }