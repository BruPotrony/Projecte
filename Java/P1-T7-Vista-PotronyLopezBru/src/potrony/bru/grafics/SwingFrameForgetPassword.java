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
public class SwingFrameForgetPassword {

    private static final int AMPLADA = 600;
    private static final int ALTURA = 400;
    
    private static JFrame framePassword;
    
    SwingControladorUsuari controlador;
    SportManagerInterfaceCP bd;
    
    JButton btnGuardar;
    JButton btnCancelar;
    JPasswordField textFieldpwd1;
    JPasswordField textFieldpwd;
    JTextField textFieldUsuari;
    
    //Aquesta variable es per a que al fer el dispose del frame
    //No crei confusions, explicat mes endevant
    private boolean isProcessingMenu = false;

    public SwingFrameForgetPassword(SwingControladorUsuari controlador, SportManagerInterfaceCP bd) {
        framePassword = new JFrame();
        framePassword.setSize(AMPLADA, ALTURA);
        framePassword.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePassword.setLocationRelativeTo(null);
        framePassword.setTitle("Resetejar contrassenya");
        framePassword.setVisible(true);
        framePassword.setResizable(false);
        
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

        JLabel labelPassword = new JLabel("Nova Contrassenya:");
        labelPassword.setFont(new Font("Arial", Font.PLAIN, 20));
        labelPassword.setBounds(50, 120, 200, 30);
        panel.add(labelPassword);

        textFieldpwd = new JPasswordField();
        textFieldpwd.setFont(new Font("Arial", Font.PLAIN, 20));
        textFieldpwd.setBounds(260, 115, 200, 40); 
        panel.add(textFieldpwd);
        
        JLabel labelPassword1 = new JLabel("Repetir Contrassenya:");
        labelPassword1.setFont(new Font("Arial", Font.PLAIN, 20));
        labelPassword1.setBounds(50, 190, 200, 30);
        panel.add(labelPassword1);

        textFieldpwd1 = new JPasswordField();
        textFieldpwd1.setFont(new Font("Arial", Font.PLAIN, 20));
        textFieldpwd1.setBounds(260, 185, 200, 40); 
        panel.add(textFieldpwd1);

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
        
        
        
        framePassword.add(panel);
    }
    
    public JFrame getFrame() {
        return framePassword;
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
                    String p2 = bd.encriptarContrassenya(new String (textFieldpwd1.getPassword()));
                    
                    if (!p1.isEmpty() && !p2.isEmpty()){
                        if (p1.equals(p2)){
                            try {
                                bd.modificarContrassenya(textFieldUsuari.getText(),p1) ;
                                bd.confirmarCanvis();
                            } catch (GestorSportManagerException ex) {
                                controlador.missatgeError("No existeix cap usuari amb nom "+textFieldUsuari.getText());
                                return;
                            }
                        }
                        else{
                            controlador.missatgeError("Les contrassenyes no son iguals");
                            return;
                        }
                    }
                    else{
                        controlador.missatgeError("Les contrassenyes no poden estar buides");
                        return;
                    }  
                    
                    controlador.moveToLogin(framePassword);
                    
                } catch (Exception ex) {
                    controlador.missatgeError("Error en modificar la base de dades\nConsultar amb Administrador");
                }
                
            }
        });
    }
    
    public void configurarBotoCancelar(){
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.moveToLogin(framePassword);
                buidarCapmps();
                
            }
        });
    }
    
    
    
    private void buidarCapmps(){
        textFieldUsuari.setText("");
        textFieldpwd.setText("");
        textFieldpwd1.setText("");
    }
    
}
