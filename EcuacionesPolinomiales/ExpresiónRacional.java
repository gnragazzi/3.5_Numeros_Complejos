package EcuacionesPolinomiales;

import java.lang.Exception;

public class ExpresiónRacional extends Expresión{
    Monomio numerador;
    Monomio denominador;

    public ExpresiónRacional(Monomio numerador, Monomio denominador) throws Exception
    {
        if(denominador.getCoeficiente() == 0)
            throw new Exception();
        else
            this.numerador = numerador;
            this.denominador = denominador;
    }

    public String toString()
    {
        return "("+numerador.toString() + "/"+denominador.toString()+")";
    }
}
