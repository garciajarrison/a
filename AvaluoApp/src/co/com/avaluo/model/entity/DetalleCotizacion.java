package co.com.avaluo.model.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "detalle_cotizacion", schema = "avalsoft")
public class DetalleCotizacion implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private Cotizacion cotizacion;
	private Propiedad propiedad;
	private BigDecimal valor;

	public DetalleCotizacion() {
	}

	public DetalleCotizacion(int id, Cotizacion cotizacion, Propiedad propiedad) {
		this.id = id;
		this.cotizacion = cotizacion;
		this.propiedad = propiedad;
	}

	public DetalleCotizacion(int id, Cotizacion cotizacion, Propiedad propiedad, BigDecimal valor) {
		this.id = id;
		this.cotizacion = cotizacion;
		this.propiedad = propiedad;
		this.valor = valor;
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
	@JoinColumn(name = "cotizacion_id", nullable = false)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	public Cotizacion getCotizacion() {
		return this.cotizacion;
	}

	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "propiedad_id", nullable = false)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	public Propiedad getPropiedad() {
		return this.propiedad;
	}

	public void setPropiedad(Propiedad propiedad) {
		this.propiedad = propiedad;
	}

	@Column(name = "valor", precision = 131089, scale = 0)
	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
