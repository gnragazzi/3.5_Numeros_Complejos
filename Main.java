//import java.util.Scanner;

import EcuacionesPolinomiales.*;

public class Main {
    public static void main(String args[]) throws Exception{
        
        Monomio m1 = new Monomio(3,'x',5);
        Monomio m2 = new Monomio(24,'x',3);
        Monomio m3 = new Monomio(48,'x',1);
        
        Polinomio p = new Polinomio();
        
        p.agregarMonomio(m3);
        p.agregarMonomio(m2);
        p.agregarMonomio(m1);
        
        p.factorizarPolinomial();

    }
    
}