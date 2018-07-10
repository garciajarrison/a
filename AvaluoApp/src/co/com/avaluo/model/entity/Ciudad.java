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
@Table(name = "ciudad", schema = "avalsoft")
public class Ciudad implements java.io.Serializable {

	private int id;
	private Departamento departamento;
	private String nombre;
	private String codigo;
	private Boolean estado;
	private Set<Licencia> licencias = new HashSet<Licencia>(0);
	private Set<Propiedad> propiedads = new HashSet<Propiedad>(0);
	private Set<Empresa> empresas = new HashSet<Empresa>(0);

	public Ciudad() {
	}

	public Ciudad(int id, Departamento departamento) {
		this.id = id;
		this.departamento = departamento;
	}

	public Ciudad(int id, Departamento departamento, String nombre, String codigo, Boolean estado,
			Set<Licencia> licencias, Set<Propiedad> propiedads, Set<Empresa> empresas) {
		this.id = id;
		this.departamento = departamento;
		this.nombre = nombre;
		this.codigo = codigo;
		this.estado = estado;
		this.licencias = licencias;
		this.propiedads = propiedads;
		this.empresas = empresas;
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
	@JoinColumn(name = "departamento_id", nullable = false)
	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Column(name = "nombre", length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "codigo", length = 10)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "estado")
	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ciudad")
	public Set<Licencia> getLicencias() {
		return this.licencias;
	}

	public void setLicencias(Set<Licencia> licencias) {
		this.licencias = licencias;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ciudad")
	public Set<Propiedad> getPropiedads() {
		return this.propiedads;
	}

	public void setPropiedads(Set<Propiedad> propiedads) {
		this.propiedads = propiedads;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ciudad")
	public Set<Empresa> getEmpresas() {
		return this.empresas;
	}

	public void setEmpresas(Set<Empresa> empresas) {
		this.empresas = empresas;
	}

}
