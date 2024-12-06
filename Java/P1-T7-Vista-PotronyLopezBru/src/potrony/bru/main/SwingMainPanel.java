package potrony.bru.main;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import potrony.bru.controladors.SwingControladorUsuari;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import potrony.bru.Interface.GestorSportManagerException;
import potrony.bru.Interface.SportManagerInterfaceCP;

/**
 *
 * @author Vago
 */

public class SwingMainPanel {
    
    private static JFrame frameCarga;
    private static SportManagerInterfaceCP gBD;
    private static String nomClasse;

    
    public static void main(String[] args) {
        
        
        mostrarFrameCarga();
        
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (Exception e) {
            System.err.println("Error al configurar el Look and Feel: " + e.getMessage());
        }
        
        Properties propsJasper = null;
        Properties propsOracle = null;
        if (args.length!=2){
            propsJasper= recuperarPropertiesJRS("properties_CP_JRS.properties");
            propsOracle = recuperarPropertiesOracle("connectionBD.properties");
        }else{
            propsJasper= recuperarPropertiesJRS(args[0]);
            propsOracle = recuperarPropertiesJRS(args[1]);
        }

        if (propsJasper==null || propsOracle==null){
            System.exit(0);
        }
        
        
        try {
            nomClasse = propsJasper.getProperty("nomClasse");

            if (nomClasse.isEmpty()) {
                System.out.println("Error en recuperar les propietats");
                System.exit(1);
            }
            
            gBD = (SportManagerInterfaceCP) Class.forName(nomClasse).newInstance();
        } catch (Exception ex) {
            System.out.println("Error en conectar amb la bd "+ ex.getMessage());
            return;
        }
                
        new SwingControladorUsuari(gBD, frameCarga, propsJasper,propsOracle);
        
        try {
            gBD.desferCanvis();
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
    
    
    private static Properties recuperarPropertiesOracle(String fitxerConfigOracle){
        try {
            Properties props = new Properties();
            props.load(new FileInputStream(fitxerConfigOracle));
            return props;
        } catch (IOException ex) {
            System.out.println("Error en recuperar el fitxer de propietats "+ex.getMessage());
            System.exit(0);
        }
        return null;
    }
    
    
    private static Properties recuperarPropertiesJRS(String fitxerConfigJRS) {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream(fitxerConfigJRS));
            return props;
        } catch (IOException ex) {
            System.out.println("Error en recuperar el fitxer de propietats "+ex.getMessage());
            System.exit(0);
        }
        return null;
    }
          
}
