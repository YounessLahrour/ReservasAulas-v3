/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import static java.time.temporal.TemporalQueries.zone;
import java.util.ArrayList;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorTramo;

/**
 *
 * @author Youness
 */
public class Reservas {

    private static final float MAX_PUNTOS_PROFESOR_MES = 200f;
    private List<Reserva> coleccionReservas;

    public Reservas() {

        coleccionReservas = new ArrayList<Reserva>();
    }

    public Reservas(Reservas reservas1) throws IllegalArgumentException {
        if (reservas1 == null) {
            throw new IllegalArgumentException("No se pueden copiar reservas nulas.");

        } else {
            this.coleccionReservas = reservas1.coleccionReservas;

        }

    }

    private void setReservas(Reservas reservas) throws IllegalArgumentException {
        if (reservas == null) {
            throw new IllegalArgumentException("No se puede realizar una reserva nula.");
        }
        this.coleccionReservas = coleccionReservas;

    }

    private List<Reserva> copiaProfundaReservas(List<Reserva> coleccionReservas) {
        List<Reserva> copiaProfunda = new ArrayList<Reserva>();
        for (Reserva copia : coleccionReservas) {
            copiaProfunda.add(new Reserva(copia));
        }

        return copiaProfunda;
    }

    public List<Reserva> getReservas() {

        return copiaProfundaReservas(coleccionReservas);
    }

    public int getNumReservas() {
        return coleccionReservas.size();
    }

    public void insertar(Reserva reserva) throws OperationNotSupportedException, IllegalArgumentException {
        if (reserva == null) {
            throw new IllegalArgumentException("No se puede realizar una reserva nula.");
        }

        if (!esMesSiguienteOPosterior(reserva)) {
            throw new OperationNotSupportedException("Sólo se pueden hacer reservas para el mes que viene o posteriores.");
        }
        if (getPuntosGastadosReserva(reserva) > MAX_PUNTOS_PROFESOR_MES) {
            throw new OperationNotSupportedException("Esta reserva excede los puntos máximos por mes para dicho profesor.");
        }

        Reserva diaReserva = getReservaDia(reserva.getPermanencia().getDia());
        if (diaReserva != null) {
            if (diaReserva.getPermanencia() instanceof PermanenciaPorTramo && reserva.getPermanencia() instanceof PermanenciaPorHora) {
                throw new OperationNotSupportedException("Ya se ha realizado una reserva por tramo para este día y aula.");
            }
            if (diaReserva.getPermanencia() instanceof PermanenciaPorHora && reserva.getPermanencia() instanceof PermanenciaPorTramo) {
                throw new OperationNotSupportedException("Ya se ha realizado una reserva por hora para este día y aula.");
            }
            if (this.coleccionReservas.contains(reserva)) {
                throw new OperationNotSupportedException("La reserva ya existe.");
            }
        }

        this.coleccionReservas.add(reserva);

    }

    private boolean esMesSiguienteOPosterior(Reserva aInsertar) {
        if (aInsertar == null) {
            throw new IllegalArgumentException("La reserva no puede ser nula.");
        }
        LocalDate mesSiguiente = LocalDate.now().plusMonths(1);
        if (aInsertar.getPermanencia().getDia().isBefore(LocalDate.of(mesSiguiente.getYear(), mesSiguiente.getMonth(), 1))) {
            return false;
        }
        return true;
    }

    private float getPuntosGastadosReserva(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("No se puede realizar una reserva nula.");
        }
        List<Reserva> reservaProfesor = getReservasProfesorMes(reserva.getProfesor(), reserva.getPermanencia().getDia());
        float puntosProfesorGastados = 0f;
        for (Reserva copia : reservaProfesor) {
            puntosProfesorGastados += copia.getPuntos();
        }
        return puntosProfesorGastados + reserva.getPuntos();

    }

    private List<Reserva> getReservasProfesorMes(Profesor profesor, LocalDate dia) {
        if (profesor == null) {
            throw new IllegalArgumentException("El profesor no puede ser nulo.");
        }
        if (dia == null) {
            throw new IllegalArgumentException("El día no puede ser nulo.");
        }

        List<Reserva> reservaProfesor = new ArrayList<>();

        for (Reserva copia : coleccionReservas) {
            if (copia.getProfesor().equals(profesor) && copia.getPermanencia().getDia().getMonthValue() == dia.getMonthValue() && copia.getPermanencia().getDia().getYear() == dia.getYear()) {
                reservaProfesor.add(copia);
            }
        }
        return reservaProfesor;

    }

    private Reserva getReservaDia(LocalDate dia) {
        if (dia == null) {
            throw new IllegalArgumentException("El día no puede ser nulo.");
        }
        for (Reserva copia : coleccionReservas) {
            if (copia.getPermanencia().getDia().equals(dia)) {
                return new Reserva(copia);
            } else {
                return null;
            }
        }

        return null;
    }

    public Reserva buscar(Reserva reserva) {
        if (reserva == null) {
            return null;
        }
        int indice = coleccionReservas.indexOf(reserva);
        if (indice == -1) {
            return null;
        } else {
            return coleccionReservas.get(coleccionReservas.indexOf(reserva));
        }
    }

    public void borrar(Reserva reserva) throws OperationNotSupportedException, IllegalArgumentException {
        if (reserva == null) {
            throw new IllegalArgumentException("No se puede anular una reserva nula.");
        }

        if (coleccionReservas.contains(reserva)) {
            coleccionReservas.remove(reserva);
        } else {
            throw new OperationNotSupportedException("La reserva a anular no existe.");
        }
    }

    public List<String> representar() {
        List<String> reservasRepresentadas = new ArrayList<>();
        for (Reserva copia : coleccionReservas) {
            reservasRepresentadas.add(copia.toString());
        }

        return reservasRepresentadas;
    }

    public List<Reserva> getReservasProfesor(Profesor profesor) throws IllegalArgumentException {
        if (profesor == null) {
            throw new IllegalArgumentException("No se puede tener reservas de un profesor nulo.");
        }

        List<Reserva> reservasProfesor = new ArrayList<Reserva>();
        for (Reserva copia : coleccionReservas) {
            if (copia.getProfesor().equals(profesor)) {
                reservasProfesor.add(new Reserva(copia));

            }
        }

        return reservasProfesor;
    }

    public List<Reserva> getReservasAula(Aula aula) throws IllegalArgumentException {
        if (aula == null) {
            throw new IllegalArgumentException("No se puede tener reservas de un aula nulo.");
        }

        List<Reserva> reservasAula = new ArrayList<Reserva>();

        for (Reserva copia : coleccionReservas) {
            if (copia.getAula().equals(aula)) {
                reservasAula.add(new Reserva(copia));
            }
        }

        return reservasAula;
    }

    public List<Reserva> getReservasPermanencia(Permanencia permanencia) throws IllegalArgumentException {
        if (permanencia == null) {
            throw new IllegalArgumentException("No se puede tener reservas de una permanencia nula.");
        }

        List<Reserva> reservasPermanencia = new ArrayList<Reserva>();

        for (Reserva copia : coleccionReservas) {
            if (copia.getPermanencia().equals(permanencia)) {
                reservasPermanencia.add(new Reserva(copia));
            }
        }

        return reservasPermanencia;
    }

    public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) throws IllegalArgumentException {
        if (aula == null) {
            throw new IllegalArgumentException("No se puede consultar la disponibilidad de un aula nula.");
        }
        if (permanencia == null) {
            throw new IllegalArgumentException("No se puede consultar la disponibilidad de una permanencia nula.");
        }

        for (Reserva copia : coleccionReservas) {
            if (copia.getAula().equals(aula) && copia.getPermanencia().getDia().equals(permanencia.getDia())) {
                return false;
            }

        }

        return true;
    }

}
