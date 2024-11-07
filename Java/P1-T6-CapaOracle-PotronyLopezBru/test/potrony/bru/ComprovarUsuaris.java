/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru;

import potrony.bru.Interface.GestorSportManagerException;
import potrony.bru.SportManager.Usuari;

/**
 *
 * @author Vago
 */
public class ComprovarUsuaris {
    public static void main(String[] args) {
        try {
            // Crear una instància de SportManagerOracle
            SportManagerOracle manager = new SportManagerOracle();

            // Crear un usuari de prova
            Usuari usuariProva = new Usuari("usuari1", "Password1", "NomProva",false);
          
            // Guardar l'usuari de prova a la base de dades
            System.out.println("Guardant usuari...");
            manager.saveUsuari(usuariProva);
            System.out.println("Usuari guardat correctament.");
            
            if (manager.loginRepetit(usuariProva.getLogin())){
                System.out.println("Funcio per veure Login repetit funciona");
            }else{
                System.out.println("Usuari no trobat a la BD");
            }

            // Modificar l'usuari de prova
            Usuari usuariModificat = new Usuari("usuari1","password43Nou", "NomModificat",false);
            System.out.println("Modificant usuari...");
            manager.modificarUsuari("usuari1", usuariModificat);
            System.out.println("Usuari modificat correctament.");

            // Comprovar que la modificació ha estat exitosa
            Usuari usuariDescarregat = manager.loadUsuariLogin("usuari1");
            System.out.println("Usuari després de la modificació:");
            System.out.println("Login: " + usuariDescarregat.getLogin());
            System.out.println("Nom: " + usuariDescarregat.getNom());
            System.out.println("Password: " + usuariDescarregat.getPassword());

            // Eliminar l'usuari de prova
            System.out.println("Eliminant usuari...");
            manager.eliminarUsuari("usuari1");
            System.out.println("Usuari eliminat correctament.");

        } catch (GestorSportManagerException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
