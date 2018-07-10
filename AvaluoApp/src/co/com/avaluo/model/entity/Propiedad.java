package co.com.avaluo.model.entity;

import java.math.BigDecimal;
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
@Table(name = "propiedad", schema = "avalsoft")
public class Propiedad implements java.io.Serializable {

	private int id;
	private Empresa empresa;
	private Usuario usuario;
	private Ciudad ciudad;
	private String tipo;
	private String registro;
	private String direccion;
	private String unidadMedida;
	private BigDecimal valorMedida;
	private Set<DetalleCotizacion> detalleCotizacions = new HashSet<DetalleCotizacion>(0);


	public Propiedad() {
	}

	public Propiedad(int id, Empresa empresa, Usuario usuario, Ciudad ciudad) {
		this.id = id;
		this.empresa = empresa;
		this.usuario = usuario;
		this.ciudad = ciudad;
	}

	public Propiedad(int id, Empresa empresa, Usuario usuario, Ciudad ciudad, String tipo, String registro,
			String direccion, String unidadMedida, BigDecimal valorMedida, Set<DetalleCotizacion> detalleCotizacions
			) {
		this.id = id;
		this.empresa = empresa;
		this.usuario = usuario;
		this.ciudad = ciudad;
		this.tipo = tipo;
		this.registro = registro;
		this.direccion = direccion;
		this.unidadMedida = unidadMedida;
		this.valorMedida = valorMedida;
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
	@JoinColumn(name = "estrato_id", nullable = false)
	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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
	public Set<DetalleCotizacion> getDetalleCotizacions() {
		return this.detalleCotizacions;
	}

	public void setDetalleCotizacions(Set<DetalleCotizacion> detalleCotizacions) {
		this.detalleCotizacions = detalleCotizacions;
	}


}
