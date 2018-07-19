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
@Table(name = "detalle_tabla", schema = "avalsoft")
public class DetalleTabla implements java.io.Serializable, Comparable<DetalleTabla> {

	private int id;
	private Tablas tablas;
	private Long desde;
	private Long hasta;
	private Double porcentajeAplicar;

	public DetalleTabla() {
	}

	public DetalleTabla(int id) {
		this.id = id;
	}

	public DetalleTabla(int id, Tablas tablas, Long desde, Long hasta, Double porcentajeAplicar) {
		this.id = id;
		this.tablas = tablas;
		this.desde = desde;
		this.hasta = hasta;
		this.porcentajeAplicar = porcentajeAplicar;
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
	@JoinColumn(name = "tabla_id")
	public Tablas getTablas() {
		return this.tablas;
	}

	public void setTablas(Tablas tablas) {
		this.tablas = tablas;
	}

	@Column(name = "desde", precision = 10, scale = 0)
	public Long getDesde() {
		return this.desde;
	}

	public void setDesde(Long desde) {
		this.desde = desde;
	}

	@Column(name = "hasta", precision = 10, scale = 0)
	public Long getHasta() {
		return this.hasta;
	}

	public void setHasta(Long hasta) {
		this.hasta = hasta;
	}

	@Column(name = "porcentaje_aplicar", precision = 17, scale = 17)
	public Double getPorcentajeAplicar() {
		return this.porcentajeAplicar;
	}

	public void setPorcentajeAplicar(Double porcentajeAplicar) {
		this.porcentajeAplicar = porcentajeAplicar;
	}

	@Override
	public int compareTo(DetalleTabla arg0) {
		// TODO Auto-generated method stub
		 if (id < arg0.id) {
             return -1;
         }
         if (id > arg0.id) {
             return 1;
         }
         return 0;
		
	
	}

}