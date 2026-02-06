package edu.unl.cc.poo.domain;

import java.util.Scanner;

/**
 * @Autor Grupo3
 */

public class CircuitoMain {
    public int identificar(Componente componente){
        if(componente.getClass().getName().contains("Resistencia")){
            return 1;
        } else if(componente.getClass().getName().contains("Capacitor")){
            return 2;
        } else if(componente.getClass().getName().contains("Switch")){
            return 3;
        } else if(componente.getClass().getName().contains("Fuente")){
            return 4;
        } else if(componente.getClass().getName().contains("Diodo")){
            return 5;
        }  else if(componente.getClass().getName().contains("Transistor")){
            return 6;
        }
        return 0;
    }
    public static void main(String[] args) throws Exception {
        CircuitoMain c = new CircuitoMain();
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido al Simulador de Circuitos");
        System.out.println("Seleccione el valor de la fuente de voltaje");
        Fuente fuente = new Fuente();
        fuente.setVoltaje(sc.nextFloat());
        Circuito circuito = new Circuito();
        circuito.agregar(fuente);
        System.out.println("Lista de componentes: "+circuito);
        int opcion;
        int opcion2;
        int posicion;
        float nuevovalor;
        boolean estado = true;
        while(estado == true){
            System.out.println("1. Añadir componente");
            System.out.println("2. Modificar valores de componente");
            System.out.println("3. Salir");
            opcion = sc.nextInt();
            switch(opcion){
                case 1:
                    System.out.println("1. Resistencia");
                    System.out.println("2. Capacitor");
                    System.out.println("3. Switch");
                    System.out.println("4. Diodo");
                    System.out.println("5. Transistor");
                    opcion2 = sc.nextInt();
                    switch(opcion2){
                        case 1:
                            System.out.println("Ingrese el valor de resistencia");
                            Resistencia resistencia = new Resistencia(sc.nextInt());
                            System.out.println("Resistencia: "+resistencia);
                            resistencia.setVoltaje(circuito.getVoltajeTotal());
                            circuito.agregar(resistencia);
                            c.actualizar(fuente.getVoltaje(), circuito);
                            break;
                        case 2:
                            Capacitor capacitor = new Capacitor();
                            System.out.println("Ingrese el valor de capacitancia");
                            capacitor.setValores(fuente.getVoltaje(), sc.nextFloat());
                            circuito.agregar(capacitor);
                            break;
                        case 3:
                            Switch switchh = new Switch();
                            System.out.println("Encendido o apagado? (true o false)");
                            switchh.setEstado(sc.nextBoolean());
                            circuito.agregar(switchh);
                            break;
                        case 4:
                            Diodo diodo = new Diodo();
                            System.out.println("Diodo creado");
                            diodo.setVoltaje(fuente.getVoltaje());
                            circuito.agregar(diodo);
                            break;
                        case 5:
                            Transistor transistor = new Transistor();
                            System.out.println("Ingrese el valor de frecuencia");
                            transistor.setFrecuencia(sc.nextFloat());
                            circuito.agregar(transistor);
                            break;
                    }
                    break;
                    case 2:
                        System.out.println("Ingrese la posicion del elemento a modificar");
                        posicion = sc.nextInt();
                        System.out.println("Componente seleccionado: ");
                        System.out.println(circuito.getComponente(posicion));
                        opcion2 = c.identificar(circuito.getComponente(posicion));
                        switch(opcion2){
                            case 0:
                                System.out.println("Este switch esta devolviendo 0");
                                break;
                            case 1:
                                System.out.println("Ingrese el nuevo valor de resistencia");
                                nuevovalor = sc.nextInt();
                                circuito.getComponente(posicion).modificarValor((int)nuevovalor);
                                c.actualizar(fuente.getVoltaje(), circuito);
                                break;
                            case 2:
                                System.out.println("Ingrese el nuevo valor de capacitancia");
                                nuevovalor = sc.nextFloat();
                                circuito.getComponente(posicion).setCapacitancia((int)nuevovalor);
                                break;
                            case 3:
                                System.out.println("Ingrese el nuevo valor logico del switch: true o false");
                                circuito.getComponente(posicion).setEstado(sc.nextBoolean());
                                break;
                            case 4:
                                System.out.println("Ingrese el nuevo valor del voltaje");
                                nuevovalor = sc.nextFloat();
                                circuito.getComponente(posicion).setVoltaje(nuevovalor);
                                c.actualizar(circuito.getComponente(posicion).getVoltaje(), circuito);
                                break;
                            case 5:
                                System.out.println("Es un Diodo nada mas sirve para rectificar no le puedes hacer cambios");
                                break;
                            case 6:
                                System.out.println("Ingrese el nuevo valor del transistor");
                                nuevovalor = sc.nextFloat();
                                circuito.getComponente(posicion).setFrecuencia(nuevovalor);
                                break;
                        }
                        break;
                case 3:
                    estado = false;
                    break;
            }
            System.out.println(circuito);
        }
    }

    public void actualizar(float voltaje, Circuito circuito){
        for(int i = 0; i <= circuito.lenght()-1; i++){
            circuito.getComponente(i).setVoltaje(voltaje);
        }
    }
}
