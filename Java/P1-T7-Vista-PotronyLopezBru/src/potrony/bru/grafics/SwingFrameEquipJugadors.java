/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru.grafics;

import java.awt.Font;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import potrony.bru.Interface.SportManagerInterfaceCP;
import potrony.bru.controladors.SwingControladorUsuari;

/**
 *
 * @author isard
 */
public class SwingFrameEquipJugadors {


    
    private static final int AMPLADA = 1100;
    private static final int ALTURA = 600;
    
    private static JFrame frameConsultarJugador;

    SwingControladorUsuari controlador;
    SportManagerInterfaceCP bd; 
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    JMenu menuCrear;
    JMenu menuAfegirJugadorEquip;
    JMenu menuConsulta;
    JMenu menu;
    JMenu tancarSessio;
    JPanel panel;
    JTextField txtfNom;
    JTextField txtfNif;
    JTextField txtfDataNaix;
    JComboBox<String> cbxCategoria;
    JComboBox<String> filtre;
    JButton btnFiltre;
    JButton btnEliminar;
    JButton btnBuscar;
    
    JScrollPane jspTaula;
    DefaultTableModel tableModel;
    JTable table;
    
    //Aquesta variable es per a que al fer el dispose del frame
    //No crei confusions, explicat mes endevant
    private boolean isProcessingMenu = false;


    public SwingFrameEquipJugadors(SwingControladorUsuari controlador, SportManagerInterfaceCP bd, String nomEquip, int any) {
        frameConsultarJugador = new JFrame();
        frameConsultarJugador.setSize(AMPLADA, ALTURA);
        frameConsultarJugador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameConsultarJugador.setLocationRelativeTo(null);
        frameConsultarJugador.setTitle("Assignar jugador a equip");
        frameConsultarJugador.setVisible(true);
        frameConsultarJugador.setResizable(false);
        
        this.controlador = controlador;
        this.bd = bd;
        
        panel = new JPanel();
        panel.setLayout(null); 
        panel.setBounds(0, 0, AMPLADA, ALTURA); 
        
        JMenuBar menuBar = new JMenuBar();
        
        menuCrear = new JMenu("Crear");
        menuAfegirJugadorEquip = new JMenu("Afegir jugador a equip");
        menuConsulta = new JMenu("Consultar");
        menu = new JMenu("Menú");
        tancarSessio = new JMenu("Tancar Sessió");

        menuAfegirJugadorEquip.setEnabled(false);
        
        menuBar.add(menuCrear);
        menuBar.add(menuConsulta);
        menuBar.add(menuAfegirJugadorEquip);
        menuBar.add(menu);
        menuBar.add(tancarSessio);
        
        frameConsultarJugador.setJMenuBar(menuBar);
        configurarMenu();
        
        cbxCategoria=new JComboBox<>();
        inicialitzarComboBoxCategoria();
        cbxCategoria.setBounds(50,40,180,40);
        panel.add(cbxCategoria);
        
        txtfNom = new JTextField();
        txtfNom.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        txtfNom.setBounds(250, 40, 180, 40);
        txtfNom.setText("🔎Nom");
        panel.add(txtfNom);
        configurarTextFieldBusca("Nom",txtfNom);
        
        txtfNif = new JTextField();
        txtfNif.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        txtfNif.setBounds(450, 40, 180, 40);
        txtfNif.setText("🔎DNI");
        panel.add(txtfNif);
        configurarTextFieldBusca("DNI",txtfNif);
        
        txtfDataNaix = new JTextField();
        txtfDataNaix.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        txtfDataNaix.setBounds(650, 40, 180, 40);
        txtfDataNaix.setText("🔎Naix(dd/MM/yyyy)");
        panel.add(txtfDataNaix);
        configurarTextFieldBusca("Naix(dd/MM/yyyy)",txtfDataNaix);
        
        filtre = new JComboBox<>();
        filtre.setBounds(850, 40, 150, 40);
        panel.add(filtre);
        InicialitzarFiltre();
        
        btnFiltre = new JButton();
        btnFiltre.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        btnFiltre.setBounds(1010,45,35,35);
        btnFiltre.setText("🔽");
        btnFiltre.setVerticalAlignment(JButton.CENTER);
        panel.add(btnFiltre);
        configurarBotoFiltre();
        
        jspTaula = new JScrollPane();
        inicialitzarTaula();
        panel.add(jspTaula);
        
        btnEliminar = new JButton();
        btnEliminar.setText("Eliminar");
        btnEliminar.setBounds(400,450,120,40);
        panel.add(btnEliminar);
        configurarBotoEliminar();
        
        btnBuscar = new JButton();
        btnBuscar.setText("Buscar");
        btnBuscar.setBounds(600,450,120,40);
        panel.add(btnBuscar);
        configurarBotoBuscar();
                    
        frameConsultarJugador.add(panel);
    }
    
    public JFrame getFrame() {
        return frameConsultarJugador;
    }
    
}
