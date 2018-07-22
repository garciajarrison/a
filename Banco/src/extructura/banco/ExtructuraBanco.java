/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extructura.banco;

/**
 *
 * @author Alex
 */
public class ExtructuraBanco {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
    	Usuario usuario = new Usuario();
    	usuario.setNombre("Jarrison");
    	System.out.println(usuario.getNombre());
    	
    	Usuario usuario2 = new Usuario();
    	usuario2.setNombre("Alex");
    	System.out.println(usuario2.getNombre());
    	
    	usuario.setIdentificacion("123");
    	
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

