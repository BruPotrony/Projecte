/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru;

import potrony.bru.CapaPersistencia.SportManagerOracle;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import potrony.bru.Interface.GestorSportManagerException;
import potrony.bru.SportManager.EnumSexe;
import potrony.bru.SportManager.Jugador;



/**
 *
 * @author Vago
 */
public class ComprovarJugadors {
    public static void main(String[] args) {
        
            SportManagerOracle gestor = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            
            try {
                gestor = new SportManagerOracle();
            } catch (GestorSportManagerException ex) {
                System.out.println("Error en instanciar SportManager");
            }
            
            
            
            
            
            System.out.println("\nGuardar Jugadors Correctes:");
            List <Jugador> jugadors = new ArrayList<>();
            
            System.out.println("\nGuardant jugadors:");
            try {
                jugadors.add(new Jugador("Juan", "López", EnumSexe.D, LocalDate.parse("2005-04-15", formatter), "foto1.jpg", "Calle 1","28720","Bellver de Cerdanya", "ES12345678901234567890", "IDLEGAL1", 2025));
                jugadors.add(new Jugador("María", "López", EnumSexe.D, LocalDate.parse("2006-06-20", formatter), "foto2.jpg", "Calle 2", "08700","Igualada", "ES09876543210987654321", "IDLEGAL2", 2024));
                jugadors.add(new Jugador("Carlos", "Gómez", EnumSexe.H, LocalDate.parse("2004-02-25", formatter), "foto3.jpg", "Calle 3","08700","Barcelona", "ES11223344556677889900", "IDLEGAL3", 2025));
            }catch (Exception ex) {
                System.out.println("Error en construir el jugador");
            }
            
            try {
                gestor.saveJugadors(jugadors);
                System.out.println("\nJugadors guardats\n");
            } catch (GestorSportManagerException ex) {
                System.out.println("Error en guardar el jugador "+ex.getMessage());
            }
            
            
            
            
            
            jugadors.clear();
            System.out.println("\nGuardar Jugadors Incorrectes:");
            
            System.out.println("\nGuardant jugadors:");
            try {
                jugadors.add(new Jugador("Juan", "López", EnumSexe.D, LocalDate.parse("2005-04-15", formatter), "foto1.jpg", "Calle 1","28720","Bellver de Cerdanya", "ES12345678901234567890", "IDLEGAL1", 2025));
                jugadors.add(new Jugador("María", "López", EnumSexe.D, LocalDate.parse("2006-06-20", formatter), "foto2.jpg", "Calle 2", "08700","Igualada", "ES09876543210987654321", "IDLEGAL2", 2024));
                jugadors.add(new Jugador("Carlos", "Gómez", EnumSexe.H, LocalDate.parse("2004-02-25", formatter), "foto3.jpg", "Calle 3","08700","Barcelona", "ES11223344556677889900", "IDLEGAL3", 2025));
            }catch (Exception ex) {
                System.out.println("Error en construir el jugador");
            }
            
            try {
                if(gestor.saveJugadors(jugadors)){
                    System.out.println("\nJugadors guardats"); 
                }
                  
            } catch (GestorSportManagerException ex) {
                System.out.println("Error en guardar el jugador ");
            }
            
            
            
            
            System.out.println("\nRecuperar Jugadors:");
            jugadors.clear();
        try {
            jugadors = gestor.loadJugadors();
        } catch (Exception ex) {
            System.out.println("Error en recuperar jugador "+ex);
        }
            
        for (Jugador jugador : jugadors) {
            System.out.println(jugador);
        }
        System.out.println("Jugadors recuperats");
        
        
        
        
        
        
        
        System.out.println("\nRecuperar Jugador per id Correcte");
        
        long id = 10;
        try {
            System.out.println(gestor.loadJugadorId(id));
            System.out.println("Jugador amb id "+id+" recuperat");
        } catch (Exception ex) {
            System.out.println("Error en recuperar el jugador amb id "+id);
        }
        
        
        
        System.out.println("\nRecuperar Jugador per id incorrecte");
        
        id=-4;
        try {
            System.out.println(gestor.loadJugadorId(id));
            System.out.println("Jugador amb id "+id+" recuperat");
        } catch (Exception ex) {
            System.out.println("Error en recuperar el jugador amb id "+id);
        }
        
        
        
        
        System.out.println("\nRecuperar Jugador per nom/cognom");
        
        String nom ="Ana";
        String cognom = "López";
        
        try {
            jugadors.clear();
            jugadors = gestor.loadJugadorNomCognom(nom, cognom);
            
            for (Jugador jugador : jugadors) {
                
                System.out.println(jugador);
            }
            
            System.out.println("\nJugadors recuperats\n");
        } catch (Exception ex) {
            System.out.println("Error en recuperar el jugador amb nom/cognom "+nom+"/"+cognom);
        }
        
        String idLegal = jugadors.get(0).getId_Legal();
        
        try {
            System.out.println("Hi ha algun jugador amb idLegal: "+idLegal+": "+gestor.jugadorIdLegalRepetit(idLegal));
        } catch (GestorSportManagerException ex) {
            System.out.println("Error en veure si "+idLegal+" esta repetit");
        }
        

        System.out.println("\nModificar jugador\n");
        try {
            long idJug = 3;

            // Carregar i mostrar el jugador original abans de la modificació
            Jugador jugAux = gestor.loadJugadorId(idJug);
            System.out.println("Jugador a modificar: \n" + jugAux);

            // Crear un nou objecte Jugador amb les dades modificades
            Jugador modificar = new Jugador("María", "López", EnumSexe.D, LocalDate.parse("2006-06-20", formatter), "foto2.jpg", "Calle 2","08700","Igualada", "ES09876543210987654321", "IDLEGAL9", 2024);

            // Cridar el mètode per modificar el jugador en la base de dades
            gestor.modificarJugador(jugAux.getId_Legal(), modificar);

            // Tornar a carregar el jugador modificat des de la base de dades i mostrar-lo per verificar els canvis
            Jugador jugadorModificat = gestor.loadJugadorId(idJug);
            System.out.println("\nJugador modificat: \n" + jugadorModificat);

        } catch (GestorSportManagerException ex) {
            System.out.println("Error en modificar jugador");
        }
        
        
        System.out.println("\nEliminar jugador(No s'hauria d'eliminar)");
        try {
            gestor.eliminarJugador(gestor.loadJugadorId((long)3));
        } catch (GestorSportManagerException ex) {
            System.out.println("Error en eliminar el jugador\n"+ ex.getMessage());
        }

    }
}
