/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru.controladors;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.plaf.multi.MultiLookAndFeel;
import potrony.bru.CapaPersistencia.SportManagerOracle;
import potrony.bru.SportManager.Usuari;
import potrony.bru.grafics.SwingFrameCrearTemporada;
import potrony.bru.grafics.SwingFrameEliminarTemporada;
import potrony.bru.grafics.SwingFrameForgetPassword;
import potrony.bru.grafics.SwingFrameMenu;
import potrony.bru.grafics.SwingFrameUsuari;

/**
 *
 * @author Vago
 */
public class SwingControladorUsuari{

        
    private SwingFrameUsuari frameUsuari;
    private SwingFrameForgetPassword frameForgetPassword;
    private SwingFrameMenu frameMenu;
    private SwingFrameCrearTemporada frameCrearTemporada;
    private SwingFrameEliminarTemporada frameEliminarTemporada;
    
    public SwingControladorUsuari(SportManagerOracle manager) {
        frameUsuari = new SwingFrameUsuari(this, manager);
        frameForgetPassword = new SwingFrameForgetPassword(this,manager);
        frameMenu = new SwingFrameMenu(this,manager);
        frameCrearTemporada = new SwingFrameCrearTemporada(this,manager);
        frameEliminarTemporada = new SwingFrameEliminarTemporada(this,manager);
    }
    
    
    
    public void moveToMenu(JFrame frame){
        frame.setVisible(false);
        frameMenu.getFrame().setVisible(true);
    }
    
    public void moveToForgetPwd(JFrame frame){
        frame.setVisible(false);
        frameForgetPassword.getFrame().setVisible(true);
    }
    
    public void moveToLogin(JFrame frame){
        frame.setVisible(false);
        frameUsuari.getFrame().setVisible(true);
    }

    public void moveToCrearTemporada(JFrame frame){
        frame.setVisible(false);
        frameCrearTemporada.getFrame().setVisible(true);
    }
    
    public void moveToEliminarTemporada(JFrame frame){
        frame.setVisible(false);
        frameEliminarTemporada.getFrame().setVisible(true);
    }
    
    public void actualitzarTemporades(){
        frameEliminarTemporada.actualitzarTemporades();
    }


    
    
    
    public void configurarJMenuBar(JFrame frame, JMenu menu, JMenu tancarSessio){
        menu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                frame.setVisible(false);
                frameMenu.getFrame().setVisible(true);
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                // Metode buit
            }

            @Override
            public void menuCanceled(MenuEvent e) {
                // // Metode buit
            }
        });
        
        
        tancarSessio.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                frame.setVisible(false);
                frameUsuari.getFrame().setVisible(true);
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                // Metode buit
            }

            @Override
            public void menuCanceled(MenuEvent e) {
                // // Metode buit
            }
        });
    }
    
    
    
    
    public void missatgeError(String missatge) {
        JFrame frameError = new JFrame("Error");
        frameError.setSize(400, 220);
        frameError.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameError.setLocationRelativeTo(null);
        frameError.setResizable(false);

        JPanel panelError = new JPanel();
        panelError.setLayout(null);

        JLabel labelError = new JLabel("<html><center>" + missatge + "</center></html>");
        labelError.setBounds(50, 30, 300, 50);
        labelError.setHorizontalAlignment(SwingConstants.CENTER);
        labelError.setFont(new Font("Arial", Font.PLAIN, 16));
        panelError.add(labelError);

        JButton btnAceptar = new JButton("Acceptar");
        btnAceptar.setBounds(150, 100, 100, 30);
        panelError.add(btnAceptar);

        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameError.dispose();
            }
        });

        frameError.add(panelError);
        frameError.setVisible(true);
    }
    
    
    public void missatgeConfirmacio(String missatge) {
        JFrame frameConfirmacio = new JFrame("Confirmació");
        frameConfirmacio.setSize(400, 220);
        frameConfirmacio.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameConfirmacio.setLocationRelativeTo(null);
        frameConfirmacio.setResizable(false);

        JPanel panelError = new JPanel();
        panelError.setLayout(null);

        JLabel labelError = new JLabel("<html><center>" + missatge + "</center></html>");
        labelError.setBounds(50, 30, 300, 50);
        labelError.setHorizontalAlignment(SwingConstants.CENTER);
        labelError.setFont(new Font("Arial", Font.PLAIN, 16));
        panelError.add(labelError);

        JButton btnAceptar = new JButton("Acceptar");
        btnAceptar.setBounds(150, 100, 100, 30);
        panelError.add(btnAceptar);

        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameConfirmacio.dispose();
            }
        });

        frameConfirmacio.add(panelError);
        frameConfirmacio.setVisible(true);
    }
    
    
    private static void infoError(Throwable aux){
        do{
            if (aux.getMessage()!=null){
                System.out.println("\t"+aux.getMessage());
            }
            aux=aux.getCause();
        }while(aux!=null);
    }
}