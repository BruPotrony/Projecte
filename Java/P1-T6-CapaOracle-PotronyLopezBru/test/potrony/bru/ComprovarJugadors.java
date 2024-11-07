/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import potrony.bru.Interface.GestorSportManagerException;
import potrony.bru.SportManager.Jugador;

/**
 *
 * @author Vago
 */
public class ComprovarJugadors {
    public static void main(String[] args) {
        try {
            SportManagerOracle gestor = new SportManagerOracle();
        } catch (GestorSportManagerException ex) {
            System.out.println("Error en instanciar SportManager");
        }
        
        List <Jugador> jugadors = new ArrayList<>();
        
        jugadors.add(new Jugador("Juan", "Pérez", 'H', "foto1.jpg", Date.valueOf("2005-04-15"), "Calle 1", 2025, "ES12345678901234567890", "IDLEGAL1"));
        jugadors.add(new Jugador("Maria", "López", 'D', "foto2.jpg", Date.valueOf("2006-06-20"), "Calle 2", 2024, "ES09876543210987654321", "IDLEGAL2"));
        jugadors.add(new Jugador("Carlos", "Gómez", 'H', "foto3.jpg", Date.valueOf("2004-02-25"), "Calle 3", 2023, "ES11223344556677889900", "IDLEGAL3"));
        
    }
}
