package extructura.banco;

import java.util.Calendar;
import java.util.Date;

public class Empleado extends Usuario {

	private Date fechaIngreso;
	private double salario;
	
	
    public int getDiasVacaciones() {
    	Calendar today = Calendar.getInstance();
    	int tiempoServicio = 0;
    	
    	tiempoServicio = today.get(Calendar.YEAR) - fechaIngreso.get(Calendar.YEAR);
    	return 0;
    }
    
    public double retirarSalario() {
    	return salario;
    }
    
    
    

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	
}
