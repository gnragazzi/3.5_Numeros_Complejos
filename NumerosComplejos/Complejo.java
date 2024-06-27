package NumerosComplejos;

public class Complejo{
    private float parte_real;
    private float parte_imaginaria;

    public Complejo(){
        parte_real = 0;
        parte_imaginaria = 0;
    }
    public Complejo(float real, float imaginario){
        this.parte_real = real;
        this.parte_imaginaria = imaginario;
    }

    public String toString(){
        return parte_imaginaria == 0 ? String.valueOf(parte_real)  : parte_imaginaria > 0 ? this.parte_real + "+" + this.parte_imaginaria + "i" : String.valueOf(this.parte_real) + this.parte_imaginaria + "i";
    }

    public static Complejo suma(Complejo x, Complejo y){
        return new Complejo(x.parte_real + y.parte_real,x.parte_imaginaria + y.parte_imaginaria);
    }

    public static Complejo resta(Complejo x, Complejo y){
        return new Complejo(x.parte_real - y.parte_real,x.parte_imaginaria - y.parte_imaginaria);
    }

    public static Complejo multiplicación(Complejo x, Complejo y){
        // (a+bi)*(c+di) = ac + adi + bci + bdi² = ac-bd + (ad+bc)i (porque i² = -1)
        float a = x.parte_real;
        float b = x.parte_imaginaria;
        float c = y.parte_real;
        float d = y.parte_imaginaria;
        return new Complejo(a*c-b*d,a*d+b*c);
    }

    public static Complejo division(Complejo divisor, Complejo dividendo)
    {
        Complejo conjugadoDividendo = dividendo.getConjugado();
        float denominador = multiplicación(dividendo, conjugadoDividendo).getParteReal();
        Complejo resultado = Complejo.multiplicación(divisor,  conjugadoDividendo);
        resultado.dividirReal(denominador);
        return resultado;
    }

    public Complejo getConjugado(){
        return new Complejo(parte_real,parte_imaginaria * -1);
    }

    public float getParteReal(){
        return this.parte_real;
    }
    public float getParteImaginaria(){
        return this.parte_imaginaria;
    }
    public void setParteReal(float real){
        this.parte_real = real;
    }
    public void setParteImaginaria(float imaginaria){
        this.parte_imaginaria = imaginaria;
    }

    public void sumarReal(float sumando){
        this.parte_real += sumando;
    }
    public void restarReal(float substraendo){
        this.parte_real -= substraendo;
    }
    public void multiplicarReal(float multiplicando){
        this.parte_real *= multiplicando;
        this.parte_imaginaria *= multiplicando;
    }
    public void dividirReal(float dividendo){
        // implementar try/catch
        if(dividendo != 0)
        {
            this.parte_real /= dividendo;
            this.parte_imaginaria /= dividendo;
        }
    }

}
