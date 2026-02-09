package unl.edu.cc.sparkstudio.domain.model;

import java.util.Objects;

/**
 * @Autor Grupo3
 */
public abstract class Componente {
    private float voltaje;
    private float corriente;
    private int resistencia;
    private float potencia;
    private float capacitancia;
    private TipoMedida medida;
    private boolean estado;
    private float frecuencia;

    public Componente() {
    }

    public Componente(float voltaje, float corriente, int resistencia, float potencia, TipoMedida medida) {
        this.voltaje = voltaje;
        this.corriente = corriente;
        this.resistencia = resistencia;
        this.potencia = potencia;
        this.medida = medida;
    }

    public Componente(float voltaje, float corriente, float potencia, TipoMedida medida) {
        this.voltaje = voltaje;
        this.corriente = corriente;
        this.potencia = potencia;
        this.medida = medida;
    }

    public void calcularVoltaje(float corriente, int resistencia) {
        float voltaje = corriente*resistencia;
        this.voltaje = voltaje;
    }

    public void calcularCorriente(float voltaje, int resistencia) {
        float corriente = 0;
        try {
            if(resistencia <= 0){
                this.corriente = 0;
                throw new ArithmeticException("Resistencia no puede ser menor o igual a 0");
            } else{
                corriente = voltaje / resistencia;
                this.corriente = corriente;
            }
        } catch (ArithmeticException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    public void calcularResistencia(float corriente, float voltaje){
        int resistencia = 0;
        try{
            if(corriente <= 0){
                this.resistencia = 0;
                throw new ArithmeticException("corriente no puede ser menor o igual a 0");
            } else{
                resistencia = (int) (voltaje/corriente);
                this.resistencia = resistencia;
            }
        } catch (ArithmeticException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    public void calcularPotencia(float voltaje, float corriente){
        float potencia = voltaje*corriente;
        this.potencia = potencia;
    }

    public void calcularPotencia(double corriente, int resistencia){
        float potencia = (float)Math.sqrt(corriente)*resistencia;
        this.potencia = potencia;
    }

    public void calcularPotencia(double voltaje, float resistencia){
        float potencia = 0;
        try {
            if(resistencia <= 0){
                this.potencia = 0;
                throw new ArithmeticException("resistencia no puede ser menor o igual a 0");
            } else{
                potencia = (float)Math.sqrt(voltaje)/resistencia;
                this.potencia = potencia;
            }
        } catch (ArithmeticException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
    public void modificarValor(int valor){

    }

    public void actualizarValores(float Total){

    }

    public float getVoltaje() {
        return voltaje;
    }

    public void setVoltaje(float voltaje) {
        this.voltaje = voltaje;
    }

    public float getCorriente() {
        return corriente;
    }

    public void setCorriente(float corriente) {
        this.corriente = corriente;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public float getPotencia() {
        return potencia;
    }

    public void setPotencia(float potencia) {
        this.potencia = potencia;
    }

    public float getCapacitancia(){
        return capacitancia;
    }

    public void setCapacitancia(float capacitancia) {
        this.capacitancia = capacitancia;
    }

    public TipoMedida getMedida() {
        return medida;
    }

    public void setMedida(TipoMedida medida) {
        this.medida = medida;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setFrecuencia(float frecuencia) {
        this.frecuencia = frecuencia;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Componente that = (Componente) o;
        return Float.compare(voltaje, that.voltaje) == 0 && Float.compare(corriente, that.corriente) == 0 && resistencia == that.resistencia && Float.compare(potencia, that.potencia) == 0 && medida == that.medida;
    }

    @Override
    public int hashCode() {
        return Objects.hash(voltaje, corriente, resistencia, potencia, medida);
    }

    @Override
    public String toString() {
        return "Componente{" +
                "voltaje=" + voltaje +
                ", corriente=" + corriente +
                ", resistencia=" + resistencia +
                ", potencia=" + potencia +
                ", medida=" + medida +
                '}';
    }
}
