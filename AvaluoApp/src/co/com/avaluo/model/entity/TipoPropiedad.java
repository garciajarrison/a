package co.com.avaluo.model.entity;
// Generated 16/07/2018 08:18:17 PM by Hibernate Tools 4.0.1.Final

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

/**
 * TipoPropiedad generated by hbm2java
 */
@Entity
@Table(name = "tipo_propiedad", schema = "avalsoft")
public class TipoPropiedad implements java.io.Serializable {

	private int id;
	private Empresa empresa;
	private String tipoPropiedad;
	private String tipoVivienda;
	private Double incremento;
	private List<Propiedad> propiedads = new ArrayList<Propiedad>(0);

	public TipoPropiedad() {
	}

	public TipoPropiedad(int id, Empresa empresa) {
		this.id = id;
		this.empresa = empresa;
	}

	public TipoPropiedad(int id, Empresa empresa, String tipoPropiedad, String tipoVivienda, Double incremento,
			List<Propiedad> propiedads) {
		this.id = id;
		this.empresa = empresa;
		this.tipoPropiedad = tipoPropiedad;
		this.tipoVivienda = tipoVivienda;
		this.incremento = incremento;
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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tipoPropiedad")
	public List<Propiedad> getPropiedads() {
		return this.propiedads;
	}

	public void setPropiedads(List<Propiedad> propiedads) {
		this.propiedads = propiedads;
	}

}
