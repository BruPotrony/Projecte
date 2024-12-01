/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru.grafics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import potrony.bru.Interface.SportManagerInterfaceCP;
import potrony.bru.controladors.SwingControladorUsuari;

/**
 *
 * @author isard
 */
public class SwingFrameConsultarEquip {
    private static final int AMPLADA = 1100;
    private static final int ALTURA = 600;
    
    private static JFrame frameConsultarEquip;

    SwingControladorUsuari controlador;
    SportManagerInterfaceCP bd; 
        
    JMenu menuCrear;
    JMenu menuConsultar;
    JMenu menuEditar;
    JMenu menuAfegirJugador;
    JMenu menu;
    JMenu tancarSessio;
    JPanel panel;

    
    JScrollPane jspTaula;
    DefaultTableModel tableModel;
    JTable table;
    
    //Aquesta variable es per a que al fer el dispose del frame
    //No crei confusions, explicat mes endevant
    private boolean isProcessingMenu = false;


    public SwingFrameConsultarEquip(SwingControladorUsuari controlador, SportManagerInterfaceCP bd) {
        frameConsultarEquip = new JFrame();
        frameConsultarEquip.setSize(AMPLADA, ALTURA);
        frameConsultarEquip.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameConsultarEquip.setLocationRelativeTo(null);
        frameConsultarEquip.setTitle("Crear Jugador");
        frameConsultarEquip.setVisible(true);
        frameConsultarEquip.setResizable(false);
        
        this.controlador = controlador;
        this.bd = bd;
        
        panel = new JPanel();
        panel.setLayout(null); 
        panel.setBounds(0, 0, AMPLADA, ALTURA); 
        
        JMenuBar menuBar = new JMenuBar();
        
        menuCrear = new JMenu("Crear");
        menuConsultar = new JMenu("Consulta");
        menuEditar = new JMenu("Edita");
        menuAfegirJugador = new JMenu("Afegir Jugadors");
        menu = new JMenu("Menú");
        tancarSessio = new JMenu("Tancar Sessió");

        menuConsultar.setEnabled(false);
        
        menuBar.add(menuCrear);
        menuBar.add(menuConsultar);
        menuBar.add(menuEditar);
        menuBar.add(menu);
        menuBar.add(tancarSessio);
        
        frameConsultarEquip.setJMenuBar(menuBar);
        configurarMenu();
        
        jspTaula = new JScrollPane();
        inicialitzarTaula();
        panel.add(jspTaula);
                    
        frameConsultarEquip.add(panel);
    }
    
    public JFrame getFrame() {
        return frameConsultarEquip;
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
        
        menuCrear.addMenuListener(new MenuListener() {
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
        
        controlador.configurarJMenuBar(frameConsultarEquip, menu, tancarSessio);
    }



    private void inicialitzarTaula() {
        String[] columnNames = {"Categoria", "Nom Equip", "Temporada", "Tipus"};

        Object[][] taula = {};

        tableModel = new DefaultTableModel(taula, columnNames);

        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100, 50, 900, 300);
        
        jspTaula.setViewportView(table);

        jspTaula.setBounds(100, 50, 900, 300);
    }

}
