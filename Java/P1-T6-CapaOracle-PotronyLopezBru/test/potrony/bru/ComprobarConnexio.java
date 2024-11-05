/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package potrony.bru;

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
            // Crea una instancia de SportManagerOracle
            sportManager = new SportManagerOracle("connectionBD.properties");
            System.out.println("Conexión establecida con éxito.");

            // Aquí puedes agregar más lógica para trabajar con la conexión, si es necesario
            
        } catch (GestorSportManagerException e) {
            System.err.println("Error al establecer la conexión: " + e.getMessage());
        } finally {
            // Asegúrate de cerrar la conexión al final
            if (sportManager != null) {
                try {
                    sportManager.tancarConnexio();
                    System.out.println("Conexión cerrada correctamente.");
                } catch (GestorSportManagerException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }
    
}
