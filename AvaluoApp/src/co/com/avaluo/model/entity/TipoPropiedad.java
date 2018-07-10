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
@Table(name = "tipo_propiedad", schema = "avalsoft")
public class TipoPropiedad implements java.io.Serializable {

	private int id;
	private Empresa empresa;
	private String tipoPropiedad;
	private String tipoVivienda;
	private Double incremento;

	public TipoPropiedad() {
	}

	public TipoPropiedad(int id, Empresa empresa) {
		this.id = id;
		this.empresa = empresa;
	}

	public TipoPropiedad(int id, Empresa empresa, String tipoPropiedad, String tipoVivienda, Double incremento) {
		this.id = id;
		this.empresa = empresa;
		this.tipoPropiedad = tipoPropiedad;
		this.tipoVivienda = tipoVivienda;
		this.incremento = incremento;
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

	@Column(name = "tipo_propiedad", length = 50)
	public String getTipoPropiedad() {
		return this.tipoPropiedad;
	}

	public void setTipoPropiedad(String tipoPropiedad) {
		this.tipoPropiedad = tipoPropiedad;
	}

	@Column(name = "tipo_vivienda", length = 100)
	public String getTipoVivienda() {
		return this.tipoVivienda;
	}

	public void setTipoVivienda(String tipoVivienda) {
		this.tipoVivienda = tipoVivienda;
	}

	@Column(name = "incremento", precision = 17, scale = 17)
	public Double getIncremento() {
		return this.incremento;
	}

	public void setIncremento(Double incremento) {
		this.incremento = incremento;
	}

}
