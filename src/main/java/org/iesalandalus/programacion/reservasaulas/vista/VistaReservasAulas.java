/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.vista;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.controlador.IControladorReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorTramo;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Tramo;
import org.iesalandalus.programacion.reservasaulas.vista.IVistaReservasAulas;
/**
 *
 * @author Youness
 */
public class VistaReservasAulas implements IVistaReservasAulas{

    private static final String ERROR = "ERROR";
    private static final String NOMBRE_VALIDO = "Youness";
    private static final String CORREO_VALIDO = "yl@hotmail.com";
    private IControladorReservasAulas controlador;

    public VistaReservasAulas() {
        Opcion.setVista((IVistaReservasAulas) this);
    }

    public void setControlador(IControladorReservasAulas controlador) {
        this.controlador = controlador;
    }

    @Override
    public void comenzar(){ //throws OperationNotSupportedException {
        Opcion opcion;
        try 
        {
            do 
            {
                Consola.mostrarMenu();
            
                opcion = Opcion.getOpcionSegunOrdinal(Consola.elegirOpcion());
                opcion.ejecutar();
           
            
            } while (opcion != Opcion.SALIR);
        } 
        catch (OperationNotSupportedException ex) {
                Logger.getLogger(VistaReservasAulas.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public void salir() {
        System.out.println("Has salido del programa");
    }

    public void insertarAula() {
        Consola.mostrarCabecera("INSERTAR AULA");
        try {
            Aula aula = Consola.leerAula();
            controlador.insertarAula(aula);
        } catch (OperationNotSupportedException e) {
            System.out.println(ERROR + e.getMessage());
        }
        System.out.println("Aula insertada correctamente.");
    }

    public void borrarAula() {
        Consola.mostrarCabecera("BORRAR AULA");
        try {
            Aula aula = Consola.leerAula();
            controlador.borrarAula(aula);
            System.out.println("Aula eliminada correctamente.");
        } catch (OperationNotSupportedException | IllegalArgumentException e) {
            System.out.println(ERROR + e.getMessage());
        }
    }

    @Override
    public void buscarAula(){ // throws OperationNotSupportedException {
        Consola.mostrarCabecera("BUSCAR AULA");
        String nombre = Consola.leerNombreAula();
        Aula aula = new Aula(nombre, 20);
        Aula aulaABuscar = controlador.buscarAula(aula);
        if (aulaABuscar == null) {
            System.out.println(ERROR + "El aula a buscar no existe.");
        } else {
            System.out.println("Esta es la aula encontrada: " + aulaABuscar);
        }
    }

    public void listarAulas() {
        Consola.mostrarCabecera("LISTAR AULAS");
        List<String> aulasRepresentadas = controlador.representarAulas();
        if (aulasRepresentadas.isEmpty()) {
            System.out.println(ERROR + "No hay aulas.");
        }
        for (String copia : aulasRepresentadas) {
            System.out.println(copia);
        }
    }

    public void insertarProfesor() {
        Consola.mostrarCabecera("INSERTAR PROFESOR");
        try {
            Profesor profesor = Consola.leerProfesor();
            controlador.insertarProfesor(profesor);
            System.out.println("Profesor insertado correctamente.");
        } catch (OperationNotSupportedException e) {
            System.out.println(ERROR + e.getMessage());
        }
    }

    public void borrarProfesor() {
        Consola.mostrarCabecera("BORRAR PROFESOR");
        String nombre = Consola.leerNombreProfesor();
        Profesor profesor = new Profesor(nombre, CORREO_VALIDO);
        try {
            controlador.borrarProfesor(profesor);
            System.out.println("Profesor borrado correctamente.");
        } catch (OperationNotSupportedException e) {
            System.out.println(ERROR + e.getMessage());
        }
    }

    @Override
    public void buscarProfesor(){// throws OperationNotSupportedException {
        Consola.mostrarCabecera("BUSCAR PROFESOR");
        String nombre = Consola.leerNombreProfesor();
        Profesor profesor = new Profesor(nombre, CORREO_VALIDO);
        Profesor profesorABuscar = controlador.buscarProfesor(profesor);
        if (profesorABuscar == null) {
            System.out.println(ERROR + "El profesor a buscar no existe.");
        } else {
            System.out.println("Este es el profesor buscado: " + profesor);
        }
    }

    public void listarProfesores() {
        Consola.mostrarCabecera("LISTAR PROFESORES");
        List<String> profesoresRepresentados = controlador.representarProfesores();
        if (profesoresRepresentados.isEmpty()) {
            System.out.println(ERROR + "No hay prefesores.");
        }
        for (String copia : profesoresRepresentados) {
            System.out.println(copia);
        }
    }

    public void realizarReserva(){// throws OperationNotSupportedException {
        Consola.mostrarCabecera("REALIZAR RESERVA");
        String nombre = Consola.leerNombreProfesor();
        Profesor profesor = new Profesor(nombre, CORREO_VALIDO);
        boolean existeProfesor = true;
        if (controlador.buscarProfesor(profesor) == null) {
            System.out.println(ERROR + "El profesor insertado no existe.");
            existeProfesor = false;
        }
        Reserva reserva = null;
        if (existeProfesor) {
            try {
                reserva = leerReserva(profesor);
            } catch (OperationNotSupportedException ex) {
                Logger.getLogger(VistaReservasAulas.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (reserva == null) {
                System.out.println(ERROR + "El aula insertada no existe.");
            }
        }
        if (reserva == null) {
            System.out.println(ERROR + "La reserva no se ha podido realizar.");
        } else {
            try {
                controlador.realizarReserva(reserva);
                System.out.println("La reserva se ha realizado correctamente.");
            } catch (OperationNotSupportedException e) {
                System.out.println(ERROR + e.getMessage());
            }
        }
    }

    private Reserva leerReserva(Profesor profesor) throws OperationNotSupportedException {
        Profesor profesorABuscar = controlador.buscarProfesor(profesor);
        if (profesorABuscar == null) {
            return null;
        }
        String nombreAula = Consola.leerNombreAula();
        Aula aulaABuscar = controlador.buscarAula(new Aula(nombreAula, 20));
        if (aulaABuscar == null) {
            return null;
        }
        Reserva leida = null;
        try {
            int permanencia = Consola.elegirPermanencia();
            if (permanencia == 0) {
                leida = new Reserva(profesorABuscar, aulaABuscar, new PermanenciaPorHora(Consola.leerDia(), Consola.leerHora()));
            }
            if (permanencia == 1) {
                leida = new Reserva(profesorABuscar, aulaABuscar, new PermanenciaPorTramo(Consola.leerDia(), Consola.leerTramo()));
            }
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR + e.getMessage());
        }
        return leida;
    }

    public void anularReserva(){// throws OperationNotSupportedException {
        Consola.mostrarCabecera("ANULAR RESERVA");
        String nombre = Consola.leerNombreProfesor();
        Profesor profesor = new Profesor(nombre, CORREO_VALIDO);
        boolean existeProfesor = true;
        if (controlador.buscarProfesor(profesor) == null) {
            System.out.println(ERROR + "El profesor insertado no existe.");
            existeProfesor = false;
        }
        Reserva reserva = null;
        if (existeProfesor) {
            try {
                reserva = leerReserva(profesor);
            } catch (OperationNotSupportedException ex) {
                Logger.getLogger(VistaReservasAulas.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (reserva == null) {
                System.out.println("El aula insertada no existe.");
            }
        }
        if (reserva == null) {
            System.out.println("La reserva no se ha podido anular.");
        } else {
            try {
                controlador.anularReserva(reserva);
            } catch (OperationNotSupportedException e) {
                System.out.println(ERROR + e.getMessage());
            }
            System.out.println("Reserva anulación de reserva se ha realizado correctamente.");
        }
    }

    public void listarReservas() {
        Consola.mostrarCabecera("LISTAR RESERVAS");
        List<String> reservasRepresentadas = controlador.representarReservas();
        if (reservasRepresentadas.isEmpty()) {
            System.out.println("No hay reservas.");
        }
        for (String copia : reservasRepresentadas) {
            System.out.println(copia);
        }
    }

    @Override
    public void listarReservasAula(){ //throws OperationNotSupportedException {
        Consola.mostrarCabecera("LISTAR RESERVAS AULA");
        String nombre = Consola.leerNombreAula();
        Aula aula = new Aula(nombre, 20);
        boolean existeAula = true;
        if (controlador.buscarAula(aula) == null) {
            System.out.println(ERROR + "El aula insertada no existe.");
            existeAula = false;
        }
        List<Reserva> reservasRepresentadas = controlador.getReservasAula(aula);
        if (existeAula && reservasRepresentadas.isEmpty()) {
            System.out.println("No hay aulas reservadas.");
        }
        if (existeAula) {
            for (Reserva copia : reservasRepresentadas) {
                System.out.println(copia);
            }
        }
    }

    public void listarReservasProfesor(){ //throws OperationNotSupportedException {
        Consola.mostrarCabecera("LISTAR RESERVAS PROFESOR");
        String nombre = Consola.leerNombreProfesor();
        Profesor profesor = new Profesor(nombre, CORREO_VALIDO);
        boolean existeProfesor = true;
        if (controlador.buscarProfesor(profesor) == null) {
            System.out.println(ERROR + "El profesor insertado no existe.");
            existeProfesor = false;
        }
        List<Reserva> reservasRepresentadas = controlador.getReservasProfesor(profesor);
        if (existeProfesor && reservasRepresentadas.isEmpty()) {
            System.out.println("No hay aulas reservas con el profesor insertado.");
        }
        if (existeProfesor) {
            for (Reserva copia : reservasRepresentadas) {
                System.out.println(copia);
            }
        }
    }

    public void listarReservasPermanencia() {
        Consola.mostrarCabecera("LISTAR RESERVAS PERMANENCIA");
        Permanencia permanencia = null;
        int indice = Consola.elegirPermanencia();
        switch (indice) {
            case 0:
                try {
                    String dia = Consola.leerDia();
                    String hora = Consola.leerDia();
                    permanencia = new PermanenciaPorHora(dia, hora);
                } catch (Exception e) {
                    System.out.println(ERROR + e.getMessage());
                }
                break;
            case 1:
                try {
                    String dia = Consola.leerDia();
                    Tramo tramo = Consola.leerTramo();
                    permanencia = new PermanenciaPorTramo(dia, tramo);
                } catch (Exception e) {
                    System.out.println(ERROR + e.getMessage());
                }
        }
        List<Reserva> reservasRepresentadas = null;
        try {
            reservasRepresentadas = controlador.getReservasPermanencia(permanencia);
        } catch (Exception e) {
            System.out.println(ERROR + e.getMessage());
        }
        if (reservasRepresentadas != null) {
            if (reservasRepresentadas.isEmpty()) {
                System.out.println("No hay ninguna aula reservada con el tramo y día insertados.");
            }else{
                for (Reserva r : reservasRepresentadas) {
                    System.out.println(r);
                }
            }
        }
    }

    public void consultarDisponibilidad(){ //throws OperationNotSupportedException {
        Consola.mostrarCabecera("CONSULTAR DISPONIBILIDAD");
        Aula aula = null;
        Permanencia permanencia = null;
        boolean existeAula = true;
        try {
            aula = new Aula(Consola.leerNombreAula(), 10);
        } catch (Exception e) {
            System.out.println(ERROR + e.getMessage());
            existeAula = false;
        }
        Aula aulaCopia = controlador.buscarAula(aula);
        if (aulaCopia == null) {
            System.out.println(ERROR + "El aula insertada no existe.");
            existeAula = false;
        }
        if (existeAula) {
            switch (Consola.elegirPermanencia()) {
                case 0:
                    try {
                        String dia = Consola.leerDia();
                        String hora = Consola.leerDia();
                        permanencia = new PermanenciaPorHora(dia, hora);
                    } catch (Exception e) {
                        System.out.println(ERROR + e.getMessage());
                        existeAula = false;
                    }
                    break;
                case 1:
                    try {
                        String dia = Consola.leerDia();
                        Tramo tramo = Consola.leerTramo();
                        permanencia = new PermanenciaPorTramo(dia, tramo);
                    } catch (Exception e) {
                        System.out.println(ERROR + e.getMessage());
                        existeAula = false;
                    }
            }
        }
        if (aulaCopia != null) {
            try {
                if (controlador.consultarDisponibilidad(aulaCopia, permanencia)) {
                    System.out.println("El aula insertada esta libre para dicha permanencia.");
                } else {
                    System.out.println("El aula insertada no esta libre para dicha permanencia.");
                }
            } catch (Exception e) {
                System.out.println(ERROR + e.getMessage());
            }
        }
    }

}
