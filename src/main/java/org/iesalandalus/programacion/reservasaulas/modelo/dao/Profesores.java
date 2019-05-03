/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import java.util.ArrayList;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;

/**
 *
 * @author Youness
 */
public class Profesores {
    private List<Profesor> coleccionProfesores;
  
  public Profesores(){
     
      coleccionProfesores = new ArrayList<Profesor>();
  }
  public Profesores(Profesores profesores1)throws IllegalArgumentException{
      if(profesores1==null){
          throw new IllegalArgumentException("No se pueden copiar profesores nulos.");
          
      }else {
          this.coleccionProfesores=profesores1.coleccionProfesores;
         
      }
      
  }

    private void setProfesores(Profesores profesores)throws IllegalArgumentException {
        if(profesores==null){
            throw new IllegalArgumentException("No se puede insertar un profesor nula.");
        }
        this.coleccionProfesores = coleccionProfesores;
     
    }
  
  private List<Profesor> copiaProfundaProfesores(List<Profesor> coleccionProfesores) {
		List<Profesor> copiaProfunda = new ArrayList<Profesor>();
                for(Profesor copia: coleccionProfesores){
                    copiaProfunda.add(new Profesor(copia));
                }
		
		return copiaProfunda;
	}
  
  public List<Profesor> getProfesores(){
      
      
      return copiaProfundaProfesores(coleccionProfesores);
  }

    public int getNumProfesores() {
        return coleccionProfesores.size();
    }
    
  
  public void insertar(Profesor profesor)throws OperationNotSupportedException, IllegalArgumentException{
      if(profesor==null){
            throw new IllegalArgumentException("No se puede insertar un profesor nulo.");
        }
      
      
      if(coleccionProfesores.contains(profesor)){
          throw new OperationNotSupportedException("El profesor ya existe.");
      }else{
          coleccionProfesores.add(profesor);
      }  
  }
  
  public Profesor buscar(Profesor profesor){
      if(profesor==null){
          return null;
      }
      int indice=coleccionProfesores.indexOf(profesor);  
      if(indice==-1){
          return null;
      }else{
      return coleccionProfesores.get(coleccionProfesores.indexOf(profesor));
      }
  }
  
  public void borrar(Profesor profesor)throws OperationNotSupportedException, IllegalArgumentException{
       if(profesor==null){
            throw new IllegalArgumentException("No se puede borrar un profesor nulo.");
        }
     
      
      if(coleccionProfesores.contains(profesor)){
          coleccionProfesores.remove(profesor);
      }else {
          throw new OperationNotSupportedException("El profesor a borrar no existe.");
      }
  }
  
 
  public List<String> representar(){
      List<String> profesoresRepresentados= new ArrayList<>();
      for(Profesor copia: coleccionProfesores){
          profesoresRepresentados.add(copia.toString());
      }
      
      return profesoresRepresentados;
  }
    
}
