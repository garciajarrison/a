package co.com.avaluo.model.entity;

import java.util.HashSet;
import java.util.Set;

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
	private Set<TipoPropiedad> tipoPropiedads = new HashSet<TipoPropiedad>(0);
	private Set<Cotizacion> cotizacionsForEmpresaId = new HashSet<Cotizacion>(0);
	private Set<Propiedad> propiedads = new HashSet<Propiedad>(0);
	private Set<Tablas> tablases = new HashSet<Tablas>(0);
	private Set<DetalleTabla> detalleTablas = new HashSet<DetalleTabla>(0);
	private Set<Usuario> usuarios = new HashSet<Usuario>(0);
	private Set<Licencia> licencias = new HashSet<Licencia>(0);
	private Set<Cotizacion> cotizacionsForClienteId = new HashSet<Cotizacion>(0);
	private Set<Estrato> estratos = new HashSet<Estrato>(0);

	public Empresa() {
	}

	public Empresa(int id, Ciudad ciudad, boolean estado) {
		this.id = id;
		this.ciudad = ciudad;
		this.estado = estado;
	}

	public Empresa(int id, Ciudad ciudad, String tipoIdentificacion, String identificacion, String nombre,
			String description, boolean estado, Set<TipoPropiedad> tipoPropiedads,
			Set<Cotizacion> cotizacionsForEmpresaId, Set<Propiedad> propiedads, Set<Tablas> tablases,
			Set<DetalleTabla> detalleTablas, Set<Usuario> usuarios, Set<Licencia> licencias,
			Set<Cotizacion> cotizacionsForClienteId, Set<Estrato> estratos) {
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
		this.detalleTablas = detalleTablas;
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
	public Set<TipoPropiedad> getTipoPropiedads() {
		return this.tipoPropiedads;
	}

	public void setTipoPropiedads(Set<TipoPropiedad> tipoPropiedads) {
		this.tipoPropiedads = tipoPropiedads;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresaByEmpresaId")
	public Set<Cotizacion> getCotizacionsForEmpresaId() {
		return this.cotizacionsForEmpresaId;
	}

	public void setCotizacionsForEmpresaId(Set<Cotizacion> cotizacionsForEmpresaId) {
		this.cotizacionsForEmpresaId = cotizacionsForEmpresaId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
	public Set<Propiedad> getPropiedads() {
		return this.propiedads;
	}

	public void setPropiedads(Set<Propiedad> propiedads) {
		this.propiedads = propiedads;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
	public Set<Tablas> getTablases() {
		return this.tablases;
	}

	public void setTablases(Set<Tablas> tablases) {
		this.tablases = tablases;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
	public Set<DetalleTabla> getDetalleTablas() {
		return this.detalleTablas;
	}

	public void setDetalleTablas(Set<DetalleTabla> detalleTablas) {
		this.detalleTablas = detalleTablas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
	public Set<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
	public Set<Licencia> getLicencias() {
		return this.licencias;
	}

	public void setLicencias(Set<Licencia> licencias) {
		this.licencias = licencias;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresaByClienteId")
	public Set<Cotizacion> getCotizacionsForClienteId() {
		return this.cotizacionsForClienteId;
	}

	public void setCotizacionsForClienteId(Set<Cotizacion> cotizacionsForClienteId) {
		this.cotizacionsForClienteId = cotizacionsForClienteId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
	public Set<Estrato> getEstratos() {
		return this.estratos;
	}

	public void setEstratos(Set<Estrato> estratos) {
		this.estratos = estratos;
	}

}
