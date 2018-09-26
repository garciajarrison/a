package co.com.avaluo.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "usuario", schema = "avalsoft")
public class Usuario implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private Rol rol;
	private Empresa empresa;
	private String tipoDocumento;
	private String identificacion;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String correo;
	private Date fechaNacimiento;
	private String direccion;
	private String telefono;
	private String celular;
	private String contrasena;
	private boolean estado;
	private String lenguaje;
	private String profesion;
	private String tipoPersona;
	private List<Contacto> contactos = new ArrayList<Contacto>();
	private List<Cotizacion> cotizacionsForRemitenteId = new ArrayList<Cotizacion>(0);
	private List<Cotizacion> cotizacionsForClienteId = new ArrayList<Cotizacion>(0);
	private List<Propiedad> propiedads = new ArrayList<Propiedad>(0);
	private List<Licencia> licencias = new ArrayList<Licencia>(0);
	private List<Avaluos> avaluoses = new ArrayList<Avaluos>(0);
	
	public Usuario() {
	}

	public Usuario(int id, Rol rol, Empresa empresa, boolean estado) {
		this.id = id;
		this.rol = rol;
		this.empresa = empresa;
		this.estado = estado;
	}

	public Usuario(int id, Rol rol, Empresa empresa, String tipoDocumento, String identificacion, String nombre, String apellido1, String apellido2,
			String correo, Date fechaNacimiento, String direccion, String telefono, String celular, String contrasena,
			boolean estado, String lenguaje, String profesion, String tipoPersona, List<Contacto> contactos, List<Cotizacion> cotizacionsForRemitenteId,
			List<Cotizacion> cotizacionsForClienteId, List<Licencia> licencias, List<Propiedad> propiedads) {
		this.id = id;
		this.rol = rol;
		this.empresa = empresa;
		this.tipoDocumento = tipoDocumento;
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.correo = correo;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.celular = celular;
		this.contrasena = contrasena;
		this.estado = estado;
		this.lenguaje = lenguaje;
		this.profesion = profesion;
		this.tipoPersona = tipoPersona;
		this.contactos = contactos;
		this.cotizacionsForClienteId = cotizacionsForClienteId;
		this.cotizacionsForRemitenteId = cotizacionsForRemitenteId;
		this.propiedads = propiedads;
		this.licencias = licencias;
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
	@JoinColumn(name = "rol_id", nullable = false)
	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@ManyToOne(fetch = FetchType.EAGER)
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

	@Column(name = "identificacion", length = 20)
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

	@Column(name = "apellido1")
	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	@Column(name = "apellido2")
	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario")
	public List<Contacto> getContactos() {
		return this.contactos;
	}

	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}

	@Column(name = "tipo_persona", length = 20)
	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuarioByRemitenteId")
	public List<Cotizacion> getCotizacionsForRemitenteId() {
		return this.cotizacionsForRemitenteId;
	}

	public void setCotizacionsForRemitenteId(List<Cotizacion> cotizacionsForRemitenteId) {
		this.cotizacionsForRemitenteId = cotizacionsForRemitenteId;
	}


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuarioByClienteId")
	public List<Cotizacion> getCotizacionsForClienteId() {
		return this.cotizacionsForClienteId;
	}

	public void setCotizacionsForClienteId(List<Cotizacion> cotizacionsForClienteId) {
		this.cotizacionsForClienteId = cotizacionsForClienteId;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario")
	public List<Propiedad> getPropiedads() {
		return this.propiedads;
	}

	public void setPropiedads(List<Propiedad> propiedads) {
		this.propiedads = propiedads;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario")
	public List<Licencia> getLicencias() {
		return this.licencias;
	}

	public void setLicencias(List<Licencia> licencias) {
		this.licencias = licencias;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	public List<Avaluos> getAvaluoses() {
		return this.avaluoses;
	}

	public void setAvaluoses(List<Avaluos> avaluoses) {
		this.avaluoses = avaluoses;
	}
	
	@Transient
	public String getNombreCompleto(){
		StringBuilder name = new StringBuilder();
		if(this.getNombre() != null)
			name.append(this.getNombre()).append(" ");
		if(this.getApellido1() != null)
			name.append(this.getApellido1()).append(" ");
		if(this.getApellido2() != null)
			name.append(this.getApellido2()).append(" ");
		return name.toString();
	}

}
