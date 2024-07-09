package EcuacionesPolinomiales;

import java.util.ArrayList;
import java.util.Collections;

import NumerosComplejos.Complejo;
import NumerosComplejos.Tupla;

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
        int coeficientePrincipal = (int)this.getCoeficienteDeGrado(this.grado);
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
        if(!factoresPrincipal.contains((int)getCoeficienteDeGrado(grado)))
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
            System.out.printf("i= %d; resultados[i-1]: %f; getCoeficienteDeGrado(grado - i): %f; resultado[i]: %f\n",i, resultados[i-1],getCoeficienteDeGrado(grado - i), resultados[i]);
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

    private Tupla<Expresión,Integer> factorComun(){
        int exponenteComun = this.terminos.get(terminos.size()-1).getExponente();
        Tupla<Expresión,Integer> ret = new Tupla<>(new Complejo(0), exponenteComun);
        for(Monomio m:terminos){
            m.setExponente(m.getExponente()-exponenteComun);
        }
        this.grado -= exponenteComun;
        //################Falta sacar multiplos comunes#############################################################
        return ret;
    }

    public ArrayList<Tupla<Expresión,Integer>> factorizarPolinomial() throws Exception{
        ArrayList<Tupla<Expresión,Integer>> factores = new ArrayList<>();
        Polinomio aux = this.clone();
        int ceros = 0;
        int safeguard = 0;

        while(ceros + safeguard < this.grado)
        {
            if(aux.terminos.size() == 1)
            {
                factores.add(new Tupla<Expresión, Integer>(new Complejo(0), aux.grado));
                ceros += aux.grado;
            }
            else if(aux.grado == 1)
            {
                factores.add(new Tupla<Expresión,Integer>(new ExpresiónRacional(new Monomio(-1*aux.getCoeficienteDeGrado(0),'x',0),new Monomio(aux.getCoeficienteDeGrado(1), 'x', 0)),1));
                ceros += aux.grado;
            }
            else if(aux.getCoeficienteDeGrado(0) == 0)
            {
                
                Tupla<Expresión,Integer> temp = aux.factorComun();
                ceros += temp.segundoTermino;
                factores.add(temp);
            }
            else if(aux.grado == 2)
            {
                
                //agregar cada respuesta con multiplicidad 1
                // retornar factores.
                safeguard++;
            }
            else if(safeguard < 0)//es probable que esta opción deba estar abajo
            {
                //Verificar si hay 3 términos únicamente y si el termino de grado mayor es el doble del término del grado intermedio.
                // En tal caso, es probable que estemos ante un polinomio que puede ser factorizado como cuadrado perfecto
                //
            }
            else
            {
                /*
                ArrayList<Float> posiblesCerosRacionales = this.getFactores();
                Polinomio cociente;
                    cociente = this.clone();
                    for(Float factor: posiblesCerosRacionales)
                    {
                        float resultado[] = cociente.divisionDePolinomio(factor);
                        if(resultado[cociente.getGrado()]==0)
                        {
                            System.out.println("División exacta. Factor: " + factor);
                            System.out.print("Resultado: ");
                            for(float res: resultado)
                                System.out.print(res+" | ");
                            System.out.println();
                            int grado = cociente.getGrado();
                            cociente = new Polinomio();
                            for(int i = 0; i < grado;i++)
                            {
                                cociente.agregarMonomio(new Monomio(resultado[i], 'x', grado-1-i));
                            }
                            System.out.println(cociente.toString());
                        }
                    }
                */
                safeguard++;
            } 
        }
        System.out.printf("***Se encontraron %d de %d ceros existentes***\n",ceros,grado,safeguard);
        for(Tupla<Expresión,Integer> t:factores){
            System.out.printf("Factor: %s, multiplicidad: %d\n",t.primerTermino.toString(),t.segundoTermino);
        }
        return factores;
    }
}
