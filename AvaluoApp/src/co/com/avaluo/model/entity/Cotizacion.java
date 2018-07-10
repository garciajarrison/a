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
@Table(name = "cotizacion", schema = "avalsoft")
public class Cotizacion implements java.io.Serializable {

	private int id;
	private Empresa empresaByClienteId;
	private Empresa empresaByEmpresaId;
	private Double valor;
	private Set<DetalleCotizacion> detalleCotizacions = new HashSet<DetalleCotizacion>(0);

	public Cotizacion() {
	}

	public Cotizacion(int id) {
		this.id = id;
	}

	public Cotizacion(int id, Empresa empresaByClienteId, Empresa empresaByEmpresaId, Double valor,
			Set<DetalleCotizacion> detalleCotizacions) {
		this.id = id;
		this.empresaByClienteId = empresaByClienteId;
		this.empresaByEmpresaId = empresaByEmpresaId;
		this.valor = valor;
		this.detalleCotizacions = detalleCotizacions;
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
	@JoinColumn(name = "cliente_id")
	public Empresa getEmpresaByClienteId() {
		return this.empresaByClienteId;
	}

	public void setEmpresaByClienteId(Empresa empresaByClienteId) {
		this.empresaByClienteId = empresaByClienteId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empresa_id")
	public Empresa getEmpresaByEmpresaId() {
		return this.empresaByEmpresaId;
	}

	public void setEmpresaByEmpresaId(Empresa empresaByEmpresaId) {
		this.empresaByEmpresaId = empresaByEmpresaId;
	}

	@Column(name = "valor", precision = 17, scale = 17)
	public Double getValor() {
		return this.valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cotizacion")
	public Set<DetalleCotizacion> getDetalleCotizacions() {
		return this.detalleCotizacions;
	}

	public void setDetalleCotizacions(Set<DetalleCotizacion> detalleCotizacions) {
		this.detalleCotizacions = detalleCotizacions;
	}

}
