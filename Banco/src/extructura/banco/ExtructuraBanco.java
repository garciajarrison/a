package extructura.banco;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Alex
 */
public class ExtructuraBanco {

    /**
     * @param args the command line arguments
     * @throws ParseException 
     */
    public static void main(String[] args) throws ParseException {
    	
    	
    	
    	Cliente clienteAlex = new Cliente();
    	
    	//1 REALIZAMOS DEPOSITO
    	clienteAlex.setIdentificacion("71364174");
    	clienteAlex.deposito(500000);
    	
    	//2. REALIZAMOS UN RETIRO
    	Cliente clienteJarry = new Cliente();
    	clienteJarry.setIdentificacion("321654");
    	clienteJarry.deposito(5000000);
    	double valorRetiro = 200000;
    	clienteJarry.retiro(valorRetiro, "321654");
    	
    	
    	
    	//3.CALCULAR VACIONES
    	Empleado empleadoAlex = new Empleado();
    	DateFormat format = new SimpleDateFormat("DD/MM/YYYY");
    	empleadoAlex.setFechaIngreso(format.parse("05/05/2007"));
    	
    	
    /*	Cuenta c = new Cuenta();
        Cuenta c1 = new Cuenta("ALFA2321");
        Cuenta c2 = new Cuenta(150, "BETA2731");
        c1.deposito(200.50);
        
        Cliente cliente = new Cliente();
        cliente.setNombre("123");
        
        */
        
        
       /* empleado.get
        Usuario usuario = new Usuario();
        usuario.*/
        
    }
    
}

