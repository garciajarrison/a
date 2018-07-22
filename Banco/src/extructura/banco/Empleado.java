package extructura.banco;

import java.util.Date;

public class Empleado extends Usuario {

	private Date fechaIngreso;
	
    public int getDiasVacaciones() {
    	return 0;
    }
	
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
}
