/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru.grafics;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
import potrony.bru.Interface.SportManagerInterfaceCP;
import potrony.bru.SportManager.Categoria;
import potrony.bru.SportManager.EnumSexe;
import potrony.bru.SportManager.EnumTipus;
import potrony.bru.SportManager.Equip;
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
        
    JMenu menuCrear;
    JMenu menuConsultar;
    JMenu menuEditar;
    JMenu menuAfegirJugadors;
    JMenu menu;
    JMenu tancarSessio;
    JPanel panel;
    
    JButton btnCrear;
    JButton btnCancelar;

    JTextField txtfNom;
    
    JRadioButton rbMasculi = new JRadioButton("Masculí");
    JRadioButton rbFemeni = new JRadioButton("Femení");
    JRadioButton rbMixt = new JRadioButton("Mixt");
    
    JComboBox<String> cbxTemporada;
    JComboBox<String> cbxCategoria;
    
    //Aquesta variable es per a que al fer el dispose del frame
    //No crei confusions, explicat mes endevant
    private boolean isProcessingMenu = false;
 

    public SwingFrameCrearEquip(SwingControladorUsuari controlador, SportManagerInterfaceCP bd) {
        frameCrearEquip = new JFrame();
        frameCrearEquip.setSize(AMPLADA, ALTURA);
        frameCrearEquip.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameCrearEquip.setLocationRelativeTo(null);
        frameCrearEquip.setTitle("Crear Equip");
        frameCrearEquip.setVisible(true);
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
        
        JLabel labelNom = new JLabel("Nom:");
        labelNom.setFont(new Font("Arial", Font.PLAIN, 20));
        labelNom.setBounds(100, 100, 100, 30); 
        panel.add(labelNom);

        txtfNom = new JTextField();
        txtfNom.setFont(new Font("Arial", Font.PLAIN, 20));
        txtfNom.setBounds(200, 95, 300, 40);
        panel.add(txtfNom);
        
        JLabel labelTipus = new JLabel("Tipus:");
        labelTipus.setFont(new Font("Arial", Font.PLAIN, 20));
        labelTipus.setBounds(100, 200, 100, 30); 
        panel.add(labelTipus);
        
        rbMasculi.setBounds(200, 205, 100, 20);
        rbMasculi.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(rbMasculi);
        rbFemeni.setBounds(320, 205, 100, 20);
        rbFemeni.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(rbFemeni);
        rbMixt.setBounds(440, 205, 100, 20);
        rbMixt.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(rbMixt);
        ButtonGroup rbGroup = new ButtonGroup();
        rbGroup.add(rbMixt);
        rbGroup.add(rbMasculi);
        rbGroup.add(rbFemeni);

        JLabel labelTemporada = new JLabel("Temporada:");
        labelTemporada.setFont(new Font("Arial", Font.PLAIN, 20));
        labelTemporada.setBounds(100, 300, 120, 30); 
        panel.add(labelTemporada);
        
        cbxTemporada = new JComboBox<>();
        cbxTemporada.setBounds(215, 300, 200, 30);
        cbxTemporada.setMaximumRowCount(5);
        frameCrearEquip.add(cbxTemporada);
        inicialitzarCbxTemporada();
        
        JLabel labelCategoria = new JLabel("Categoria:");
        labelCategoria.setFont(new Font("Arial", Font.PLAIN, 20));
        labelCategoria.setBounds(650, 100, 100, 30); 
        panel.add(labelCategoria);
        
        cbxCategoria = new JComboBox<>();
        cbxCategoria.setBounds(750, 100, 200, 30);
        frameCrearEquip.add(cbxCategoria);
        inicialitzarCbxCategoria();
        
        btnCrear = new JButton();
        btnCrear.setText("Crear");
        btnCrear.setBounds(600,450,120,40);
        panel.add(btnCrear);
        configurarBotoCrear();
        
        btnCancelar = new JButton();
        btnCancelar.setText("Cancelar");
        btnCancelar.setBounds(400,450,120,40);
        panel.add(btnCancelar);
        configurarBotoCancelar();
        
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
                
                //Faig el seguent metode de isProcessingMenu, ja que sinó al fer el dispose
                //del frame provoca que es generin events adicionals al lliberar els recursos
                //i aquest listener es crida dues vegades
                if (!isProcessingMenu) {
                    isProcessingMenu = true;
                    controlador.moveToConsultarEquip(frameCrearEquip);
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

    private void inicialitzarCbxTemporada() {
        try {
            List<Temporada> temporades = bd.loadTemporades();
            cbxTemporada.addItem("Selecciona una temporada");
            if (temporades!=null){
                for (Temporada temporada : temporades) {
                    cbxTemporada.addItem(String.valueOf(temporada.getAny()));
                }
            }
            
        } catch (Exception e) {
            controlador.missatgeError(e.getMessage());
            controlador.moveToMenu(frameCrearEquip);
        }
        
    }

    private void inicialitzarCbxCategoria() {
        try {
            List<Categoria> categories = bd.loadCategories();
            cbxCategoria.addItem("Selecciona una categoria");
            if (categories!=null){
                for (Categoria category : categories) {
                    cbxCategoria.addItem(category.getNom());
                }
            }
            
        } catch (Exception e) {
            controlador.missatgeError(e.getMessage());
            controlador.moveToMenu(frameCrearEquip);
        }
    }

    private void configurarBotoCrear() {
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Equip equip = null;
 
                try {
                    
                    Object selectedAny = cbxTemporada.getSelectedItem();
                    if (selectedAny == null || cbxTemporada.getSelectedIndex()==0) {
                        controlador.missatgeError("Selecciona una temporada");
                        return;
                    }
                    
                    int any = Integer.parseInt(selectedAny.toString());
                    String nom = txtfNom.getText();
                    EnumTipus tipus;
                    
                    if (rbMasculi.isSelected()) {
                        tipus = EnumTipus.H;
                    } else if (rbFemeni.isSelected()) {
                        tipus = EnumTipus.D;
                    } else if (rbMixt.isSelected()){
                        tipus = EnumTipus.M;
                    }else {
                        controlador.missatgeError("Selecciona un Tipus d'equip");
                        return;
                    }
                    
                    Object selectedCategory = cbxCategoria.getSelectedItem();
                    if (selectedCategory == null || cbxCategoria.getSelectedIndex()==0) {
                        controlador.missatgeError("Selecciona una Categoria");
                        return;
                    }
                    
                    String categoria = selectedCategory.toString().trim();
                    
                    if (rbMixt.isSelected()){
                        if(cbxCategoria.getSelectedIndex()>=4){
                            controlador.missatgeError("La categoria no pot ser de tipus Mixte");
                        }
                    }
                    
                    equip = new Equip(bd.getIdCategoria(categoria),any,nom,tipus);
                }catch (Exception ex) {
                    controlador.missatgeError(ex.getMessage());
                    return;
                }
                    

                   
                try {
                        bd.saveEquip(equip);
                        controlador.missatgeConfirmacio("Equip creat correctament.");                    
                } catch (Exception ex) {
                    controlador.missatgeError("Ja existeix equip amb nom "+equip.getNom()+" en la temporada "+equip.getIdTemporada());
                }
            }
        });
    }
    

}
