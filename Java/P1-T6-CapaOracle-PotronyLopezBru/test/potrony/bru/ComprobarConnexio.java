/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package potrony.bru;

import potrony.bru.CapaPersistencia.SportManagerOracle;
import potrony.bru.Interface.GestorSportManagerException;

/**
 *
 * @author Vago
 */
public class ComprobarConnexio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SportManagerOracle sportManager = null;
        try {
            sportManager = new SportManagerOracle("connectionBD.properties");
            System.out.println("Connexió establerta correctament");

            
        } catch (GestorSportManagerException e) {
            e.printStackTrace();
            System.err.println("Error a l'establir la connexió: " + e.getMessage());
        } finally {
            if (sportManager != null) {
                try {
                    sportManager.tancarConnexio();
                    System.out.println("Connexió tancada correctament");
                } catch (GestorSportManagerException e) {
                    System.err.println("Error al tancar la connexió: " + e.getMessage());
                }
            }
        }
    }
    
}
