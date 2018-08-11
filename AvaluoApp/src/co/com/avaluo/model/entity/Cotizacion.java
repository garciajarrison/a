package co.com.avaluo.model.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "cotizacion", schema = "avalsoft")
public class Cotizacion implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private Usuario usuarioByClienteId;
	private Usuario usuarioByRemitenteId;
	private Empresa empresa;
	private BigDecimal valor;
	private List<DetalleCotizacion> detalleCotizacions = new ArrayList<DetalleCotizacion>(0);

	public Cotizacion() {
	}

	public Cotizacion(int id, Usuario usuarioByClienteId, Usuario usuarioByRemitenteId, Empresa empresa) {
		this.id = id;
		this.usuarioByClienteId = usuarioByClienteId;
		this.usuarioByRemitenteId = usuarioByRemitenteId;
		this.empresa = empresa;
	}

	public Cotizacion(int id, Usuario usuarioByClienteId, Usuario usuarioByRemitenteId, Empresa empresa,
			BigDecimal valor, List<DetalleCotizacion> detalleCotizacions) {
		this.id = id;
		this.usuarioByClienteId = usuarioByClienteId;
		this.usuarioByRemitenteId = usuarioByRemitenteId;
		this.empresa = empresa;
		this.valor = valor;
		this.detalleCotizacions = detalleCotizacions;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente_id", nullable = false)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	public Usuario getUsuarioByClienteId() {
		return this.usuarioByClienteId;
	}

	public void setUsuarioByClienteId(Usuario usuarioByClienteId) {
		this.usuarioByClienteId = usuarioByClienteId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "remitente_id", nullable = false)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	public Usuario getUsuarioByRemitenteId() {
		return this.usuarioByRemitenteId;
	}

	public void setUsuarioByRemitenteId(Usuario usuarioByRemitenteId) {
		this.usuarioByRemitenteId = usuarioByRemitenteId;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cotizacion")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	public List<DetalleCotizacion> getDetalleCotizacions() {
		return this.detalleCotizacions;
	}

	public void setDetalleCotizacions(List<DetalleCotizacion> detalleCotizacions) {
		this.detalleCotizacions = detalleCotizacions;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "empresa_id", nullable = false)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Column(name = "valor", precision = 131089, scale = 0)
	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}



}
