/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru.grafics;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
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
import potrony.bru.SportManager.Categoria;
import potrony.bru.SportManager.Jugador;
import potrony.bru.controladors.SwingControladorUsuari;

/**
 *
 * @author Vago
 */
public class SwingFrameConsultarJugador {
    private static final int AMPLADA = 1100;
    private static final int ALTURA = 600;
    
    private static JFrame frameConsultarJugador;

    SwingControladorUsuari controlador;
    SportManagerOracle bd; 
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    JMenu menuCrear;
    JMenu menuConsultar;
    JMenu menuEditar;
    JMenu menu;
    JMenu tancarSessio;
    JPanel panel;
    JTextField txtfNom;
    JTextField txtfNid;
    JTextField txtfDataNaix;
    JComboBox<String> cbxCategoria;
    


    public SwingFrameConsultarJugador(SwingControladorUsuari controlador, SportManagerOracle bd, HashMap<String,Jugador>jugadorsCarregats) {
        frameConsultarJugador = new JFrame();
        frameConsultarJugador.setSize(AMPLADA, ALTURA);
        frameConsultarJugador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameConsultarJugador.setLocationRelativeTo(null);
        frameConsultarJugador.setTitle("Crear Jugador");
        frameConsultarJugador.setResizable(false);
        
        this.controlador = controlador;
        this.bd = bd;
        
        panel = new JPanel();
        panel.setLayout(null); 
        panel.setBounds(0, 0, AMPLADA, ALTURA); 
        
        JMenuBar menuBar = new JMenuBar();
        
        menuCrear = new JMenu("Crear");
        menuConsultar = new JMenu("Consulta");
        menuEditar = new JMenu("Edita");
        menu = new JMenu("Menú");
        tancarSessio = new JMenu("Tancar Sessió");

        menuConsultar.setEnabled(false);
        
        menuBar.add(menuCrear);
        menuBar.add(menuConsultar);
        menuBar.add(menuEditar);
        menuBar.add(menu);
        menuBar.add(tancarSessio);
        
        frameConsultarJugador.setJMenuBar(menuBar);
        configurarMenu();
        
        cbxCategoria=new JComboBox<>();
        inicialitzarComboBoxCategoria();
        cbxCategoria.setBounds(50,50,180,30);
        cbxCategoria.setVisible(true);
        panel.add(cbxCategoria);
        
        frameConsultarJugador.add(panel);
    }
    
    public JFrame getFrame() {
        return frameConsultarJugador;
    }
    
    private void configurarMenu() {
        menuEditar.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                controlador.moveToEditarJugador(frameConsultarJugador);
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
        
        menuCrear.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                controlador.moveToCrearJugador(frameConsultarJugador);
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
        
        controlador.configurarJMenuBar(frameConsultarJugador, menu, tancarSessio);
    }

    private void inicialitzarComboBoxCategoria() {
        try {
            List<Categoria> categories = bd.loadCategories();
            cbxCategoria.addItem("Selecciona una categoria");
            for (Categoria category : categories) {
                cbxCategoria.addItem(category.getNom());
            }
        } catch (Exception e) {
        }
    }

}
