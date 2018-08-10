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

import co.com.avaluo.common.Util;
import co.com.avaluo.model.entity.Empresa;
import co.com.avaluo.service.IEmpresaService;

@ManagedBean(name = "empresaBB")
@ViewScoped
public class EmpresaBB extends SpringBeanAutowiringSupport implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IEmpresaService empresaService;
	private List<Empresa> listaEmpresas;
	private Empresa empresa = new Empresa();
	private Empresa selectedEmpresa;
	private Util util;
	
	public EmpresaBB() {
		util = Util.getInstance();
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
				empresa.setNombre(empresa.getNombre().trim());
				if(estr.getNombre().equals(empresa.getNombre())) {
					guardar = false;
					util.mostrarErrorKey("empresa.ya.existe");
				}
			}
			
			if(guardar) {
				getEmpresaService().addEmpresa(empresa);
				this.cargarListaEmpresas();
				util.mostrarMensajeKey("exito.guardar"); 
				empresa = new Empresa();
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
	}
	
 }