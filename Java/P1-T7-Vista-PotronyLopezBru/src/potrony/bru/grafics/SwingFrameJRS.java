/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru.grafics;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import potrony.bru.Interface.GestorSportManagerException;
import potrony.bru.Interface.SportManagerInterfaceCP;
import potrony.bru.SportManager.Categoria;
import potrony.bru.SportManager.Equip;
import potrony.bru.SportManager.Temporada;
import potrony.bru.controladors.SwingControladorUsuari;

/**
 *
 * @author isard
 */
public class SwingFrameJRS {
    private static final int AMPLADA = 600;
    private static final int ALTURA = 400;
    
    private static JFrame frameJRS;
    
    private JComboBox<String> cbxCategoria;
    private JComboBox<String> cbxTemporada;

    private JButton btnCrear;
    private JButton btnCancelar;
    
    SwingControladorUsuari controlador;
    SportManagerInterfaceCP bd;

    public String urlJRS;
    public String pswdJRS;
    public String userJRS;    
    
    Equip equipActual;

    public SwingFrameJRS(SwingControladorUsuari controlador, SportManagerInterfaceCP bd, long idEquip,
                         String userJRS, String pswdJRS, String urlJRS) {
        frameJRS = new JFrame();
        frameJRS.setSize(AMPLADA, ALTURA);
        frameJRS.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameJRS.setLocationRelativeTo(null);
        frameJRS.setTitle("Crear Informe");
        frameJRS.setVisible(true);
        frameJRS.setResizable(false);
 
        
        this.urlJRS=urlJRS;
        this.pswdJRS = pswdJRS;
        this.userJRS= userJRS;
        
        this.controlador = controlador;
        this.bd=bd;
        
        if (idEquip==-1){
            equipActual=null;
        }else{
            try {
                equipActual=bd.loadEquipId(idEquip);
            } catch (GestorSportManagerException ex) {
                controlador.missatgeError(ex.getMessage());
                controlador.moveToConsultarEquip(frameJRS);
            }
        }
        
        JPanel panel = new JPanel();
        panel.setLayout(null); 
        panel.setBounds(0, 0, AMPLADA, ALTURA); 

        JLabel titol = new JLabel();
        String text="Tots";
        if (equipActual!=null){
            text = equipActual.getNom();
        }
        titol.setText("Equip: "+text);
        titol.setFont(new Font("Arial", Font.BOLD, 20));
        titol.setBounds(50, 30, 400, 30);
        panel.add(titol);
        
        cbxCategoria=new JComboBox<>();
        inicialitzarComboBoxCategoria();
        cbxCategoria.setBounds(50,100,180,40);
        panel.add(cbxCategoria);
        
        cbxTemporada=new JComboBox<>();
        inicialitzarComboBoxTemporada();
        cbxTemporada.setBounds(320,100,180,40);
        panel.add(cbxTemporada);
        
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
        
        if (idEquip!=-1){
            JLabel missatgeInfo = new JLabel(
            "INFO: Si no es selecciona equip es mostren tots"
            );
            missatgeInfo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            missatgeInfo.setBounds(50, 60, 400, 30);
            panel.add(missatgeInfo);  
        }
        
        
        
        frameJRS.add(panel);
    }
    
    public JFrame getFrame() {
        return frameJRS;
    }

    private void inicialitzarComboBoxTemporada() {
        try {
            List<Temporada> temporades = bd.loadTemporades();
            
            if (temporades!=null){
                cbxTemporada.addItem("Totes");
                int anyActual = LocalDate.now().getYear();
                for (Temporada temporada : temporades) {
                    int anyTemp = temporada.getAny();
                    cbxTemporada.addItem(String.valueOf(anyTemp));
                    if (anyActual == anyTemp){
                        cbxTemporada.setSelectedItem(String.valueOf(anyTemp));
                    }
                }
            }
        } catch (Exception e) {
            controlador.missatgeError(e.getMessage());
            controlador.moveToMenu(this.getFrame());
        }
    }

    private void inicialitzarComboBoxCategoria() {
        try {
            List<Categoria> categories = bd.loadCategories();
            cbxCategoria.addItem("Totes");
            if (categories!=null){
                for (Categoria category : categories) {
                    cbxCategoria.addItem(category.getNom());
                }
            }
            
        } catch (Exception e) {
            controlador.missatgeError(e.getMessage());
            controlador.moveToConsultarEquip(frameJRS);
        }
        
    }
    
    
    public void configurarBotoCancelar(){
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.moveToConsultarEquip(frameJRS);
            }
        });
    }
    
    public void configurarBotoCrear(){
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean isCategoriaSelected = cbxCategoria.getSelectedIndex()!=0;
                    long idCategoria=-1;
                    if (isCategoriaSelected){
                        try {
                            idCategoria=bd.getIdCategoria(cbxCategoria.getSelectedItem().toString().trim());
                        } catch (GestorSportManagerException ex) {
                            controlador.missatgeError(ex.getMessage());
                        }
                    }
                    
                    boolean isTemporadaSelected = cbxTemporada.getSelectedIndex()!=0;
                    String anyTemporada="";
                    if (isTemporadaSelected){
                        anyTemporada = cbxTemporada.getSelectedItem().toString().trim();
                    }
                    
                    boolean isEquipSelected = equipActual!=null;
                    long idEquip = -1;
                    if (isEquipSelected){
                        idEquip=equipActual.getId();
                    }
                    
                    int BUFFER_SIZE = 4096;
                    StringBuilder urlBuilder = new StringBuilder(urlJRS + "Jugadors.pdf");
                    
                    if (isCategoriaSelected) {
                        urlBuilder.append("?Categoria=").append(idCategoria);
                    }
                    if (isTemporadaSelected) {
                        urlBuilder.append(idCategoria != -1 ? "&" : "?");
                        urlBuilder.append("Temporada=").append(anyTemporada);
                    }
                    if (isEquipSelected) {
                        urlBuilder.append((idCategoria != -1 || !anyTemporada.isEmpty()) ? "&" : "?");
                        urlBuilder.append("Equip=").append(idEquip);
                    }
                    
                    String url = urlBuilder.toString();
                    
                    
                    
                    URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");
                    String autenticacio = Base64.getEncoder().encodeToString((userJRS + ":" + pswdJRS).getBytes());
                    con.setRequestProperty("Authorization", "Basic " + autenticacio);
                    int responseCode = con.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        
                        String fileName;
                        
                        String categoriaNom = (idCategoria != -1) ? cbxCategoria.getSelectedItem().toString().trim() : "Tots";
                        String temporadaNom = (!anyTemporada.isEmpty()) ? anyTemporada : "Tots";
                        String equipNom = (equipActual != null) ? equipActual.getNom() : "Tots";
                        
                        fileName = "InformeJugadors-Categoria_" + categoriaNom + "-Temporada_" + temporadaNom + "-Equip_" + equipNom + ".pdf";
                                                
                        // Obrim InputStream des de HTTP connection
                        InputStream inputStream = con.getInputStream();
                        // Obrim OutputStream per enregistrar el fitxer
                        FileOutputStream outputStream = new FileOutputStream(fileName);
                        
                        // Llegim de inputStrem i escrivima outputStream, byte a byte:
                        int bytesRead = -1;
                        byte[] buffer = new byte[BUFFER_SIZE];
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                        outputStream.close();
                        inputStream.close();
                        
                        controlador.missatgeConfirmacio("Fitxer descarregat\nIntentant obrir-lo...");
                        // Intentem obrir-lo en alguna aplicació del SO
                        if (Desktop.isDesktopSupported()) {
                            try {
                                Desktop.getDesktop().open(new File(fileName));
                            } catch (IOException ex) {
                                controlador.missatgeError("No hi ha aplicacions disponibles per obrir el fitxer");
                            }
                        }
                    } else {
                        String txtInfo;
                        txtInfo = "Mètode 'GET' : " + url;
                        txtInfo= txtInfo + "\nCodi resposta: " + responseCode;
                        txtInfo= txtInfo + "\nCap fitxer a descarregar";
                        //System.out.println(txtInfo);
                    }
                    con.disconnect();
                } catch (Exception ex) {
                    controlador.missatgeError("Error en crear l'informe");
                }
            }
        });
    }
    
}
