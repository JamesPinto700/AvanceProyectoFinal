package unl.edu.cc.sparkstudio.domain.model;

/**
 * @Autor Grupo3
 */
public class Transistor extends Componente {
    private float frecuencia;

    public Transistor(){
    }

    public Transistor(float voltaje, float corriente, int resistencia, float potencia, TipoMedida medida, float frecuencia) {
        super(voltaje, corriente, resistencia, potencia, medida);
        this.frecuencia = frecuencia;
    }

    public float getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(float frecuencia) {
        this.frecuencia = frecuencia;
    }

    @Override
    public String toString() {
        return "Transistor{"+
                "frecuencia=" + frecuencia +
                '}';
    }
}