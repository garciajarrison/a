package extructura.banco;

public class Cuenta{
	
    double balance = 100;
    private String numeroCuenta;

    public Cuenta() {
    	
    }
    
    public Cuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
    
    public Cuenta (double balance, String numeroCuenta){
        if (balance>=100){
            this.balance = balance;
        }else{
            balance = 100;
        }
        
        this.numeroCuenta = numeroCuenta;
    }
  
    public void deposito(double deposito){
        if(deposito > 0){
            balance += deposito;
            System.out.println(deposito + "$ han sido depositados en su cuenta satisfactoriamente."
                                            + " Su nuevo blaance en su cuenta es " + balance);
        }else{
            System.err.println("Porfavor deposite un importe positivo");
        }
    }
    public void retiro(){
    
    }
    public void getBalance(){
    
    }
}