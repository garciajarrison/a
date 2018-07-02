package co.com.avaluo.model.entity;
// Generated 2/07/2018 06:16:36 PM by Hibernate Tools 4.0.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DetalleTabla generated by hbm2java
 */
@Entity
@Table(name = "detalle_tabla", schema = "avalsoft")
public class DetalleTabla implements java.io.Serializable {

	private int id;
	private Integer tablaId;
	private Long desde;
	private Long hasta;
	private Double porcentajeAplicar;
	private Integer empresaId;

	public DetalleTabla() {
	}

	public DetalleTabla(int id) {
		this.id = id;
	}

	public DetalleTabla(int id, Integer tablaId, Long desde, Long hasta, Double porcentajeAplicar, Integer empresaId) {
		this.id = id;
		this.tablaId = tablaId;
		this.desde = desde;
		this.hasta = hasta;
		this.porcentajeAplicar = porcentajeAplicar;
		this.empresaId = empresaId;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "tabla_id")
	public Integer getTablaId() {
		return this.tablaId;
	}

	public void setTablaId(Integer tablaId) {
		this.tablaId = tablaId;
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

	@Column(name = "empresa_id")
	public Integer getEmpresaId() {
		return this.empresaId;
	}

	public void setEmpresaId(Integer empresaId) {
		this.empresaId = empresaId;
	}

}