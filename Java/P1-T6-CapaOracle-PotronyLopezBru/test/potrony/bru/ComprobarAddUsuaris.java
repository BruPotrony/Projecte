/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package potrony.bru;

import java.util.ArrayList;
import java.util.List;
import potrony.bru.Interface.GestorSportManagerException;
import potrony.bru.SportManager.Usuari;

/**
 *
 * @author Vago
 */
public class ComprobarAddUsuaris {

    /**
     * Comproba crear un usuari i afegir-lo a la base de dades, un cop afegit fa rollback
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String nomProperties = "connectionBD.properties";
            SportManagerOracle manager = new SportManagerOracle(nomProperties);

            List<Usuari> usuaris = new ArrayList<>();
            usuaris.add(new Usuari("usuari1", "paswW1ord", "Juan",false));
            usuaris.add(new Usuari("usuari2", "1pa232SssWord2", "Ana",false));
            usuaris.add(new Usuari("usuari3", "1pas3232Dsword3", "Carlos",false));

            boolean result = manager.saveUsuaris(usuaris);
            System.out.println("Usuaris guardats: " + result);
            
            manager.desferCanvis();
        } catch (Throwable e) {
            System.out.println("Error en creaar la connexió");
            e.printStackTrace();
        }
    }
    
}
