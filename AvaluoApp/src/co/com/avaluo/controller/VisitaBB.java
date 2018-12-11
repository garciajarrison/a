package co.com.avaluo.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

import org.primefaces.component.tabview.TabView;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.avaluo.common.CalcularCoordenadas;
import co.com.avaluo.common.EnumSessionAttributes;
import co.com.avaluo.common.ListasGenericas;
import co.com.avaluo.common.Util;
import co.com.avaluo.model.entity.Avaluos;
import co.com.avaluo.model.entity.Ciudad;
import co.com.avaluo.model.entity.Cotizacion;
import co.com.avaluo.model.entity.Departamento;
import co.com.avaluo.model.entity.DetalleCotizacion;
import co.com.avaluo.model.entity.DetalleTabla;
import co.com.avaluo.model.entity.Direcciones;
//import co.com.avaluo.model.entity.Direcciones;
import co.com.avaluo.model.entity.Empresa;
import co.com.avaluo.model.entity.Estrato;
import co.com.avaluo.model.entity.Pais;
import co.com.avaluo.model.entity.Propiedad;
import co.com.avaluo.model.entity.Propietarios;
import co.com.avaluo.model.entity.Rol;
import co.com.avaluo.model.entity.Tablas;
import co.com.avaluo.model.entity.TipoPropiedad;
import co.com.avaluo.model.entity.Usuario;
import co.com.avaluo.model.entity.Visitas;
import co.com.avaluo.service.ICiudadService;
import co.com.avaluo.service.ICotizacionService;
import co.com.avaluo.service.IDepartamentoService;
import co.com.avaluo.service.IDireccionesService;
import co.com.avaluo.service.IEmpresaService;
import co.com.avaluo.service.IPropiedadService;
import co.com.avaluo.service.IPropietariosService;
import co.com.avaluo.service.IUsuarioService;


@ManagedBean(name = "visitaBB")
@ViewScoped
public class VisitaBB extends SpringBeanAutowiringSupport implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICotizacionService cotizacionService;
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IEmpresaService empresaService;
	@Autowired
	private ICiudadService ciudadService;
	@Autowired
	private IDepartamentoService DepartamentoService;
	@Autowired
	private IPropiedadService propiedadService;
	@Autowired
	private IDireccionesService direccionService;
	@Autowired
	private IPropietariosService propietariosService;
	
	private Avaluos avaluo = new Avaluos();
	private Visitas visita = new Visitas();
	private Avaluos avaluoSelected = new Avaluos();
	private List<Avaluos> listaAvaluos;
	private Cotizacion cotizacion = new Cotizacion();
	private DetalleCotizacion detCotizacion = new DetalleCotizacion();
	private Cotizacion selectedCotizacion = new Cotizacion();
	private DetalleCotizacion selectedDetalle = new DetalleCotizacion();
	private List<Cotizacion> listaCotizaciones;
	private Usuario usuario;
	private Usuario usuarioCliente = new Usuario();
	private Usuario usuarioRemitente = new Usuario();
	private Usuario usuarioPropietario = new Usuario();
	private Propietarios propietario = new Propietarios();

	private List<Usuario> listaAvaluadores = new ArrayList<Usuario>();
	private SortedMap<String,Integer> listAvaluadores = new TreeMap<String, Integer>();
	
	//private Empresa empresa = new Usuario();
	private List<SelectItem> listaTipoDocumentos;
	private List<SelectItem> listaTipoVia;
	private List<SelectItem> listaPosicionVia;
	private List<SelectItem> listaUrbanizacion;
	private String tabla;
	private String propiedad;
	private String estrato;
	private String ciudad;
	private String departamento;
	private String pais;
	private String dpto;
	private String paisCot;
	private String identificacion;
	private List<Tablas> tablas;
	private Direcciones direccion = new Direcciones();
	private Departamento depart = new Departamento();
	private Pais paisC = new Pais();


	private Propiedad selectedPropiedad;

	private Propiedad infPropiedad = new Propiedad();
	private SortedMap<String,Integer> listaTablas = new TreeMap<String, Integer>();
	private SortedMap<String,Integer> listaTipoPropiedad = new TreeMap<String, Integer>();
	private List<TipoPropiedad> listaTipoPropiedades;
	private List<Propiedad> listaPropiedades = new ArrayList<Propiedad>();

	private SortedMap<String,Integer> listaTipoPropietario = new TreeMap<String, Integer>();
	private List<Propietarios> listaPropietarios = new ArrayList<Propietarios>();
	
	
	private SortedMap<String,Integer> listaCiudad = new TreeMap<String, Integer>();

	private List<Ciudad> listaCiudades;
	private SortedMap<String,Integer> listaDepartamento = new TreeMap<String, Integer>();
	private List<Departamento> listaDepartamentos;
	private SortedMap<String,Integer> listaPais = new TreeMap<String, Integer>();
	private List<Pais> listaPaises;
	private SortedMap<String,Integer> listaEstrato = new TreeMap<String, Integer>();
	private List<Estrato> listaEstratos;
	private List<DetalleCotizacion> listaDetCotizacion;
	
	private List<Cotizacion> listaCotizacion;
	private List<SelectItem> listaUnidadMedida;
	private int tabIndex = 0;

	private Usuario usuarioExiste = new Usuario();
	private boolean skip;	
	private Util util;
	private CalcularCoordenadas calc = new CalcularCoordenadas();
	private String direc;

	private StreamedContent file;
	
	
	public VisitaBB() {
		avaluo = new Avaluos();
		listaAvaluos = getCotizacionService().getAvaluos();
		cotizacion = new Cotizacion();
		Empresa empresa =new Empresa();
		Usuario clien = new Usuario();
		Ciudad ciudad = new Ciudad();
		cotizacion.setEmpresa(empresa);
		cotizacion.setUsuarioByClienteId(clien);
		cotizacion.setCiudad(ciudad);
		util = Util.getInstance();
		usuario = (Usuario) util.getSessionAttribute(EnumSessionAttributes.USUARIO);
		
		avaluo.setEmpresa(usuario.getEmpresa());
		avaluo.setUsuario(usuario);
		listaTipoDocumentos=ListasGenericas.getInstance().getListaTiposDocumento();
		listaCiudades = ciudadService.getEntitys();
		listaPropiedades =  new ArrayList<Propiedad>();
		listaDetCotizacion = new ArrayList<DetalleCotizacion>();
		listaUnidadMedida = ListasGenericas.getInstance().getListaUnidadMedida();
		listaTipoVia=ListasGenericas.getInstance().getListaTiposVia();
		listaPosicionVia=ListasGenericas.getInstance().getListaPosicionVia();
		listaUrbanizacion=ListasGenericas.getInstance().getListaUrbanizacion();
		listaAvaluadores = usuarioService.getAvaluadores();
		visita = new Visitas();
		Usuario usu = new Usuario();
		visita.setUsuario(usu);
		listaPropietarios = propietariosService.listaPropietarios(visita.getId());
		//direc=calc.getCoordenadasDeEstaDireccion("http://maps.googleapis.com/maps/api/geocode/json?address=Calle+48+F+Sur+40+55+Envigado");
		nuevaCotizacion();
		if(listaCotizaciones == null)
			listaCotizaciones = new ArrayList<>();
	}
	
	@PostConstruct
	public void validarSession() {
		util.validarSession();
	}
	
	public void nuevaCotizacion() {
		this.avaluo = new Avaluos();
		this.cotizacion = new Cotizacion();
		Usuario usu = new Usuario();
		Ciudad ciudad = new Ciudad();
		cotizacion.setEmpresa(usuario.getEmpresa());
		cotizacion.setUsuarioByClienteId(usu);
		cotizacion.setUsuarioByRemitenteId(usu);
		cotizacion.setCiudad(ciudad);
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
		direccion= new Direcciones();
		infPropiedad.setDirecciones(direccion);
		listaDetCotizacion = new ArrayList<DetalleCotizacion>();
		listaPropiedades = new ArrayList<Propiedad>();
		
		avaluo.setEmpresa(usuario.getEmpresa());
		avaluo.setUsuario(usuario);
		
	}
	

	public void consultar() {
		listaCotizaciones = getCotizacionService().getEntitys(usuario.getEmpresa().getId());			
		
		util.ejecutarPF("PF('dlgCotizaciones').show();");
		util.actualizarPF(util.findComponentClientIdPF("tCoti"));
	}
	
	public void guardar() {
		try {
	        //cotizacion.setDetalleCotizacions(listaDetCotizacion);
			if (cotizacion.getId() == 0) {
				getCotizacionService().addEntity(cotizacion);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cotizacion creada on exito.", "Message: "));
			}
			else {
				getCotizacionService().updateEntity(cotizacion);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cotización actualizada con exito.", "Message: "));
			}
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar la cotización. Intente mas tarde.", "Message: ")); 
		} 	
		
	}
	
	public void almacenarDireccion() {
		infPropiedad.setDireccion(direccion.getTipoVia()+" "+direccion.getNombreVia()+" "+direccion.getNumeroVia()+" "+direccion.getLetraVia()+" "+direccion.getPosicionVia()+" "+direccion.getNumeroInterseccion()+" "+direccion.getNumeroUbicacion());
	}
	
	
	public void addCliente(String bloque) {
		try {
			boolean guardar = true;
			usuarioExiste = getUsuarioService().consultaIdentificacion(cotizacion.getUsuarioByClienteId().getTipoDocumento(), cotizacion.getUsuarioByClienteId().getIdentificacion(), usuario.getEmpresa().getId(), 3);
			if (usuarioExiste != null && usuarioExiste.getNombre() != null) {
				guardar = false;
				getUsuarioService().updateUsuario(cotizacion.getUsuarioByClienteId());
				util.mostrarMensajeKey("cotizacion.cliente.ya.existe");
			}
			
			if(guardar) {
				Rol rol = new Rol();
				rol.setId(3);
				cotizacion.getUsuarioByClienteId().setEmpresa(usuario.getEmpresa());
				cotizacion.getUsuarioByClienteId().setRol(rol);
				cotizacion.getUsuarioByClienteId().setEstado(true);
				cotizacion.getUsuarioByClienteId().setLenguaje("ES");
				getUsuarioService().addUsuario(cotizacion.getUsuarioByClienteId());
				util.mostrarMensajeKey("exito.guardar"); 
				util.actualizarPF(bloque);
			}else {
				util.actualizarPF("growl");
			}
			
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarErrorKey("error.guardar");  
		} 	
		
	}
	
	public void addRemitente(String bloque) {
		try {
			boolean guardar = true;
			usuarioExiste = getUsuarioService().consultaIdentificacion(cotizacion.getUsuarioByRemitenteId().getTipoDocumento(), cotizacion.getUsuarioByRemitenteId().getIdentificacion(), usuario.getEmpresa().getId(), 0);
			if (usuarioExiste != null && usuarioExiste.getNombre() != null) {
				guardar = false;
				util.mostrarMensajeKey("cotizacion.remitente.actualizado");
			}
			
			if(guardar) {
				Rol rol = new Rol();
				rol.setId(2);
				cotizacion.getUsuarioByRemitenteId().setEmpresa(usuario.getEmpresa());
				cotizacion.getUsuarioByRemitenteId().setRol(rol);
				cotizacion.getUsuarioByRemitenteId().setEstado(true);
				cotizacion.getUsuarioByRemitenteId().setLenguaje("ES");
				getUsuarioService().addUsuario(cotizacion.getUsuarioByRemitenteId());
				util.mostrarMensajeKey("exito.guardar"); 
				util.actualizarPF(bloque);
			}else {
				util.actualizarPF("growl");
			}
			
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			util.mostrarErrorKey("error.guardar");  
		} 	
		
	}

   public void telefonoFijo(FacesContext arg0, UIComponent arg1, Object arg2)
	         throws ValidatorException {
	      if (((String)arg2).length()<8) {
	         throw new ValidatorException(new FacesMessage("El número de telédno debe ser máximo de 7. "));
	      }
	   }
	
	public void addPropiedad() {
		
		infPropiedad.setUsuario(usuario);

		
		Estrato est = new Estrato();
		TipoPropiedad tprop = new TipoPropiedad();
		Tablas tablas = new Tablas();
		

		infPropiedad.setEstrato(est);
		infPropiedad.setTipoPropiedad(tprop);
		infPropiedad.setTablas(tablas);
		infPropiedad.setDirecciones(direccion);
		listaPropiedades.add(infPropiedad);
		limpiarPropiedad();
		
		
		util.actualizarPF("jnfProp");
		util.mostrarMensajeKey("cotizacion.propiedad.agregada"); 
	}
	
	public void actualizarPropiedad() {
		
		infPropiedad.setUsuario(usuario);

		
		Estrato est = new Estrato();
		TipoPropiedad tprop = new TipoPropiedad();
		Tablas tablas = new Tablas();

		infPropiedad.setEstrato(est);
		infPropiedad.setTipoPropiedad(tprop);
		infPropiedad.setTablas(tablas);
		infPropiedad.setDirecciones(direccion);
		int i = 0;
		for (Propiedad p: listaPropiedades) {
			if (infPropiedad.getId()==p.getId() ) {
				listaPropiedades.set(i, infPropiedad);
				i = i + 1;
			}
		}
		
		//listaPropiedades.add(infPropiedad);
		//setSelectedPropiedad(infPropiedad);
		limpiarPropiedad();
		
		
		util.actualizarPF("jnfProp");
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
		Direcciones dir = new Direcciones();
		infPropiedad.setCiudad(ciud);
		infPropiedad.setEstrato(est);	
		infPropiedad.setDirecciones(dir);
		direccion = new Direcciones();
		
	}

	

	
	public void cotizar() {
		if (listaPropiedades.size() <= 0) {
			util.mostrarErrorKey("cotizacion.no.existen.propiedades");  
		}
		else {
		
		
		listaDetCotizacion = new ArrayList<DetalleCotizacion>();
		BigDecimal resultado = new BigDecimal(0);
		BigDecimal valorCotizacion = new BigDecimal(0);
		BigDecimal totalCotizacion = new BigDecimal(0);
		
		BigDecimal tax = new BigDecimal(0);
		for (Propiedad p : listaPropiedades) {
			//Tablas t=p.getTablas();
			List<DetalleTabla> lista = p.getTablas().getDetalleTablas();
			// Collection.sort(lista);
			BigDecimal mt2 = p.getValorMedida();
			BigDecimal totmt2 = p.getValorMedida();
			valorCotizacion = new BigDecimal(0);
			
			for (DetalleTabla detTabla : p.getTablas().getDetalleTablas()) {
				BigDecimal delta = new BigDecimal(detTabla.getHasta()  - detTabla.getDesde());
				BigDecimal used;
				BigDecimal convert;
				
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
	                
	                
	                
	                if (used.compareTo(BigDecimal.ZERO) == 0) {
	                
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
				
			} 	 
                	 valorCotizacion = valorCotizacion.multiply(new BigDecimal(100).add( p.getEstrato().getPorcentaje().multiply(new BigDecimal(-1)))).divide(new BigDecimal(100)) ;
                	 valorCotizacion = valorCotizacion.multiply(new BigDecimal(100).add( p.getTipoPropiedad().getIncremento().multiply(new BigDecimal(-1)))).divide(new BigDecimal(100)) ;
                	 
                     //cotizacion = cotizacion * (100 + objEst.getPorcentaje()) / 100;
                	 valorCotizacion = valorCotizacion.divide(new BigDecimal(100000));
                	 valorCotizacion = valorCotizacion.setScale(0, BigDecimal.ROUND_HALF_UP);
                	 valorCotizacion = valorCotizacion.multiply(new BigDecimal(100000));
                	 
                	 int res2;
     				 res2 = valorCotizacion.compareTo(p.getEstrato().getValor());
                	 
                	 if (res2 == -1) {
                		 valorCotizacion = p.getEstrato().getValor();
                	 }
                	 
                	 totalCotizacion = totalCotizacion.add(valorCotizacion);
                	 
                	 avaluo.setPropiedad(p);
                	 p.getAvaluoses().add(avaluo);
                	 
                	 this.detCotizacion = new DetalleCotizacion();
             		detCotizacion.setCotizacion(cotizacion);
             		detCotizacion.setPropiedad(p);

             		detCotizacion.setValor(valorCotizacion);
             		
             		listaDetCotizacion.add(detCotizacion);
             		
             		
			}
		cotizacion.setDetalleCotizacions(listaDetCotizacion);
		cotizacion.setValor(totalCotizacion);
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

	
	public void onconsultaCliente(String tipoIdentif, String identif) { 
		usuarioCliente = getUsuarioService().consultaIdentificacion(tipoIdentif, identif, usuario.getEmpresa().getId(), 3);
		/*if (usuarioCliente != null) {
			cotizacion.setUsuarioByClienteId(usuarioCliente);
			//cotizacion.setUsuarioByRemitenteId(cliente);
		}
		else {
			Rol rol = new Rol();
			rol.setId(3);
			cotizacion.getUsuarioByClienteId().setNombre("");
			cotizacion.getUsuarioByClienteId().setTelefono("");
			cotizacion.getUsuarioByClienteId().setCelular("");
			cotizacion.getUsuarioByClienteId().setDireccion("");
			cotizacion.getUsuarioByClienteId().setEstado(false);
			cotizacion.getUsuarioByClienteId().setDireccion("");
			cotizacion.getUsuarioByClienteId().setCorreo("");
			cotizacion.getUsuarioByClienteId().setId(0);
			cotizacion.getUsuarioByClienteId().setRol(rol);
		}*/
		
	}

	public void onconsultaRemitente(String tipoIdentif, String identif) { 
		usuarioRemitente = getUsuarioService().consultaIdentificacion(tipoIdentif, identif, usuario.getEmpresa().getId(), 0);
		/*if (usuarioRemitente != null) {
			
			cotizacion.setUsuarioByRemitenteId(usuarioRemitente);
		}
		else {
			Rol rol = new Rol();
			rol.setId(3);
			cotizacion.getUsuarioByRemitenteId().setNombre("");
			cotizacion.getUsuarioByRemitenteId().setTelefono("");
			cotizacion.getUsuarioByRemitenteId().setCelular("");
			cotizacion.getUsuarioByRemitenteId().setDireccion("");
			cotizacion.getUsuarioByRemitenteId().setEstado(false);
			cotizacion.getUsuarioByRemitenteId().setDireccion("");
			cotizacion.getUsuarioByRemitenteId().setCorreo("");
			cotizacion.getUsuarioByRemitenteId().setId(0);
			cotizacion.getUsuarioByRemitenteId().setRol(rol);
		}*/
		
	}
	public void guardar2() {
		
	}

	
	public List<Cotizacion> getlistaCotizaciones() {
		//listaCotizaciones = new ArrayList<Cotizacion>();
		//listaCotizaciones = getCotizacionService().getEntitys(usuario.getEmpresa().getId());
		return listaCotizaciones;
	}
	
	public ICotizacionService getCotizacionService() {
		return cotizacionService;
	}

	public void setCotizacionService(ICotizacionService cotizacionService) {
		this.cotizacionService = cotizacionService;
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



	public Cotizacion getSelectedCotizacion() {
		return selectedCotizacion;
	}

	public void setSelectedCotizacion(Cotizacion selectedCotizacion) {
		this.selectedCotizacion = selectedCotizacion;
	}

	public void setlistaCotizaciones(List<Cotizacion> listaCotizaciones) {
		this.listaCotizaciones = listaCotizaciones;
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

	public List<SelectItem> getListaTipoVia() {
		return listaTipoVia;
	}

	public void setListaTipoVia(List<SelectItem> listaTipoVia) {
		this.listaTipoVia = listaTipoVia;
	}

	public List<SelectItem> getListaPosicionVia() {
		return listaPosicionVia;
	}

	public void setListaPosicionVia(List<SelectItem> listaPosicionVia) {
		this.listaPosicionVia = listaPosicionVia;
	}

	public List<SelectItem> getListaUrbanizacion() {
		return listaUrbanizacion;
	}

	public void setListaUrbanizacion(List<SelectItem> listaUrbanizacion) {
		this.listaUrbanizacion = listaUrbanizacion;
	}

	public SortedMap<String, Integer> getListaTipoPropiedad() {
		return listaTipoPropiedad;
	}

	public void setListaTipoPropiedad(SortedMap<String, Integer> listaTipoPropiedad) {
		this.listaTipoPropiedad = listaTipoPropiedad;
	}

	/*public List<TipoPropiedad> getListaPropiedades() {
		return listaPropiedades;
	}

	public void setListaPropiedades(List<TipoPropiedad> listaPropiedades) {
		this.listaPropiedades = listaPropiedades;
	}*/


	public void setListaEstrato(SortedMap<String, Integer> listaEstrato) {
		this.listaEstrato = listaEstrato;
	}

	public List<Estrato> getListaEstratos() {
		return listaEstratos;
	}

	public void setListaEstratos(List<Estrato> listaEstratos) {
		this.listaEstratos = listaEstratos;
	}

	public SortedMap<String, Integer> getListaCiudad() {
		
		
		return listaCiudad;
	}

	public void setListaCiudad(int idDepartamento) {
		
		this.listaCiudad = listaCiudad;		
		listaCiudades = new ArrayList<Ciudad>();
		listaCiudades.addAll(getCiudadService().getCiudades(idDepartamento));
		for (Ciudad ciudad : listaCiudades) {
			listaCiudad.put(ciudad.getNombre(), ciudad.getId() );
		}

	}

	public void onPaisChange(int idPais) {
	  setListaDepartamento(idPais);
	}

	public void onDepartamentoChange(int idDepartamento) {
		  setListaCiudad(idDepartamento);
		}
	
	public SortedMap<String, Integer> getListaDepartamento() {
		
		
		return listaDepartamento;
	}

	public void setListaDepartamento(int idPais) {
		listaDepartamentos = new ArrayList<Departamento>();
		listaDepartamentos.addAll(getCiudadService().getDepartamentos(idPais));
		for (Departamento departamento : listaDepartamentos) {
			listaDepartamento.put(departamento.getNombre(), departamento.getId() );
		}
		this.listaDepartamento = listaDepartamento;
	}


	public SortedMap<String, Integer> getListaPais() {
		listaPaises = new ArrayList<Pais>();
		listaPaises.addAll(getCiudadService().getPaises());
		for (Pais pais : listaPaises) {
			listaPais.put(pais.getNombre(), pais.getId() );
		}
		
		
		return listaPais;
	}

	public void setListaPais(SortedMap<String, Integer> listaPais) {
		this.listaPais = listaPais;
	}

	public List<Pais> getListaPaises() {
		return listaPaises;
	}

	public void setListaPaises(List<Pais> listaPaises) {
		this.listaPaises = listaPaises;
	}

	public List<Cotizacion> getListaCotizacion() {
		return listaCotizacion;
	}

	public void setListaCotizacion(List<Cotizacion> listaCotizacion) {
		this.listaCotizacion = listaCotizacion;
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

	public IDireccionesService getDireccionService() {
		return direccionService;
	}

	public void setDireccionService(IDireccionesService direccionService) {
		this.direccionService = direccionService;
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

	public List<TipoPropiedad> getListaTipoPropiedades() {
		return listaTipoPropiedades;
	}

	public void setListaTipoPropiedades(List<TipoPropiedad> listaTipoPropiedades) {
		this.listaTipoPropiedades = listaTipoPropiedades;
	}

	public List<Propiedad> getListaPropiedades() {
		return listaPropiedades;
	}

	public void setListaPropiedades(List<Propiedad> listaPropiedades) {
		this.listaPropiedades = listaPropiedades;
	}

	public SortedMap<String, Integer> getListaTablas() {
		return listaTablas;
	}

	public void setListaTablas(SortedMap<String, Integer> listaTablas) {
		this.listaTablas = listaTablas;
	}

	public SortedMap<String, Integer> getListaEstrato() {
		return listaEstrato;
	}

	public void setListaCiudad(SortedMap<String, Integer> listaCiudad) {
		this.listaCiudad = listaCiudad;
	}

	public void setListaDepartamento(SortedMap<String, Integer> listaDepartamento) {
		this.listaDepartamento = listaDepartamento;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPaisCot() {
		return paisCot;
	}

	public void setPaisCot(String paisCot) {
		this.paisCot = paisCot;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public Departamento getDepart() {
		return depart;
	}

	public void setDepart(Departamento depart) {
		this.depart = depart;
	}

	public String getDpto() {
		return dpto;
	}

	public void setDpto(String dpto) {
		this.dpto = dpto;
	}

	public Pais getPaisC() {
		return paisC;
	}

	public void setPaisC(Pais paisC) {
		this.paisC = paisC;
	}

	public List<Cotizacion> getListaCotizaciones() {
		return listaCotizaciones;
	}

	public void setListaCotizaciones(List<Cotizacion> listaCotizaciones) {
		this.listaCotizaciones = listaCotizaciones;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}


	public Usuario getUsuarioCliente() {
		return usuarioCliente;
	}

	public void setUsuarioCliente(Usuario usuarioCliente) {
		this.usuarioCliente = usuarioCliente;
	}

	public Usuario getUsuarioPropietario() {
		return usuarioPropietario;
	}

	public void setUsuarioPropietario(Usuario usuarioPropietario) {
		this.usuarioPropietario = usuarioPropietario;
	}

	public Usuario getUsuarioRemitente() {
		return usuarioRemitente;
	}

	public void setUsuarioRemitente(Usuario usuarioRemitente) {
		this.usuarioRemitente = usuarioRemitente;
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

	public List<DetalleCotizacion> getListaDetCotizacion() {
		return listaDetCotizacion;
	}

	public void setListaDetCotizacion(List<DetalleCotizacion> listaDetCotizacion) {
		this.listaDetCotizacion = listaDetCotizacion;
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

	
	

	public Cotizacion getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}

	public Avaluos getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(Avaluos avaluo) {
		this.avaluo = avaluo;
	}

	public List<Avaluos> getListaAvaluos() {
		return listaAvaluos;
	}

	public void setListaAvaluos(List<Avaluos> listaAvaluos) {
		this.listaAvaluos = listaAvaluos;
	}

	public Direcciones getDireccion() {
		return direccion;
	}

	public void setDireccion(Direcciones direccion) {
		this.direccion = direccion;
	}

	public List<Tablas> getTablas() {
		return tablas;
	}

	public void setTablas(List<Tablas> tablas) {
		this.tablas = tablas;
	}

	public List<SelectItem> getListaUnidadMedida() {
		return listaUnidadMedida;
	}

	public void setListaUnidadMedida(List<SelectItem> listaUnidadMedida) {
		this.listaUnidadMedida = listaUnidadMedida;
	}

	public boolean isSkip() {
		return skip;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
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


	public void nuevoAvaluo() {
		avaluo = new Avaluos();
		avaluo.setUsuario(usuario);
		avaluo.setEmpresa(usuario.getEmpresa());
		
		infPropiedad = new Propiedad();
		Ciudad ciu = new Ciudad();
		usuarioCliente = new Usuario();
		direccion = new Direcciones();
		DetalleCotizacion det = new DetalleCotizacion();
		Estrato est = new Estrato();
		Tablas tabla = new Tablas();
		TipoPropiedad tp = new  TipoPropiedad();
		infPropiedad.setCiudad(ciu);
		infPropiedad.setDirecciones(direccion);
		infPropiedad.setEstrato(est);
		infPropiedad.setTablas(tabla);
		infPropiedad.setTipoPropiedad(tp);
		infPropiedad.setUsuario(usuarioCliente);
		avaluo.setPropiedad(infPropiedad);
		visita = new Visitas();
		List<Visitas> lVisitas = new ArrayList<Visitas>();
		lVisitas.add(visita);
		visita.setAvaluos(avaluo);
		Usuario usu = new Usuario();
		visita.setUsuario(usu);
		avaluo.setVisitases(lVisitas);
		tabIndex=1;
	}
	
	public void verAvaluo(SelectEvent event) {
		avaluo = (Avaluos) event.getObject();
		//listaDetCotizacion = cotizacion.getDetalleCotizacions();
		//listaDetCotizacion = getCotizacionService().getDetCotizacion(cotizacion.getId());
		//cotizacion.setDetalleCotizacions(listaDetCotizacion);
		infPropiedad = avaluo.getPropiedad();
		usuarioCliente = avaluo.getPropiedad().getDetalleCotizacions().get(0).getCotizacion().getUsuarioByClienteId();
		direccion = avaluo.getPropiedad().getDetalleCotizacions().get(0).getPropiedad().getDirecciones();
		onPaisChange(avaluo.getPropiedad().getDetalleCotizacions().get(0).getPropiedad().getCiudad().getDepartamento().getPais().getId());
		onDepartamentoChange(avaluo.getPropiedad().getDetalleCotizacions().get(0).getPropiedad().getCiudad().getDepartamento().getId());
		pais = Integer.toString(avaluo.getPropiedad().getDetalleCotizacions().get(0).getPropiedad().getCiudad().getDepartamento().getPais().getId());
		departamento = Integer.toString(avaluo.getPropiedad().getDetalleCotizacions().get(0).getPropiedad().getCiudad().getDepartamento().getId());
		tabIndex=1;
		/*listaPropiedades = new ArrayList<Propiedad>();
		listaDetCotizacion = new ArrayList<DetalleCotizacion>();
		//listaDetCotizacion = cotizacion.getDetalleCotizacions();
		for (DetalleCotizacion detalle: cotizacion.getDetalleCotizacions()) {
			listaPropiedades.add(detalle.getPropiedad());
			//listaDetCotizacion.add(detalle);
		}
		listaDetCotizacion = cotizacion.getDetalleCotizacions();
		
		depart = getCiudadService().getDepartamento(cotizacion.getCiudad().getDepartamento().getId());
		dpto = String.valueOf(depart.getId());
		paisC = getCiudadService().getPais(depart.getPais().getId());
		paisCot =String.valueOf(paisC.getId());
		onPaisChange(paisC.getId());
		onDepartamentoChange(cotizacion.getCiudad().getDepartamento().getId());*/
    }

	public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        BigDecimal total = new BigDecimal(0);
        for (DetalleCotizacion d: listaDetCotizacion) {
        	total = total.add(d.getValor());
        }
        cotizacion.setValor(total);

    }
    public void onRowUnselect(UnselectEvent event) {
    }

	public Propiedad getSelectedPropiedad() {
		return selectedPropiedad;
	}

	public void setSelectedPropiedad(Propiedad selectedPropiedad) {
		this.selectedPropiedad = selectedPropiedad;
		
	}

	public DetalleCotizacion getSelectedDetalle() {
		return selectedDetalle;
	}

	public void setSelectedDetalle(DetalleCotizacion selectedDetalle) {
		this.selectedDetalle = selectedDetalle;
	}

	public CalcularCoordenadas getCalc() {
		return calc;
	}

	public void setCalc(CalcularCoordenadas calc) {
		this.calc = calc;
	}

	public String getDirec() {
		return direc;
	}

	public void setDirec(String direc) {
		this.direc = direc;
	}

	public Avaluos getAvaluoSelected() {
		return avaluoSelected;
	}

	public void setAvaluoSelected(Avaluos avaluoSelected) {
		this.avaluoSelected = avaluoSelected;
	}	

	public void verDetalle(SelectEvent event) {
		avaluoSelected = (Avaluos) event.getObject();
		//mostrarDetalle = true;
	}

	public int getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}

	public Visitas getVisita() {
		return visita;
	}

	public void setVisita(Visitas visita) {
		this.visita = visita;
	}

	public List<Usuario> getListaAvaluadores() {
		return listaAvaluadores;
	}

	public void setListaAvaluadores(List<Usuario> listAvaluadores) {
		this.listaAvaluadores = listaAvaluadores;
	}

	public SortedMap<String, Integer> getListAvaluadores() {
		
		listaAvaluadores = new ArrayList<Usuario>();
		listaAvaluadores.addAll(getUsuarioService().getAvaluadores());
			for (Usuario usu : listaAvaluadores) {
				listAvaluadores.put(usu.getNombreCompleto(), usu.getId() );
			}
		return listAvaluadores;
	}

	public void setListAvaluadores(SortedMap<String, Integer> listaAvaluadores) {
		this.listAvaluadores = listAvaluadores;
	}

	public IPropietariosService getPropietariosService() {
		return propietariosService;
	}

	public void setPropietariosService(IPropietariosService propietariosService) {
		this.propietariosService = propietariosService;
	}

	public SortedMap<String, Integer> getListaTipoPropietario() {
		return listaTipoPropietario;
	}

	public void setListaTipoPropietario(SortedMap<String, Integer> listaTipoPropietario) {
		this.listaTipoPropietario = listaTipoPropietario;
	}

	public List<Propietarios> getListaPropietarios() {
		return listaPropietarios;
	}

	public void setListaPropietarios(List<Propietarios> listaPropietarios) {
		this.listaPropietarios = listaPropietarios;
	}

	public Propietarios getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietarios propietario) {
		this.propietario = propietario;
	}
	
 }