/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;

/**
 *
 * @author Youness
 */
public class Profesores {
    private static final String NOMBRE_FICHERO_PROFESORES = "ficheros/profesores.dat";
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
  
  public void leer() throws FileNotFoundException, IOException {
        File ficheroProfesores = new File(NOMBRE_FICHERO_PROFESORES);
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroProfesores))) {
            Profesor profesor = null;
            do {
                profesor = (Profesor) entrada.readObject();
                insertar(profesor);
            } while (profesor != null);
        } catch (ClassNotFoundException e) {
            System.out.println("No puedo encontrar la clase que tengo que leer.");
        } catch (FileNotFoundException e) {
            System.out.println("No puedo abrir el fihero de profesores.");
        } catch (EOFException e) {
            System.out.println("Fichero profesores leído satisfactoriamente.");
        } catch (IOException e) {
            System.out.println("Error inesperado de Entrada/Salida.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void escribir() {
        File ficheroProfesores = new File(NOMBRE_FICHERO_PROFESORES);
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ficheroProfesores))) {
            for (Profesor profesor : coleccionProfesores) {
                salida.writeObject(profesor);
            }
            System.out.println("Fichero aulas escrito satisfactoriamente.");
        } catch (FileNotFoundException e) {
            System.out.println("No puedo crear el fichero de profesores");
        } catch (IOException e) {
            System.out.println("Error inesperado de Entrada/Salida");
        }
    }
    
}
