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

	private int id;
	private Ciudad ciudad;
	private String tipoIdentificacion;
	private String identificacion;
	private String nombre;
	private String description;
	private boolean estado;
	private List<TipoPropiedad> tipoPropiedads = new ArrayList<TipoPropiedad>();
	private List<Cotizacion> cotizacionsForEmpresaId = new ArrayList<Cotizacion>();
	private List<Propiedad> propiedads = new ArrayList<Propiedad>();
	private List<Tablas> tablases = new ArrayList<Tablas>();
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private List<Licencia> licencias = new ArrayList<Licencia>();
	private List<Cotizacion> cotizacionsForClienteId = new ArrayList<Cotizacion>();
	private List<Estrato> estratos = new ArrayList<Estrato>();

	public Empresa() {
	}

	public Empresa(int id, Ciudad ciudad, boolean estado) {
		this.id = id;
		this.ciudad = ciudad;
		this.estado = estado;
	}

	public Empresa(int id, Ciudad ciudad, String tipoIdentificacion, String identificacion, String nombre,
			String description, boolean estado, List<TipoPropiedad> tipoPropiedads,
			List<Cotizacion> cotizacionsForEmpresaId, List<Propiedad> propiedads, List<Tablas> tablases,
			List<Usuario> usuarios, List<Licencia> licencias,
			List<Cotizacion> cotizacionsForClienteId, List<Estrato> estratos) {
		this.id = id;
		this.ciudad = ciudad;
		this.tipoIdentificacion = tipoIdentificacion;
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.description = description;
		this.estado = estado;
		this.tipoPropiedads = tipoPropiedads;
		this.cotizacionsForEmpresaId = cotizacionsForEmpresaId;
		this.propiedads = propiedads;
		this.tablases = tablases;
		this.usuarios = usuarios;
		this.licencias = licencias;
		this.cotizacionsForClienteId = cotizacionsForClienteId;
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

	@ManyToOne(fetch = FetchType.LAZY)
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
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "estado", nullable = false)
	public boolean isEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
	public List<TipoPropiedad> getTipoPropiedads() {
		return this.tipoPropiedads;
	}

	public void setTipoPropiedads(List<TipoPropiedad> tipoPropiedads) {
		this.tipoPropiedads = tipoPropiedads;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresaByEmpresaId")
	public List<Cotizacion> getCotizacionsForEmpresaId() {
		return this.cotizacionsForEmpresaId;
	}

	public void setCotizacionsForEmpresaId(List<Cotizacion> cotizacionsForEmpresaId) {
		this.cotizacionsForEmpresaId = cotizacionsForEmpresaId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
	public List<Propiedad> getPropiedads() {
		return this.propiedads;
	}

	public void setPropiedads(List<Propiedad> propiedads) {
		this.propiedads = propiedads;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
	public List<Tablas> getTablases() {
		return this.tablases;
	}

	public void setTablases(List<Tablas> tablases) {
		this.tablases = tablases;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
	public List<Licencia> getLicencias() {
		return this.licencias;
	}

	public void setLicencias(List<Licencia> licencias) {
		this.licencias = licencias;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresaByClienteId")
	public List<Cotizacion> getCotizacionsForClienteId() {
		return this.cotizacionsForClienteId;
	}

	public void setCotizacionsForClienteId(List<Cotizacion> cotizacionsForClienteId) {
		this.cotizacionsForClienteId = cotizacionsForClienteId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
	public List<Estrato> getEstratos() {
		return this.estratos;
	}

	public void setEstratos(List<Estrato> estratos) {
		this.estratos = estratos;
	}

}
