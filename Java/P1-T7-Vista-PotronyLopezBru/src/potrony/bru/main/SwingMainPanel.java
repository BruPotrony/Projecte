package potrony.bru.main;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.BorderLayout;
import potrony.bru.controladors.SwingControladorUsuari;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import potrony.bru.CapaPersistencia.SportManagerOracle;
import potrony.bru.Interface.GestorSportManagerException;

/**
 *
 * @author Vago
 */

public class SwingMainPanel {
    
    private static JFrame frameCarga;
    
    public static void main(String[] args) {
        
        
        mostrarFrameCarga();
        
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (Exception e) {
            System.err.println("Error al configurar el Look and Feel: " + e.getMessage());
        }
        
        SportManagerOracle manager;
        try {
            manager = new SportManagerOracle();
        } catch (Exception ex) {
            System.out.println("Error en conectar amb la bd "+ ex.getMessage());
            return;
        }
        
        new SwingControladorUsuari(manager, frameCarga);
        
        try {
            manager.desferCanvis();
        } catch (GestorSportManagerException ex) {
            System.out.println("Error en tancar la connexió amb la bd "+ex.getMessage());
        }
    }

    

    private static void mostrarFrameCarga() {
        frameCarga = new JFrame("Cargant...");
        frameCarga.setSize(300, 150);
        frameCarga.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameCarga.setLocationRelativeTo(null);
        frameCarga.setResizable(false);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        JLabel label = new JLabel("Cargant, siusplau esperi...", JLabel.CENTER);
        panel.add(label, BorderLayout.CENTER);
        
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        panel.add(progressBar, BorderLayout.SOUTH);
        
        frameCarga.add(panel);
        frameCarga.setVisible(true);
    }
          
}
