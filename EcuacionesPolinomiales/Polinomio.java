package EcuacionesPolinomiales;

import java.util.ArrayList;

public class Polinomio{
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
        terminos.sort(null);
    }

    public String toString()
    {
        String ret = "";

        for(Monomio m: terminos)
        {
            ret = String.format("%s + %s", m.toString(),ret);
        }
        ret = ret.substring(0, ret.length() - 2);

        return ret;
    }

    public int getGrado()
    {
        return grado;
    }

    public void unirTerminos(){
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
}