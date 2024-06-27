import NumerosComplejos.Complejo;

public class main{
    public static void main(String args[]){
        Complejo numA = new Complejo(7,3);
        Complejo numB = new Complejo(0,4);

        /*
        Pruebas
        System.out.println(numA.toString() + " + " +numB.toString()+" = "+ Complejo.suma(numA, numB).toString());
        System.out.println(numA.toString() + " - " +numB.toString()+" = "+ Complejo.resta(numA, numB).toString());
        System.out.println(numA.toString() + " * " +numB.toString()+" = "+ Complejo.multiplicación(numA, numB).toString());
        System.out.println("El conjugado de " + numA.toString() + " es: " + numA.getConjugado().toString());
        */
        System.out.println(numA.toString() + " * " +numA.getConjugado().toString()+" = "+ Complejo.multiplicación(numA, numA.getConjugado()).toString());
        System.out.println(numA.toString() + " / " +numB.toString()+" = "+ Complejo.division(numA, numB).toString());
    }
    
}