package EcuacionesPolinomiales;

import java.lang.Exception;

public class Monomio implements Comparable<Monomio>{
    private float coeficiente;
    private char variable = 'x';
    private int exponente = 0;


    public Monomio(float coeficiente, char variable, int exponente) throws Exception
    {
        this.coeficiente = coeficiente;
        this.exponente = exponente;
        if(Character.isLetter(variable))
            this.variable = Character.toLowerCase(variable);
        else    
        {
            throw new Exception("ERROR: el símbolo de variable ingresado '" + variable + "' es inválido.");
        }
    }
    public String toString()
    {
        if(exponente > 1)
            return String.format("%.2f%c^%d", coeficiente,variable,exponente);
        else if(exponente == 1)
            return String.format("%.2f%c", coeficiente,variable);
        else
            return String.format("%.2f", coeficiente);
    }
    
    public boolean equals(Object o){
        Monomio m = (Monomio)o;
        if (this.coeficiente == m.coeficiente && this.exponente== m.exponente && this.variable == m.variable)
            return true;
            else 
            return false;
        }
        
        @Override
        public int compareTo(Monomio m) {
            if(this.variable != m.variable)
            return -2;
            else if(this.exponente < m.exponente)
            return -1;
            else if(this.exponente > m.exponente)
            return 1;
            else
            {
                if(this.coeficiente<m.coeficiente)
                return -1;
                else if(this.coeficiente > m.coeficiente)
                return 1;
                else
                return 0;
            }
        }
        
        public static Monomio sumaMonomio(Monomio a, Monomio b) throws Exception
        {
            if (a.exponente != b.exponente || a.variable != b.variable)
                return null;
            else    
                return new Monomio(a.coeficiente + b.coeficiente, a.variable, a.exponente);
            
        }

        // Setters y Getters ....

        public int getExponente(){
            return this.exponente;
        }
        public float getCoeficiente(){
            return this.coeficiente;
        }


}