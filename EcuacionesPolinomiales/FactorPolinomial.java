package EcuacionesPolinomiales;

import NumerosComplejos.Complejo;
import NumerosComplejos.Tupla;

public class FactorPolinomial {
    float MultiploDeVariable = 1;
    Complejo cero;
    int multiplicidad;

    public FactorPolinomial(float mv,Complejo c, int m){
        this.MultiploDeVariable = mv;
        this.cero = c;
        this.multiplicidad = m;
    }

    public FactorPolinomial(Complejo c, int m){
        this.cero = c;
        this.multiplicidad = m;
    }

    public static Tupla<FactorPolinomial,FactorPolinomial> factorizarCuadratica(Polinomio p){
        float a = p.getCoeficienteDeGrado(2);
        float b = p.getCoeficienteDeGrado(1);
        float c = p.getCoeficienteDeGrado(0);

        Tupla<Complejo,Complejo> determinante = Complejo.raizCuadrada(b*b - 4* a * c);
        
        if(determinante.primerTermino.equals(determinante.segundoTermino))
        {
            determinante.primerTermino.sumarReal(b*-1);
            determinante.primerTermino.dividirReal(2*a);
            FactorPolinomial res = new FactorPolinomial(determinante.primerTermino, 2);
            return new Tupla<>(res, null);
        }
        else
        {
            determinante.primerTermino.sumarReal(b*-1);
            determinante.primerTermino.dividirReal(2*a);
            FactorPolinomial res = new FactorPolinomial(determinante.primerTermino, 2);
            determinante.segundoTermino.sumarReal(b*-1);
            determinante.segundoTermino.dividirReal(2*a);
            FactorPolinomial res2 = new FactorPolinomial(determinante.segundoTermino, 2);
            return new Tupla<>(res,res2);
        }
    }
    
    public String toString(){
        String s = String.format("(x-(%s))^%d", cero.toString(),multiplicidad);
        if(MultiploDeVariable != 1)
            s = String.format("%.2f%s", MultiploDeVariable,s);
        return s;
    }
}
