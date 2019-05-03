/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo;

import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;

/**
 *
 * @author Youness
 */
public interface IModeloReservasAulas {

    List<Aula> getAulas();

    int getNumAulas();

    List<String> representarAulas();

    Aula buscarAula(Aula aula);

    void insertarAula(Aula aula) throws OperationNotSupportedException, IllegalArgumentException;

    void borrarAula(Aula aula) throws OperationNotSupportedException, IllegalArgumentException;
    
    void leerAulas();
    
    void escribirAulas();

    List<Profesor> getProfesores();

    int getNumProfesores();

    List<String> representarProfesores();

    Profesor buscarProfesor(Profesor profesor);

    void insertarProfesor(Profesor profesor) throws OperationNotSupportedException, IllegalArgumentException;

    void borrarProfesor(Profesor profesor) throws OperationNotSupportedException, IllegalArgumentException;
    
    void leerProfesores();
    
    void escribirProfesores();

    List<Reserva> getReservas();

    int getNumReservas();

    List<String> representarReservas();

    Reserva buscarReserva(Reserva reserva);

    void realizarReserva(Reserva reserva) throws OperationNotSupportedException, IllegalArgumentException;

    void anularReserva(Reserva reserva) throws OperationNotSupportedException, IllegalArgumentException;

    List<Reserva> getReservasAula(Aula aula) throws IllegalArgumentException;

    List<Reserva> getReservasProfesor(Profesor profesor) throws IllegalArgumentException;

    List<Reserva> getReservasPermanencia(Permanencia permanencia) throws IllegalArgumentException;

    boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) throws IllegalArgumentException;
    
    void leerReservas();
    
    void escribirReservas();
    
    

}
