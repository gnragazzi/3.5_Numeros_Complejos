package EcuacionesPolinomiales;

import java.util.ArrayList;

import NumerosComplejos.*;

public class EcuacionPolinomial{
    Polinomio p;
    float igualadoA = 0; //eventualmente, tendré que implementar cuando la ecuación no está igualada a cero

    public EcuacionPolinomial(Polinomio pol, float val){
        this.p = pol;
        igualadoA = val;
    }
    public EcuacionPolinomial(Polinomio pol){
        this.p = pol;
    }
    
    public Tupla<Complejo,Complejo> resolverPolinomial(){
        if(p.getGrado() == 2)
        {
            float a = p.getCoeficienteDeGrado(2);
            float b = p.getCoeficienteDeGrado(1);
            float c = p.getCoeficienteDeGrado(0);
            //System.out.printf("a: %.2f b: %.2f c: %.2f\n",a,b,c);
            Tupla<Complejo,Complejo> determinante = Complejo.raizCuadrada(b*b - 4* a * c);
            //System.out.printf("Discriminante: %s, %s\n",resultado.primerTermino.toString(), resultado.segundoTermino.toString());
            Complejo res = determinante.primerTermino;
            Complejo resNegativa = determinante.segundoTermino;

            res.sumarReal(b*-1);
            res.dividirReal(2*a);
            
            resNegativa.sumarReal(b*-1);
            resNegativa.dividirReal(2*a);
            return new Tupla<Complejo,Complejo>(res, resNegativa);
        }
        else 
        {
            ArrayList<Float> posiblesCerosRacionales = p.getFactores();
            //Polinomio aux = p;

            for(Float factor: posiblesCerosRacionales)
            {
                p.divisionDePolinomio(factor);
            }
            return new Tupla<>(null, null);
        }    
    }

}