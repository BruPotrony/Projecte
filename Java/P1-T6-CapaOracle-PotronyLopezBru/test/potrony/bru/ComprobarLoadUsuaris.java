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
                System.out.println("Usuario cargado con éxito:");
                System.out.println("Login: " + usuari.getLogin());
                System.out.println("Nombre: " + usuari.getNom());
                System.out.println("Password: " + usuari.getPassword());
            } else {
                System.out.println("No se encontró un usuario con el login: " + login);
            }

        } catch (GestorSportManagerException ex) {
            System.out.println("Error al cargar el usuario: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error inesperado: " + ex.getMessage());
        }
        
        
        // Ruta del archivo de propiedades para la conexión a la base de datos
        String propertiesFile = "connectionBD.properties"; // Ajusta el nombre si es necesario
        
        try {
            // Crear instancia de SportManagerOracle con el archivo de propiedades
            SportManagerOracle sportManager = new SportManagerOracle(propertiesFile);
            
            // Llamar al método loadUsuaris para cargar todos los usuarios
            List<Usuari> usuaris = sportManager.loadUsuaris();
            
            // Verificar si se encontraron usuarios
            if (usuaris.isEmpty()) {
                System.out.println("No se encontraron usuarios.");
            } else {
                // Imprimir los usuarios cargados
                System.out.println("Usuarios encontrados:");
                for (Usuari usuari : usuaris) {
                    System.out.println("Login: " + usuari.getLogin() + ", Nombre: " + usuari.getNom());
                }
            }
            
        } catch (GestorSportManagerException ex) {
            // Manejar errores específicos del gestor de deportes
            System.err.println("Error en el gestor de deporte: " + ex.getMessage());
            ex.printStackTrace();
        } 
    }
    
}
