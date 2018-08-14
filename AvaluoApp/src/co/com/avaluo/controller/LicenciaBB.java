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

import co.com.avaluo.common.ListasGenericas;
import co.com.avaluo.common.Util;
import co.com.avaluo.model.entity.Ciudad;
import co.com.avaluo.model.entity.Departamento;
import co.com.avaluo.model.entity.Empresa;
import co.com.avaluo.model.entity.Licencia;
import co.com.avaluo.model.entity.Pais;
import co.com.avaluo.service.ICiudadService;
import co.com.avaluo.service.IEmpresaService;
import co.com.avaluo.service.ILicenciaService;

@ManagedBean(name = "licenciaBB")
@ViewScoped
public class LicenciaBB extends SpringBeanAutowiringSupport implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ILicenciaService licenciaService;
	@Autowired
	private ICiudadService ciudadService;
	@Autowired
	private IEmpresaService empresaService;
	private List<Licencia> listaLicencias;
	private List<Pais> listaPaises;
	private List<Departamento> listaDepartamentos;
	private List<Ciudad> listaCiudades;
	private List<Empresa> listaEmpresas;
	private Licencia licencia = new Licencia();
	private ListasGenericas listasGenericas;
	private Licencia selectedLicencia = new Licencia();
	private Util util;
	
	public LicenciaBB() {
		util = Util.getInstance();
		listasGenericas = ListasGenericas.getInstance();
		listaPaises = ciudadService.getPaises();
		listaEmpresas = empresaService.getEmpresas();
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
				util.ejecutarPF("PF('dlgAgregar').hide();");
				util.actualizarPF("formulario");
				licencia = new Licencia();
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
	
	public void cargarListasDependientesUpdate() {
		cargarListasDependientes(selectedLicencia.getCiudad().getDepartamento().getPais().getId(), 
				selectedLicencia.getCiudad().getDepartamento().getId());
	}
	
	public void cargarListasDependientes() {
		cargarListasDependientes(licencia.getCiudad().getDepartamento().getPais().getId(), 
				licencia.getCiudad().getDepartamento().getId());
	}
	
	public void cargarListasDependientes(int pais, int departamento) {
		//Cargar departamentos
		if(pais > 0) {
			listaDepartamentos = ciudadService.getDepartamentos(pais);
		}else{
			listaDepartamentos = new ArrayList<>();
		}
		
		//Cargar ciudades
		if(departamento > 0) {
			listaCiudades = ciudadService.getCiudades(departamento);
		}else{
			listaCiudades = new ArrayList<>();
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
		cargarListasDependientesUpdate();
	}

	public ICiudadService getCiudadService() {
		return ciudadService;
	}

	public void setCiudadService(ICiudadService ciudadService) {
		this.ciudadService = ciudadService;
	}

	public List<Pais> getListaPaises() {
		return listaPaises;
	}

	public void setListaPaises(List<Pais> listaPaises) {
		this.listaPaises = listaPaises;
	}

	public List<Departamento> getListaDepartamentos() {
		return listaDepartamentos;
	}

	public void setListaDepartamentos(List<Departamento> listaDepartamentos) {
		this.listaDepartamentos = listaDepartamentos;
	}

	public List<Ciudad> getListaCiudades() {
		return listaCiudades;
	}

	public void setListaCiudades(List<Ciudad> listaCiudades) {
		this.listaCiudades = listaCiudades;
	}

	public IEmpresaService getEmpresaService() {
		return empresaService;
	}

	public void setEmpresaService(IEmpresaService empresaService) {
		this.empresaService = empresaService;
	}

	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public void setListaEmpresas(List<Empresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}

	public ListasGenericas getListasGenericas() {
		return listasGenericas;
	}

	public void setListasGenericas(ListasGenericas listasGenericas) {
		this.listasGenericas = listasGenericas;
	}

	
 }