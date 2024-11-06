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
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String nomProperties = "connectionBD.properties";
            SportManagerOracle manager = new SportManagerOracle(nomProperties);

            List<Usuari> usuaris = new ArrayList<>();
            usuaris.add(new Usuari("usuari1", "2pasSw._434ord", "Juan"));
            usuaris.add(new Usuari("usuari2", "1pa232S_.ssword2", "Ana"));
            usuaris.add(new Usuari("usuari3", "1p_-as3232Dsword3", "Carlos"));

            boolean result = manager.saveUsuaris(usuaris);
            System.out.println("Usuarios guardados: " + result);
            
            manager.desferCanvis();
        } catch (GestorSportManagerException e) {
            e.printStackTrace();
        }
    }
    
}
