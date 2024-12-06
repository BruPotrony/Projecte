/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package potrony.bru.grafics;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import potrony.bru.Interface.GestorSportManagerException;
import potrony.bru.Interface.SportManagerInterfaceCP;
import potrony.bru.SportManager.Temporada;
import potrony.bru.controladors.SwingControladorUsuari;


/**
 *
 * @author Vago
 */
public class SwingFrameEliminarTemporada{

    private static final int AMPLADA = 600;
    private static final int ALTURA = 400;
    
    private static JFrame frameTemporada;
    
    private SwingControladorUsuari controlador;
    private SportManagerInterfaceCP bd;
    
    JButton btnCancelar;
    JButton btnEliminar;
    JMenu menuCrear;
    JMenu menu;
    JMenu tancarSessio;
    JComboBox<String> comboBoxAnys;
    
    //Aquesta variable es per a que al fer el dispose del frame
    //No crei confusions, explicat mes endevant
    private boolean isProcessingMenu = false;

    public SwingFrameEliminarTemporada(SwingControladorUsuari controlador, SportManagerInterfaceCP bd) {
        frameTemporada = new JFrame();
        frameTemporada.setSize(AMPLADA, ALTURA);
        frameTemporada.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameTemporada.setLocationRelativeTo(null);
        frameTemporada.setTitle("Eliminar Temporada");
        frameTemporada.setVisible(true);
        frameTemporada.setResizable(false);

        this.controlador = controlador;
        this.bd=bd;
        
        JPanel panel = new JPanel();
        panel.setLayout(null); 
        panel.setBounds(0, 0, AMPLADA, ALTURA);  

        
        JMenuBar menuBar = new JMenuBar();

        menuCrear = new JMenu("Crear");
        JMenu menuEliminar = new JMenu("Eliminar");
        menu = new JMenu("Menú");
        tancarSessio = new JMenu("Tancar Sessió");

        menuEliminar.setEnabled(false);
        
        menuBar.add(menuCrear);
        menuBar.add(menuEliminar);
        menuBar.add(menu);
        menuBar.add(tancarSessio);

        frameTemporada.setJMenuBar(menuBar);
        configurarMenu();
        
        JLabel titol = new JLabel("Eliminar temporada");
        titol.setFont(new Font("Arial", Font.BOLD, 30));
        titol.setBounds(140, 50, 300, 30);
        panel.add(titol);

        comboBoxAnys = new JComboBox<>();
        comboBoxAnys.setBounds(210, 100, 150, 30);
        comboBoxAnys.setMaximumRowCount(5);
        frameTemporada.add(comboBoxAnys);
        
        btnCancelar = new JButton();
        btnCancelar.setText("Cancelar");
        btnCancelar.setBounds(150,270,120,40);
        panel.add(btnCancelar);
        configurarBotoCancelar();
        
        btnEliminar = new JButton();
        btnEliminar.setText("Eliminar");
        btnEliminar.setBounds(300,270,120,40);
        panel.add(btnEliminar);
        configurarBotoEliminar();
        
        actualitzarTemporades();
        
        frameTemporada.add(panel);
        
    }
    
    public JFrame getFrame() {
        return frameTemporada;
    }
    
    public void configurarMenu(){
        menuCrear.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                //Faig el seguent metode de isProcessingMenu, ja que sinó al fer el dispose
                //del frame provoca que es generin events adicionals al lliberar els recursos
                //i aquest listener es crida dues vegades
                if (!isProcessingMenu) {
                    isProcessingMenu = true;
                    controlador.moveToCrearTemporada(frameTemporada);
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
        
        controlador.configurarJMenuBar(frameTemporada, menu, tancarSessio);
    }
    
    
    public void configurarBotoCancelar(){
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.moveToMenu(frameTemporada); 
            }
        });
    }
    
    public void configurarBotoEliminar(){
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selectedItem = comboBoxAnys.getSelectedItem();
                if (selectedItem == null) {
                    controlador.missatgeError("Selecciona una temporada abans d'eliminar.");
                    return;
                }

                try {
                    int any = Integer.parseInt(selectedItem.toString());
                    bd.eliminarTemporada(any);
                    comboBoxAnys.removeItem(selectedItem);
                    controlador.missatgeConfirmacio("Temporada eliminada correctament.");
                    bd.confirmarCanvis();
                } catch (GestorSportManagerException ex) {

                    controlador.missatgeError("No es pot eliminar una temporada que conté dades.");
                }
            }
        });
    }

    public void actualitzarTemporades() {
        try {
            List<Temporada> temporades = bd.loadTemporades();
            comboBoxAnys.removeAllItems();
            
            if (!temporades.isEmpty()){
                for (Temporada tempo : temporades) {
                    comboBoxAnys.addItem(String.valueOf(tempo.getAny()));
                }
            }
        } catch (GestorSportManagerException ex) {
            controlador.missatgeError("Error en carregar les temporades\nConsultar amb l'Administrador");
        }
    }

}
