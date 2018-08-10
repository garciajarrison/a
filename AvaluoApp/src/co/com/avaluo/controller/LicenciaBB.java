package co.com.avaluo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.avaluo.common.EnumSessionAttributes;
import co.com.avaluo.common.Util;
import co.com.avaluo.model.entity.Licencia;
import co.com.avaluo.model.entity.Usuario;
import co.com.avaluo.service.ILicenciaService;

@ManagedBean(name = "licenciaBB")
@ViewScoped
public class LicenciaBB extends SpringBeanAutowiringSupport implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ILicenciaService licenciaService;
	private List<Licencia> listaLicencias;
	private Licencia licencia = new Licencia();
	private Licencia selectedLicencia;
	private Util util;
	private Usuario usuario;
	
	public LicenciaBB() {
		util = Util.getInstance();
		usuario = (Usuario) util.getSessionAttribute(EnumSessionAttributes.USUARIO);
		cargarListaLicencias();
	}
	
	@PostConstruct
	public void validarSession() {
		util.validarSession();
	}
	
	private void cargarListaLicencias() {
		listaLicencias = getLicenciaService().getLicencias();
		if(listaLicencias == null)
			listaLicencias = new ArrayList<>();
	}
	
	public void addEntity() {
		try {
			boolean guardar = true;
			//Validamos que no exista un estrato con esa configuracion
			for(Licencia estr : listaLicencias) {
				licencia.setNombre(licencia.getNombre().trim());
				if(estr.getNombre().equals(licencia.getNombre())) {
					guardar = false;
					util.mostrarErrorKey("licencia.ya.existe");
				}
			}
			
			if(guardar) {
				getLicenciaService().addLicencia(licencia);
				this.cargarListaLicencias();
				util.mostrarMensajeKey("exito.guardar"); 
				licencia = new Licencia();
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
			getLicenciaService().updateLicencia(selectedLicencia);
			util.mostrarMensajeKey("exito.actualizar");  
			cargarListaLicencias();
			util.actualizarPF("formulario");
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarErrorKey("error.actualizar"); 
		} 	
	}
	
	public void deleteEntity() {
		try {
			getLicenciaService().deleteLicencia(selectedLicencia);
			util.mostrarMensajeKey("exito.eliminar");
			cargarListaLicencias();
			util.actualizarPF("formulario");
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarErrorKey("error.eliminando");
		} 	
		
	}

	public ILicenciaService getLicenciaService() {
		return licenciaService;
	}

	public void setLicenciaService(ILicenciaService licenciaService) {
		this.licenciaService = licenciaService;
	}

	public List<Licencia> getListaLicencias() {
		return listaLicencias;
	}

	public void setListaLicencias(List<Licencia> listaLicencias) {
		this.listaLicencias = listaLicencias;
	}

	public Licencia getLicencia() {
		return licencia;
	}

	public void setLicencia(Licencia licencia) {
		this.licencia = licencia;
	}

	public Licencia getSelectedLicencia() {
		return selectedLicencia;
	}

	public void setSelectedLicencia(Licencia selectedLicencia) {
		this.selectedLicencia = selectedLicencia;
	}

	
 }