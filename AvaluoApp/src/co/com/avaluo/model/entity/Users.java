package co.com.avaluo.model.entity;
// Generated 28/06/2018 07:22:07 PM by Hibernate Tools 5.3.0.Beta2

import java.util.HashSet;
import java.util.Set;

/**
 * Users generated by hbm2java
 */
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
	private int idUsers;
	private Set marketCategorieses = new HashSet(0);
	private Set propertyTypes = new HashSet(0);

	public Users() {
	}

	public Users(int id, Rol rol, String name, String email, String password, boolean state, int idUsers) {
		this.id = id;
		this.rol = rol;
		this.name = name;
		this.email = email;
		this.password = password;
		this.state = state;
		this.idUsers = idUsers;
	}

	public Users(int id, Rol rol, String name, String email, String address, String phone, String celphone,
			String password, boolean state, int idUsers, Set marketCategorieses, Set propertyTypes) {
		this.id = id;
		this.rol = rol;
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.celphone = celphone;
		this.password = password;
		this.state = state;
		this.idUsers = idUsers;
		this.marketCategorieses = marketCategorieses;
		this.propertyTypes = propertyTypes;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCelphone() {
		return this.celphone;
	}

	public void setCelphone(String celphone) {
		this.celphone = celphone;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isState() {
		return this.state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public int getIdUsers() {
		return this.idUsers;
	}

	public void setIdUsers(int idUsers) {
		this.idUsers = idUsers;
	}

	public Set getMarketCategorieses() {
		return this.marketCategorieses;
	}

	public void setMarketCategorieses(Set marketCategorieses) {
		this.marketCategorieses = marketCategorieses;
	}

	public Set getPropertyTypes() {
		return this.propertyTypes;
	}

	public void setPropertyTypes(Set propertyTypes) {
		this.propertyTypes = propertyTypes;
	}

}
