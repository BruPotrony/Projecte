/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru.grafics;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import potrony.bru.Interface.GestorSportManagerException;
import potrony.bru.Interface.SportManagerInterfaceCP;
import potrony.bru.SportManager.Categoria;
import potrony.bru.SportManager.EnumSexe;
import potrony.bru.SportManager.EnumTipus;
import potrony.bru.SportManager.EnumTitular;
import potrony.bru.SportManager.Equip;
import potrony.bru.SportManager.Jugador;
import potrony.bru.SportManager.Membre;
import potrony.bru.controladors.SwingControladorUsuari;

/**
 *
 * @author isard
 */
public class SwingFrameEquipJugadors {


    
    private static final int AMPLADA = 1100;
    private static final int ALTURA = 600;
    
    private static JFrame frameEquipJugadors;

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
    JButton btnCancelar;
    JButton btnBuscar;
    JButton btnGuardar;
    
    JScrollPane jspTaula;
    DefaultTableModel tableModel;
    JTable table;
    
    List<Integer> rowEditades;
    Equip equip;
    
    //Aquesta variable es per a que al fer el dispose del frame
    //No crei confusions, explicat mes endevant
    private boolean isProcessingMenu = false;


    public SwingFrameEquipJugadors(SwingControladorUsuari controlador, SportManagerInterfaceCP bd, String nomEquip, int any) {
        
        frameEquipJugadors = new JFrame();
        frameEquipJugadors.setSize(AMPLADA, ALTURA);
        frameEquipJugadors.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameEquipJugadors.setLocationRelativeTo(null);
        frameEquipJugadors.setTitle("Assignar jugador a equip");
        frameEquipJugadors.setVisible(true);
        frameEquipJugadors.setResizable(false);
        
        this.controlador = controlador;
        this.bd = bd;
        this.rowEditades = new ArrayList<>();
        
        try {
            equip = bd.loadEquipNom(nomEquip, any);
        } catch (GestorSportManagerException ex) {
            controlador.missatgeError("Error en cargar l'equip");
            return;
        }
        
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
        
        frameEquipJugadors.setJMenuBar(menuBar);
        configurarMenu();
        
        JLabel titol = new JLabel("Equip: "+equip.getNom());
        titol.setFont(new Font("Arial", Font.BOLD, 20));
        titol.setBounds(50, 30, 800, 30);
        panel.add(titol);
        
        cbxCategoria=new JComboBox<>();
        inicialitzarComboBoxCategoria();
        cbxCategoria.setBounds(50,90,180,40);
        panel.add(cbxCategoria);
        
        txtfNom = new JTextField();
        txtfNom.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        txtfNom.setBounds(250, 90, 180, 40);
        txtfNom.setText("🔎Nom");
        panel.add(txtfNom);
        configurarTextFieldBusca("Nom",txtfNom);
        
        txtfNif = new JTextField();
        txtfNif.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        txtfNif.setBounds(450, 90, 180, 40);
        txtfNif.setText("🔎DNI");
        panel.add(txtfNif);
        configurarTextFieldBusca("DNI",txtfNif);
        
        txtfDataNaix = new JTextField();
        txtfDataNaix.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        txtfDataNaix.setBounds(650, 90, 180, 40);
        txtfDataNaix.setText("🔎Naix(dd/MM/yyyy)");
        panel.add(txtfDataNaix);
        configurarTextFieldBusca("Naix(dd/MM/yyyy)",txtfDataNaix);
        
        filtre = new JComboBox<>();
        filtre.setBounds(850, 90, 150, 40);
        panel.add(filtre);
        InicialitzarFiltre();
        
        btnFiltre = new JButton();
        btnFiltre.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        btnFiltre.setBounds(1010,95,35,35);
        btnFiltre.setText("🔽");
        btnFiltre.setVerticalAlignment(JButton.CENTER);
        panel.add(btnFiltre);
        configurarBotoFiltre();
        
        jspTaula = new JScrollPane();
        inicialitzarTaula();
        panel.add(jspTaula);
        
        JLabel missatgeInfo = new JLabel(
            "<html><span style='color:yellow;'>&#9888;</span> Nomes apareixen els jugadors que poden estar o estan a l'equip</html>"
        );
        missatgeInfo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        missatgeInfo.setBounds(50, 400, 500, 30);
        panel.add(missatgeInfo);  
        
        btnCancelar = new JButton();
        btnCancelar.setText("Cancelar");
        btnCancelar.setBounds(350,450,120,40);
        panel.add(btnCancelar);
        configurarBotoCancelar();
        
        btnGuardar = new JButton();
        btnGuardar.setText("Guardar");
        btnGuardar.setBounds(650,450,120,40);
        panel.add(btnGuardar);
        configurarBotoGuardar();
        
        btnBuscar = new JButton();
        btnBuscar.setText("Buscar");
        btnBuscar.setBounds(500,450,120,40);
        panel.add(btnBuscar);
        configurarBotoBuscar();
                    
        frameEquipJugadors.add(panel);
    }
    
    public JFrame getFrame() {
        return frameEquipJugadors;
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
                    controlador.moveToCrearEquip(frameEquipJugadors);
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
        
        menuConsulta.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                if (!isProcessingMenu) {
                    isProcessingMenu = true;
                    controlador.moveToConsultarEquip(frameEquipJugadors);
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
        
        controlador.configurarJMenuBar(frameEquipJugadors, menu, tancarSessio);
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
    
    private void configurarBotoGuardar() {
        btnGuardar.addActionListener((ActionEvent e) -> {
            
            if (rowEditades.isEmpty()){
                controlador.missatgeError("No s'ha modificat cap jugador");
                return;
            }
            
            for (Integer row : rowEditades) {
                String idLegal = table.getValueAt(row, 2).toString().trim();
                Membre membre = null;
                EnumTitular titularitat;
                
                try{
                    long idJugador = bd.getGeneratedJugadorId(idLegal);
                    boolean estaJugadorEnEquip = bd.estaJugadorEnEquip(idJugador,equip.getId());
                    boolean esTitularAlgunEquip = bd.esTitular(idJugador, equip.getIdTemporada());
                    boolean estaCbxTitularApretat = (Boolean)table.getValueAt(row, 7);
                    
                    if ((Boolean)table.getValueAt(row, 6)){
                        if (estaCbxTitularApretat) {

                            if (esTitularAlgunEquip) {
                                bd.remplacarTitularConvidat(idJugador);
                                bd.confirmarCanvis();
                            }
                            titularitat = EnumTitular.T;
                        } else {
                            titularitat = EnumTitular.C;
                        }

                        if (!estaJugadorEnEquip){
                            membre = new Membre(equip.getId(), idJugador, titularitat);
                            bd.afegirJugadorEquip(membre);
                        }else{
                            bd.canviarTitularitat(idJugador,equip.getId(),titularitat.toString());
                        }
                        
                    }else{
                        if (estaJugadorEnEquip){
                            bd.eliminarJugadorEquip(idJugador,equip.getId());
                        }else{
                            return;
                        }
                    }
                    
                }catch (Exception ex){
                    int anyEquip = equip.getIdTemporada();
                    int anyActual = LocalDate.now().getYear();

                    if (anyEquip > anyActual) {
                        controlador.missatgeError("En la temporada: "+anyEquip+" el jugador sera d'una edat superior a la permesa en la categoria");
                    }else{
                        controlador.missatgeError("Error: "+ex.getMessage());
                    }
                    
                    rowEditades.clear();
                    return;
                }                    
            }
            controlador.missatgeConfirmacio("Canvis guardats correctament");
            try {
                bd.confirmarCanvis();
            } catch (GestorSportManagerException ex) {
                controlador.missatgeError(ex.getMessage());
            }
            rowEditades.clear();
        });
    }

    private void configurarBotoCancelar() {
        btnCancelar.addActionListener((ActionEvent e) -> {
            controlador.moveToConsultarEquip(frameEquipJugadors);
        });
    }
    
        private void configurarBotoBuscar() {
        btnBuscar.addActionListener((ActionEvent e) -> {
            try {
                String nom = txtfNom.getText().equals("🔎Nom") ? "" : txtfNom.getText().trim();
                String idLegal = txtfNif.getText().equals("🔎DNI") ? "" :txtfNif.getText().trim();
                String dataNaixStr = txtfDataNaix.getText().equals("🔎Naix(dd/MM/yyyy)") ?"":txtfDataNaix.getText().trim();
                Date dataNaix = null;
                if (!dataNaixStr.isEmpty()) {
                    try {
                        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                        dataNaix = sdf1.parse(dataNaixStr);
                    }catch (ParseException ex) {
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
            }catch (GestorSportManagerException ex) {
                controlador.missatgeError(ex.getMessage());
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
    
    private void inicialitzarTaula() {
        String[] columnNames = {"Nom", "Cognom", "NIF", "Edat", "Categoria","Sexe", "Afegir/Treure", "Es Titular"};

        Object[][] data = {};

        
        tableModel = new DefaultTableModel(data, columnNames) {
        
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 6 || columnIndex == 7) {
                    return Boolean.class;
                }
                return String.class;
            }
            
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 6) {
                    return true;
                } else if (column == 7) {
                    Object valueInColumn6 = getValueAt(row, 6);
                    return valueInColumn6 instanceof Boolean && (Boolean) valueInColumn6;
                }
                return false;
            }
        
        };

        table = new JTable(tableModel);
        
        tableModel.addTableModelListener(event -> {
            int column = event.getColumn();
            int row = event.getFirstRow();

            if (column == 6) {
                Object valueInColumn6 = tableModel.getValueAt(row, column);
                
                if (!rowEditades.contains(row)){
                    rowEditades.add(row);
                }

                if (valueInColumn6 instanceof Boolean) {
                    boolean isChecked = (Boolean) valueInColumn6;

                    if (!isChecked) {
                        Object valueInColumn7 = tableModel.getValueAt(row, 7);
                        if (valueInColumn7 instanceof Boolean && (Boolean) valueInColumn7) {
                            tableModel.setValueAt(false, row, 7);
                        }
                    }
                    table.repaint();
                }
            }else if (column==7){
                if (!rowEditades.contains(row)){
                    rowEditades.add(row);
                }
            }
        });


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 150, 1000, 250);
        
        jspTaula.setViewportView(table);

        jspTaula.setBounds(50, 150, 1000, 250);

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

            Categoria cat = bd.loadCategoriaId(equip.getIdCategoria());

            HashMap<Long,Boolean> jugadorsEnEquip = bd.loadJugadorsTitularIdEquip(equip.getId());
            
            for (Jugador jugador : jugadors) {
                String categoriaJugador = calcularCategoria(jugador.calcularEdatIniciAnyActual(jugador.getData_naix())).toString();
                EnumTipus sexeJugador = jugador.getSexe().equals(EnumSexe.H)?EnumTipus.H:EnumTipus.D;
                
                
                if (jugador.calcularEdatIniciAnyActual(jugador.getData_naix()) > cat.getEdat_max()) {
                    continue; 
                }

                if (equip.getTipus().equals(EnumTipus.M)) {
                    if (jugador.calcularEdatIniciAnyActual(jugador.getData_naix()) > cat.getEdat_max()) {
                        continue;
                    }
                } else {
                    if (!sexeJugador.equals(equip.getTipus())) {
                        continue;
                    }
                }
                
                if (!selectedCategory.isEmpty() && !categoriaJugador.equals(selectedCategory)) {
                    continue;
                }

                
                boolean estaEnEquip=false;
                boolean esTitular = false;
                if (jugadorsEnEquip != null){
                    if (jugadorsEnEquip.containsKey(jugador.getId())) {
                        estaEnEquip = true;

                        if (Boolean.TRUE.equals(jugadorsEnEquip.get(jugador.getId()))) {
                            esTitular = true;
                        }
                    }
                }
           
                Object[] row = {
                    jugador.getNom(),
                    jugador.getCognom(),
                    jugador.getId_Legal(),
                    jugador.calcularEdatIniciAnyActual(jugador.getData_naix()),
                    categoriaJugador,
                    jugador.getSexe().toString(),
                    estaEnEquip,
                    esTitular
                };
                tableModel.addRow(row);
            }
        } catch (GestorSportManagerException e) {
            controlador.missatgeError(e.getMessage());
        }
        
        panel.revalidate();
        panel.repaint();
    }

}
