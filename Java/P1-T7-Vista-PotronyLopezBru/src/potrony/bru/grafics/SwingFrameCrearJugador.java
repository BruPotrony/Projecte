/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru.grafics;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import potrony.bru.CapaPersistencia.SportManagerOracle;
import potrony.bru.Interface.GestorSportManagerException;
import potrony.bru.Interface.SportManagerInterfaceCP;
import potrony.bru.SportManager.EnumSexe;
import potrony.bru.SportManager.Jugador;
import potrony.bru.SportManager.Temporada;
import potrony.bru.controladors.SwingControladorUsuari;

/**
 *
 * @author Vago
 */
public class SwingFrameCrearJugador {
    private static final int AMPLADA = 1100;
    private static final int ALTURA = 600;
    
    private static JFrame frameCrearJugador;

    SwingControladorUsuari controlador;
    SportManagerInterfaceCP bd; 
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    JMenu menuCrear;
    JMenu menuConsultar;
    JMenu menu;
    JMenu tancarSessio;
    JPanel panel;
    JTextField txtfImatge;
    JTextField txtfNom;
    JTextField txtfCognom;
    JTextField txtfIdLegal;
    JTextField txtfIban;
    JTextField txtfPoblacio;
    JTextField txtfDataNaix;
    JTextField txtfCodiPostal;
    JTextField txtfIdAdreca;
    JRadioButton rbMasculi = new JRadioButton("Masculí");
    JRadioButton rbFemeni = new JRadioButton("Femení");
    JButton btnCancelar;
    JButton btnCrear;
    JComboBox<String> comboBoxAnys;
    JLabel labelImatge;
    
    
    //Aquesta variable es per a que al fer el dispose del frame
    //No crei confusions, explicat mes endevant
    private boolean isProcessingMenu = false;


    public SwingFrameCrearJugador(SwingControladorUsuari controlador, SportManagerInterfaceCP bd) {
        frameCrearJugador = new JFrame();
        frameCrearJugador.setSize(AMPLADA, ALTURA);
        frameCrearJugador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameCrearJugador.setLocationRelativeTo(null);
        frameCrearJugador.setTitle("Crear Jugador");
        frameCrearJugador.setVisible(true);
        frameCrearJugador.setResizable(false);
        
        this.controlador = controlador;
        this.bd = bd;
        
        panel = new JPanel();
        panel.setLayout(null); 
        panel.setBounds(0, 0, AMPLADA, ALTURA); 
        
        JMenuBar menuBar = new JMenuBar();
        
        menuCrear = new JMenu("Crear");
        menuConsultar = new JMenu("Consulta");
        menu = new JMenu("Menú");
        tancarSessio = new JMenu("Tancar Sessió");

        menuCrear.setEnabled(false);
        
        menuBar.add(menuCrear);
        menuBar.add(menuConsultar);
        menuBar.add(menu);
        menuBar.add(tancarSessio);
        
        frameCrearJugador.setJMenuBar(menuBar);
        configurarMenu();
        
        labelImatge = controlador.afegirImatgeUrl("", 50, 50, 250, 250);
        panel.add(labelImatge);
        
        
        txtfImatge = new JTextField();
        txtfImatge.setFont(new Font("Arial", Font.PLAIN, 10));
        txtfImatge.setBounds(50, 310, 250, 30);
        txtfImatge.setText("URL Imatge");
        panel.add(txtfImatge);
        configurarImatge();
        
        
        rbMasculi.setBounds(50, 350, 70, 20);
        panel.add(rbMasculi);
        rbFemeni.setBounds(130, 350, 70, 20);
        panel.add(rbFemeni);
        ButtonGroup rbGroup = new ButtonGroup();
        rbGroup.add(rbMasculi);
        rbGroup.add(rbFemeni);
        
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String[] years = new String[6];
        years[0] = "Fi Revisió Mèdica";
        for (int i = 1; i < 6; i++) {
            years[i] = String.valueOf(currentYear + i);
        }

        comboBoxAnys = new JComboBox<>(years);
        comboBoxAnys.setBounds(50, 380, 250, 25);
        comboBoxAnys.setMaximumRowCount(5);
        frameCrearJugador.add(comboBoxAnys);
        
        JLabel labelNom = new JLabel("Nom:");
        labelNom.setFont(new Font("Arial", Font.PLAIN, 20));
        labelNom.setBounds(350, 50, 50, 30); 
        panel.add(labelNom);

        txtfNom = new JTextField();
        txtfNom.setFont(new Font("Arial", Font.PLAIN, 20));
        txtfNom.setBounds(450, 45, 200, 40);
        panel.add(txtfNom);
        
        JLabel labelCogNom = new JLabel("Cognom:");
        labelCogNom.setFont(new Font("Arial", Font.PLAIN, 20));
        labelCogNom.setBounds(350, 125, 100, 30); 
        panel.add(labelCogNom);    
        
        txtfCognom = new JTextField();
        txtfCognom.setFont(new Font("Arial", Font.PLAIN, 20));
        txtfCognom.setBounds(450, 120, 200, 40);
        panel.add(txtfCognom);
        
        JLabel labelIdLegal = new JLabel("Id Legal:");
        labelIdLegal.setFont(new Font("Arial", Font.PLAIN, 20));
        labelIdLegal.setBounds(350, 200, 100, 30); 
        panel.add(labelIdLegal);    
        
        txtfIdLegal = new JTextField();
        txtfIdLegal.setFont(new Font("Arial", Font.PLAIN, 20));
        txtfIdLegal.setBounds(450, 195, 200, 40);
        panel.add(txtfIdLegal);
        
        JLabel labelIban = new JLabel("IBAN:");
        labelIban.setFont(new Font("Arial", Font.PLAIN, 20));
        labelIban.setBounds(350, 275, 100, 30); 
        panel.add(labelIban);    
        
        txtfIban = new JTextField();
        txtfIban.setFont(new Font("Arial", Font.PLAIN, 20));
        txtfIban.setBounds(450, 270, 200, 40);
        panel.add(txtfIban);
        
        JLabel labelCodiPostal = new JLabel("C. P.");
        labelCodiPostal.setFont(new Font("Arial", Font.PLAIN, 20));
        labelCodiPostal.setBounds(690, 50, 100, 30); 
        panel.add(labelCodiPostal);    
        
        txtfCodiPostal = new JTextField();
        txtfCodiPostal.setFont(new Font("Arial", Font.PLAIN, 20));
        txtfCodiPostal.setBounds(800, 45, 200, 40);
        panel.add(txtfCodiPostal);
        
        JLabel adreca = new JLabel("Adreca:");
        adreca.setFont(new Font("Arial", Font.PLAIN, 20));
        adreca.setBounds(690, 125, 100, 30); 
        panel.add(adreca);    
        
        txtfIdAdreca = new JTextField();
        txtfIdAdreca.setFont(new Font("Arial", Font.PLAIN, 20));
        txtfIdAdreca.setBounds(800, 120, 200, 40);
        panel.add(txtfIdAdreca);
        
        JLabel labelPoblacio = new JLabel("Població:");
        labelPoblacio.setFont(new Font("Arial", Font.PLAIN, 20));
        labelPoblacio.setBounds(690, 200, 100, 30); 
        panel.add(labelPoblacio);    
        
        txtfPoblacio = new JTextField();
        txtfPoblacio.setFont(new Font("Arial", Font.PLAIN, 20));
        txtfPoblacio.setBounds(800, 195, 200, 40);
        panel.add(txtfPoblacio);
        
        JLabel labelDataNaix = new JLabel("<html>Naixement:<br><font size='3'>(dd/MM/yyyy)</font></html>");
        labelDataNaix.setFont(new Font("Arial", Font.PLAIN, 20));
        labelDataNaix.setBounds(690, 264, 150, 60); 
        panel.add(labelDataNaix);  
        
        txtfDataNaix = new JTextField();
        txtfDataNaix.setFont(new Font("Arial", Font.PLAIN, 20));
        txtfDataNaix.setBounds(800, 274, 200, 40);
        panel.add(txtfDataNaix);
        
        btnCancelar = new JButton();
        btnCancelar.setText("Cancelar");
        btnCancelar.setBounds(400,450,120,40);
        panel.add(btnCancelar);
        configurarBotoCancelar();
        
        btnCrear = new JButton();
        btnCrear.setText("Crear");
        btnCrear.setBounds(600,450,120,40);
        panel.add(btnCrear);
        configurarBotoCrear();
        
        frameCrearJugador.add(panel);
    }
    
    public JFrame getFrame() {
        return frameCrearJugador;
    }

    private void configurarMenu() {
        menuConsultar.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                if (!isProcessingMenu) {
                    isProcessingMenu = true;
                    controlador.moveToConsultarJugador(frameCrearJugador);
                    isProcessingMenu = false;
                }
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
        
        controlador.configurarJMenuBar(frameCrearJugador, menu, tancarSessio);
    }

    
    
    private void configurarImatge() {
        txtfImatge.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtfImatge.getText().equals("URL Imatge")) {
                    txtfImatge.setText("");

                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtfImatge.getText().isEmpty()) {
                    txtfImatge.setText("URL Imatge");
                }else {
                    actualitzarImatge();
                }
            }
        });
    }
    
    
    private void actualitzarImatge() {
                
        String urlImatge = txtfImatge.getText();
        JLabel novaImg = controlador.afegirImatgeUrl(urlImatge, 50, 50, 250, 250);

        if (labelImatge != null) {
            panel.remove(labelImatge);
        }

        if (novaImg != null) {
            labelImatge = novaImg;
            panel.add(labelImatge);
        }
        
        //faig això ja que sinó el comboBox em desapareixia fins que no tornes a passar el 
        //ratolí per sobre
        panel.remove(comboBoxAnys);
        panel.add(comboBoxAnys);

        panel.revalidate();
        panel.repaint();
    }

    public void configurarBotoCancelar(){
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.moveToMenu(frameCrearJugador);
            }
        });
    }
    
    
    public void configurarBotoCrear(){
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Jugador jugador = null;
 
                try {
                    
                    Object selectedItem = comboBoxAnys.getSelectedItem();
                    if (selectedItem == null || comboBoxAnys.getSelectedIndex()==0) {
                        controlador.missatgeError("Selecciona un any de la revisió mèdica");
                        return;
                    }
                    
                    int any = Integer.parseInt(selectedItem.toString());
                    String nom = txtfNom.getText();
                    String cognom = txtfCognom.getText();
                    EnumSexe sexe;
                    
                    if (rbMasculi.isSelected()) {
                        sexe = EnumSexe.H;
                    } else if (rbFemeni.isSelected()) {
                        sexe = EnumSexe.D;
                    } else {
                        controlador.missatgeError("Selecciona un sexe");
                        return;
                    }
                    
                    String dataNaixStr = txtfDataNaix.getText();
                    Date date = sdf.parse(dataNaixStr);
                    LocalDate dataNaix = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                    String foto = txtfImatge.getText();
                    String adreca = txtfIdAdreca.getText();
                    String codiPostal = txtfCodiPostal.getText();
                    String poblacio = txtfPoblacio.getText();
                    String iban = txtfIban.getText();
                    String idLegal = txtfIdLegal.getText();
                    
                    if (!controlador.esURLValida(foto)){
                        controlador.missatgeError("URL de l'imatge no valida");
                        return;
                    }
                    
                    jugador = new Jugador(nom,cognom,sexe,dataNaix,foto,adreca,codiPostal,poblacio,iban,idLegal,any);
                } catch (ParseException ex) {
                    controlador.missatgeError("la Data no esta en un format correcte, Format correcte: dd/MM/yyyy");
                    return;
                }catch (Exception ex) {
                    controlador.missatgeError(ex.getMessage());
                    return;
                }
                    

                   
                try {
                        bd.saveJugador(jugador);
                        controlador.missatgeConfirmacio("Jugador creat correctament.");                    
                } catch (Exception ex) {
                    controlador.missatgeError("Ja existeix jugador amb idLegal "+jugador.getId_Legal());
                }
            }
        });
    }
    
    
    

}
