/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.vista;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.OperationNotSupportedException;

/**
 *
 * @author Youness
 */
public enum Opcion {
    
    SALIR("Salir."){
        public void ejecutar(){
            vista.salir();
        }
    }, INSERTAR_AULA("Insertar un aula."){
        public void ejecutar(){
            vista.insertarAula();
        }
    }, BORRAR_AULA("Borrar un aula."){
        public void ejecutar(){
            vista.borrarAula();
        }
    }, BUSCAR_AULA("Buscar un aula."){
        public void ejecutar(){
            vista.buscarAula();
        }
    }, LISTAR_AULAS("Listar las aulas."){
        public void ejecutar(){
            vista.listarAulas();
        }
    }, INSERTAR_PROFESOR("Insertar un profesor."){
        public void ejecutar(){
            vista.insertarProfesor();
        }
    },  BORRAR_PROFESOR("Borrar un profesor."){
        public void ejecutar(){
            vista.borrarProfesor();
        }
    }, BUSCAR_PROFESOR("Buscar un profesor."){
        public void ejecutar(){
            vista.buscarProfesor();
        }
    }, LISTAR_PROFESORES("Listar los profesores."){
        public void ejecutar(){
            vista.listarProfesores();
        }
    }, INSERTAR_RESERVA("Realizar una reserva."){
        public void ejecutar(){
            vista.realizarReserva();
        }
    }, BORRAR_RESERVA("Anular una reserva."){
        public void ejecutar(){
            vista.anularReserva();
        }
    }, LISTAR_RESERVAS("Listar las reservas."){
        public void ejecutar(){
            vista.listarReservas();
        }
    }, LISTAR_RESERVAS_AULA("Listar las reservas de un aula."){
        public void ejecutar(){
            vista.listarReservasAula();
        }
    }, LISTAR_RESERVAS_PROFESOR("Listar las reservas de un profesor."){
        public void ejecutar(){
            vista.listarReservasProfesor();
        }
    }, LISTAR_RESERVAS_PERMANENCIA("Listar las reservas de una permanencia."){
        public void ejecutar(){
            vista.listarReservasPermanencia();
        }
    }, CONSULTAR_DISPONIBILIDAD("Consultar una disponibilidad."){
        public void ejecutar(){
            vista.consultarDisponibilidad();
        }
    };
    
    private String mensajeAMostrar;
    private static IVistaReservasAulas vista;
    
    private Opcion(String mensajeAMostrar){
        this.mensajeAMostrar=mensajeAMostrar;

    }
    
    public String getMensaje(){
        return mensajeAMostrar;
    }
    
    public abstract void ejecutar();

    protected static void setVista(IVistaReservasAulas vista1) {
        vista = vista1;
    }

    @Override
    public String toString() {
        return  String.format("%d.- %s", ordinal(), mensajeAMostrar);
    }
    
    public static boolean esOrdinalValido(int numero){
        if(numero>=0 && numero<values().length){
            return true;
        }
        return false;
    }
    
    public static Opcion getOpcionSegunOrdinal(int numero)throws OperationNotSupportedException{
        if(!esOrdinalValido(numero)){
            throw new OperationNotSupportedException("Opción invalida");
        }else{
            return Opcion.values()[numero];
        }
        
        
    }
    
    
    
    
    
    
    
    
    
    

}
