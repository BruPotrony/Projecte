/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru.grafics;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import potrony.bru.CapaPersistencia.SportManagerOracle;
import potrony.bru.Interface.GestorSportManagerException;
import potrony.bru.Interface.SportManagerInterfaceCP;
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
    SportManagerInterfaceCP bd; 
        
    JMenu menuCrear;
    JMenu menuConsultar;
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


    public SwingFrameConsultarJugador(SwingControladorUsuari controlador, SportManagerInterfaceCP bd) {
        frameConsultarJugador = new JFrame();
        frameConsultarJugador.setSize(AMPLADA, ALTURA);
        frameConsultarJugador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameConsultarJugador.setLocationRelativeTo(null);
        frameConsultarJugador.setTitle("Consultar Jugadors");
        frameConsultarJugador.setVisible(true);
        frameConsultarJugador.setResizable(false);
        
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

        menuConsultar.setEnabled(false);
        
        menuBar.add(menuCrear);
        menuBar.add(menuConsultar);
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
        
        JLabel missatgeInfo = new JLabel(
            "<html><span style='color:yellow;'>&#9888;</span> Doble clic sobre jugador per a editar-lo</html>"
        );
        missatgeInfo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        missatgeInfo.setBounds(50, 400, 400, 30);
        panel.add(missatgeInfo);    
        
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
    
    private void configurarMenu() {
        
        menuCrear.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                //Faig el seguent metode de isProcessingMenu, ja que sinó al fer el dispose
                //del frame provoca que es generin events adicionals al lliberar els recursos
                //i aquest listener es crida dues vegades
                if (!isProcessingMenu) {
                    isProcessingMenu = true;
                    controlador.moveToCrearJugador(frameConsultarJugador);
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
        
        controlador.configurarJMenuBar(frameConsultarJugador, menu, tancarSessio);
    }

    private void inicialitzarComboBoxCategoria() {
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
            controlador.moveToMenu(frameConsultarJugador);
        }
        
        
    }

    private void configurarTextFieldBusca(String cadena, JTextField textField) {
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals("🔎"+cadena)) {
                    textField.setText("");

                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText("🔎"+cadena);
                    
                }
            }
        });
    }


    private void InicialitzarFiltre() {
        filtre.addItem("Filtre");
        filtre.addItem("Cognom");
        filtre.addItem("Data Naixement");
    }

    private void configurarBotoFiltre() {
        btnFiltre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnFiltre.getText().equals("🔽")){
                    btnFiltre.setText("🔼");
                }else{
                    btnFiltre.setText("🔽");
                }
            }
        });
    }

    private void inicialitzarTaula() {
        String[] columnNames = {"Nom", "Cognom", "NIF", "Edat", "Categoria","Sexe"};

        Object[][] data = {};
        
        tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 100, 1000, 300);
        
        jspTaula.setViewportView(table);

        jspTaula.setBounds(50, 100, 1000, 300);
        
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = table.rowAtPoint(e.getPoint());
                    if (!table.getValueAt(selectedRow, 1).toString().isEmpty()) {
                        String idLegal = table.getValueAt(selectedRow, 2).toString();

                        controlador.moveToEditarJugador(frameConsultarJugador, idLegal);
                    }
                }
            }
        });
    }
    
    private Object calcularCategoria(int edat) {
        try {
            List<Categoria>categories = bd.loadCategories();
            
            for (Categoria cat : categories) {
                if (edat>=cat.getEdat_min() && edat<=cat.getEdat_max()) {
                    return cat.getNom();
                }
            }
        } catch (GestorSportManagerException ex) {
            return null;
        }
        return null;
    }
    
    private void inserirJugadorsTaula(List<Jugador> jugadors){

       try {
            tableModel.setRowCount(0);

            String selectedCategory = (cbxCategoria.getSelectedIndex() != 0) ? cbxCategoria.getSelectedItem().toString().trim() : "";
            
            if(filtre.getSelectedIndex()!=0){
                ordenarJugadors(jugadors);
            }

            for (Jugador jugador : jugadors) {
                String categoriaJugador = calcularCategoria(jugador.calcularEdatIniciAnyActual(jugador.getData_naix())).toString();

                if (!selectedCategory.isEmpty() && !categoriaJugador.equals(selectedCategory)) {
                    continue;
                }

                Object[] row = {
                    jugador.getNom(),
                    jugador.getCognom(),
                    jugador.getId_Legal(),
                    jugador.calcularEdatIniciAnyActual(jugador.getData_naix()),
                    categoriaJugador,
                    jugador.getSexe().toString()
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            controlador.missatgeError(e.getMessage());
        }
        
        panel.revalidate();
        panel.repaint();
    }

    private void configurarBotoEliminar() {
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) { 
                    controlador.missatgeError("Selecciona un jugador abans d'eliminar.");
                    return;
                }

                try {                  

                    int resposta = JOptionPane.showConfirmDialog(null, 
                        "Estas segur de que vols eliminar el jugador?", 
                        "Confirmar Eliminació",
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE);

                    if (resposta == JOptionPane.YES_OPTION) {
                        String idLegal = table.getValueAt(selectedRow, 2).toString();

                        bd.eliminarJugadorIdLegal(idLegal);
                        tableModel.removeRow(selectedRow);
                        controlador.missatgeConfirmacio("Jugador eliminat correctament");
                        panel.revalidate();
                        panel.repaint();
                    }
                } catch (Exception ex) {
                    controlador.missatgeError("ERROR: El jugador esta en un equip");
                }
            }
        });
    }

    private void configurarBotoBuscar() {
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nom = txtfNom.getText().equals("🔎Nom") ? "" : txtfNom.getText().trim();
                    String idLegal = txtfNif.getText().equals("🔎DNI") ? "" :txtfNif.getText().trim();
                    String dataNaixStr = txtfDataNaix.getText().equals("🔎Naix(dd/MM/yyyy)") ?"":txtfDataNaix.getText().trim();
                    Date dataNaix = null;

                    if (!dataNaixStr.isEmpty()) {
                        try {
                            SimpleDateFormat sdf    = new SimpleDateFormat("dd/MM/yyyy");
                            dataNaix = sdf.parse(dataNaixStr);
                        } catch (ParseException ex) {
                            controlador.missatgeError("Data no valida");
                            return;
                        }
                    }
                    
                    List<Jugador> jugadors;

                    if (nom.isEmpty() && idLegal.isEmpty() && dataNaix == null) {
                        jugadors = bd.loadJugadors();
                    }

                    

                    if (!nom.isEmpty() && idLegal.isEmpty() && dataNaix == null) {
                        jugadors = bd.loadJugadorNomNifDatanaix(nom, null, null);
                        
                    } else if (nom.isEmpty() && !idLegal.isEmpty() && dataNaix == null) {
                        jugadors = bd.loadJugadorNomNifDatanaix(null, idLegal, null);
                        
                    } else if (nom.isEmpty() && idLegal.isEmpty() && dataNaix != null) {
                        jugadors = bd.loadJugadorNomNifDatanaix(null, null, dataNaix);
                        
                    } else {
                        jugadors = bd.loadJugadorNomNifDatanaix(nom, idLegal, dataNaix);
                    }

                    if (!jugadors.isEmpty()) {
                        inserirJugadorsTaula(jugadors);
                    } else {
                        controlador.missatgeError("No hi ha cap jugador amb els parametres especificats.");
                        tableModel.setRowCount(0);
                    }
                } catch (GestorSportManagerException ex) {
                    controlador.missatgeError(ex.getMessage());
                }
            }
        });
    }

    private void ordenarJugadors(List<Jugador> jugadors) {
        int selectedIndex = filtre.getSelectedIndex();
        boolean esOrdreDescendent = btnFiltre.getText().equals("🔽");

        Comparator<Jugador> comparator = null;

        switch (selectedIndex) {
            case 1: // Ordenar per cognom
                comparator = (j1, j2) -> j1.getCognom().compareToIgnoreCase(j2.getCognom());
                break;

            case 2: // Ordenar per data de naixement
                comparator = (j1, j2) -> j1.getData_naix().compareTo(j2.getData_naix());
                break;
        }

        // Si l'ordre és descendent, inverteix la comparació
        if (esOrdreDescendent && comparator != null) {
            comparator = comparator.reversed();
        }

        if (comparator != null) {
            Collections.sort(jugadors, comparator);
        }
        
    }

}