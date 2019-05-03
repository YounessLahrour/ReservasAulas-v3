/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.dominio;

import java.io.Serializable;
import java.util.Objects;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;

/**
 *
 * @author Youness
 */
public class Reserva implements Serializable{
     Aula aula;
    Profesor profesor;
    Permanencia permanencia;
    
    public Reserva( Profesor profesor, Aula aula, Permanencia permanencia){
        setAula(aula);
        setProfesor(profesor);
        setPermanencia(permanencia);
    }
    
    public Reserva(Reserva Reserva1)throws IllegalArgumentException{
        if(Reserva1==null){
            throw new IllegalArgumentException("No se puede copiar una reserva nula.");
        }else{
            this.aula=Reserva1.aula;
            this.permanencia=Reserva1.permanencia;
            this.profesor=Reserva1.profesor;
        }
    }

    public Aula getAula() {
        return aula;
    }

    private void setAula(Aula aula)throws IllegalArgumentException {
        if(aula==null){
            throw new IllegalArgumentException("La reserva debe ser para un aula concreta.");
        }else{
        this.aula = aula;
        }
    }

    public Profesor getProfesor() {
        return profesor;
    }

    private void setProfesor(Profesor profesor)throws IllegalArgumentException {
        if(profesor==null){
            throw new IllegalArgumentException("La reserva debe estar a nombre de un profesor.");
        }else{
        this.profesor = profesor;
        }
    }

    public Permanencia getPermanencia() {
        return permanencia;
    }
    
    public float getPuntos(){
        return permanencia.getPuntos() + aula.getPuntos();
    }

    private void setPermanencia(Permanencia permanencia)throws IllegalArgumentException {
        if(permanencia==null){
            throw new IllegalArgumentException("La reserva se debe hacer para una permanencia concreta.");
        }else{
        this.permanencia = permanencia;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.aula);
        hash = 73 * hash + Objects.hashCode(this.permanencia);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reserva other = (Reserva) obj;
        if (!Objects.equals(this.aula, other.aula)) {
            return false;
        }
        if (!Objects.equals(this.permanencia, other.permanencia)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        
        
       return "[profesor=" + getProfesor() + ", aula=" + getAula() + ", permanencia=" + getPermanencia() + ", puntos=" + getPuntos() + "]";
    }
    
}
