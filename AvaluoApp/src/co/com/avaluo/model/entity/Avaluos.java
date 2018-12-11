package co.com.avaluo.model.entity;
// Generated 24/09/2018 07:28:06 PM by Hibernate Tools 4.0.1.Final

import java.util.ArrayList;
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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Avaluos generated by hbm2java
 */
@Entity
@Table(name = "avaluos", schema = "avalsoft")
public class Avaluos implements java.io.Serializable {

	private int id;
	private Empresa empresa;
	private Propiedad propiedad;
	private Usuario usuario;
	private List<Visitas> visitases = new ArrayList();

	public Avaluos() {
	}

	public Avaluos(int id, Empresa empresa, Propiedad propiedad, Usuario usuario) {
		this.id = id;
		this.empresa = empresa;
		this.propiedad = propiedad;
		this.usuario = usuario;
	}
	

	public Avaluos(int id, Empresa empresa, Propiedad propiedad, Usuario usuario, List<Visitas> visitases) {
		this.id = id;
		this.empresa = empresa;
		this.propiedad = propiedad;
		this.usuario = usuario;
		this.visitases = visitases;
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
	@JoinColumn(name = "empresa_id", nullable = false)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "propiedad_id", nullable = false)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	public Propiedad getPropiedad() {
		return this.propiedad;
	}

	public void setPropiedad(Propiedad propiedad) {
		this.propiedad = propiedad;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id", nullable = false)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@OneToMany (fetch = FetchType.LAZY, mappedBy = "avaluos")
	public List<Visitas> getVisitases() {
		return visitases;
	}

	public void setVisitases(List<Visitas> visitases) {
		this.visitases = visitases;
	}

}
