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
        
    
    try {
            // Crear una instancia de SportManagerOracle
            SportManagerOracle sportManager=null;
        try {
            sportManager = new SportManagerOracle();
        } catch (GestorSportManagerException ex) {
            System.out.println("Error en instanciar SportManager");
        }

            // Crear y guardar una nueva temporada
            Temporada nuevaTemporada = new Temporada(2025);
            boolean temporadaGuardada=false;
        try {
            temporadaGuardada = sportManager.saveTemporada(nuevaTemporada);
        } catch (GestorSportManagerException ex) {
            System.out.println("Error en guardar la temporada");
        }
            System.out.println("Temporada guardada: " + temporadaGuardada);

            boolean temporadaRepetida=false;
        try {
            temporadaRepetida = sportManager.temporadaRepetida(2025);
        } catch (GestorSportManagerException ex) {
            System.out.println("Error en comprobar temporada repetida");
        }
            System.out.println("Temporada repetida: " + temporadaRepetida);

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

            // Eliminar una temporada
            boolean temporadaEliminada = sportManager.eliminarTemporada(2025);
            System.out.println("Temporada eliminada: " + temporadaEliminada);

        } catch (GestorSportManagerException ex) {
            System.out.println("Error al gestionar las temporadas: " + ex.getMessage());
        }
    
    
    
    }

}
