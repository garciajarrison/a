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
@Table(name = "tablas", schema = "avalsoft")
public class Tablas implements java.io.Serializable {

	private int id;
	private Empresa empresa;
	private String tipo;
	private String nombre;
	private BigDecimal conversion;
	private BigDecimal base;
	private BigDecimal gastos;
	private boolean estado = true;
	private String uom;
	private String uomAlt;
	private Long diasDeTrabajo;
	private Long minimo;
	private List<DetalleTabla> detalleTablas = new ArrayList<>();

	public Tablas() {
	}

	public Tablas(int id, Empresa empresa,  boolean estado) {
		this.id = id;
		this.empresa = empresa;
		this.estado = estado;
	}

	public Tablas(int id, Empresa empresa, String tipo, String nombre, BigDecimal conversion,
			BigDecimal base, BigDecimal gastos, boolean estado, String uom, String uomAlt, Long diasDeTrabajo,
			Long minimo, List<DetalleTabla> detalleTablas) {
		this.id = id;
		this.empresa = empresa;
		this.tipo = tipo;
		this.nombre = nombre;
		this.conversion = conversion;
		this.base = base;
		this.gastos = gastos;
		this.estado = estado;
		this.uom = uom;
		this.uomAlt = uomAlt;
		this.diasDeTrabajo = diasDeTrabajo;
		this.minimo = minimo;
		this.detalleTablas = detalleTablas;
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
	@JoinColumn(name = "empresa_id", nullable = false)
	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Column(name = "tipo", length = 25)
	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Column(name = "nombre", length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "conversion", precision = 131089, scale = 0)
	public BigDecimal getConversion() {
		return this.conversion;
	}

	public void setConversion(BigDecimal conversion) {
		this.conversion = conversion;
	}

	@Column(name = "base", precision = 131089, scale = 0)
	public BigDecimal getBase() {
		return this.base;
	}

	public void setBase(BigDecimal base) {
		this.base = base;
	}

	@Column(name = "gastos", precision = 131089, scale = 0)
	public BigDecimal getGastos() {
		return this.gastos;
	}

	public void setGastos(BigDecimal gastos) {
		this.gastos = gastos;
	}

	@Column(name = "estado", nullable = false)
	public boolean isEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Column(name = "uom", length = 10)
	public String getUom() {
		return this.uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	@Column(name = "uom_alt", length = 10)
	public String getUomAlt() {
		return this.uomAlt;
	}

	public void setUomAlt(String uomAlt) {
		this.uomAlt = uomAlt;
	}

	@Column(name = "dias_de_trabajo", precision = 10, scale = 0)
	public Long getDiasDeTrabajo() {
		return this.diasDeTrabajo;
	}

	public void setDiasDeTrabajo(Long diasDeTrabajo) {
		this.diasDeTrabajo = diasDeTrabajo;
	}

	@Column(name = "minimo", precision = 10, scale = 0)
	public Long getMinimo() {
		return this.minimo;
	}

	public void setMinimo(Long minimo) {
		this.minimo = minimo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tablas")
	public List<DetalleTabla> getDetalleTablas() {
		return this.detalleTablas;
	}

	public void setDetalleTablas(List<DetalleTabla> detalleTablas) {
		this.detalleTablas = detalleTablas;
	}

}
