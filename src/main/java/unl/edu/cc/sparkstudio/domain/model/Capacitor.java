package unl.edu.cc.sparkstudio.domain.model;

/**
 * @Autor Grupo3
 */
public class Capacitor extends Componente {
    private float capacitancia;
    private float cargaElectrica;

    public Capacitor(){
        this.setMedida(TipoMedida.FARADS);
    }
    public Capacitor(float voltaje, float corriente, float potencia, TipoMedida medida, int reactanciaCapacitiva) {
        super(voltaje, corriente, potencia, TipoMedida.FARADS);
        this.capacitancia = reactanciaCapacitiva;
    }

    public void calcularCargaElectrica(float capacitancia, float voltaje){
        float cargaElectrica = capacitancia * voltaje;
        setCargaElectrica(cargaElectrica);
    }

    public float getCapacitancia() {
        return capacitancia;
    }

    public void actualizarValores(){
        setValores(getVoltaje(), getCapacitancia());
    }

    public void setValores(float voltaje, float capacitancia){
        this.capacitancia = capacitancia;
        super.setVoltaje(voltaje);
        calcularCargaElectrica(voltaje, capacitancia);
    }

    @Override
    public void setCapacitancia(float capacitancia) {
        this.capacitancia = capacitancia;
        actualizarValores();
    }

    @Override
    public void setVoltaje(float voltaje) {
        super.setVoltaje(voltaje);
        actualizarValores();
    }

    public float getCargaElectrica() {
        return cargaElectrica;
    }

    public void setCargaElectrica(float cargaElectrica) {
        this.cargaElectrica = cargaElectrica;
    }

    @Override
    public String toString() {
        return "Capacitor{"+
                "capacitancia = " + capacitancia +
                ", carga electrica = " + cargaElectrica+
                '}';
    }
}