/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru;

import java.util.ArrayList;
import java.util.List;
import potrony.bru.CapaPersistencia.SportManagerOracle;
import potrony.bru.Interface.GestorSportManagerException;
import potrony.bru.SportManager.Usuari;

/**
 *
 * @author Vago
 */
public class ComprovarUsuaris {
    public static void main(String[] args) {
        
        SportManagerOracle manager=null;
        try {
            // Crear una instància de SportManagerOracle
            manager = new SportManagerOracle();
        } catch (GestorSportManagerException e) {
            System.err.println("Error: " + e.getMessage());
        }
        

        
        
        
        
        System.out.println("\n\n\nGuardar usuaris");
        List<Usuari> usuaris = new ArrayList<>();
            try {
                
                usuaris.add(new Usuari("user1sfgsdf", "Passadfsword1", "Joan", false));
                usuaris.add(new Usuari("user2", "Passwasdford2", "Maria", false));
                usuaris.add(new Usuari("user3", "Ecureasfdas1s", "Pere", false));
                usuaris.add(new Usuari("user4", "AdminasdfPass4", "Anna", false));
                usuaris.add(new Usuari("user5", "MyPasasdfs5", "Carla", false));
                usuaris.add(new Usuari("user6", "Supadasdfsaf2", "Marc", false));
                usuaris.add(new Usuari("user2", "Superusuari1", "Marc", false));

                    
                        for (Usuari usuari : usuaris) {
                            try {
                                
                                manager.saveUsuari(usuari);
                                
                                System.out.println("\nUsuari amb login "+usuari.getLogin()+" carregat correctament");
                            } catch (Exception e) {
                                System.out.println("\nError: "+ e.getMessage());
                            } 
                        }
                        
                
                
            } catch (Exception e) {
                System.out.println("\nError en carregar usuaris "+e.getMessage());
            }
            
            
        
        
        
        
        
        


            System.out.println("\n\n\nModificar usuari");
            try {
                Usuari usuariModificat = new Usuari("usuari1","password43Nou", "NomModificat",false);
                System.out.println("Modificant usuari...");
                manager.modificarContrassenya(usuaris.get(0).getLogin(), usuariModificat.getPassword());
                System.out.println("Usuari modificat correctament.");
            } catch (Exception e) {
                System.out.println("Error en modificar usuari");
            }
            

            // Comprovar que la modificació ha estat exitosa
            try {
                Usuari usuariDescarregat = manager.loadUsuariLogin(usuaris.get(1).getLogin());
                
            } catch (Exception e) {
                System.out.println("Error en cargar usuari amb id "+ usuaris.get(1).getLogin());
            }
            
            
            
            
            
            
            
            System.out.println("\n\n\nEliminar Usuari");
            for (Usuari usuari : usuaris) {
                try {
                    System.out.println("\nEliminant usuari amb login "+usuari.getLogin());
                    manager.eliminarUsuari(usuari.getLogin());
                    System.out.println("Usuari eliminat correctament");
                } catch (Exception e) {
                    System.out.println("Error en eliminar usuari amb login "+usuari.getLogin());
                }
            }







        try {
            manager.desferCanvis();
            manager.tancarConnexio();
            System.out.println("\n\n\nFet rollback i connexió tancada");
        } catch (Exception e) {
            System.out.println("Error en tancar la connexio o fer rollback "+e.getMessage());
        }
        
    }
}
