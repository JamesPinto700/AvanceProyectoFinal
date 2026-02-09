package unl.edu.cc.sparkstudio.domain.model;

/**
 * @Autor Grupo3
 */
public class LED extends Componente {
    public LED() {
    }

    public LED(float voltaje, float corriente, int resistencia, float potencia, TipoMedida medida) {
        super(voltaje, corriente, resistencia, potencia, medida);
    }

    @Override
    public String toString() {
        return "Diodo{ Voltaje que pasa por el diodo: "+super.getVoltaje()+"}";
    }
}