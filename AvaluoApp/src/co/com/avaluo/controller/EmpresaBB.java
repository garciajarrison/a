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
import co.com.avaluo.model.entity.Estrato;
import co.com.avaluo.model.entity.Pais;
import co.com.avaluo.service.ICiudadService;
import co.com.avaluo.service.IEmpresaService;

@ManagedBean(name = "empresaBB")
@ViewScoped
public class EmpresaBB extends SpringBeanAutowiringSupport implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IEmpresaService empresaService;
	@Autowired
	private ICiudadService ciudadService;
	private List<Empresa> listaEmpresas;
	private List<Pais> listaPaises;
	private List<Departamento> listaDepartamentos;
	private List<Ciudad> listaCiudades;
	private Empresa empresa = new Empresa();
	private Empresa selectedEmpresa = new Empresa();
	private Util util;
	private ListasGenericas listasGenericas;
	
	public EmpresaBB() {
		util = Util.getInstance();
		listaPaises = ciudadService.getPaises();
		listaDepartamentos = new ArrayList<>();
		listaCiudades = new ArrayList<>();
		listasGenericas = ListasGenericas.getInstance();
		cargarListaEmpresas();
	}
	
	@PostConstruct
	public void validarSession() {
		util.validarSession();
	}
	
	private void cargarListaEmpresas() {
		listaEmpresas = getEmpresaService().getEmpresas();
		if(listaEmpresas == null)
			listaEmpresas = new ArrayList<>();
	}
	
	public void addEntity() {
		try {
			boolean guardar = true;
			//Validamos que no exista un estrato con esa configuracion
			for(Empresa estr : listaEmpresas) {
				empresa.setIdentificacion(empresa.getIdentificacion().trim());
				empresa.setTipoIdentificacion(empresa.getTipoIdentificacion().trim());
				if(estr.getTipoIdentificacion().equals(empresa.getTipoIdentificacion()) &&
						estr.getIdentificacion().equals(empresa.getIdentificacion()) ) {
					guardar = false;
					util.mostrarErrorKey("empresa.ya.existe");
				}
			}
			
			if(guardar) {
				getEmpresaService().addEmpresa(empresa);
				this.cargarListaEmpresas();
				util.mostrarMensajeKey("exito.guardar"); 
				util.ejecutarPF("PF('dlgAgregar').hide();");
				util.actualizarPF("formulario");
				empresa = new Empresa();
				
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
			getEmpresaService().updateEmpresa(selectedEmpresa);
			util.mostrarMensajeKey("exito.actualizar");  
			cargarListaEmpresas();
			util.actualizarPF("formulario");
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarErrorKey("error.actualizar"); 
		} 	
	}
	
	public void deleteEntity() {
		try {
			getEmpresaService().deleteEmpresa(selectedEmpresa);
			util.mostrarMensajeKey("exito.eliminar");
			cargarListaEmpresas();
			util.actualizarPF("formulario");
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarErrorKey("error.eliminando");
		} 	
		
	}
	
	public void cargarListasDependientesUpdate() {
		cargarListasDependientes(selectedEmpresa.getCiudad().getDepartamento().getPais().getId(), 
				selectedEmpresa.getCiudad().getDepartamento().getId());
	}
	
	public void cargarListasDependientes() {
		cargarListasDependientes(empresa.getCiudad().getDepartamento().getPais().getId(), 
				empresa.getCiudad().getDepartamento().getId());
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

	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public void setListaEmpresas(List<Empresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}

	public IEmpresaService getEmpresaService() {
		return empresaService;
	}

	public void setEmpresaService(IEmpresaService empresaService) {
		this.empresaService = empresaService;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Empresa getSelectedEmpresa() {
		return selectedEmpresa;
	}

	public void setSelectedEmpresa(Empresa selectedEmpresa) {
		this.selectedEmpresa = selectedEmpresa;
		cargarListasDependientesUpdate();
	}

	public ListasGenericas getListasGenericas() {
		return listasGenericas;
	}

	public void setListasGenericas(ListasGenericas listasGenericas) {
		this.listasGenericas = listasGenericas;
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

	public ICiudadService getCiudadService() {
		return ciudadService;
	}

	public void setCiudadService(ICiudadService ciudadService) {
		this.ciudadService = ciudadService;
	}
	
 }