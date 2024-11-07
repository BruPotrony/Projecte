/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package potrony.bru;

import java.util.List;
import potrony.bru.Interface.GestorSportManagerException;
import potrony.bru.SportManager.Usuari;

/**
 *
 * @author Vago
 */
public class ComprobarLoadUsuaris {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            SportManagerOracle manager = new SportManagerOracle("connectionBD.properties");

            String login = "Ronix888"; 
            Usuari usuari = manager.loadUsuariLogin(login);

            if (usuari != null) {
                System.out.println("Usuari cargat:");
                System.out.println("Login: " + usuari.getLogin());
                System.out.println("Nombre: " + usuari.getNom());
                System.out.println("Password: " + usuari.getPassword());
            } else {
                System.out.println("No es troba cap usuari amb el login: " + login);
            }

        } catch (GestorSportManagerException ex) {
            System.out.println("Error al cargar el usuario: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error inesperado: " + ex.getMessage());
        }
        
        
        String propertiesFile = "connectionBD.properties";
        
        try {
            SportManagerOracle sportManager = new SportManagerOracle(propertiesFile);
            
            List<Usuari> usuaris = sportManager.loadUsuaris();
            
            if (usuaris.isEmpty()) {
                System.out.println("No s'han trobat usuaris");
            } else {
                System.out.println("Usuarios trobats:");
                for (Usuari usuari : usuaris) {
                    System.out.println("Login: " + usuari.getLogin() + ", Nom: " + usuari.getNom());
                }
            }
            
        } catch (GestorSportManagerException ex) {
            System.err.println("Error en el gestor de deporte: " + ex.getMessage());
            
        } 
    }
    
}
