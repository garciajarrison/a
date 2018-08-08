package co.com.avaluo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.avaluo.model.entity.Empresa;
import co.com.avaluo.service.IEmpresaService;

@ManagedBean(name = "empresaBB")
@ViewScoped
public class EmpresaBB extends SpringBeanAutowiringSupport implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IEmpresaService empresaService;
	private List<Empresa> listaEmpresas;
	
	public EmpresaBB() {
		listaEmpresas = new ArrayList<>();
	}

	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public void setListaEmpresas(List<Empresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}
	
	
	
 }