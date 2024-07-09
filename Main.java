import java.util.Scanner;

import NumerosComplejos.Complejo;

public class Main {
    public static void main(String args[]){
        Complejo numA = new Complejo(7,3);
        Complejo numB = new Complejo(0,4);
        
        System.out.println(numA.toString() + " + " +numB.toString()+" = "+ Complejo.suma(numA, numB).toString());
        System.out.println(numA.toString() + " - " +numB.toString()+" = "+ Complejo.resta(numA, numB).toString());
        System.out.println(numA.toString() + " * " +numB.toString()+" = "+ Complejo.multiplicación(numA, numB).toString());
        System.out.println("El conjugado de " + numA.toString() + " es: " + numA.getConjugado().toString());
        System.out.println(numA.toString() + " * " +numA.getConjugado().toString()+" = "+ Complejo.multiplicación(numA, numA.getConjugado()).toString());
        System.out.println(numA.toString() + " / " +numB.toString()+" = "+ Complejo.division(numA, numB).toString());
        Scanner in = new Scanner(System.in);
        float n;
        do
        {
            try {
                n = in.nextFloat();
                System.out.println("La raiz cuadrada de "+ n +" es: ±" + Complejo.raizCuadrada(n));
            } catch (Exception e) {
                System.out.println("Ingrese un número válido de formato xx,xxx donde x = 0..9");
                break;
            }
        } while(true);
        in.close();
    }
}