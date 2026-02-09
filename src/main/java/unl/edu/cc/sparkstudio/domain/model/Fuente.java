package unl.edu.cc.sparkstudio.domain.model;

/**
 * @Autor Grupo3
 */
public class Fuente extends Componente {

    public Fuente(){
        this.setMedida(TipoMedida.VOLT);
    }

    public Fuente(int Voltaje){
        super.setVoltaje(Voltaje);
    }

    public Fuente(float voltaje, float corriente, int resistencia, float potencia, TipoMedida medida) {
        super(voltaje, corriente, resistencia, potencia, TipoMedida.VOLT);
    }

    @Override
    public String toString() {
        return "Fuente{Voltaje ="+getVoltaje()+"}";
    }
}