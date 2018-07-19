package co.com.avaluo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.hibernate.service.spi.InjectService;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.avaluo.common.EnumSessionAttributes;
import co.com.avaluo.common.ListasGenericas;
import co.com.avaluo.common.Util;
import co.com.avaluo.model.entity.Ciudad;
import co.com.avaluo.model.entity.Cotizacion;
import co.com.avaluo.model.entity.Departamento;
import co.com.avaluo.model.entity.Empresa;
import co.com.avaluo.model.entity.Estrato;
import co.com.avaluo.model.entity.Propiedad;
import co.com.avaluo.model.entity.Tablas;
import co.com.avaluo.model.entity.TipoPropiedad;
import co.com.avaluo.model.entity.Usuario;
import co.com.avaluo.service.EstratoService;
import co.com.avaluo.service.ICiudadService;
import co.com.avaluo.service.ICotizacionService;
import co.com.avaluo.service.IDepartamentoService;
import co.com.avaluo.service.IEmpresaService;
import co.com.avaluo.service.IEstratoService;
import co.com.avaluo.service.ITablasService;
import co.com.avaluo.service.ITipoPropiedadService;
import co.com.avaluo.service.IUsuarioService;


@ManagedBean(name = "cotizacionAdmBB")
@ViewScoped
public class CotizacionAdmBB extends SpringBeanAutowiringSupport implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICotizacionService cotizacionService;
	
	@Autowired
	private ITipoPropiedadService propertyService;
	@Autowired
	private ITablasService tablasService;
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IEstratoService estratoService;	
	@Autowired
	private IEmpresaService empresaService;
	@Autowired
	private ICiudadService ciudadService;
	@Autowired
	private IDepartamentoService DepartamentoService;
	
	private Cotizacion cotizacion = new Cotizacion();
	private Cotizacion selectedCotizacion = new Cotizacion();
	private List<Cotizacion> entityList;
	private Usuario usuario;
	private Usuario cliente = new Usuario();
	//private Empresa empresa = new Usuario();
	private List<SelectItem> listaTipoDocumentos;
	private String tabla;
	private String propiedad;
	private String estrato;
	private String ciudad;
	private String departamento;
	private String identificacion;
	private List<Tablas> tablas;
	
	private Propiedad infoPropiedad = new Propiedad();
	private Map<String,String> listaTablas = new HashMap<String, String>();
	private Map<String,String> listaTipoPropiedad = new HashMap<String, String>();
	private List<TipoPropiedad> listaPropiedades;
	private Map<String,String> listaCiudad = new HashMap<String, String>();
	private List<Ciudad> listaCiudades;
	private Map<String,String> listaDepartamento = new HashMap<String, String>();
	private List<Departamento> listaDepartamentos;
	private Map<String,String> listaEstrato = new HashMap<String, String>();
	private List<Estrato> listaEstratos;
	private boolean skip;	
	private Util util;

	
	
	public CotizacionAdmBB() {
		util = Util.getInstance();
		usuario = (Usuario) util.getSessionAttribute(EnumSessionAttributes.USUARIO);
		listaTipoDocumentos=ListasGenericas.getInstance().getListaTiposDocumento();
		listaCiudades = ciudadService.getEntitys();
		if(entityList == null)
			entityList = new ArrayList<>();

	}
	
	public void addEntity() {
		try {
			getCotizacionService().addEntity(cotizacion);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Added!", "Message: "));  
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "D'oh!", "Message: ")); 
		} 	
		
	}

	public void updateEntity() {
		try {
			getCotizacionService().updateEntity(selectedCotizacion);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Update!", "Message: "));  
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "D'oh!", "Message: ")); 
		} 	
		
	}
	
	public void deleteEntity() {
		try {
			getCotizacionService().deleteEntity(selectedCotizacion);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete!", "Message: "));  
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "D'oh!", "Message: ")); 
		} 	
		
	}

	
	public void onconsultaCliente(String identif) { 
		//cliente = getEmpresaService().consultaIdentificacion(identif, usuario.getEmpresa().getId(), 3);
		
	}
		
	
	public List<Cotizacion> getEntityList() {
		entityList = getCotizacionService().getEntitys();
		return entityList;
	}
	
	public ICotizacionService getCotizacionService() {
		return cotizacionService;
	}

	public void setCotizacionService(ICotizacionService cotizacionService) {
		this.cotizacionService = cotizacionService;
	}

	public ITipoPropiedadService getPropertyService() {
		return propertyService;
	}

	public void setPropertyService(ITipoPropiedadService propertyService) {
		this.propertyService = propertyService;
	}

	public ITablasService getTablasService() {
		return tablasService;
	}

	public void setTablasService(ITablasService tablasService) {
		this.tablasService = tablasService;
	}

	public IUsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(IUsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public Propiedad getInfoPropiedad() {
		return infoPropiedad;
	}

	public void setInfoPropiedad(Propiedad infoPropiedad) {
		this.infoPropiedad = infoPropiedad;
	}

	public IEmpresaService getEmpresaService() {
		return empresaService;
	}

	public void setEmpresaService(IEmpresaService empresaService) {
		this.empresaService = empresaService;
	}

	public IEstratoService getEstratoService() {
		return estratoService;
	}

	public void setEstratoService(IEstratoService estratoService) {
		this.estratoService = estratoService;
	}

	public Cotizacion getSelectedCotizacion() {
		return selectedCotizacion;
	}

	public void setSelectedCotizacion(Cotizacion selectedCotizacion) {
		this.selectedCotizacion = selectedCotizacion;
	}

	public void setEntityList(List<Cotizacion> entityList) {
		this.entityList = entityList;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(String propiedad) {
		this.propiedad = propiedad;
	}

	public String getEstrato() {
		return estrato;
	}

	public void setEstrato(String estrato) {
		this.estrato = estrato;
	}

	public List<SelectItem> getListaTipoDocumentos() {
		return listaTipoDocumentos;
	}

	public void setListaTipoDocumentos(List<SelectItem> listaTipoDocumentos) {
		this.listaTipoDocumentos = listaTipoDocumentos;
	}

	public Map<String, String> getListaTipoPropiedad() {
		return listaTipoPropiedad;
	}

	public void setListaTipoPropiedad(Map<String, String> listaTipoPropiedad) {
		this.listaTipoPropiedad = listaTipoPropiedad;
	}

	public List<TipoPropiedad> getListaPropiedades() {
		return listaPropiedades;
	}

	public void setListaPropiedades(List<TipoPropiedad> listaPropiedades) {
		this.listaPropiedades = listaPropiedades;
	}

	public Map<String, String> getListaEstrato() {
		listaEstratos = new ArrayList<Estrato>();
		listaEstratos.addAll(getEstratoService().getEntitys(usuario.getEmpresa().getId()));
		for (Estrato estrato : listaEstratos) {
			listaEstrato.put(estrato.getNombre(),String.valueOf(estrato.getId()));
		}
		

		return listaEstrato;
	}

	public void setListaEstrato(Map<String, String> listaEstrato) {
		this.listaEstrato = listaEstrato;
	}

	public List<Estrato> getListaEstratos() {
		return listaEstratos;
	}

	public void setListaEstratos(List<Estrato> listaEstratos) {
		this.listaEstratos = listaEstratos;
	}

	public Map<String, String> getListaCiudad() {
		listaCiudades = new ArrayList<Ciudad>();
		listaCiudades.addAll(getCiudadService().getEntitys());
		for (Ciudad ciudad : listaCiudades) {
			listaCiudad.put(ciudad.getNombre(), String.valueOf(ciudad.getId() ));
		}
		
		return listaCiudad;
	}

	public void setListaCiudad(Map<String, String> listaCiudad) {
		this.listaCiudad = listaCiudad;
	}


	public Map<String, String> getListaDepartamento() {
		listaDepartamentos = new ArrayList<Departamento>();
		listaDepartamentos.addAll(getDepartamentoService().getEntitys());
		for (Departamento departamento : listaDepartamentos) {
			listaDepartamento.put(departamento.getNombre(), String.valueOf(departamento.getId() ));
		}
		
		return listaDepartamento;
	}

	public void setListaDepartamento(Map<String, String> listaDepartamento) {
		this.listaDepartamento = listaDepartamento;
	}


	public ICiudadService getCiudadService() {
		return ciudadService;
	}

	public void setCiudadService(ICiudadService ciudadService) {
		this.ciudadService = ciudadService;
	}

	public IDepartamentoService getDepartamentoService() {
		return DepartamentoService;
	}

	public void setDepartamentoService(IDepartamentoService departamentoService) {
		DepartamentoService = departamentoService;
	}

	public void setListaCiudades(List<Ciudad> listaCiudades) {
		this.listaCiudades = listaCiudades;
	}

	public void setListaDepartamentos(List<Departamento> listaDepartamentos) {
		this.listaDepartamentos = listaDepartamentos;
	}

	public List<Ciudad> getListaCiudades() {
		return listaCiudades;
	}

	public List<Departamento> getListaDepartamentos() {
		return listaDepartamentos;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public Util getUtil() {
		return util;
	}

	public void setUtil(Util util) {
		this.util = util;
	}

	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public Map<String, String> getListaTablas() {
		tablas = new ArrayList<Tablas>();
		tablas.addAll(getTablasService().getTablas(usuario.getEmpresa().getId()));
		for (Tablas tabla : tablas) {
			listaTablas.put(tabla.getNombre(),tabla.getTipo());
		}
		return listaTablas;
	}
	
	

	public void setListaTablas(Map<String, String> listaTablas) {
		this.listaTablas = listaTablas;
	}

	public Cotizacion getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}

	public List<Tablas> getTablas() {
		return tablas;
	}

	public void setTablas(List<Tablas> tablas) {
		this.tablas = tablas;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }

	public void onTableChange(String tabla) {
        if(tabla !=null && !tabla.equals("")) {
        	listaPropiedades = new ArrayList<TipoPropiedad>();
        	listaPropiedades.addAll(getPropertyService().getEntitys(tabla, usuario.getEmpresa().getId()));
			for (TipoPropiedad property : listaPropiedades) {
				listaTipoPropiedad.put(property.getTipoVivienda(),property.getTipoVivienda());
			}
	    }
        else
        	listaTipoPropiedad =  new HashMap<String,String>();
        
	}

	public void onRowSelect(SelectEvent event) {
    }
 
    public void onRowUnselect(UnselectEvent event) {
    }	
 }