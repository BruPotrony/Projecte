/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru.grafics;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import potrony.bru.Interface.GestorSportManagerException;
import potrony.bru.Interface.SportManagerInterfaceCP;
import potrony.bru.SportManager.Categoria;
import potrony.bru.SportManager.EnumTipus;
import potrony.bru.SportManager.Equip;
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
    JMenu menu;
    JMenu tancarSessio;
    JPanel panel;

    JLabel labelJRS;
    JButton btnEliminar;
    
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
        menu = new JMenu("Menú");
        tancarSessio = new JMenu("Tancar Sessió");

        menuConsultar.setEnabled(false);
        
        menuBar.add(menuCrear);
        menuBar.add(menuConsultar);
        menuBar.add(menu);
        menuBar.add(tancarSessio);
        
        frameConsultarEquip.setJMenuBar(menuBar);
        configurarMenu();
        
        jspTaula = new JScrollPane();
        configurarTaula();
        inicialitzarTaula();
        panel.add(jspTaula);
        
        labelJRS = new JLabel("<html><u><font color='#ADD8E6'>Crear informe Jasper Report Server</font></u></html>");
        labelJRS.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelJRS.setFont(new Font("Arial", Font.PLAIN, 15));
        labelJRS.setBounds(420, 400, 300, 30); 
        panel.add(labelJRS);
        configurarLabelJRS();
        
        JLabel missatgeInfo = new JLabel(
            "<html><span style='color:yellow;'>&#9888;</span> Doble clic sobre equip per a assignar Jugadors</html>"
        );
        missatgeInfo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        missatgeInfo.setBounds(100, 350, 400, 30);
        panel.add(missatgeInfo);    
        
        btnEliminar = new JButton();
        btnEliminar.setText("Eliminar");
        btnEliminar.setBounds(480,450,120,40);
        panel.add(btnEliminar);
        configurarBotoEliminar();
                    
        frameConsultarEquip.add(panel);
    }
    
    public JFrame getFrame() {
        return frameConsultarEquip;
    }
    
    private void configurarMenu() {
        menuCrear.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                controlador.moveToCrearEquip(frameConsultarEquip);
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



    private void configurarTaula() {

        
        String[] columnNames = {"Categoria", "Nom Equip", "Temporada", "Tipus"};

        Object[][] taula = {};

        tableModel = new DefaultTableModel(taula, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100, 50, 900, 300);
        
        jspTaula.setViewportView(table);

        jspTaula.setBounds(100, 50, 900, 300);
        
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = table.rowAtPoint(e.getPoint());
                    if (!table.getValueAt(selectedRow, 1).toString().isEmpty()) {
                        String nomEquip = table.getValueAt(selectedRow, 1).toString();
                        int temporada = Integer.parseInt(table.getValueAt(selectedRow, 2).toString());

                        controlador.moveToEquipJugadors(frameConsultarEquip, nomEquip, temporada);
                    }
                }
            }
        });
    }

    private void inicialitzarTaula() {
        try {
            List<Equip> equips = bd.loadEquips();
            Map<Long, Categoria> categoriasMap = new HashMap<>();

            if (equips != null && !equips.isEmpty()) {
                for (Equip equip : equips) {
                    long idCategoria = equip.getIdCategoria();
                    if (!categoriasMap.containsKey(idCategoria)) {
                        Categoria categoria = bd.loadCategoriaId(idCategoria);
                        categoriasMap.put(idCategoria, categoria);
                    }
                }

                String nomCategoriaAnterior = categoriasMap.get(equips.get(0).getIdCategoria()).getNom();

                for (Equip equip : equips) {
                    String nomCategoriaActual = categoriasMap.get(equip.getIdCategoria()).getNom();

                    if (nomCategoriaActual.equals(nomCategoriaAnterior)) {
                        
                        String tipusText;
                        switch (equip.getTipus()) {
                            case H:
                                tipusText = "Masculí";
                                break;
                            case D:
                                tipusText = "Femení";
                                break;
                            default:
                                tipusText = "Mixt";
                                break;
                        }
                        
                        Object[] row = {
                            nomCategoriaActual,
                            equip.getNom(),
                            equip.getIdTemporada(),
                            tipusText
                        };
                        tableModel.addRow(row);
                    } else {
                        Object[] row = {"", "", "", ""};
                        tableModel.addRow(row);
                        nomCategoriaAnterior = nomCategoriaActual;
                    }
                }
            }
            
        } catch (GestorSportManagerException ex) {
            controlador.missatgeError(ex.getMessage());
        }
    }
    
    private void configurarBotoEliminar() {
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1 || table.getValueAt(selectedRow, 1).toString().isEmpty()) { 
                    controlador.missatgeError("Selecciona un equip abans d'eliminar.");
                    return;
                }

                try {                  

                    int resposta = JOptionPane.showConfirmDialog(null, 
                        "Estas segur de que vols eliminar l'Equip?", 
                        "Confirmar Eliminació",
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE);

                    if (resposta == JOptionPane.YES_OPTION) {
                        String nom = table.getValueAt(selectedRow, 1).toString();
                        int temporada = Integer.parseInt(table.getValueAt(selectedRow, 2).toString());
                    
                        
                        try{
                            if (bd.equipTeJugadors(nom, temporada)){
                                int resposta2 = JOptionPane.showConfirmDialog(null, 
                                "L'equip conte jugadors, estas segur que el vols eliminar?", 
                                "Confirmar Eliminació",
                                JOptionPane.YES_NO_OPTION, 
                                JOptionPane.QUESTION_MESSAGE);

                                if (resposta2 == JOptionPane.YES_OPTION) {
                                    bd.eliminarEquipAmbJugadors(nom, temporada);
                                }else{
                                    return;
                                }
                            }else{
                                bd.eliminarEquip(nom,temporada);
                            }
                        }catch (Exception ex) {
                            controlador.missatgeError(ex.getMessage());
                            return;
                        }
                    
                        
                        tableModel.removeRow(selectedRow);
                        controlador.missatgeConfirmacio("Equip "+nom+" eliminat correctament");
                        panel.revalidate();
                        panel.repaint();
                        
                        
                    } else {
                        controlador.missatgeConfirmacio("Operació cancelada.");
                    }
                } catch (Exception ex) {
                    controlador.missatgeError(ex.getMessage());
                    
                }
            }
        });
    }

    private void configurarLabelJRS() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
