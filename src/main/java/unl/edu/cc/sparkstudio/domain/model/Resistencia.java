package unl.edu.cc.sparkstudio.domain.model;

/**
 * @Autor Grupo3
 */
public class Resistencia extends Componente {
    private static float RTotal = 0;

    public Resistencia(int resistencia) {
        setResistencia(resistencia);
        RTotal += resistencia;
        this.setMedida(TipoMedida.OHM);

    }

    public Resistencia(float voltaje, float corriente, int resistencia, float potencia, TipoMedida medida) {
        super(voltaje, corriente, resistencia, potencia, TipoMedida.OHM);
    }

    public void actualizarValores(){
        setValores(this.getVoltaje(), this.getResistencia());
    }

    public void setValores(float voltaje, int resistencia){
        super.setResistencia(resistencia);
        super.setVoltaje(voltaje * (resistencia/RTotal));
        super.calcularCorriente(voltaje, resistencia);
        super.calcularPotencia((double)voltaje, resistencia);
    }

    @Override
    public void modificarValor(int valor){
        RTotal = RTotal - this.getResistencia() + valor;
        setValores(this.getVoltaje(), valor);
    }

    @Override
    public void setResistencia(int resistencia) {
        super.setResistencia(resistencia);
        actualizarValores();
    }

    @Override
    public void setVoltaje(float voltaje) {
        super.setVoltaje(voltaje);
        actualizarValores();
    }

    @Override
    public String toString() {
        return "Resistencia{resistencia = "+this.getResistencia()+
                ", voltaje =" +this.getVoltaje()+
                ", corriente = "+this.getCorriente()+
                ", potencia = "+this.getPotencia()+
                ", Resistencia total = "+RTotal+"}";
    }
}