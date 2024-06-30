//import java.util.Scanner;

//import NumerosComplejos.Complejo;
import EcuacionesPolinomiales.*;

public class Main {
    public static void main(String args[]) throws Exception{
        //Scanner in = new Scanner(System.in);
        /*
        Complejo numA = new Complejo(7,3);
        Complejo numB = new Complejo(0,4);
        
        Pruebas
        System.out.println(numA.toString() + " + " +numB.toString()+" = "+ Complejo.suma(numA, numB).toString());
        System.out.println(numA.toString() + " - " +numB.toString()+" = "+ Complejo.resta(numA, numB).toString());
        System.out.println(numA.toString() + " * " +numB.toString()+" = "+ Complejo.multiplicación(numA, numB).toString());
        System.out.println("El conjugado de " + numA.toString() + " es: " + numA.getConjugado().toString());
        System.out.println(numA.toString() + " * " +numA.getConjugado().toString()+" = "+ Complejo.multiplicación(numA, numA.getConjugado()).toString());
        System.out.println(numA.toString() + " / " +numB.toString()+" = "+ Complejo.division(numA, numB).toString());
        */
        /*
        Raiz Cuadrada
        float n;
        do
        {
            try {
                n = in.nextFloat();
                System.out.println("La raiz cuadrada de "+ n +" es: " + Complejo.raizCuadrada(n));
            } catch (Exception e) {
                System.out.println("Ingrese un número válido de formato xx,xxx donde x = 0..9");
                break;
            }
        } while(true);
        System.out.printf("?- ");
        String input = in.next();
        char variable = input.charAt(0);
        Monomio m = new Monomio(4, 2, variable);
        System.out.println(m.toString());
        in.close();
        */

        Monomio m1 = new Monomio(4,'x',2);
        Monomio m2 = new Monomio(3,'x',1);
        Monomio m3 = new Monomio(2,'x',0);

        Polinomio p = new Polinomio();

        p.agregarMonomio(m2);
        p.agregarMonomio(m1);
        p.agregarMonomio(new Monomio(5,'x',3));
        p.agregarMonomio(m3);
        p.agregarMonomio(new Monomio(6,'x',3));

        System.out.println("Polinomio: " + p.toString() + "\nGrado: " + p.getGrado());

        p.unirTerminos();

        System.out.println("Polinomio: " + p.toString() + "\nGrado: " + p.getGrado());


    }
    
}