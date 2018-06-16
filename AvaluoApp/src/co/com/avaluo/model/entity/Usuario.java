package co.com.avaluo.model.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@javax.persistence.Entity(name="Entity")
public class Usuario {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	@Column(name="attribute")
	private String attribute;
	
	public Usuario(){}
	
	public Usuario(int id, String attribute) {
		super();
		this.id = id;
		this.attribute = attribute;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
}
