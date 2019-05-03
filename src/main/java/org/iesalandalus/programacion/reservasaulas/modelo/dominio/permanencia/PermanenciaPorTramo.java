/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Youness
 */
public class PermanenciaPorTramo extends Permanencia{
    
    private static final int PUNTOS=10;
    private Tramo tramo;
    
    public PermanenciaPorTramo(LocalDate dia, Tramo tramo)throws IllegalArgumentException{
        super(dia);
        setTramo(tramo);
        
    }
    
    public PermanenciaPorTramo(String dia, Tramo tramo)throws IllegalArgumentException{
        super(dia);
        setTramo(tramo);
        
    }
    
    public PermanenciaPorTramo(PermanenciaPorTramo copia1)throws IllegalArgumentException{
        if(copia1==null){
            throw new IllegalArgumentException("No se puede copiar una permanencia nula.");
        }else{
            super.dia=copia1.dia;
            this.tramo=copia1.tramo;
        }
    }

    public Tramo getTramo() {
        return tramo;
    }

    public void setTramo(Tramo tramo)throws IllegalArgumentException {
        if(tramo==null){
            throw new IllegalArgumentException("El tramo de una permanencia no puede ser nulo.");
        }
        this.tramo = tramo;
    }
    
    

    @Override
    public int getPuntos() {
        return PUNTOS; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "[dia=" + getDia().format(FORMATO_DIA) + ", tramo=" + tramo + "]"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.tramo);
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
        final PermanenciaPorTramo other = (PermanenciaPorTramo) obj;
        if (this.tramo != other.tramo) {
            return false;
        }
        return true;
    }

    
    

    
    
}
