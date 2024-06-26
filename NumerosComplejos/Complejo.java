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
        return this.parte_real + " + " + this.parte_imaginaria + "i";
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
}
