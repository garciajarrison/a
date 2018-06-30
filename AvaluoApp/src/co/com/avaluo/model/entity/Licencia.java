package co.com.avaluo.model.entity;
// Generated 30/06/2018 02:39:09 PM by Hibernate Tools 5.3.0.Beta2

import java.util.Date;

/**
 * Licencia generated by hbm2java
 */
public class Licencia implements java.io.Serializable {

	private int id;
	private Ciudad ciudad;
	private Empresa empresa;
	private String nombre;
	private Date ultimoPago;
	private Date fechaExpiracion;
	private Date fechaUltimaConn;

	public Licencia() {
	}

	public Licencia(int id, Ciudad ciudad, Empresa empresa) {
		this.id = id;
		this.ciudad = ciudad;
		this.empresa = empresa;
	}

	public Licencia(int id, Ciudad ciudad, Empresa empresa, String nombre, Date ultimoPago, Date fechaExpiracion,
			Date fechaUltimaConn) {
		this.id = id;
		this.ciudad = ciudad;
		this.empresa = empresa;
		this.nombre = nombre;
		this.ultimoPago = ultimoPago;
		this.fechaExpiracion = fechaExpiracion;
		this.fechaUltimaConn = fechaUltimaConn;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getUltimoPago() {
		return this.ultimoPago;
	}

	public void setUltimoPago(Date ultimoPago) {
		this.ultimoPago = ultimoPago;
	}

	public Date getFechaExpiracion() {
		return this.fechaExpiracion;
	}

	public void setFechaExpiracion(Date fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	public Date getFechaUltimaConn() {
		return this.fechaUltimaConn;
	}

	public void setFechaUltimaConn(Date fechaUltimaConn) {
		this.fechaUltimaConn = fechaUltimaConn;
	}

}
