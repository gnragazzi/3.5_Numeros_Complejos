import NumerosComplejos.Complejo;

public class main{
    public static void main(String args[]){
        Complejo numA = new Complejo(1,2);
        Complejo numB = new Complejo(5,7);

        System.out.println(numA.toString() + "+" +numB.toString()+"="+ Complejo.suma(numA, numB).toString());
        System.out.println(numA.toString() + "-" +numB.toString()+"="+ Complejo.resta(numA, numB).toString());
        System.out.println(numA.toString() + "*" +numB.toString()+"="+ Complejo.multiplicación(numA, numB).toString());
    }
}