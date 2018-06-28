package co.com.avaluo.model.entity;
// Generated 18/06/2018 08:01:50 PM by Hibernate Tools 4.0.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name = "users", schema = "avalsoft")
public class Users implements java.io.Serializable {

	private int id;
	private Rol rol;
	private String name;
	private String email;
	private String address;
	private String phone;
	private String celphone;
	private String password;
	private boolean state;

	public Users() {
	}

	public Users(int id, Rol rol, String name, String email, String password, boolean state) {
		this.id = id;
		this.rol = rol;
		this.name = name;
		this.email = email;
		this.password = password;
		this.state = state;
	}

	public Users(int id, Rol rol, String name, String email, String address, String phone, String celphone,
			String password, boolean state) {
		this.id = id;
		this.rol = rol;
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.celphone = celphone;
		this.password = password;
		this.state = state;
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
	@JoinColumn(name = "id_rol", nullable = false)
	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Column(name = "name", nullable = false, length = 150)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "email", nullable = false, length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "address", length = 150)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "phone", length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "celphone", length = 20)
	public String getCelphone() {
		return this.celphone;
	}

	public void setCelphone(String celphone) {
		this.celphone = celphone;
	}

	@Column(name = "password", nullable = false, length = 200)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "state", nullable = false)
	public boolean isState() {
		return this.state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

}
