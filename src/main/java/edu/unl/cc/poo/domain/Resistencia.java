package edu.unl.cc.poo.domain;

/**
 * @Autor Grupo3
 */
public class Resistencia extends Componente {
    private static float RTotal = 0;

    public Resistencia(int resistencia) {
        setResistencia(resistencia);
        this.setMedida(TipoMedida.OHM);

    }

    public Resistencia(float voltaje, float corriente, int resistencia, float potencia, TipoMedida medida) {
        super(voltaje, corriente, resistencia, potencia, TipoMedida.OHM);
    }

    @Override
    public void actualizarValores(float Total){
        System.out.println("Resistencia: "+this.getResistencia());
        System.out.println("Voltaje: "+this.getVoltaje());
        setValores(this.getVoltaje(), this.getResistencia(), Total);
    }

    public void setValores(float voltaje, int resistencia, float Total){
        RTotal = Total;
        super.setResistencia(resistencia);
        super.setVoltaje(voltaje*(resistencia/Total));
        System.out.println(voltaje+" * "+resistencia+"/"+Total+" = "+voltaje*(resistencia/Total));
        super.calcularCorriente(voltaje, resistencia);
        super.calcularPotencia((double)voltaje, resistencia);
    }

    @Override
    public void modificarValor(int valor){
        RTotal = RTotal - this.getResistencia() + valor;
        //setValores(this.getVoltaje(), valor, );
    }

    @Override
    public void setResistencia(int resistencia) {
        super.setResistencia(resistencia);
    }

    @Override
    public void setVoltaje(float voltaje) {
        super.setVoltaje(voltaje);
        actualizarValores(RTotal);
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

