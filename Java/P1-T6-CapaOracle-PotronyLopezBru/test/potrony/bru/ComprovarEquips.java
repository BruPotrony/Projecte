/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package potrony.bru;

import potrony.bru.CapaPersistencia.SportManagerOracle;
import java.util.ArrayList;
import java.util.List;
import potrony.bru.Interface.GestorSportManagerException;
import potrony.bru.SportManager.EnumTipus;
import potrony.bru.SportManager.Equip;

/**
 *
 * @author Vago
 */
public class ComprovarEquips {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SportManagerOracle gestor = null;

        try {
            gestor = new SportManagerOracle();
        } catch (GestorSportManagerException ex) {
            System.out.println("Error en instanciar SportManager");
        }

        
        System.out.println("\nRecuperar Equip per nom i temporada:");
        String nomEquip = "Benjamí Mixt";
        int temporada = 2024;
        try {
            Equip equipPerNom = gestor.loadEquipNom(nomEquip, temporada);
            System.out.println("Equip trobat: " + equipPerNom);
        } catch (GestorSportManagerException ex) {
            System.out.println("Error en recuperar equip per nom i temporada: " + ex.getMessage());
        }
        
        System.out.println("\nRecuperar Equip per nom i temporada Erronis:");
        String nomEquipErroni = "Benja Mixt";
        int temporadaErroni = 2024;
        try {
            Equip equipPerNom = gestor.loadEquipNom(nomEquipErroni, temporadaErroni);
            System.out.println("Equip trobat: " + equipPerNom);
        } catch (Exception ex) {
            System.out.println("Error en recuperar equip per nom i temporada: " + ex.getMessage());
        }

        System.out.println("\nRecuperar Equip per ID:");
        long idEquip = 1;
        try {
            Equip equipPerId = gestor.loadEquipId(idEquip);
            System.out.println("Equip trobat: " + equipPerId);
        } catch (GestorSportManagerException ex) {
            System.out.println("Error en recuperar equip per ID: " + ex.getMessage());
        }
        
        
        System.out.println("\nRecuperar Equip per ID Erroni:");
        long idEquipErroni = 18;
        try {
            Equip equipPerId = gestor.loadEquipId(idEquipErroni);
            System.out.println("Equip trobat: " + equipPerId);
        } catch (GestorSportManagerException ex) {
            System.out.println("Error en recuperar equip per ID: " + ex.getMessage());
        }

        System.out.println("\nRecuperar tots els Equips:");
        try {
            List<Equip> equips = gestor.loadEquips();
            System.out.println("Llista d'equips:");
            for (Equip equip : equips) {
                System.out.println(equip);
            }
        } catch (Exception ex) {
            System.out.println("Error en recuperar la llista d'equips: " + ex.getMessage());
        }
        
        
        
        System.out.println("\nGuardar equips");
        Equip equip1 = new Equip(1, 2024, "EquipTest1", EnumTipus.D);
        Equip equip2 = new Equip(2, 2024, "EquipTest2", EnumTipus.H);

        try {
            gestor.saveEquip(equip1);
            gestor.saveEquip(equip2);
            System.out.println("Equips: \n"+equip1+"\n"+equip2+"\nEquips guardats correctament");
        } catch (GestorSportManagerException ex) {
            ex.printStackTrace();
            System.out.println("Error al guardar la llista d'equips: " + ex.getMessage());
        }
        
        
        System.out.println("\nGuardar equip erroni");
        Equip equip3 = new Equip(1, 2021, "EquipTest1", EnumTipus.D);

        try {
            gestor.saveEquip(equip3);
            System.out.println("Equips: \n"+equip3+"\nEquips guardats correctament");
        } catch (Exception ex) {
            System.out.println("Error al guardar l'equip: "+ equip3+"\n"+ex.getMessage());
        }

        try {
            boolean deleted = gestor.eliminarEquip(equip2.getNom(),equip2.getIdTemporada());
            if (deleted) {
                System.out.println("\nEquip eliminat correctament: " + equip1.getNom());
            } else {
                System.out.println("No s'ha trobat o ja s'ha eliminat l'equip amb nom: " + equip1.getNom());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al eliminar l'equip: " + ex.getMessage());
        }
        
        
        
        System.out.println("\nModificar Equip:");
        String nomEquipModificar = "EquipTest1";
        int anyModificar = 2024;
        Equip equipModificat = new Equip(1, 2024, "EquipModificat", EnumTipus.M); // Exemple de nou nom i tipus

        try {
            boolean modificat = gestor.modificarEquip(nomEquipModificar, anyModificar, equipModificat);
            if (modificat) {
                System.out.println("Equip modificat correctament: " + equipModificat.getNom());
            } else {
                System.out.println("No s'ha pogut modificar l'equip amb nom: " + nomEquipModificar);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error en modificar l'equip: " + ex.getMessage());
        }
    }
    
    
    
    
}
