package extructura.banco;

public class Cliente extends Usuario  {
	
	
	
	public double deposito(double valorDeposito) {
		this.setValorCuenta(this.getValorCuenta() + valorDeposito);
		return this.getValorCuenta();
	}
	
	

}
