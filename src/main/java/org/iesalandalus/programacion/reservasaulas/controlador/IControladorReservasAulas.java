/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.controlador;

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
public interface IControladorReservasAulas {
    
    void comenzar();

	void salir();

	void insertarAula(Aula aula) throws OperationNotSupportedException, IllegalArgumentException;

	void borrarAula(Aula aula) throws OperationNotSupportedException, IllegalArgumentException;

	Aula buscarAula(Aula aula);

	List<String> representarAulas();

	void insertarProfesor(Profesor profesor) throws OperationNotSupportedException, IllegalArgumentException;

	void borrarProfesor(Profesor profesor) throws OperationNotSupportedException, IllegalArgumentException;

	Profesor buscarProfesor(Profesor profesor);

	List<String> representarProfesores();

	void realizarReserva(Reserva reserva) throws OperationNotSupportedException, IllegalArgumentException;

	void anularReserva(Reserva reserva) throws OperationNotSupportedException, IllegalArgumentException;

	List<String> representarReservas();

	List<Reserva> getReservasAula(Aula aula) throws IllegalArgumentException;

	List<Reserva> getReservasProfesor(Profesor profesor) throws IllegalArgumentException;

	List<Reserva> getReservasPermanencia(Permanencia permanencia) throws IllegalArgumentException;

	boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) throws IllegalArgumentException;
    
}
