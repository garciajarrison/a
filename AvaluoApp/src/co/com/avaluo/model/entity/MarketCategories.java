package co.com.avaluo.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "market_categories", schema = "avalsoft")
public class MarketCategories implements java.io.Serializable {

	private int id;
	private String name;
	private Double valor;

	public MarketCategories() {
	}

	public MarketCategories(int id) {
		this.id = id;
	}

	public MarketCategories(int id, String name, Double valor) {
		this.id = id;
		this.name = name;
		this.valor = valor;
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

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "valor", precision = 17, scale = 17)
	public Double getValor() {
		return this.valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
