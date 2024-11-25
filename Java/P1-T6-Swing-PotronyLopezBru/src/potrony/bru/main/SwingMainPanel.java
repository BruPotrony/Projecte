package potrony.bru.main;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import potrony.bru.controladors.SwingControladorUsuari;
import java.awt.CardLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.multi.MultiLookAndFeel;
import potrony.bru.CapaPersistencia.SportManagerOracle;
import potrony.bru.Interface.GestorSportManagerException;

/**
 *
 * @author Vago
 */

public class SwingMainPanel {
    public static void main(String[] args) {
        
        SportManagerOracle manager;
        try {
            manager = new SportManagerOracle();
        } catch (Exception ex) {
            System.out.println("Error en conectar amb la bd "+ ex.getMessage());
            return;
        }
        
        new SwingControladorUsuari(manager);
        
        try {
            manager.desferCanvis();
        } catch (GestorSportManagerException ex) {
            System.out.println("Error en tancar la connexió amb la bd "+ex.getMessage());
        }
    }
}
