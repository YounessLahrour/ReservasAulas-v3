/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.controlador;

import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.IModeloReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.vista.IVistaReservasAulas;

/**
 *
 * @author Youness
 */
public class ControladorReservasAulas implements IControladorReservasAulas {

    private IVistaReservasAulas vista;
    private IModeloReservasAulas modelo;

    public ControladorReservasAulas(IModeloReservasAulas modelo, IVistaReservasAulas vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista.setControlador((IControladorReservasAulas) this);
    }

    public void comenzar() {
        modelo.leerAulas();
        modelo.leerProfesores();
        modelo.leerReservas();
       
        vista.comenzar();
    }

    public void salir() {
        modelo.escribirAulas();
        modelo.escribirProfesores();
        modelo.escribirReservas();
        System.out.println("Hasta luego Lucas!!!");
        
    }

    public void insertarAula(Aula aula) throws OperationNotSupportedException, IllegalArgumentException {
        modelo.insertarAula(aula);
    }

    public void borrarAula(Aula aula) throws OperationNotSupportedException, IllegalArgumentException {
        modelo.borrarAula(aula);
    }

    public Aula buscarAula(Aula aula) {
        return modelo.buscarAula(aula);
    }

    public List<String> representarAulas() {
        return modelo.representarAulas();
    }

    public void insertarProfesor(Profesor profesor) throws OperationNotSupportedException, IllegalArgumentException {
        modelo.insertarProfesor(profesor);
    }

    public void borrarProfesor(Profesor profesor) throws OperationNotSupportedException, IllegalArgumentException {
        modelo.borrarProfesor(profesor);
    }

    public Profesor buscarProfesor(Profesor profesor) {
        return modelo.buscarProfesor(profesor);
    }

    public List<String> representarProfesores() {
        return modelo.representarProfesores();
    }

    public void realizarReserva(Reserva reserva) throws OperationNotSupportedException, IllegalArgumentException {
        modelo.realizarReserva(reserva);
    }

    public void anularReserva(Reserva reserva) throws OperationNotSupportedException, IllegalArgumentException {
        modelo.anularReserva(reserva);
    }

    public List<String> representarReservas() {
        return modelo.representarReservas();
    }

    public List<Reserva> getReservasAula(Aula aula) throws IllegalArgumentException {
        return modelo.getReservasAula(aula);
    }

    public List<Reserva> getReservasProfesor(Profesor profesor) throws IllegalArgumentException {
        return modelo.getReservasProfesor(profesor);
    }

    public List<Reserva> getReservasPermanencia(Permanencia permanencia) throws IllegalArgumentException {
        return modelo.getReservasPermanencia(permanencia);
    }

    public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) throws IllegalArgumentException {
        return modelo.consultarDisponibilidad(aula, permanencia);
    }

}
