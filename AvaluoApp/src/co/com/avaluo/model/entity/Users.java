package co.com.avaluo.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users", schema = "avalsoft")
public class Users implements java.io.Serializable {

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	@ManyToOne(fetch = FetchType.LAZY)
	private Rol rol;
	@Column(name="name")
	private String name;
	@Column(name="email")
	private String email;
	@Column(name="address")
	private String address;
	@Column(name="phone")
	private String phone;
	@Column(name="celphone")
	private String celphone;
	@Column(name="password")
	private String password;
	@Column(name="state")
	private String state;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCelphone() {
		return celphone;
	}
	public void setCelphone(String celphone) {
		this.celphone = celphone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	

}
