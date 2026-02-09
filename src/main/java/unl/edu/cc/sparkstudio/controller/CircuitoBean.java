package unl.edu.cc.sparkstudio.controller;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import unl.edu.cc.sparkstudio.domain.model.*;

import java.io.Serializable;

/**
 * Autor James Rex
 */
@Named
@SessionScoped
public class CircuitoBean implements Serializable {
    private Circuito circuito;
    private Componente componenteSeleccionado;

    // Valores por defecto para nuevos componentes
    private float voltaje = 10.0f;
    private float corriente = 0.5f;
    private int resistencia = 1000;
    private float potencia = 6.0f;
    private float capacitancia = 0.0001f;
    private boolean estado = false;
    private float frecuencia = 1000000.0f;

    @PostConstruct
    public void init() {
        Componente Fuenteinicial = new Fuente(10);
        circuito = new Circuito();
        circuito.agregar(Fuenteinicial);
    }

    // Métodos para agregar componentes usando setters
    public void agregarFuente() {
        Fuente fuente = new Fuente();
        fuente.setVoltaje(voltaje);
        circuito.agregar(fuente);
    }

    public void agregarResistencia() {
        Resistencia res = new Resistencia(resistencia);
        res.setVoltaje(voltaje);
        circuito.agregar(res);
    }

    public void agregarCapacitor() {
        Capacitor cap = new Capacitor();
        cap.setVoltaje(voltaje);
        cap.setCapacitancia(capacitancia);
        circuito.agregar(cap);
    }

    public void agregarSwitch() {
        Switch sw = new Switch(estado);
        circuito.agregar(sw);
    }

    public void agregarTransistor() {
        Transistor trans = new Transistor();
        trans.setFrecuencia(frecuencia);
        circuito.agregar(trans);
    }

    // Para futura implementación de LED
    public void agregarLED() {
        // Si tienes clase LED, descomenta
        // LED led = new LED();
        // led.setVoltaje(voltaje);
        // circuito.agregar(led);
    }

    public void eliminarComponente(Componente componente) {
        circuito.remover(componente);
    }

    public void editarComponente(Componente componente) {
        if(componente.getClass().getSimpleName().equals("Fuente")) {
            circuito.modificarFuente(componente, this.getVoltaje());
        } else if(componente.getClass().getSimpleName().equals("Resistencia")) {
            circuito.modificarResistencia(componente, this.getResistencia());
        } else if(componente.getClass().getSimpleName().equals("Capacitor")) {

        } else if(componente.getClass().getSimpleName().equals("Switch")) {

        } else if(componente.getClass().getSimpleName().equals("Transistor")) {

        }
    }

    public void seleccionarComponente(Componente componente) {
        this.componenteSeleccionado = componente;
    }



    // Getters y Setters
    public Circuito getCircuito() { return circuito; }
    public void setCircuito(Circuito circuito) { this.circuito = circuito; }

    public Componente getComponenteSeleccionado() { return componenteSeleccionado; }
    public void setComponenteSeleccionado(Componente componenteSeleccionado) {
        this.componenteSeleccionado = componenteSeleccionado;
    }

    public float getVoltaje() { return voltaje; }
    public void setVoltaje(float voltaje) { this.voltaje = voltaje; }

    public float getCorriente() { return corriente; }
    public void setCorriente(float corriente) { this.corriente = corriente; }

    public int getResistencia() { return resistencia; }
    public void setResistencia(int resistencia) { this.resistencia = resistencia; }

    public float getPotencia() { return potencia; }
    public void setPotencia(float potencia) { this.potencia = potencia; }

    public float getCapacitancia() { return capacitancia; }
    public void setCapacitancia(float capacitancia) { this.capacitancia = capacitancia; }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    public float getFrecuencia() { return frecuencia; }
    public void setFrecuencia(float frecuencia) { this.frecuencia = frecuencia; }
}