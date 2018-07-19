package co.com.avaluo.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

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
import co.com.avaluo.model.entity.DetalleCotizacion;
import co.com.avaluo.model.entity.DetalleTabla;
import co.com.avaluo.model.entity.Empresa;
import co.com.avaluo.model.entity.Estrato;
import co.com.avaluo.model.entity.Propiedad;
import co.com.avaluo.model.entity.Rol;
import co.com.avaluo.model.entity.Tablas;
import co.com.avaluo.model.entity.TipoPropiedad;
import co.com.avaluo.model.entity.Usuario;
import co.com.avaluo.service.ICiudadService;
import co.com.avaluo.service.ICotizacionService;
import co.com.avaluo.service.IDepartamentoService;
import co.com.avaluo.service.IEmpresaService;
import co.com.avaluo.service.IEstratoService;
import co.com.avaluo.service.IPropiedadService;
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
	@Autowired
	private IPropiedadService propiedadService;
	
	private Cotizacion cotizacion = new Cotizacion();
	private DetalleCotizacion detCotizacion = new DetalleCotizacion();
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


	private Propiedad selectedPropiedad;

	private Propiedad infPropiedad = new Propiedad();
	private Map<String,Integer> listaTablas = new HashMap<String, Integer>();
	private Map<String,Integer> listaTipoPropiedad = new HashMap<String, Integer>();
	private List<TipoPropiedad> listaTipoPropiedades;
	private List<Propiedad> listaPropiedades ;

	private Map<String,Integer> listaCiudad = new HashMap<String, Integer>();

	private List<Ciudad> listaCiudades;
	private Map<String,Integer> listaDepartamento = new HashMap<String, Integer>();
	private List<Departamento> listaDepartamentos;
	private Map<String,Integer> listaEstrato = new HashMap<String, Integer>();
	private List<Estrato> listaEstratos;

	private Usuario usuarioExiste = new Usuario();
	private boolean skip;	
	private Util util;

	
	
	public CotizacionAdmBB() {

		cotizacion = new Cotizacion();
		Empresa empresa =new Empresa();
		Usuario clien = new Usuario();
		cotizacion.setEmpresa(empresa);
		cotizacion.setUsuario(clien);
		util = Util.getInstance();
		usuario = (Usuario) util.getSessionAttribute(EnumSessionAttributes.USUARIO);
		listaTipoDocumentos=ListasGenericas.getInstance().getListaTiposDocumento();
		listaCiudades = ciudadService.getEntitys();
		listaPropiedades =  new ArrayList<Propiedad>();
		nuevaCotizacion();
		if(entityList == null)
			entityList = new ArrayList<>();



	}
	
	public void nuevaCotizacion() {
		this.cotizacion = new Cotizacion();
		Usuario usu = new Usuario();
		cotizacion.setEmpresa(usuario.getEmpresa());
		cotizacion.setUsuario(usu);
		
		this.detCotizacion = new DetalleCotizacion();
		detCotizacion.setCotizacion(cotizacion);
		Tablas tabla = new Tablas();
		Propiedad prop = new Propiedad();
		TipoPropiedad tprop = new TipoPropiedad() ;
		prop.setTipoPropiedad(tprop);
		detCotizacion.setPropiedad(prop);
		prop.setTablas(tabla);
		infPropiedad.setTipoPropiedad(tprop);
		Ciudad ciud = new Ciudad();
		Estrato est = new Estrato();
		infPropiedad.setTablas(tabla);
		infPropiedad.setCiudad(ciud);
		infPropiedad.setEstrato(est);
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
	
	
	public void addCliente() {
		try {
			boolean guardar = true;
			usuarioExiste = getUsuarioService().consultaIdentificacion(cotizacion.getUsuario().getIdentificacion(), usuario.getEmpresa().getId(), 3);
			if (usuarioExiste != null && usuarioExiste.getNombre() != null) {
				guardar = false;
				util.mostrarErrorKey("cotizacion.cliente.ya.existe");
			}
			
			if(guardar) {
				Rol rol = new Rol();
				rol.setId(3);
				cotizacion.getUsuario().setEmpresa(usuario.getEmpresa());
				cotizacion.getUsuario().setRol(rol);
				cotizacion.getUsuario().setEstado(true);
				cotizacion.getUsuario().setLenguaje("ES");
				getUsuarioService().addEntity(cotizacion.getUsuario());
				util.mostrarMensajeKey("exito.guardar"); 
				util.actualizarPF("formulario");
			}else {
				util.actualizarPF("growl");
			}
			
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarErrorKey("error.guardar");  
		} 	
		
	}
	
	public void addPropiedad() {
		
		infPropiedad.setUsuario(usuario);
		infPropiedad.setUnidadMedida("m2");
		infPropiedad.setValorMedida(new BigDecimal(500));
		
		Estrato est = new Estrato();
		TipoPropiedad tprop = new TipoPropiedad();
		Tablas tablas = new Tablas();
		
		est=getEstratoService().getEntityById(infPropiedad.getEstrato().getId());
		tprop=getPropertyService().getEntityById(infPropiedad.getTipoPropiedad().getId());
		tablas = getTablasService().getTablaById(infPropiedad.getTablas().getId());
		infPropiedad.setEstrato(est);
		infPropiedad.setTipoPropiedad(tprop);
		infPropiedad.setTablas(tablas);
		listaPropiedades.add(infPropiedad);
		limpiarPropiedad();
		
		
		util.actualizarPF("formulario");
		util.mostrarMensajeKey("cotizacion.propiedad.agregada"); 
	}
	
	public void limpiarPropiedad() {
		infPropiedad = new Propiedad();
		TipoPropiedad tprop = new TipoPropiedad() ;
		Tablas tablas = new Tablas();
		
		infPropiedad.setTipoPropiedad(tprop);
		infPropiedad.setTablas(tablas);		
		
		Ciudad ciud = new Ciudad();
		Estrato est = new Estrato();
		infPropiedad.setCiudad(ciud);
		infPropiedad.setEstrato(est);	
		
		
	}
	
	public void cotizar() {
		BigDecimal resultado = new BigDecimal(0);
		BigDecimal valorCotizacion = new BigDecimal(0);
		
		BigDecimal tax = new BigDecimal(0);
		for (Propiedad p : listaPropiedades) {
			//Tablas t=p.getTablas();
			for (DetalleTabla detTabla : p.getTablas().getDetalleTablas()) {
				BigDecimal delta = new BigDecimal(detTabla.getHasta()  - detTabla.getDesde());
				BigDecimal used;
				BigDecimal convert;
				BigDecimal mt2 = p.getValorMedida();
				BigDecimal totmt2 = p.getValorMedida();
				if (p.getUnidadMedida().equals("m2")) {
				  convert = new BigDecimal(1);	
				}
				else {
					if (p.getUnidadMedida().equals("ha")) {
						 convert = new BigDecimal(10000);		
					}
					else {
						 convert = new BigDecimal(6400);
					}
				}
				
				mt2 = mt2.multiply(convert);
				
				
				int res;
				res = mt2.compareTo(delta);
				
				if (res == 1) {
	                used = delta;
	                mt2 = mt2.subtract(delta);
	            } else {
	                used = mt2;
	                mt2 = new BigDecimal(0);
	            }
				
				BigDecimal resta = new BigDecimal(detTabla.getHasta()  - detTabla.getDesde());
				int compara1; //detTabla.getHasta()  - detTabla.getDesde() >= used
				compara1 = resta.compareTo(used);
				int compara2; //totmt2 >= detTabla.getDesde()  
				int compara3; //totmt2 <= detTabla.getHasta()
				compara2 = totmt2.compareTo(new BigDecimal(detTabla.getDesde()));
				compara3 = totmt2.compareTo(new BigDecimal(detTabla.getHasta()));
				if (compara1 >= 0 || (compara2 >= 0 && compara3 <= 0)) {
					
	                BigDecimal baseapply = p.getTablas().getBase().divide(new BigDecimal(30));
	                tax = new BigDecimal(detTabla.getPorcentajeAplicar()).divide(new BigDecimal(100)).multiply(baseapply);
	                
	                
	                
	                if (used.compareTo(BigDecimal.ZERO) == 0)
	                
	                    resultado = new BigDecimal(0);
	                } else {
	                    if (used.compareTo(BigDecimal.ZERO) != 0) {
	                        resultado = tax.multiply(used);
	                    } else {
	                    	resultado = new BigDecimal(0);
	                    }
	                }
				valorCotizacion = valorCotizacion.add(resultado);
	            }
				
                	 
                	 valorCotizacion = valorCotizacion.multiply(new BigDecimal(100).add( p.getEstrato().getPorcentaje())).divide(new BigDecimal(100)) ;
                	 
                     //cotizacion = cotizacion * (100 + objEst.getPorcentaje()) / 100;
                	 valorCotizacion = valorCotizacion.divide(new BigDecimal(10000));
                	 valorCotizacion = valorCotizacion.multiply(new BigDecimal(10000));
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
		cliente = getUsuarioService().consultaIdentificacion(identif, usuario.getEmpresa().getId(), 3);
		if (cliente.getNombre() != null) {
			cotizacion.setUsuario(cliente);
		}
		else {
			Rol rol = new Rol();
			rol.setId(3);
			cotizacion.getUsuario().setNombre("");
			cotizacion.getUsuario().setTelefono("");
			cotizacion.getUsuario().setCelular("");
			cotizacion.getUsuario().setDireccion("");
			cotizacion.getUsuario().setEstado(false);
			cotizacion.getUsuario().setDireccion("");
			cotizacion.getUsuario().setCorreo("");
			cotizacion.getUsuario().setId(0);
			cotizacion.getUsuario().setRol(rol);
		}
		
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

	public Propiedad getInfPropiedad() {
		return infPropiedad;
	}

	public void setInfPropiedad(Propiedad infPropiedad) {
		this.infPropiedad = infPropiedad;
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

	public Map<String, Integer> getListaTipoPropiedad() {
		return listaTipoPropiedad;
	}

	public void setListaTipoPropiedad(Map<String, Integer> listaTipoPropiedad) {
		this.listaTipoPropiedad = listaTipoPropiedad;
	}

	/*public List<TipoPropiedad> getListaPropiedades() {
		return listaPropiedades;
	}

	public void setListaPropiedades(List<TipoPropiedad> listaPropiedades) {
		this.listaPropiedades = listaPropiedades;
	}*/

	public Map<String, Integer> getListaEstrato() {
		listaEstratos = new ArrayList<Estrato>();
		listaEstratos.addAll(getEstratoService().getEntitys(usuario.getEmpresa().getId()));
		for (Estrato estrato : listaEstratos) {
			listaEstrato.put(estrato.getNombre(),estrato.getId());
		}
		

		return listaEstrato;
	}

	public void setListaEstrato(Map<String, Integer> listaEstrato) {
		this.listaEstrato = listaEstrato;
	}

	public List<Estrato> getListaEstratos() {
		return listaEstratos;
	}

	public void setListaEstratos(List<Estrato> listaEstratos) {
		this.listaEstratos = listaEstratos;
	}

	public Map<String, Integer> getListaCiudad() {
		listaCiudades = new ArrayList<Ciudad>();
		listaCiudades.addAll(getCiudadService().getEntitys());
		for (Ciudad ciudad : listaCiudades) {
			listaCiudad.put(ciudad.getNombre(), ciudad.getId() );
		}
		
		
		
		return listaCiudad;
	}

	public void setListaCiudad(Map<String, Integer> listaCiudad) {
		this.listaCiudad = listaCiudad;
	}


	public Map<String, Integer> getListaDepartamento() {
		listaDepartamentos = new ArrayList<Departamento>();
		listaDepartamentos.addAll(getDepartamentoService().getEntitys());
		for (Departamento departamento : listaDepartamentos) {
			listaDepartamento.put(departamento.getNombre(), departamento.getId() );
		}
		
		return listaDepartamento;
	}

	public void setListaDepartamento(Map<String, Integer> listaDepartamento) {
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

	public IPropiedadService getPropiedadService() {
		return propiedadService;
	}

	public void setPropiedadService(IPropiedadService propiedadService) {
		this.propiedadService = propiedadService;
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


	public DetalleCotizacion getDetCotizacion() {
		return detCotizacion;
	}

	public void setDetCotizacion(DetalleCotizacion detCotizacion) {
		this.detCotizacion = detCotizacion;
	}

	public Usuario getUsuarioExiste() {
		return usuarioExiste;
	}

	public void setUsuarioExiste(Usuario usuarioExiste) {
		this.usuarioExiste = usuarioExiste;
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

	public Map<String, Integer> getListaTablas() {
		tablas = new ArrayList<Tablas>();
		tablas.addAll(getTablasService().getTablas(usuario.getEmpresa().getId()));
		for (Tablas tabla : tablas) {
			listaTablas.put(tabla.getNombre(),tabla.getId());
		}
		return listaTablas;
	}
	
	

	public void setListaTablas(Map<String, Integer> listaTablas) {
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

	public void onTableChange(int idTabla) {
		String tabla;
		Tablas tablas = new Tablas();
		tablas = getTablasService().getTablaById(idTabla);
		tabla = tablas.getTipo();
        if(tabla !=null && !tabla.equals("")) {
        	listaTipoPropiedades = new ArrayList<TipoPropiedad>();
        	listaTipoPropiedades.addAll(getPropertyService().getEntitys(tabla, usuario.getEmpresa().getId()));
			for (TipoPropiedad property : listaTipoPropiedades) {
				listaTipoPropiedad.put(property.getTipoVivienda(),property.getId());
			}
	    }
        else
        	listaTipoPropiedad =  new HashMap<String,Integer>();
        
	}

	public void onRowSelect(SelectEvent event) {
    }
 
    public void onRowUnselect(UnselectEvent event) {
    }

	public Propiedad getSelectedPropiedad() {
		return selectedPropiedad;
	}

	public void setSelectedPropiedad(Propiedad selectedPropiedad) {
		this.selectedPropiedad = selectedPropiedad;
	}	
 }