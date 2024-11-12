/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package potrony.bru;

import java.util.List;
import potrony.bru.Interface.GestorSportManagerException;
import potrony.bru.SportManager.EnumTitular;
import potrony.bru.SportManager.Equip;
import potrony.bru.SportManager.Jugador;
import potrony.bru.SportManager.Membre;

/**
 *
 * @author Vago
 */
public class ComprovarMembre {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            SportManagerOracle gestor = new SportManagerOracle();

            // Crear un objecte Membre per afegir a l'equip
            Membre membre = new Membre(3, 2, EnumTitular.C);
            
            // Afegir el membre a l'equip
            boolean afegit = gestor.afegirJugadorEquip(membre);

            if (afegit) {
                System.out.println("El jugador s'ha afegit correctament a l'equip.");
                
                boolean esTitular = gestor.esTitular(membre.getIdJugador());
                if (esTitular) {
                    System.out.println("El jugador és titular.");
                } else {
                    System.out.println("El jugador no és titular.");
                }
            } else {
                System.out.println("No s'ha pogut afegir el jugador a l'equip.\n\n");
            }
            
            
            long idEquip = 1; 
            long idJugador = 2; 

            // Comprovar la funció loadJugadorsIdEquip
            System.out.println("Jugadors en l'equip amb ID " + idEquip + ":");
            try {
                List<Jugador> jugadors = gestor.loadJugadorsIdEquip(idEquip);
                for (Jugador jugador : jugadors) {
                    System.out.println("ID: " + jugador.getId() + ", Nom: " + jugador.getNom());
                }
            } catch (GestorSportManagerException ex) {
                System.out.println("Error: " + ex.getMessage());
            }

            // Comprovar la funció loadEquipsIdJugador
            System.out.println("\nEquips en els quals juga el jugador amb ID " + idJugador + ":");
            try {
                List<Equip> equips = gestor.loadEquipsIdJugador(idJugador);
                for (Equip equip : equips) {
                    System.out.println("ID: " + equip.getId() + ", Nom: " + equip.getNom());
                }
            } catch (GestorSportManagerException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            

        } catch (GestorSportManagerException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
}
