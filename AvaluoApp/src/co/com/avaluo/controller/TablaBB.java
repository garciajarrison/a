package co.com.avaluo.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.avaluo.common.EnumSessionAttributes;
import co.com.avaluo.common.ListasGenericas;
import co.com.avaluo.common.Util;
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
	private Tablas selectedTablas = new Tablas();
	private Tablas detalleList = new Tablas();
	private DetalleTabla detalle = new DetalleTabla();
	private DetalleTabla selectedDetalle = new DetalleTabla();
	private List<Tablas> entityList;
	private Usuario usuario;
	private Util util;
	private ListasGenericas listasGenericas;
	private boolean mostrarDetalle;
	
	public TablaBB() {
		util = Util.getInstance();
		usuario = (Usuario) util.getSessionAttribute(EnumSessionAttributes.USUARIO);
		listasGenericas = ListasGenericas.getInstance();
		mostrarDetalle = false;
		cargarListaTablas();
	}
	
	@PostConstruct
	public void validarSession() {
		util.validarSession();
	}
	
	private void cargarListaTablas() {
		entityList = tablaService.getTablas(usuario.getEmpresa().getId());
		if(entityList == null)
			entityList = new ArrayList<>();
	}
	
	public void addEntity() {
		try {
			if(validar(tablas)){
				boolean guardar = true;
				//Validamos que no exista una tabla con esa configuracion
				for(Tablas tbl : entityList) {
					if(tbl.getTipo().equals(tablas.getTipo()) &&
							tbl.getNombre().equals(tablas.getNombre())
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
					util.actualizarPF("formulario");
					tablas = new Tablas();
				}else {
					util.actualizarPF("growl");
				}
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
			if(validar(selectedTablas)){
				getTablaService().updateTabla(selectedTablas);
				util.mostrarMensajeKey("exito.actualizar");  
				cargarListaTablas();
				util.actualizarPF("formulario");
			}else {
				util.actualizarPF("growl");
			}
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
			util.mostrarErrorKey("tabla.error.eliminando");
		} 	
	}
	
	private boolean validar(Tablas tbl) {
		boolean continuar = true;
		
		if(util.validaNuloVacio(tbl.getTipo())) {
			util.mostrarErrorKey("javax.faces.component.UIInput.REQUIRED", util.getMessage("tabla.tipo"));
			continuar = false;
		}
		
		if(util.validaNuloVacio(tbl.getNombre())) {
			util.mostrarErrorKey("javax.faces.component.UIInput.REQUIRED", util.getMessage("tabla.nombre"));
			continuar = false;
		}
		
		if(tbl.getConversion() == null || tbl.getConversion().compareTo(BigDecimal.ZERO) == -1) {
			util.mostrarErrorKey("javax.faces.component.UIInput.REQUIRED", util.getMessage("tabla.conversion"));
			continuar = false;
		}
		
		if(tbl.getBase() == null || tbl.getBase().compareTo(BigDecimal.ZERO) == -1) {
			util.mostrarErrorKey("javax.faces.component.UIInput.REQUIRED", util.getMessage("tabla.base"));
			continuar = false;
		}
		
		if(tbl.getGastos() == null || tbl.getGastos().compareTo(BigDecimal.ZERO) == -1) {
			util.mostrarErrorKey("javax.faces.component.UIInput.REQUIRED", util.getMessage("tabla.gasto"));
			continuar = false;
		}
		
		if(util.validaNuloVacio(tbl.getUom())) {
			util.mostrarErrorKey("javax.faces.component.UIInput.REQUIRED", util.getMessage("tabla.uom"));
			continuar = false;
		}
		
		if(util.validaNuloVacio(tbl.getUomAlt())) {
			util.mostrarErrorKey("javax.faces.component.UIInput.REQUIRED", util.getMessage("tabla.uomalt"));
			continuar = false;
		}
		
		if(tbl.getDiasDeTrabajo() == null || tbl.getDiasDeTrabajo() < 0) {
			util.mostrarErrorKey("javax.faces.component.UIInput.REQUIRED", util.getMessage("tabla.dias.trabajados"));
			continuar = false;
		}
		
		if(tbl.getMinimo() == null || tbl.getMinimo() < 0) {
			util.mostrarErrorKey("javax.faces.component.UIInput.REQUIRED", util.getMessage("tabla.minimo"));
			continuar = false;
		}
		
		return continuar;
	}
	
	public void addEntityDetalle() {
		try {
			if(validarDetalle(detalle)){
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
					util.actualizarPF("formulario");
				}else {
					util.actualizarPF("growl");
				}
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
			if(validarDetalle(selectedDetalle)){
				getTablaService().updateTablaDetalle(selectedDetalle);
				util.mostrarMensajeKey("exito.actualizar");  
				cargarListaTablas();
				util.actualizarPF("formulario");
			}else {
				util.actualizarPF("growl");
			}
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
			util.mostrarErrorKey("tabla.detalle.error.eliminando");
		} 	
	}
	
	private boolean validarDetalle(DetalleTabla tbl) {
		boolean continuar = true;
		
		if(tbl.getDesde() == null || tbl.getDesde() < 0) {
			util.mostrarErrorKey("javax.faces.component.UIInput.REQUIRED", util.getMessage("tabla.desde"));
			continuar = false;
		}
		
		if(tbl.getHasta() == null || tbl.getHasta() < 0) {
			util.mostrarErrorKey("javax.faces.component.UIInput.REQUIRED", util.getMessage("tabla.hasta"));
			continuar = false;
		}
		
		if(tbl.getPorcentajeAplicar() == null || tbl.getPorcentajeAplicar() < 0) {
			util.mostrarErrorKey("javax.faces.component.UIInput.REQUIRED", util.getMessage("tabla.porcentaje.aplicar"));
			continuar = false;
		}
		
		return continuar;
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

	public boolean isMostrarDetalle() {
		return mostrarDetalle;
	}

	public void setMostrarDetalle(boolean mostrarDetalle) {
		this.mostrarDetalle = mostrarDetalle;
	}
	
 }