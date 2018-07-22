package extructura.banco;

public class Cliente extends Usuario  {
	
	
	
	public double deposito(double valorDeposito) {
		
		if (this.getIdentificacion() == null || "".equals(this.getIdentificacion())) {
			System.out.println("Debe ingresar el numero de identificación");
			return 0;
		}else {
		
			this.setValorCuenta(this.getValorCuenta() + valorDeposito);
			
			System.out.println("el deposito se realizo con éxito, el valor en su cuenta es: " + this.getValorCuenta() );
			return this.getValorCuenta();
		}
	}
	
	public double retiro(double valorRetiro, String identificacion) {
		
		if(identificacion.equals(this.getIdentificacion())) {
		
			if (this.getIdentificacion() == null || "".equals(this.getIdentificacion())) {
				System.out.println("Debe ingresar el numero de identificación");
			}else if(this.getValorCuenta() > 0) {
				
				if(this.getValorCuenta() >= valorRetiro) {
			
					this.setValorCuenta(this.getValorCuenta() - valorRetiro);
					System.out.println("el valor del retiro fue " + valorRetiro);
					System.out.println("el retiro se realizo con éxito, el valor en su cuenta es: " + this.getValorCuenta());
					return this.getValorCuenta();
				}else {
					System.out.println("Fondos insuficientes");
				}
			}else {
				System.out.println("No tiene saldo en la cuenta para retirar");
			}
		}else {
			System.out.println("La identificación del cliente no coincide");
		}
	
		return 0;
	}
	
	

}
