/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru.grafics;

import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import potrony.bru.CapaPersistencia.SportManagerOracle;
import potrony.bru.controladors.SwingControladorUsuari;

/**
 *
 * @author Vago
 */
public class SwingFrameCrearJugador {
        private static final int AMPLADA = 1000;
    private static final int ALTURA = 600;
    
    private static JFrame frameCrearJugador;

    SwingControladorUsuari controlador;
    SportManagerOracle bd; 
    
    JMenu menuCrear;
    JMenu menuConsultar;
    JMenu menuEditar;
    JMenu menu;
    JMenu tancarSessio;


    public SwingFrameCrearJugador(SwingControladorUsuari controlador, SportManagerOracle bd) {
        frameCrearJugador = new JFrame();
        frameCrearJugador.setSize(AMPLADA, ALTURA);
        frameCrearJugador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameCrearJugador.setLocationRelativeTo(null);
        frameCrearJugador.setTitle("Crear Jugador");
        frameCrearJugador.setResizable(false);
        
        this.controlador = controlador;
        this.bd = bd;
        
        JPanel panel = new JPanel();
        panel.setLayout(null); 
        panel.setBounds(0, 0, AMPLADA, ALTURA); 
        
        JMenuBar menuBar = new JMenuBar();
        
        menuCrear = new JMenu("Crear");
        menuConsultar = new JMenu("Consulta");
        menuEditar = new JMenu("Edita");
        menu = new JMenu("Menú");
        tancarSessio = new JMenu("Tancar Sessió");

        menuCrear.setEnabled(false);
        
        menuBar.add(menuCrear);
        menuBar.add(menuConsultar);
        menuBar.add(menuEditar);
        menuBar.add(menu);
        menuBar.add(tancarSessio);
        
        frameCrearJugador.setJMenuBar(menuBar);
        configurarMenu();
        
        frameCrearJugador.add(panel);
        
    }
    
    public JFrame getFrame() {
        return frameCrearJugador;
    }

    private void configurarMenu() {
        menuEditar.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                //controlador.moveToEliminarTemporada(frameCrearJugador);
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
                //controlador.moveToEliminarTemporada(frameCrearJugador);
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
}
