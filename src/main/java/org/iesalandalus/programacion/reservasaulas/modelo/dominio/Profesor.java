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
public class Profesor implements Serializable{
    static final String ER_TELEFONO = "[69]([0-9]{8})";
    static final String ER_CORREO = "([0-9a-zA-Z._-]+@)([a-z]+[.])([a-z])+";
    private String nombre;
    private String telefono;
    private String correo;
    
    public Profesor(String nombre, String correo, String telefono){
        setNombre(nombre);
        setTelefono(telefono);
        setCorreo(correo);
        
    }
    
    public Profesor(String nombre, String correo){
        setNombre(nombre);
        setCorreo(correo);
    }
    
    public Profesor(Profesor Profesor1)throws IllegalArgumentException{
        
        if(Profesor1==null){
            throw new IllegalArgumentException("No se puede copiar un profesor nulo.");
        }else{
            this.correo=Profesor1.correo;
            this.nombre=Profesor1.nombre;
            this.telefono=Profesor1.telefono;
        }
        
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) throws IllegalArgumentException {
        
        if(nombre==null){
            throw new IllegalArgumentException("El nombre del profesor no puede ser nulo.");
        }
        if(nombre==""){
            throw new IllegalArgumentException("El nombre del profesor no puede estar vacío.");
        }else{
        this.nombre = nombre;
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) throws IllegalArgumentException {
        if(telefono==null){
            this.telefono = telefono;
        }else if(!telefono.matches(ER_TELEFONO)){
            throw new IllegalArgumentException("El teléfono del profesor no es válido.");
        }else{
           this.telefono = telefono;
        }
        
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo)throws IllegalArgumentException {
        if(correo==null){
            throw new IllegalArgumentException("El correo del profesor no puede ser nulo.");
        }
        if(correo==""){
            throw new IllegalArgumentException("El correo del profesor no es válido.");
        }
        if(correo.matches(ER_CORREO)){
        this.correo = correo;
        }else{
            throw new IllegalArgumentException("formato del correo inválido");
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.nombre);
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
        final Profesor other = (Profesor) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if(telefono!=null)
        return "[nombre=" + nombre +", correo=" + correo + ", telefono=" + telefono +  ']';
        else
            return "[nombre=" + nombre +", correo=" + correo +  ']';
    }
}
