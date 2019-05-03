/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.dominio;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Youness
 */
public class Aula implements Serializable{
    private static final float PUNTOS_POR_PUESTO=0.5f;
    private static final int MIN_PUESTOS=10;
    private static final int MAX_PUESTOS=100;
    private String nombre;
    private int puestos;
    
    public Aula(String nombre, int puestos){
        setNombre(nombre);
        setPuestos(puestos);
    }
    
    
    
    public Aula(Aula Aula1)throws IllegalArgumentException{
        if(Aula1==null){
            throw new IllegalArgumentException("No se puede copiar un aula nula.");
            
        }else {
            this.nombre=Aula1.nombre;
            this.puestos=Aula1.puestos;
        }
        
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre)throws IllegalArgumentException {
        if(nombre==null){
            throw new IllegalArgumentException("El nombre del aula no puede ser nulo.");
        }
        if(nombre==""){
            throw new IllegalArgumentException("El nombre del aula no puede estar vacío.");
        }else {
        this.nombre = nombre;
        }
    }

    private void setPuestos(int puestos)throws IllegalArgumentException {
        if(puestos< MIN_PUESTOS || puestos>MAX_PUESTOS){
            throw new IllegalArgumentException("El número de puestos no es correcto.");
        }else{
            this.puestos = puestos;
        }
        
    }

    public int getPuestos() {
        return puestos;
    }
    
    public float getPuntos(){
        float puntos=0.0f;
        puntos=getPuestos()* PUNTOS_POR_PUESTO;
        return puntos;
    }
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.nombre);
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
        final Aula other = (Aula) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[nombre=" + nombre +", puestos="+puestos+ ']';
    }
    
    
}
