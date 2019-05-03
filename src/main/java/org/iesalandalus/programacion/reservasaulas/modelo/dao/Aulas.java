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
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;

/**
 *
 * @author Youness
 */
public class Aulas {
    private static final String NOMBRE_FICHERO_AULAS = "ficheros/aulas.dat";
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
  
    public void leer() throws FileNotFoundException, IOException {
        File ficheroAulas = new File(NOMBRE_FICHERO_AULAS);
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroAulas))) {
            Aula aula = null;
            do {
                aula = (Aula) entrada.readObject();
                insertar(aula);
            } while (aula != null);
        } catch (ClassNotFoundException e) {
            System.out.println("No puedo encontrar la clase que tengo que leer.");
        } catch (FileNotFoundException e) {
            System.out.println("No puedo abrir el fihero de aulas.");
        } catch (EOFException e) {
            System.out.println("Fichero aulas leído satisfactoriamente.");
        } catch (IOException e) {
            System.out.println("Error inesperado de Entrada/Salida.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void escribir() {
		try {
			File ficheroAulas = new File(NOMBRE_FICHERO_AULAS );
			FileOutputStream fos = new FileOutputStream(ficheroAulas);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			for(Aula a : coleccionAulas)
				oos.writeObject(a);
			oos.close();
		} catch(Exception e) {
			System.out.println("Error en la escritura del fichero aulas.dat");
		}
	}
    
}
