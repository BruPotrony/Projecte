/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import potrony.bru.Interface.GestorSportManagerException;
import potrony.bru.SportManager.Temporada;

/**
 *
 * @author Vago
 */
public class ComprovarTemporades {
    public static void main(String[] args) {

        SportManagerOracle sportManager=null;
    try {
        sportManager = new SportManagerOracle();
    } catch (GestorSportManagerException ex) {
        System.out.println("Error en instanciar SportManager");
    }

        // Crear y guardar una nova temporada
        System.out.println("\nGuardar temporada correcte");
        Temporada novaTemporada = new Temporada(2025);
        boolean temporadaGuardada=false;
    try {
        temporadaGuardada = sportManager.saveTemporada(novaTemporada);
        System.out.println("Temporada guardada: " + temporadaGuardada);
    } catch (GestorSportManagerException ex) {
        System.out.println("Error en guardar la temporada");
    }







        // Crear y guardar una nova temporada erronia
        System.out.println("\nGuardar temporada erronia");
    try {

        Temporada novaTemporadaErronia = new Temporada(2040);

        temporadaGuardada = sportManager.saveTemporada(novaTemporadaErronia);
        System.out.println("Temporada guardada: " + temporadaGuardada);
    } catch (Exception ex) {
        System.out.println("Error en guardar la temporada\n"+ex.getMessage());
    }






        System.out.println("\nComprovar temporada repetida (temporada bona)");
        boolean temporadaRepetida=false;
    try {
        temporadaRepetida = sportManager.temporadaRepetida(2025);
        System.out.println("Temporada repetida: " + temporadaRepetida);
    } catch (GestorSportManagerException ex) {
        System.out.println("Error en comprobar temporada repetida");
    }



        System.out.println("\nComprovar temporada repetida (temporada falsa)");
        boolean temporadaRepetidaFalse=false;
    try {
        temporadaRepetida = sportManager.temporadaRepetida(2021);
        System.out.println("Temporada repetida: " + temporadaRepetidaFalse);
    } catch (GestorSportManagerException ex) {
        System.out.println("Error en comprobar temporada repetida");
    }






        System.out.println("\nCargar temporades actuals:");
        List<Temporada> temporadas=new ArrayList<>();
    try {
        temporadas = sportManager.loadTemporades();
    } catch (GestorSportManagerException ex) {
        System.out.println("Error en cargar les temporades");
    }
        System.out.println("Temporadas cargadas:");
        for (Temporada temporada : temporadas) {
            System.out.println("Año: " + temporada.getAny());
        }



        System.out.println("\nEliminar temporada correcte");
        // Eliminar una temporada

        boolean temporadaEliminada = false;
        try {
            temporadaEliminada = sportManager.eliminarTemporada(2025);
            System.out.println("Temporada eliminada: " + temporadaEliminada);
        } catch (GestorSportManagerException ex) {
            System.out.println("Error en eliminar la temporada: "+temporadaEliminada);
        }
        
        
        
        
        System.out.println("\nEliminar temporada false");
        // Eliminar una temporada
        temporadaEliminada=false;
        try {
            temporadaEliminada = sportManager.eliminarTemporada(2019);
            System.out.println("Temporada eliminada: " + temporadaEliminada);
        } catch (GestorSportManagerException ex) {
            System.out.println("Error en eliminar la temporada: "+temporadaEliminada);
        }
    
    }

}
