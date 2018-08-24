package co.com.avaluo.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "licencia", schema = "avalsoft")
public class Licencia implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private Usuario usuario = new Usuario();
	private Ciudad ciudad = new Ciudad();
	private String nombre;
	private Date ultimoPago;
	private Date fechaExpiracion;
	private Date fechaUltimaConn;

	public Licencia() {
	}

	public Licencia(int id, Usuario usuario, Ciudad ciudad) {
		this.id = id;
		this.usuario = usuario;
		this.ciudad = ciudad;
	}

	public Licencia(int id, Usuario usuario, Ciudad ciudad, String nombre, Date ultimoPago, Date fechaExpiracion,
			Date fechaUltimaConn) {
		this.id = id;
		this.usuario = usuario;
		this.ciudad = ciudad;
		this.nombre = nombre;
		this.ultimoPago = ultimoPago;
		this.fechaExpiracion = fechaExpiracion;
		this.fechaUltimaConn = fechaUltimaConn;
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
	@JoinColumn(name = "usuario_id", nullable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ciudad_id", nullable = false)
	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	@Column(name = "nombre", length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ultimo_pago", length = 13)
	public Date getUltimoPago() {
		return this.ultimoPago;
	}

	public void setUltimoPago(Date ultimoPago) {
		this.ultimoPago = ultimoPago;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_expiracion", length = 13)
	public Date getFechaExpiracion() {
		return this.fechaExpiracion;
	}

	public void setFechaExpiracion(Date fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_ultima_conn", length = 13)
	public Date getFechaUltimaConn() {
		return this.fechaUltimaConn;
	}

	public void setFechaUltimaConn(Date fechaUltimaConn) {
		this.fechaUltimaConn = fechaUltimaConn;
	}

}
