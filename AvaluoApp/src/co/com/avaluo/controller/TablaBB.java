package co.com.avaluo.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.avaluo.common.EnumSessionAttributes;
import co.com.avaluo.common.ListasGenericas;
import co.com.avaluo.common.Util;
import co.com.avaluo.controller.reporte.RCotizacion;
import co.com.avaluo.model.entity.Cotizacion;
import co.com.avaluo.model.entity.DetalleTabla;
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
	private Tablas detalleList;
	private DetalleTabla detalle = new DetalleTabla();
	private DetalleTabla selectedDetalle;
	private List<Tablas> entityList;
	private Usuario usuario;
	private Util util;
	private ListasGenericas listasGenericas;
	private boolean mostrarDetalle;
	
	//TODO Borrar o mover
	private StreamedContent file;
	
	public TablaBB() {
		util = Util.getInstance();
		usuario = (Usuario) util.getSessionAttribute(EnumSessionAttributes.USUARIO);
		listasGenericas = ListasGenericas.getInstance();
		mostrarDetalle = false;
		cargarListaTablas();
		generarReporteCotizacion();
	}
	
	private void cargarListaTablas() {
		entityList = tablaService.getTablas(usuario.getEmpresa().getId());
		if(entityList == null)
			entityList = new ArrayList<>();
	}
	
	//TODO borrar o mover
	public void generarReporteCotizacion() {
		
		RCotizacion reporte = new RCotizacion();
		ByteArrayOutputStream docExport = reporte.generarReporte(new Cotizacion());
		InputStream targetStream = new ByteArrayInputStream(docExport.toByteArray());
        file = new DefaultStreamedContent(targetStream, "application/pdf", "cotización.pdf");
	}
	
	public void addEntity() {
		try {
			boolean guardar = true;
			//Validamos que no exista una tabla con esa configuracion
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
				tablaService.addTabla(tablas);
				this.cargarListaTablas();
				util.mostrarMensajeKey("exito.guardar"); 
				tablas = new Tablas();
				util.actualizarPF(util.findComponentClientIdPF("dtTablas"));
				util.actualizarPF(util.findComponentClientIdPF("formAddTabla"));
				util.ejecutarPF("PF('dlgAgregar').hide()");
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
			getTablaService().updateTabla(selectedTablas);
			util.mostrarMensajeKey("exito.actualizar");  
			cargarListaTablas();
			util.actualizarPF(util.findComponentClientIdPF("dtTablas"));
			util.actualizarPF(util.findComponentClientIdPF("formAddTabla"));
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarErrorKey("error.actualizar"); 
		} 	
	}
	
	public void deleteEntity() {
		try {
			getTablaService().deleteTabla(selectedTablas);
			util.mostrarMensajeKey("exito.eliminar");
			cargarListaTablas();
			util.actualizarPF(util.findComponentClientIdPF("dtTablas"));
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarErrorKey("error.eliminando");
		} 	
	}
	
	public void addEntityDetalle() {
		try {
			boolean guardar = true;
			//Validamos que no exista un detalle con esa configuracion
			for(DetalleTabla det : detalleList.getDetalleTablas()) {
				if(det.getDesde().equals(detalle.getDesde()) &&
						det.getHasta().equals(detalle.getHasta())
						//Falta validar que el rango no se duplique
						) {
					guardar = false;
					util.mostrarErrorKey("ya.existe");
				}
			}
			
			if(guardar) {
				detalle.setTablas(detalleList);
				tablaService.addTablaDetalle(detalle);
				this.cargarListaTablas();
				this.detalleList.getDetalleTablas().add(detalle);
				util.mostrarMensajeKey("exito.guardar"); 
				detalle = new DetalleTabla();
				util.actualizarPF(util.findComponentClientIdPF("dtDetalle"));
				util.actualizarPF(util.findComponentClientIdPF("formAgregarDetalle"));
				util.ejecutarPF("PF('dlgAgregarDet').hide()");
			}else {
				util.actualizarPF("growl");
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarErrorKey("error.guardar"); 
		} 	
	}
	
	public void updateEntityDetalle() {
		try {
			getTablaService().updateTablaDetalle(selectedDetalle);
			util.mostrarMensajeKey("exito.actualizar");  
			cargarListaTablas();
			util.actualizarPF(util.findComponentClientIdPF("dtDetalle"));
			util.actualizarPF(util.findComponentClientIdPF("formAgregarDetalle"));
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarErrorKey("error.actualizar"); 
		} 	
	}
	
	public void deleteEntityDetalle() {
		try {
			getTablaService().deleteTablaDetalle(selectedDetalle);
			detalleList.getDetalleTablas().remove(selectedDetalle);
			util.mostrarMensajeKey("exito.eliminar");
			util.actualizarPF(util.findComponentClientIdPF("dtDetalle"));
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarErrorKey("error.eliminando");
		} 	
	}
	
	public void verDetalle(SelectEvent event) {
		detalleList = (Tablas) event.getObject();
		mostrarDetalle = true;
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

	public DetalleTabla getSelectedDetalle() {
		return selectedDetalle;
	}

	public void setSelectedDetalle(DetalleTabla selectedDetalle) {
		this.selectedDetalle = selectedDetalle;
	}

	public Tablas getDetalleList() {
		return detalleList;
	}

	public void setDetalleList(Tablas detalleList) {
		this.detalleList = detalleList;
	}

	public DetalleTabla getDetalle() {
		return detalle;
	}

	public void setDetalle(DetalleTabla detalle) {
		this.detalle = detalle;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public boolean isMostrarDetalle() {
		return mostrarDetalle;
	}

	public void setMostrarDetalle(boolean mostrarDetalle) {
		this.mostrarDetalle = mostrarDetalle;
	}
	
 }