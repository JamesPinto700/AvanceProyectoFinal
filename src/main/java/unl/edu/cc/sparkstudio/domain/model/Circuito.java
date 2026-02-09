package unl.edu.cc.sparkstudio.domain.model;

import java.util.ArrayList;
import java.util.List;
/**
 * @Autor Grupo3
 */
public class Circuito {
    private List<Componente> componentes;
    private float voltajeTotal = 0.0f;
    private float NFuentes = 0;
    private float resistenciaTotal = 0.0f;

    public Circuito() {
        this.componentes = new ArrayList<>();
    }

    public Circuito(Componente Fuente) {
        this.componentes = new ArrayList<>();
        this.componentes.add(Fuente);
    }
    public void agregar(Componente componente) {
        if (this.componentes == null) {
            this.componentes = new ArrayList<>();
        }
        if (!this.componentes.contains(componente)) {
            this.componentes.add(componente);
            if(componente.getClass().getName().contains("Resistencia")){
                resistenciaTotal += componente.getResistencia();
                componente.setVoltaje(voltajeTotal);
                componente.actualizarValores(resistenciaTotal);
            } else if(componente.getClass().getName().contains("Fuente")){
                voltajeTotal += componente.getVoltaje();
                NFuentes += 1;
                ordenarComponentes();
            } else if(componente.getClass().getName().contains("Capacitor")){
                voltajeTotal += componente.getVoltaje();
            } else {
                System.out.println("La salida fue else");
            }
        }
        actualizarValores(voltajeTotal);
    }

    public void agregar(Componente... componentes) {
        for (Componente componente : componentes) {
            agregar(componente);
        }
    }

    public void remover(Componente componente) {
        if (this.componentes.contains(componente)) {
            if(componente.getClass().getName().contains("Resistencia")){
                resistenciaTotal -= componente.getResistencia();
                componente.actualizarValores(resistenciaTotal);
                this.componentes.remove(componente);
            } else if(componente.getClass().getName().contains("Fuente")){
                voltajeTotal -= componente.getVoltaje();
                this.componentes.remove(componente);
            } else if(componente.getClass().getName().contains("Capacitor")){
                voltajeTotal -= componente.getVoltaje();
                this.componentes.remove(componente);
            } else {
                System.out.println("La salida fue else");
            }
        }
        actualizarValores(voltajeTotal);
    }

    public void actualizarValores(float voltaje) {
        for (Componente componente : componentes) {
            if(componente.getClass().getSimpleName().contains("Fuente")){
                System.out.println("Nada que añadir aqui");
            } else {
                componente.setVoltaje(voltaje);
            }
        }
    }

    public void ordenarComponentes(){
        for (int i = componentes.size()-1; i > 0; i--) {
            if(componentes.get(i).getClass().getSimpleName().equals("Fuente")){
                componentes.addFirst(componentes.get(i));
            }
        }
        for(int j = componentes.size()-1; j > NFuentes-1; j--) {
            if(componentes.get(j).getClass().getSimpleName().equals("Fuente")){
                componentes.remove(j);
            }
        }
    }

    public void modificarFuente(Componente componente, float voltaje) {
        voltajeTotal -= componente.getVoltaje();
        componente.setVoltaje(voltaje);
        voltajeTotal += voltaje;
        actualizarValores(voltajeTotal);
    }

    public void modificarResistencia(Componente componente, float resistencia) {
        resistenciaTotal =  resistenciaTotal-componente.getResistencia();
        componente.setResistencia((int)resistencia);
        resistenciaTotal += resistencia;
        componente.actualizarValores(resistenciaTotal);
        actualizarValores(voltajeTotal);
    }

    public int lenght() {
        return this.componentes != null ? this.componentes.size() : 0;
    }

    public Componente getComponente(int posicion) {
        return componentes.get(posicion);
    }

    public Componente setComponente(int posicion, Componente componente) {
        return componentes.set(posicion, componente);
    }

    public List<Componente> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<Componente> componentes) {
        this.componentes = componentes;
    }

    @Override
    public String toString() {
        return "Circuito{" +
                "componentes=" + componentes +
                '}';
    }

    public float getVoltajeTotal() {
        return voltajeTotal;
    }

    public void setVoltajeTotal(float voltajeTotal) {
        this.voltajeTotal = voltajeTotal;
    }

    public float getResistenciaTotal() {
        return resistenciaTotal;
    }

    public void setResistenciaTotal(float resistenciaTotal) {
        this.resistenciaTotal = resistenciaTotal;
    }

    public float getNFuentes() {
        return NFuentes;
    }

    public void setNFuentes(float NFuentes) {
        this.NFuentes = NFuentes;
    }
}