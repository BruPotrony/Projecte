/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package potrony.bru.grafics;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import potrony.bru.CapaPersistencia.SportManagerOracle;
import potrony.bru.Interface.GestorSportManagerException;
import potrony.bru.SportManager.Usuari;
import potrony.bru.controladors.SwingControladorUsuari;

/**
 *
 * @author Vago
 */
public class SwingFrameUsuari{

    private static final int AMPLADA = 600;
    private static final int ALTURA = 400;
    
    private static JFrame frameUsuari;

    SwingControladorUsuari controlador;
    SportManagerOracle bd; 
    
    private JLabel labelForgetPwd;
    
    private JButton btnEntrar;
    private JTextField textFieldNom;
    private JTextField textFieldUsuari;
    private JPasswordField textFieldpwd;


    public SwingFrameUsuari(SwingControladorUsuari controlador, SportManagerOracle bd) {
        frameUsuari = new JFrame();
        frameUsuari.setSize(AMPLADA, ALTURA);
        frameUsuari.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameUsuari.setLocationRelativeTo(null);
        frameUsuari.setTitle("Login");
        frameUsuari.setVisible(true);
        frameUsuari.setResizable(false);
        
        this.controlador = controlador;
        this.bd = bd;
        
        JPanel panel = new JPanel();
        panel.setLayout(null); 
        panel.setBounds(0, 0, AMPLADA, ALTURA); 

        JLabel labelNom = new JLabel("Nom:");
        labelNom.setFont(new Font("Arial", Font.PLAIN, 20));
        labelNom.setBounds(150, 50, 100, 30);
        panel.add(labelNom);
        
        textFieldNom = new JTextField();
        textFieldNom.setFont(new Font("Arial", Font.PLAIN, 20));
        textFieldNom.setBounds(260, 45, 200, 40); 
        panel.add(textFieldNom);
        
        JLabel labelUsuari = new JLabel("Usuari:");
        labelUsuari.setFont(new Font("Arial", Font.PLAIN, 20));
        labelUsuari.setBounds(150, 100, 100, 30); 
        panel.add(labelUsuari);

        textFieldUsuari = new JTextField();
        textFieldUsuari.setFont(new Font("Arial", Font.PLAIN, 20));
        textFieldUsuari.setBounds(260, 95, 200, 40);
        panel.add(textFieldUsuari);

        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setFont(new Font("Arial", Font.PLAIN, 20));
        labelPassword.setBounds(150, 150, 100, 30);
        panel.add(labelPassword);

        textFieldpwd = new JPasswordField();
        textFieldpwd.setFont(new Font("Arial", Font.PLAIN, 20));
        textFieldpwd.setBounds(260, 145, 200, 40); 
        panel.add(textFieldpwd);
        
        
        labelForgetPwd = new JLabel("<html><u><font color='blue'>He oblidat la contrassenya</font></u></html>");
        labelForgetPwd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelForgetPwd.setFont(new Font("Arial", Font.PLAIN, 15));
        labelForgetPwd.setBounds(230, 200, 200, 30); 
        panel.add(labelForgetPwd);
        configurarForgetPasword();

        btnEntrar = new JButton();
        btnEntrar.setText("Entrar");
        btnEntrar.setBounds(235,300,120,40);
        panel.add(btnEntrar);
        configurarBotoEntrar();
        
        
        frameUsuari.add(panel);
        
    }
    
    public JFrame getFrame() {
        return frameUsuari;
    }

    
    public void configurarBotoEntrar(){
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
//                Usuari usuTemp;
//                try {
//                    usuTemp = new Usuari(textFieldUsuari.getText(), new String(textFieldpwd.getPassword()),textFieldNom.getText(), false);
//                } catch (Exception ex) {
//                    controlador.missatgeError(ex.getMessage());
//                    return;
//                }
//                
//                try {
//                    if (!bd.estaRegistrat(usuTemp)){
//                        bd.saveUsuari(usuTemp);
//                    }
//                } catch (GestorSportManagerException ex) {
//                    controlador.missatgeError("Error en guardar usuari");
//                    return;
//                }
                
                controlador.moveToMenu(frameUsuari);
                buidarCapmps();
            }
        });
    }
    
    public void configurarForgetPasword(){
        labelForgetPwd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controlador.moveToForgetPwd(frameUsuari);
                buidarCapmps();
            }
        });
    }
    
    
    
    private void buidarCapmps(){
        textFieldUsuari.setText("");
        textFieldpwd.setText("");
        textFieldNom.setText("");
    }
}
