package EcuacionesPolinomiales;

import java.util.ArrayList;
import java.util.Collections;

public class Polinomio implements Cloneable{
    private ArrayList<Monomio> terminos;
    private int grado;

    public Polinomio()
    {
        terminos = new ArrayList<>();
        grado =  0;
    }

    public void agregarMonomio(Monomio m){
        terminos.add(m);
        if(m.getExponente()>grado)
            grado = m.getExponente();
        unirTerminos();
        terminos.sort(null);
        Collections.reverse(terminos);
    }

    public String toString()
    {
        String ret = "";

        for(Monomio m: terminos)
        {
            if(m.getExponente() == grado)
                ret = ret + m.toString();
            else
                ret = m.getCoeficiente() < 0 ? ret +" " + m.toString():ret + " + " + m.toString();
        }
        //ret = ret.substring(0, ret.length() - 2);

        return ret;
    }

    public int getGrado()
    {
        return grado;
    }

    private void unirTerminos(){
        for(int i = 0; i < terminos.size();i++)
        {
            for (int j = i + 1; j < terminos.size();j++)
            {
                Monomio termino_i = terminos.get(i);
                Monomio termino_j = terminos.get(j);
                if(termino_i.getExponente() == termino_j.getExponente())
                {
                    System.out.println("i: " + i + termino_i.getExponente() +" j: "+ termino_j.getExponente());
                    try {
                        terminos.set(i, Monomio.sumaMonomio(termino_i, termino_j));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    terminos.remove(j--);
                }
            }
        }
    }

    public ArrayList<Monomio> getTerminos(){
        return this.terminos;
    }

    public float getCoeficienteDeGrado(int exponente)
    {
        for(int i = 0; i < terminos.size(); i++)
        {
            if(terminos.get(i).getExponente() == exponente)
                return terminos.get(i).getCoeficiente();
        }
        return 0;
    }

    public ArrayList<Float> getFactores(){
        ArrayList<Integer> factoresConstante = new ArrayList<>();
        int coeficienteConstante = (int)this.getCoeficienteDeGrado(0);
        int i = 2;
        factoresConstante.add(1);
        //factores del coeficiente constante
        while(i <= coeficienteConstante/2)
        {
            if(coeficienteConstante%i==0)
            {
                coeficienteConstante = coeficienteConstante/i;
                if(!factoresConstante.contains(i))
                    factoresConstante.add(i);
                i = 2;
            }
            else
                i++;
        }
        if(!factoresConstante.contains(coeficienteConstante))
            factoresConstante.add(coeficienteConstante);
        factoresConstante.add((int)getCoeficienteDeGrado(0));
        // factores del coeficiente principal
        ArrayList<Integer> factoresPrincipal = new ArrayList<>();
        int coeficientePrincipal = (int)this.getCoeficienteDeGrado(2);
        i = 2;
        factoresPrincipal.add(1);
        while(i <= coeficientePrincipal/2)
        {
            if(coeficientePrincipal%i==0)
            {
                coeficientePrincipal = coeficientePrincipal/i;
                if(!factoresPrincipal.contains(i))
                    factoresPrincipal.add(i);
                i = 2;
            }
            else
                i++;
        }
        if(!factoresPrincipal.contains(coeficientePrincipal))
            factoresPrincipal.add(coeficientePrincipal);
        factoresPrincipal.add((int)getCoeficienteDeGrado(grado));
        // Lista de posibles ceros racionales
        ArrayList<Float> posiblesCerosRacionales = new ArrayList<>();
        for(i = 0; i < factoresConstante.size();i++)
        {
            for(int j = 0; j < factoresPrincipal.size();j++)
                {
                    float div = (float)factoresConstante.get(i)/factoresPrincipal.get(j);
                    if(!posiblesCerosRacionales.contains(div))
                    {
                        posiblesCerosRacionales.add(div);
                        posiblesCerosRacionales.add(div*-1);
                    }
                }
        }
        return posiblesCerosRacionales;
    }
    
    public float[] divisionDePolinomio(float c){
        float resultados[] = new float[grado + 1];
        resultados[0] = terminos.get(0).getCoeficiente();
        for(int i = 1; i <=grado; i++){
            resultados[i] = c * resultados[i-1] + getCoeficienteDeGrado(grado - i);
            //System.out.printf("i= %d; resultados[i-1]: %f; getCoeficienteDeGrado(grado - i): %f; resultado[i]: %f\n",i, resultados[i-1],getCoeficienteDeGrado(grado - i), resultados[i]);
        }
        return resultados;
        
    }

    public Polinomio clone() throws CloneNotSupportedException{
        Polinomio clon = new Polinomio();
        clon.grado = this.grado;
        for(Monomio m: terminos){
            Monomio aux;
            try {
                aux = new Monomio(m.getCoeficiente(), m.getVariable(), m.getExponente());
                clon.terminos.add(aux);
            } catch (Exception e) {
                return null;
            }
        }
        return clon;
    }
}