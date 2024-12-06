/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package potrony.bru.grafics;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
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
public class SwingFrameCrearTemporada{

    private static final int AMPLADA = 600;
    private static final int ALTURA = 400;
    
    private static JFrame frameTemporada;
    
    SwingControladorUsuari controlador;
    private SportManagerInterfaceCP bd;
    
    JButton btnCancelar;
    JButton btnCrear;
    JMenu menuEliminar;
    JMenu menu;
    JMenu tancarSessio;
    JComboBox<String> comboBoxAnys;
    
    //Aquesta variable es per a que al fer el dispose del frame
    //No crei confusions, explicat mes endevant
    private boolean isProcessingMenu = false;

    public SwingFrameCrearTemporada(SwingControladorUsuari controlador, SportManagerInterfaceCP bd) {
        frameTemporada = new JFrame();
        frameTemporada.setSize(AMPLADA, ALTURA);
        frameTemporada.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameTemporada.setLocationRelativeTo(null);
        frameTemporada.setTitle("Crear Temporada");
        frameTemporada.setVisible(true);
        frameTemporada.setResizable(false);
        
        this.controlador = controlador;
        this.bd=bd;

        JPanel panel = new JPanel();
        panel.setLayout(null); 
        panel.setBounds(0, 0, AMPLADA, ALTURA);  

        
        JMenuBar menuBar = new JMenuBar();

        JMenu crear = new JMenu("Crear");
        menuEliminar = new JMenu("Eliminar");
        menu = new JMenu("Menú");
        tancarSessio = new JMenu("Tancar Sessió");

        crear.setEnabled(false);
        
        menuBar.add(crear);
        menuBar.add(menuEliminar);
        menuBar.add(menu);
        menuBar.add(tancarSessio);

        frameTemporada.setJMenuBar(menuBar);
        configurarMenu();
        
        JLabel titol = new JLabel("Nova temporada");
        titol.setFont(new Font("Arial", Font.BOLD, 30));
        titol.setBounds(170, 50, 250, 30);
        panel.add(titol);
        
        
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        //Generar un rang de 5 anys mes i menys, ja que es el que la bd accepta
        String[] years = new String[11];
        years[0] = "Any Inici";
        for (int i = 1; i < 11; i++) {
            years[i] = String.valueOf(currentYear - 5 + i);
        }

        comboBoxAnys = new JComboBox<>(years);
        comboBoxAnys.setBounds(215, 100, 150, 30);
        comboBoxAnys.setMaximumRowCount(5);
        frameTemporada.add(comboBoxAnys);
        
        btnCancelar = new JButton();
        btnCancelar.setText("Cancelar");
        btnCancelar.setBounds(150,270,120,40);
        panel.add(btnCancelar);
        configurarBotoCancelar();
        
        
        btnCrear = new JButton();
        btnCrear.setText("Crear");
        btnCrear.setBounds(300,270,120,40);
        panel.add(btnCrear);
        configurarBotoCrear();
        
        frameTemporada.add(panel);
        
    }
    
    public JFrame getFrame() {
        return frameTemporada;
    }
    
    
    public void configurarMenu(){
        menuEliminar.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                //Faig el seguent metode de isProcessingMenu, ja que sinó al fer el dispose
                //del frame provoca que es generin events adicionals al lliberar els recursos
                //i aquest listener es crida dues vegades
                if (!isProcessingMenu) {
                    isProcessingMenu = true;
                    controlador.moveToEliminarTemporada(frameTemporada);
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
    
    public void configurarBotoCrear(){
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selectedItem = comboBoxAnys.getSelectedItem();
                if (selectedItem == null || comboBoxAnys.getSelectedIndex()==0) {
                    controlador.missatgeError("Selecciona una temporada abans de crearla.");
                    return;
                }

                try {
                    int any = Integer.parseInt(selectedItem.toString());
                    bd.saveTemporada(new Temporada(any));
                    comboBoxAnys.addItem(selectedItem.toString());
                    controlador.missatgeConfirmacio("Temporada creada correctament.");
                    bd.confirmarCanvis();
                    
                } catch (GestorSportManagerException ex) {

                    controlador.missatgeError("Ja existeix temporada amb any "+selectedItem.toString());
                }
            }
        });
    }
}
