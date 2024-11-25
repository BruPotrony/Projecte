/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package potrony.bru.grafics;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import potrony.bru.CapaPersistencia.SportManagerOracle;
import potrony.bru.controladors.SwingControladorUsuari;

/**
 *
 * @author Vago
 */
public class SwingFrameMenu {

    private static final int AMPLADA = 600;
    private static final int ALTURA = 400;
    
    private static JFrame frameMenu;
    
    SwingControladorUsuari controlador;
    SportManagerOracle bd;

    JButton btnTemporada;
    JButton btnJugadors;
    JButton btnEquips;

    public SwingFrameMenu(SwingControladorUsuari controlador, SportManagerOracle bd) {
        frameMenu = new JFrame();
        frameMenu.setSize(AMPLADA, ALTURA);
        frameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMenu.setLocationRelativeTo(null);
        frameMenu.setTitle("Menu");
        frameMenu.setResizable(false);
        
        this.controlador = controlador;
        this.bd=bd;
        
        
        JPanel panel = new JPanel();
        panel.setLayout(null); 
        panel.setBounds(0, 0, AMPLADA, ALTURA); 

        btnEquips = new JButton();
        btnEquips.setText("Gestió Equips");
        btnEquips.setBounds(180,70,200,40);
        panel.add(btnEquips);
        configurarBotoEquip();
        
        btnJugadors = new JButton();
        btnJugadors.setText("Gestió Jugadors");
        btnJugadors.setBounds(180,150,200,40);
        panel.add(btnJugadors);
        configurarBotoJugador();
        
        btnTemporada = new JButton();
        btnTemporada.setText("Gestió Temporada");
        btnTemporada.setBounds(180,230,200,40);
        panel.add(btnTemporada);
        configurarBotoTemporada();
        
        
        frameMenu.add(panel);
    }
    
    public JFrame getFrame() {
        return frameMenu;
    }
    
    public void configurarBotoTemporada(){
        btnTemporada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.moveToCrearTemporada(frameMenu);
                
            }
        });
    }

    private void configurarBotoJugador() {
        btnJugadors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.moveToCrearJugador(frameMenu);
            }
        });
    }

    private void configurarBotoEquip() {
        //Equip
    }
    
}
