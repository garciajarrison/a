package co.com.avaluo.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detalle_tabla", schema = "avalsoft")
public class DetalleTabla implements java.io.Serializable {

	private int id;
	private Tablas tablas;
	private Empresa empresa;
	private Long desde;
	private Long hasta;
	private Double porcentajeAplicar;

	public DetalleTabla() {
	}

	public DetalleTabla(int id) {
		this.id = id;
	}

	public DetalleTabla(int id, Tablas tablas, Empresa empresa, Long desde, Long hasta, Double porcentajeAplicar) {
		this.id = id;
		this.tablas = tablas;
		this.empresa = empresa;
		this.desde = desde;
		this.hasta = hasta;
		this.porcentajeAplicar = porcentajeAplicar;
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
	@JoinColumn(name = "tabla_id")
	public Tablas getTablas() {
		return this.tablas;
	}

	public void setTablas(Tablas tablas) {
		this.tablas = tablas;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empresa_id")
	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Column(name = "desde", precision = 10, scale = 0)
	public Long getDesde() {
		return this.desde;
	}

	public void setDesde(Long desde) {
		this.desde = desde;
	}

	@Column(name = "hasta", precision = 10, scale = 0)
	public Long getHasta() {
		return this.hasta;
	}

	public void setHasta(Long hasta) {
		this.hasta = hasta;
	}

	@Column(name = "porcentaje_aplicar", precision = 17, scale = 17)
	public Double getPorcentajeAplicar() {
		return this.porcentajeAplicar;
	}

	public void setPorcentajeAplicar(Double porcentajeAplicar) {
		this.porcentajeAplicar = porcentajeAplicar;
	}

}
