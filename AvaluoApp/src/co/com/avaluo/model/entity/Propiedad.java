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

@Entity
@Table(name = "propiedad", schema = "avalsoft")
public class Propiedad implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private Estrato estrato;
	private Tablas tablas;
	private Direcciones direcciones;
	private Usuario usuario;
	private Ciudad ciudad;
	private TipoPropiedad tipoPropiedad;
	private String tipo;
	private String registro;
	private String direccion;
	private String unidadMedida;
	private BigDecimal valorMedida;
	private List<DetalleCotizacion> detalleCotizacions = new ArrayList<DetalleCotizacion>(0);

	public Propiedad() {
	}

	public Propiedad(int id, Estrato estrato, Tablas tablas, Usuario usuario, Ciudad ciudad,
			TipoPropiedad tipoPropiedad) {
		this.id = id;
		this.estrato = estrato;
		this.tablas = tablas;
		this.usuario = usuario;
		this.ciudad = ciudad;
		this.tipoPropiedad = tipoPropiedad;
	}

	public Propiedad(int id, Estrato estrato, Tablas tablas, Direcciones direcciones, Usuario usuario, Ciudad ciudad,
			TipoPropiedad tipoPropiedad, String tipo, String registro, String direccion, String unidadMedida,
			BigDecimal valorMedida, List<DetalleCotizacion> detalleCotizacions) {
		this.id = id;
		this.estrato = estrato;
		this.tablas = tablas;
		this.direcciones = direcciones;
		this.usuario = usuario;
		this.ciudad = ciudad;
		this.tipoPropiedad = tipoPropiedad;
		this.tipo = tipo;
		this.registro = registro;
		this.direccion = direccion;
		this.unidadMedida = unidadMedida;
		this.valorMedida = valorMedida;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estrato_id", nullable = false)
	public Estrato getEstrato() {
		return this.estrato;
	}

	public void setEstrato(Estrato estrato) {
		this.estrato = estrato;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tablas_id", nullable = false)
	public Tablas getTablas() {
		return this.tablas;
	}

	public void setTablas(Tablas tablas) {
		this.tablas = tablas;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "direccion_id")
	public Direcciones getDirecciones() {
		return this.direcciones;
	}

	public void setDirecciones(Direcciones direcciones) {
		this.direcciones = direcciones;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", nullable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ciudad_id", nullable = false)
	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_propiedad_id", nullable = false)
	public TipoPropiedad getTipoPropiedad() {
		return this.tipoPropiedad;
	}

	public void setTipoPropiedad(TipoPropiedad tipoPropiedad) {
		this.tipoPropiedad = tipoPropiedad;
	}

	@Column(name = "tipo", length = 25)
	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Column(name = "registro", length = 100)
	public String getRegistro() {
		return this.registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	@Column(name = "direccion", length = 150)
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Column(name = "unidad_medida", length = 20)
	public String getUnidadMedida() {
		return this.unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	@Column(name = "valor_medida", precision = 131089, scale = 0)
	public BigDecimal getValorMedida() {
		return this.valorMedida;
	}

	public void setValorMedida(BigDecimal valorMedida) {
		this.valorMedida = valorMedida;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "propiedad")
	public List<DetalleCotizacion> getDetalleCotizacions() {
		return this.detalleCotizacions;
	}

	public void setDetalleCotizacions(List<DetalleCotizacion> detalleCotizacions) {
		this.detalleCotizacions = detalleCotizacions;
	}

}
