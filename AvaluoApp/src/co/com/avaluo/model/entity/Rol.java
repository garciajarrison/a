package co.com.avaluo.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Rol", schema = "avalsoft")
public class Rol implements java.io.Serializable {

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	private List<Users> users;
	@Column(name="name")
	private String name;
	@Column(name="description")
	private String description;
	@Column(name="state")
	private boolean state;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Users> getUsers() {
		return users;
	}
	public void setUsers(List<Users> users) {
		this.users = users;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}


}
