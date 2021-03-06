package co.com.avaluo.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.avaluo.common.EnumSessionAttributes;
import co.com.avaluo.common.ListasGenericas;
import co.com.avaluo.common.Util;
import co.com.avaluo.model.entity.TipoPropiedad;
import co.com.avaluo.model.entity.Usuario;
import co.com.avaluo.service.IPropiedadService;

@ManagedBean(name = "tipoPropiedadBB")
@ViewScoped
public class TipoPropiedadBB extends SpringBeanAutowiringSupport implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private IPropiedadService propiedadService;
	
	private TipoPropiedad tipoPropiedad = new TipoPropiedad();
	private TipoPropiedad selectedTipoPropiedad = new TipoPropiedad();
	private List<TipoPropiedad> entityList;
	private Usuario usuario;
	private Util util;
	private List<SelectItem> listaTipoPropiedad;
	
	public TipoPropiedadBB() {
		util = Util.getInstance();
		usuario = (Usuario) util.getSessionAttribute(EnumSessionAttributes.USUARIO);
		listaTipoPropiedad = ListasGenericas.getInstance().getListaTipoPropiedad();
		cargarListaTipoPropiedad();
	}
	
	@PostConstruct
	public void validarSession() {
		util.validarSession();
	}
	
	private void cargarListaTipoPropiedad() {
		entityList = propiedadService.getListaTipoPropiedad(usuario.getEmpresa().getId());
		if(entityList == null)
			entityList = new ArrayList<>();
	}
	
	public void addEntity() {
		try {
			if(validar(tipoPropiedad)){
				boolean guardar = true;
				//Validamos que no exista un estrato con esa configuracion
				for(TipoPropiedad estr : entityList) {
					if(estr.getTipoPropiedad().equals(tipoPropiedad.getTipoPropiedad()) &&
							estr.getTipoVivienda().equals(tipoPropiedad.getTipoVivienda())) {
						guardar = false;
						util.mostrarErrorKey("tipo.propiedad.ya.existe");
					}
				}
				
				if(guardar) {
					tipoPropiedad.setEmpresa(usuario.getEmpresa());
					getPropiedadService().addTipoPropiedad(tipoPropiedad);
					this.cargarListaTipoPropiedad();
					util.mostrarMensajeKey("exito.guardar"); 
					tipoPropiedad = new TipoPropiedad();
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

	public void updateEntity() {
		try {
			if(validar(selectedTipoPropiedad)){
				getPropiedadService().updateTipoPropiedad(selectedTipoPropiedad);
				util.mostrarMensajeKey("exito.actualizar");  
				cargarListaTipoPropiedad();
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
			getPropiedadService().deleteTipoPropiedad(selectedTipoPropiedad);
			util.mostrarMensajeKey("exito.eliminar");
			cargarListaTipoPropiedad();
			util.actualizarPF("formulario");
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarErrorKey("tipo.propiedad.error.eliminando");
		} 	
	}
	
	private boolean validar(TipoPropiedad tp) {
		boolean continuar = true;
		
		if(util.validaNuloVacio(tp.getTipoPropiedad())) {
			util.mostrarErrorKey("javax.faces.component.UIInput.REQUIRED", util.getMessage("tipo.propiedad"));
			continuar = false;
		}
		
		if(util.validaNuloVacio(tp.getTipoVivienda())) {
			util.mostrarErrorKey("javax.faces.component.UIInput.REQUIRED", util.getMessage("tipo.propiedad.tipo.vivienda"));
			continuar = false;
		}
		
		if(tp.getIncremento() == null || tp.getIncremento().compareTo(BigDecimal.ZERO) == -1) {
			util.mostrarErrorKey("javax.faces.component.UIInput.REQUIRED", util.getMessage("tipo.propiedad.incremento"));
			continuar = false;
		}
		
		return continuar;
	}

	public IPropiedadService getPropiedadService() {
		return propiedadService;
	}

	public void setPropiedadService(IPropiedadService propiedadService) {
		this.propiedadService = propiedadService;
	}

	public TipoPropiedad getTipoPropiedad() {
		return tipoPropiedad;
	}

	public void setTipoPropiedad(TipoPropiedad tipoPropiedad) {
		this.tipoPropiedad = tipoPropiedad;
	}

	public TipoPropiedad getSelectedTipoPropiedad() {
		if(selectedTipoPropiedad == null)
			selectedTipoPropiedad = new TipoPropiedad();
		return selectedTipoPropiedad;
	}

	public void setSelectedTipoPropiedad(TipoPropiedad selectedTipoPropiedad) {
		this.selectedTipoPropiedad = selectedTipoPropiedad;
	}

	public List<TipoPropiedad> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<TipoPropiedad> entityList) {
		this.entityList = entityList;
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

	public List<SelectItem> getListaTipoPropiedad() {
		return listaTipoPropiedad;
	}

	public void setListaTipoPropiedad(List<SelectItem> listaTipoPropiedad) {
		this.listaTipoPropiedad = listaTipoPropiedad;
	}
	
 }