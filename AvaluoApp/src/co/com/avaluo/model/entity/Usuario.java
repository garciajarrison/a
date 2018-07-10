package co.com.avaluo.model.entity;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "usuario", schema = "avalsoft")
public class Usuario implements java.io.Serializable {

	private int id;
	private Rol rol;
	private Empresa empresa;
	private String tipoDocumento;
	private String identificacion;
	private String nombre;
	private String correo;
	private Date fechaNacimiento;
	private String direccion;
	private String telefono;
	private String celular;
	private String contrasena;
	private boolean estado;
	private String lenguaje;
	private String profesion;
	private Set<Contacto> contactos = new HashSet<Contacto>(0);
	private Set<Propiedad> propiedads = new HashSet<Propiedad>(0);

	public Usuario() {
	}

	public Usuario(int id, Rol rol, Empresa empresa, boolean estado) {
		this.id = id;
		this.rol = rol;
		this.empresa = empresa;
		this.estado = estado;
	}

	public Usuario(int id, Rol rol, Empresa empresa, String tipoDocumento, String identificacion, String nombre,
			String correo, Date fechaNacimiento, String direccion, String telefono, String celular, String contrasena,
			boolean estado, String lenguaje, String profesion, Set<Contacto> contactos, Set<Propiedad> propiedads) {
		this.id = id;
		this.rol = rol;
		this.empresa = empresa;
		this.tipoDocumento = tipoDocumento;
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.correo = correo;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.celular = celular;
		this.contrasena = contrasena;
		this.estado = estado;
		this.lenguaje = lenguaje;
		this.profesion = profesion;
		this.contactos = contactos;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rol_id", nullable = false)
	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empresa_id", nullable = false)
	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Column(name = "tipo_documento", length = 10)
	public String getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	//TODO cree esta columna mal para q la cuadremos despues
	@Column(name = "indetificacion", length = 20)
	public String getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	@Column(name = "nombre", length = 50)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "correo", length = 80)
	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_nacimiento", length = 13)
	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Column(name = "direccion", length = 150)
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Column(name = "telefono", length = 20)
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "celular", length = 30)
	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Column(name = "contrasena", length = 150)
	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Column(name = "estado", nullable = false)
	public boolean isEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Column(name = "lenguaje", length = 2)
	public String getLenguaje() {
		return this.lenguaje;
	}

	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje;
	}

	@Column(name = "profesion", length = 150)
	public String getProfesion() {
		return this.profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<Contacto> getContactos() {
		return this.contactos;
	}

	public void setContactos(Set<Contacto> contactos) {
		this.contactos = contactos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<Propiedad> getPropiedads() {
		return this.propiedads;
	}

	public void setPropiedads(Set<Propiedad> propiedads) {
		this.propiedads = propiedads;
	}

}
