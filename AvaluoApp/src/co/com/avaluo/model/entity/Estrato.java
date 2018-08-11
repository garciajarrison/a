package co.com.avaluo.model.entity;

import java.math.BigDecimal;
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
@Table(name = "estrato", schema = "avalsoft")
public class Estrato implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private Empresa empresa = new Empresa();
	private String nombre;
	private BigDecimal valor;
	private BigDecimal porcentaje;
	private List<Propiedad> propiedads = new ArrayList<Propiedad>();

	public Estrato() {
	}

	public Estrato(int id, Empresa empresa, BigDecimal valor, BigDecimal porcentaje) {
		this.id = id;
		this.empresa = empresa;
		this.valor = valor;
		this.porcentaje = porcentaje;
	}

	public Estrato(int id, Empresa empresa, String nombre, BigDecimal valor, BigDecimal porcentaje,
			List<Propiedad> propiedads) {
		this.id = id;
		this.empresa = empresa;
		this.nombre = nombre;
		this.valor = valor;
		this.porcentaje = porcentaje;
		this.propiedads = propiedads;
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
	@JoinColumn(name = "empresa_id", nullable = false)
	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Column(name = "nombre", length = 50)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "valor", nullable = false, precision = 131089, scale = 0)
	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Column(name = "porcentaje", nullable = false, precision = 131089, scale = 0)
	public BigDecimal getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "estrato")
	public List<Propiedad> getPropiedads() {
		return this.propiedads;
	}

	public void setPropiedads(List<Propiedad> propiedads) {
		this.propiedads = propiedads;
	}

}
