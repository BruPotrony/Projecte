/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package potrony.bru.grafics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import potrony.bru.Interface.SportManagerInterfaceCP;
import potrony.bru.controladors.SwingControladorUsuari;

/**
 *
 * @author Vago
 */
public class SwingFrameMenu {

    private static final int AMPLADA = 600;
    private static final int ALTURA = 400;
    
    private static JFrame frameMenu;
    
    private static SwingControladorUsuari controlador;
    private static SportManagerInterfaceCP bd;

    private JButton btnTemporada;
    private JButton btnJugadors;
    private JButton btnEquips;
    private JButton btnExportar;
    
    private static String urlOracle, pswdOracle,userOracle;
    
    //Aquesta variable es per a que al fer el dispose del frame
    //No crei confusions, explicat mes endevant
    private boolean isProcessingMenu = false;

    public SwingFrameMenu(SwingControladorUsuari controlador, SportManagerInterfaceCP bd,
                        String urlOracle, String pswdOracle, String userOracle) {
        frameMenu = new JFrame();
        frameMenu.setSize(AMPLADA, ALTURA);
        frameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMenu.setLocationRelativeTo(null);
        frameMenu.setTitle("Menu");
        frameMenu.setVisible(true);
        frameMenu.setResizable(false);
        
        this.controlador = controlador;
        this.bd=bd;
        this.urlOracle=urlOracle;
        this.userOracle=userOracle;
        this.pswdOracle = pswdOracle;
        
        
        JPanel panel = new JPanel();
        panel.setLayout(null); 
        panel.setBounds(0, 0, AMPLADA, ALTURA); 

        btnEquips = new JButton();
        btnEquips.setText("Gestió Equips");
        btnEquips.setBounds(190,50,200,40);
        panel.add(btnEquips);
        configurarBotoEquip();
        
        btnJugadors = new JButton();
        btnJugadors.setText("Gestió Jugadors");
        btnJugadors.setBounds(190,120,200,40);
        panel.add(btnJugadors);
        configurarBotoJugador();
        
        btnTemporada = new JButton();
        btnTemporada.setText("Gestió Temporada");
        btnTemporada.setBounds(190,190,200,40);
        panel.add(btnTemporada);
        configurarBotoTemporada();
        
        btnExportar = new JButton();
        btnExportar.setText("Exportar Dades");
        btnExportar.setBounds(190,260,200,40);
        panel.add(btnExportar);
        configurarBotoExportar();
        
        
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
        btnEquips.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.moveToCrearEquip(frameMenu);
            }
        });
    }

    private void configurarBotoExportar() {
        btnExportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String[] tables = {"CATEGORIA", "EQUIP", "JUGADOR","MEMBRE","TEMPORADA","USUARI"};

                for (String tableName : tables) {
                    exportTableToCSV(tableName);
                }
                controlador.missatgeConfirmacio("dades exportades a la ruta:\nP1-T7-Vista-PotronyLopezBru\\exportacions");
            }
        });
    }
    
    
    public static void exportTableToCSV(String tableName) {
        String csvFile = "exportacions/" + tableName + ".csv";

        try (Connection conn = DriverManager.getConnection(urlOracle, userOracle, pswdOracle)) {
            String sql = "SELECT * FROM " + tableName;
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            FileWriter csvWriter = new FileWriter(csvFile);

            for (int i = 1; i <= columnCount; i++) {
                csvWriter.append(metaData.getColumnName(i));
                if (i < columnCount) {
                    csvWriter.append(",");
                }
            }
            csvWriter.append("\n");

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    csvWriter.append(resultSet.getString(i));
                    if (i < columnCount) {
                        csvWriter.append(",");
                    }
                }
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();

        } catch (SQLException | IOException e) {
            controlador.missatgeError(e.getMessage());
        }
    }
    
}
