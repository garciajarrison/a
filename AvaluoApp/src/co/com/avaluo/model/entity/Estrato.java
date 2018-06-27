package co.com.avaluo.model.entity;
// Generated 18/06/2018 08:01:50 PM by Hibernate Tools 4.0.1.Final

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Customer generated by hbm2java
 */
@Entity
@Table(name = "customer", schema = "avalsoft")
public class Estrato implements java.io.Serializable {

	private String customerIdentification;
	private int id;
	private String name;
	private String firstLastName;
	private String secondLastName;
	private String phone;
	private String address;
	private String email;
	private Serializable personType;

	public Estrato() {
	}

	public Estrato(String customerIdentification, int id) {
		this.customerIdentification = customerIdentification;
		this.id = id;
	}

	public Estrato(String customerIdentification, int id, String name, String firstLastName, String secondLastName,
			String phone, String address, String email, Serializable personType) {
		this.customerIdentification = customerIdentification;
		this.id = id;
		this.name = name;
		this.firstLastName = firstLastName;
		this.secondLastName = secondLastName;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.personType = personType;
	}

	@Id

	@Column(name = "customer_identification", unique = true, nullable = false, length = 20)
	public String getCustomerIdentification() {
		return this.customerIdentification;
	}

	public void setCustomerIdentification(String customerIdentification) {
		this.customerIdentification = customerIdentification;
	}

	@Column(name = "id", nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "first_last_name", length = 200)
	public String getFirstLastName() {
		return this.firstLastName;
	}

	public void setFirstLastName(String firstLastName) {
		this.firstLastName = firstLastName;
	}

	@Column(name = "second_last_name", length = 200)
	public String getSecondLastName() {
		return this.secondLastName;
	}

	public void setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
	}

	@Column(name = "phone", length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "address", length = 300)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "email", length = 300)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "person_type")
	public Serializable getPersonType() {
		return this.personType;
	}

	public void setPersonType(Serializable personType) {
		this.personType = personType;
	}

}
