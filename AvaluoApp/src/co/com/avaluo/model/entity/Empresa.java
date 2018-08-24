package co.com.avaluo.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "empresa", schema = "avalsoft")
public class Empresa implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private Ciudad ciudad = new Ciudad();
	private String tipoIdentificacion;
	private String identificacion;
	private String nombre;
	private String descripcion;
	private boolean estado = true;
	private List<TipoPropiedad> tipoPropiedads = new ArrayList<>(0);
	private List<Cotizacion> cotizacions = new ArrayList<Cotizacion>(0);
	
	private List<Tablas> tablases = new ArrayList<Tablas>(0);
	private List<Usuario> usuarios = new ArrayList<Usuario>(0);
	private List<Estrato> estratos = new ArrayList<Estrato>(0);
	private List<Reporte> reportes = new ArrayList<Reporte>(0);

	public Empresa() {
	}

	public Empresa(int id, Ciudad ciudad, boolean estado) {
		this.id = id;
		this.ciudad = ciudad;
		this.estado = estado;
	}

	public Empresa(int id, Ciudad ciudad, String tipoIdentificacion, String identificacion, String nombre,
			String descripcion, boolean estado, List<TipoPropiedad> tipoPropiedads, List<Cotizacion> cotizacions,
			List<Propiedad> propiedads, List<Tablas> tablases, List<Usuario> usuarios,
			List<Estrato> estratos) {
		this.id = id;
		this.ciudad = ciudad;
		this.tipoIdentificacion = tipoIdentificacion;
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
		this.tipoPropiedads = tipoPropiedads;
		this.cotizacions = cotizacions;
		
		this.tablases = tablases;
		this.usuarios = usuarios;
		this.estratos = estratos;
	}

	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "serial", name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ciudad_id", nullable = false)
	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	@Column(name = "tipo_identificacion", length = 10)
	public String getTipoIdentificacion() {
		return this.tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	@Column(name = "identificacion", length = 20)
	public String getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	@Column(name = "nombre", length = 50)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "description", length = 100)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "estado", nullable = false)
	public boolean isEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "empresa")
	public List<TipoPropiedad> getTipoPropiedads() {
		return this.tipoPropiedads;
	}

	public void setTipoPropiedads(List<TipoPropiedad> tipoPropiedads) {
		this.tipoPropiedads = tipoPropiedads;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
	public List<Cotizacion> getCotizacions() {
		return this.cotizacions;
	}

	public void setCotizacions(List<Cotizacion> cotizacions) {
		this.cotizacions = cotizacions;
	}


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
	public List<Tablas> getTablases() {
		return this.tablases;
	}

	public void setTablases(List<Tablas> tablases) {
		this.tablases = tablases;
	}



	@OneToMany(fetch = FetchType.EAGER, mappedBy = "empresa")
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "empresa")
	public List<Estrato> getEstratos() {
		return this.estratos;
	}

	public void setEstratos(List<Estrato> estratos) {
		this.estratos = estratos;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "empresa")
	public List<Reporte> getReportes() {
		return reportes;
	}

	public void setReportes(List<Reporte> reportes) {
		this.reportes = reportes;
	}

}
