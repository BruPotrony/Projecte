/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package potrony.bru.grafics;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import potrony.bru.CapaPersistencia.SportManagerOracle;
import potrony.bru.Interface.GestorSportManagerException;
import potrony.bru.Interface.SportManagerInterfaceCP;
import potrony.bru.SportManager.Usuari;
import potrony.bru.controladors.SwingControladorUsuari;

/**
 *
 * @author Vago
 */
public class SwingFrameRegistrar {

    private static final int AMPLADA = 600;
    private static final int ALTURA = 400;
    
    private static JFrame frameRegistrar;
    
    SwingControladorUsuari controlador;
    SportManagerInterfaceCP bd;
    
    JButton btnGuardar;
    JButton btnCancelar;
    JTextField textFieldNom;
    JPasswordField textFieldpwd;
    JTextField textFieldUsuari;
    
    //Aquesta variable es per a que al fer el dispose del frame
    //No crei confusions, explicat mes endevant
    private boolean isProcessingMenu = false;

    public SwingFrameRegistrar(SwingControladorUsuari controlador, SportManagerInterfaceCP bd) {
        frameRegistrar = new JFrame();
        frameRegistrar.setSize(AMPLADA, ALTURA);
        frameRegistrar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameRegistrar.setLocationRelativeTo(null);
        frameRegistrar.setTitle("Resetejar contrassenya");
        frameRegistrar.setVisible(true);
        frameRegistrar.setResizable(false);
        
        this.controlador = controlador;
        this.bd=bd;
        
        JPanel panel = new JPanel();
        panel.setLayout(null); 
        panel.setBounds(0, 0, AMPLADA, ALTURA); 

        JLabel labelUsuari = new JLabel("Usuari:");
        labelUsuari.setFont(new Font("Arial", Font.PLAIN, 20));
        labelUsuari.setBounds(50, 50, 100, 30); 
        panel.add(labelUsuari);

        textFieldUsuari = new JTextField();
        textFieldUsuari.setFont(new Font("Arial", Font.PLAIN, 20));
        textFieldUsuari.setBounds(260, 45, 200, 40);
        panel.add(textFieldUsuari);

        JLabel labelPassword = new JLabel("Nom:");
        labelPassword.setFont(new Font("Arial", Font.PLAIN, 20));
        labelPassword.setBounds(50, 120, 200, 30);
        panel.add(labelPassword);

        textFieldNom = new JTextField();
        textFieldNom.setFont(new Font("Arial", Font.PLAIN, 20));
        textFieldNom.setBounds(260, 115, 200, 40); 
        panel.add(textFieldNom);
        
        JLabel labelNom = new JLabel("Password:");
        labelNom.setFont(new Font("Arial", Font.PLAIN, 20));
        labelNom.setBounds(50, 190, 200, 30);
        panel.add(labelNom);

        textFieldpwd = new JPasswordField();
        textFieldpwd.setFont(new Font("Arial", Font.PLAIN, 20));
        textFieldpwd.setBounds(260, 185, 200, 40); 
        panel.add(textFieldpwd);

        btnCancelar = new JButton();
        btnCancelar.setText("Cancelar");
        btnCancelar.setBounds(150,300,120,40);
        panel.add(btnCancelar);
        configurarBotoCancelar();
        
        btnGuardar = new JButton();
        btnGuardar.setText("Guardar");
        btnGuardar.setBounds(300,300,120,40);
        panel.add(btnGuardar);
        configurarBotoGuardar();
        
        
        
        frameRegistrar.add(panel);
    }
    
    public JFrame getFrame() {
        return frameRegistrar;
    }
    
    public void configurarBotoGuardar(){
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Usuari temp = new Usuari();
                    
                    try {
                        temp.isPasswordValid(new String(textFieldpwd.getPassword()));
                    } catch (Exception ex) {
                        controlador.missatgeError(ex.getMessage());
                        return;
                    }
                    
                    
                    String p1 = bd.encriptarContrassenya(new String (textFieldpwd.getPassword()));
                    
                    if (!p1.isEmpty()){
                        try {
                            Usuari usu = new Usuari(textFieldUsuari.getText(),p1,
                                                        textFieldNom.getText(),true);
                            bd.saveUsuari(usu) ;
                            bd.confirmarCanvis();
                        } catch (GestorSportManagerException ex) {
                            controlador.missatgeError(ex.getMessage());
                            return;
                        }
                    }
                    else{
                        controlador.missatgeError("Les contrassenyes no poden estar buides");
                        return;
                    }  
                    
                    controlador.moveToLogin(frameRegistrar);
                    
                } catch (Exception ex) {
                    controlador.missatgeError(ex.getMessage());
                }
                
            }
        });
    }
    
    public void configurarBotoCancelar(){
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.moveToLogin(frameRegistrar);
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
