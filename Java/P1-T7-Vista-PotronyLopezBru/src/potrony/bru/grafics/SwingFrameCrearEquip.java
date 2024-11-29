/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru.grafics;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
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
public class SwingFrameCrearEquip {
    private static final int AMPLADA = 1100;
    private static final int ALTURA = 600;
    
    private static JFrame frameCrearEquip;

    SwingControladorUsuari controlador;
    SportManagerInterfaceCP bd; 
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    JMenu menuCrear;
    JMenu menuConsultar;
    JMenu menuEditar;
    JMenu menuAfegirJugadors;
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
    
    


    public SwingFrameCrearEquip(SwingControladorUsuari controlador, SportManagerInterfaceCP bd) {
        frameCrearEquip = new JFrame();
        frameCrearEquip.setSize(AMPLADA, ALTURA);
        frameCrearEquip.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameCrearEquip.setLocationRelativeTo(null);
        frameCrearEquip.setTitle("Crear Equip");
        frameCrearEquip.setResizable(false);
        
        this.controlador = controlador;
        this.bd = bd;
        
        panel = new JPanel();
        panel.setLayout(null); 
        panel.setBounds(0, 0, AMPLADA, ALTURA); 
        
        JMenuBar menuBar = new JMenuBar();
        
        menuCrear = new JMenu("Crear");
        menuConsultar = new JMenu("Consulta");
        menuEditar = new JMenu("Edita");
        menuAfegirJugadors = new JMenu("Afegir Jugadors");
        menu = new JMenu("Menú");
        tancarSessio = new JMenu("Tancar Sessió");

        menuCrear.setEnabled(false);
        
        menuBar.add(menuCrear);
        menuBar.add(menuConsultar);
        menuBar.add(menuEditar);
        menuBar.add(menuAfegirJugadors);
        menuBar.add(menu);
        menuBar.add(tancarSessio);
        
        frameCrearEquip.setJMenuBar(menuBar);
        configurarMenu();
        
        
        
        frameCrearEquip.add(panel);
    }
    
    public JFrame getFrame() {
        return frameCrearEquip;
    }

    private void configurarMenu() {
        menuEditar.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                //controlador.moveToEditarJugador(frameCrearEquip);
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
        
        menuConsultar.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                //controlador.moveToConsultarJugador(frameCrearEquip);
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
        
        menuAfegirJugadors.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                //controlador.moveToConsultarJugador(frameCrearEquip);
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
        
        controlador.configurarJMenuBar(frameCrearEquip, menu, tancarSessio);
    }

    
    
    

    public void configurarBotoCancelar(){
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.moveToMenu(frameCrearEquip);
            }
        });
    }
    

}
