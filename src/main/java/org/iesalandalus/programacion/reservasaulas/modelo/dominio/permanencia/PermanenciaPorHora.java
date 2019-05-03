/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 *
 * @author Youness
 */
public class PermanenciaPorHora extends Permanencia{
    
    private static final int PUNTOS=3;
    private static final int HORA_INICIO=8;
    private static final int HORA_FIN=22;
    private static final DateTimeFormatter FORMATO_HORA=DateTimeFormatter.ofPattern("HH:mm");
    private LocalTime hora;
    
    public PermanenciaPorHora(LocalDate dia, LocalTime hora){
        super(dia);
        setHora(hora);
        
    }
    
     public PermanenciaPorHora(String dia, LocalTime hora){
        super(dia);
        setHora(hora);
        
    }
     
      public PermanenciaPorHora(LocalDate dia, String hora){
        super(dia);
        setHora(hora);
        
    }
        public PermanenciaPorHora(String dia, String hora){
        super(dia);
        setHora(hora);
        
    }
      
       public PermanenciaPorHora(PermanenciaPorHora copia1){
        if(copia1==null){
            throw new IllegalArgumentException("No se puede copiar una permanencia nula.");
        }else{
            super.dia=copia1.dia;
            this.hora=copia1.hora;
        }
        
    }

    public LocalTime getHora() {
        return hora;
    }

    private void setHora(LocalTime hora)throws IllegalArgumentException {
        if(hora==null){
            throw new IllegalArgumentException("La hora de una permanencia no puede ser nula.");
        }
        if (hora.getHour() < HORA_INICIO || hora.getHour() > HORA_FIN){
             throw new IllegalArgumentException("La hora de una permanencia debe estar comprendida entre las " + HORA_INICIO + " y las " + HORA_FIN + ".");
        }
	if (hora.getMinute() == 0){
			this.hora = hora;
        }else{
        throw new IllegalArgumentException("La hora de una permanencia debe ser una hora en punto.");
        }
    }
     
    private void setHora(String hora)throws IllegalArgumentException {
        if(hora==null){
            throw new IllegalArgumentException("La hora de una permanencia no puede ser nula.");
        }
        LocalTime hora1=null;
        try{
            hora1=LocalTime.parse(hora, FORMATO_HORA);
        }catch (DateTimeParseException e){
            throw new IllegalArgumentException("El formato de la hora de la permanencia no es correcto.");
        }
        if (hora1.getHour() < HORA_INICIO || hora1.getHour() > HORA_FIN){
             throw new IllegalArgumentException("La hora de una permanencia debe estar comprendida entre las " + HORA_INICIO + " y las " + HORA_FIN + ".");
        }
	if (hora1.getMinute() == 0){
			this.hora = hora1;
        }else{
        throw new IllegalArgumentException("La hora de una permanencia debe ser una hora en punto.");
        }
    }

    @Override
    public int getPuntos() {
        return PUNTOS; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "[dia=" + getDia().format(FORMATO_DIA) + ", hora=" + getHora().format(FORMATO_HORA) + "]";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.hora);
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
        final PermanenciaPorHora other = (PermanenciaPorHora) obj;
        if (!Objects.equals(this.hora, other.hora)) {
            return false;
        }
        return true;
    }
    

    
    
      
    
}
