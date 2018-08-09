package co.com.avaluo.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reporte", schema = "avalsoft")
public class Reporte implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private Empresa empresa;
	private String codigo;
	private String idContenido;
	private boolean visible;

	public Reporte() {
		empresa =  new Empresa();
	}

	public Reporte(int id, Empresa empresa, String codigo, String idContenido, boolean visible) {
		this.id = id;
		this.empresa = empresa;
		this.codigo = codigo;
		this.idContenido = idContenido;
		this.visible = visible;
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
	@JoinColumn(name = "empresa_id", nullable = false)
	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Column(name = "codigo", nullable = false, length = 150)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "visible", nullable = false)
	public boolean isVisible() {
		return this.visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Column(name = "contenido_id", nullable = false)
	public String getIdContenido() {
		return idContenido;
	}

	public void setIdContenido(String idContenido) {
		this.idContenido = idContenido;
	}

}
