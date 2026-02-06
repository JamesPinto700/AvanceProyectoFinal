package edu.unl.cc.poo.domain;

/**
 * @Autor Grupo3
 */
public class Diodo extends Componente{
    public Diodo() {
    }

    public Diodo(float voltaje, float corriente, int resistencia, float potencia, TipoMedida medida) {
        super(voltaje, corriente, resistencia, potencia, medida);
    }

    @Override
    public String toString() {
        return "Diodo{ Voltaje que pasa por el diodo: "+super.getVoltaje()+"}";
    }
}
