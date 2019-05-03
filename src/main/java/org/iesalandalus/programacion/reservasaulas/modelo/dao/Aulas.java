/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import java.util.ArrayList;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;

/**
 *
 * @author Youness
 */
public class Aulas {
    private List<Aula> coleccionAulas;
  
  public Aulas(){
     
      coleccionAulas = new ArrayList<Aula>();
  }
  public Aulas(Aulas aulas1)throws IllegalArgumentException{
      if(aulas1==null){
          throw new IllegalArgumentException("No se pueden copiar aulas nulas.");
          
      }else {
          this.coleccionAulas=aulas1.coleccionAulas;
         
      }
      
  }

    private void setAulas(Aulas aulas)throws IllegalArgumentException {
        if(aulas==null){
            throw new IllegalArgumentException("No se puede insertar un aula nula.");
        }
        this.coleccionAulas = coleccionAulas;
     
    }
  
  private List<Aula> copiaProfundaAulas(List<Aula> coleccionAulas) {
		List<Aula> copiaProfunda = new ArrayList<Aula>();
                for(Aula copia: coleccionAulas){
                    copiaProfunda.add(new Aula(copia));
                }
		
		return copiaProfunda;
	}
  
  public List<Aula> getAulas(){
      
      
      return copiaProfundaAulas(coleccionAulas);
  }

    public int getNumAulas() {
        return coleccionAulas.size();
    }
    
  
  public void insertar(Aula aula)throws OperationNotSupportedException, IllegalArgumentException{
      if(aula==null){
            throw new IllegalArgumentException("No se puede insertar un aula nula.");
        }
      
      
      if(coleccionAulas.contains(aula)){
          throw new OperationNotSupportedException("El aula ya existe.");
      }else{
          coleccionAulas.add(aula);
      }  
  }
  
  public Aula buscar(Aula aula){
      if(aula==null){
          return null;
      }
      int indice=coleccionAulas.indexOf(aula);  
      if(indice==-1){
          return null;
      }else{
      return coleccionAulas.get(coleccionAulas.indexOf(aula));
      }
  }
  
  public void borrar(Aula aula)throws OperationNotSupportedException, IllegalArgumentException{
       if(aula==null){
            throw new IllegalArgumentException("No se puede borrar un aula nula.");
        }
     
      
      if(coleccionAulas.contains(aula)){
          coleccionAulas.remove(aula);
      }else {
          throw new OperationNotSupportedException("El aula a borrar no existe.");
      }
  }
  
 
  public List<String> representar(){
      List<String> aulasRepresentadas= new ArrayList<>();
      for(Aula copia: coleccionAulas){
          aulasRepresentadas.add(copia.toString());
      }
      
      return aulasRepresentadas;
  }
    
}
