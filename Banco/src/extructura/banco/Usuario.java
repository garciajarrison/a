package extructura.banco;

public class Usuario {
	
    private String nombre;
    private Cuenta cuenta;
    private String identificacion;
    private double valorCuenta;
    
    public void mostrar(){
    	
    }
    
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public double getValorCuenta() {
		return valorCuenta;
	}

	public void setValorCuenta(double valorCuenta) {
		this.valorCuenta = valorCuenta;
	}

	
	
	
}