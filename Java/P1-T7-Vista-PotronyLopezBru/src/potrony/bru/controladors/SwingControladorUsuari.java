/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru.controladors;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import potrony.bru.grafics.SwingFrameConsultarJugador;
import potrony.bru.grafics.SwingFrameCrearEquip;
import potrony.bru.grafics.SwingFrameCrearJugador;
import potrony.bru.grafics.SwingFrameCrearTemporada;
import potrony.bru.grafics.SwingFrameEditarJugador;
import potrony.bru.grafics.SwingFrameEliminarTemporada;
import potrony.bru.grafics.SwingFrameForgetPassword;
import potrony.bru.grafics.SwingFrameMenu;
import potrony.bru.grafics.SwingFrameUsuari;
import potrony.bru.Interface.SportManagerInterfaceCP;
import potrony.bru.grafics.SwingFrameConsultarEquip;

/**
 *
 * @author Vago
 */
public class SwingControladorUsuari{
        
    private SwingFrameUsuari frameUsuari;
    private SwingFrameForgetPassword frameForgetPassword;
    private SwingFrameMenu frameMenu;
    private SwingFrameCrearTemporada frameCrearTemporada;
    private SwingFrameEliminarTemporada frameEliminarTemporada;
    private SwingFrameCrearJugador frameCrearJugador;
    private SwingFrameEditarJugador frameEditarJugador;
    private SwingFrameConsultarJugador frameConsultarJugador;
    private SwingFrameCrearEquip frameCrearEquip;
    private SwingFrameConsultarEquip frameConsultarEquip;

    
    
    private SportManagerInterfaceCP manager;
    
    public SwingControladorUsuari(SportManagerInterfaceCP manager, JFrame frameCarga) {

        
        moveToLogin(frameCarga);
        
        this.manager = manager;
        
        frameUsuari.getFrame().setVisible(true);
    }
    
    
    
    public void moveToMenu(JFrame frame){
        frame.dispose();
        frameMenu = new SwingFrameMenu(this,manager);
    }
    
    public void moveToForgetPwd(JFrame frame){
        frame.dispose();
        frameForgetPassword = new SwingFrameForgetPassword(this,manager);
    }
    
    public void moveToLogin(JFrame frame){
        frame.dispose();
        frameUsuari = new SwingFrameUsuari(this, manager);
    }

    public void moveToCrearTemporada(JFrame frame){
        frame.dispose();
        frameCrearTemporada = new SwingFrameCrearTemporada(this,manager);
    }
    
    public void moveToEliminarTemporada(JFrame frame){
        frame.dispose();
        frameEliminarTemporada = new SwingFrameEliminarTemporada(this,manager);
    }
    
    public void moveToCrearJugador(JFrame frame){
        frame.dispose();
        frameCrearJugador = new SwingFrameCrearJugador(this, manager);
    }
    
    public void moveToEditarJugador(JFrame frame){
        frame.dispose();
        frameEditarJugador = new SwingFrameEditarJugador(this, manager);
    }
    
    public void moveToConsultarJugador(JFrame frame){
        frame.dispose();
        frameConsultarJugador = new SwingFrameConsultarJugador(this,manager);
    }

    public void moveToCrearEquip(JFrame frame){
        frame.dispose();
        frameCrearEquip = new SwingFrameCrearEquip(this, manager);
    }
    
    public void moveToConsultarEquip(JFrame frame){
        frame.dispose();
        frameConsultarEquip = new SwingFrameConsultarEquip(this,manager);
    }
    
    
    
    public void configurarJMenuBar(JFrame frame, JMenu menu, JMenu tancarSessio){
        menu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                frame.setVisible(false);
                frameMenu.getFrame().setVisible(true);
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
        
        
        tancarSessio.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                frame.setVisible(false);
                frameUsuari.getFrame().setVisible(true);
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
    }
    
    
    
    
    public void missatgeError(String missatge) {
        JFrame frameError = new JFrame("Error");
        frameError.setSize(400, 220);
        frameError.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameError.setLocationRelativeTo(null);
        frameError.setResizable(false);

        JPanel panelError = new JPanel();
        panelError.setLayout(null);

        JLabel labelError = new JLabel("<html><center>" + missatge + "</center></html>");
        labelError.setBounds(50, 30, 300, 50);
        labelError.setHorizontalAlignment(SwingConstants.CENTER);
        labelError.setFont(new Font("Arial", Font.PLAIN, 16));
        panelError.add(labelError);

        JButton btnAceptar = new JButton("Acceptar");
        btnAceptar.setBounds(150, 100, 100, 30);
        panelError.add(btnAceptar);

        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameError.dispose();
            }
        });

        frameError.add(panelError);
        frameError.setVisible(true);
    }
    
    
    public void missatgeConfirmacio(String missatge) {
        JFrame frameConfirmacio = new JFrame("Confirmació");
        frameConfirmacio.setSize(400, 220);
        frameConfirmacio.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameConfirmacio.setLocationRelativeTo(null);
        frameConfirmacio.setResizable(false);

        JPanel panelError = new JPanel();
        panelError.setLayout(null);

        JLabel labelError = new JLabel("<html><center>" + missatge + "</center></html>");
        labelError.setBounds(50, 30, 300, 50);
        labelError.setHorizontalAlignment(SwingConstants.CENTER);
        labelError.setFont(new Font("Arial", Font.PLAIN, 16));
        panelError.add(labelError);

        JButton btnAceptar = new JButton("Acceptar");
        btnAceptar.setBounds(150, 100, 100, 30);
        panelError.add(btnAceptar);

        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameConfirmacio.dispose();
            }
        });

        frameConfirmacio.add(panelError);
        frameConfirmacio.setVisible(true);
    }
    
    
    
        public JLabel afegirImatgeUrl(String url, int x, int y, int width, int height) {
            try {
                ImageIcon imageIcon;

                // Verificar si es una URL remota o una ruta local
                if (url.isEmpty()) {
                    url = "https://cdn.pixabay.com/photo/2017/11/10/05/48/user-2935527_1280.png";
                }

                if (url.startsWith("http://") || url.startsWith("https://")) {
                    imageIcon = new ImageIcon(new URL(url));
                } else {
                    imageIcon = new ImageIcon(url);
                }

                Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                JLabel labelImatge = new JLabel(new ImageIcon(image));
                labelImatge.setBounds(x, y, width, height);
                return labelImatge;

            } catch (Exception e) {
                missatgeError("Error en cargar l'imatge");
                try {
                    ImageIcon fallbackIcon = new ImageIcon(new URL("https://cdn.pixabay.com/photo/2017/11/10/05/48/user-2935527_1280.png"));
                    Image fallbackImage = fallbackIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                    JLabel fallbackLabel = new JLabel(new ImageIcon(fallbackImage));
                    fallbackLabel.setBounds(x, y, width, height);
                    return fallbackLabel;
                } catch (MalformedURLException ex) {
                    missatgeError("ERROR\nConsultar amb l'administrador");
                    return null;
                }
            }
        }
        
    public boolean esURLValida(String url) {
        if (url == null || url.isEmpty()) {
        return false;
        }

        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            File file = new File(url);
            return file.exists();
        }
    }
    
    
    
    private static void infoError(Throwable aux){
        do{
            if (aux.getMessage()!=null){
                System.out.println("\t"+aux.getMessage());
            }
            aux=aux.getCause();
        }while(aux!=null);
    }

}