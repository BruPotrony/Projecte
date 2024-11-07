/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru;

import potrony.bru.Interface.SportManagerInferfaceCP;
import potrony.bru.Interface.GestorSportManagerException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import potrony.bru.SportManager.Categoria;
import potrony.bru.SportManager.EnumTitular;
import potrony.bru.SportManager.Equip;
import potrony.bru.SportManager.Jugador;
import potrony.bru.SportManager.Temporada;
import potrony.bru.SportManager.Usuari;

/**
 *
 * @author Vago
 */
public class SportManagerOracle implements SportManagerInferfaceCP {

    private Connection conn;
    
    private PreparedStatement psSaveUsuaris;
    private PreparedStatement psLoadUsuari;
    private PreparedStatement psLoadUsuaris;
    private PreparedStatement psUpdateUsuari;
    private PreparedStatement psDeleteUsuari;
    private PreparedStatement psLoginCorrecte;
    
    public SportManagerOracle() throws GestorSportManagerException {
        this("connectionBD.properties");
    }
    
    
    public SportManagerOracle(String nomProperties) throws GestorSportManagerException {
        
        String url, user, password;
        try {
            Properties p = new Properties();
            p.load(new FileInputStream(nomProperties));
            
            url = p.getProperty("url");
            user = p.getProperty("user");
            password = p.getProperty("pwd");
            
            if (url==null||url.length()==0|| user==null||user.length()==0|| password == null||password.length()==0){
                throw new GestorSportManagerException("Hi ha una propietat null o amb longitud 0");
            }
            
            conn = DriverManager.getConnection(url,user,password);
            conn.setAutoCommit(false);
            
        } catch (IOException ex) {
            throw new GestorSportManagerException("Problemes en recuperar l'arxiu de propietats " + nomProperties, ex);
        } catch (SQLException ex) {
            throw new GestorSportManagerException("No es pot establir la connexió", ex);
        }
    }

    
    
    
    
    
    
    
    //***********************************************************USUARI*************************************************************
    
    
    
    
    
    
    
    
    
    @Override
    public boolean saveUsuari(Usuari usuari) throws GestorSportManagerException {
        if (psSaveUsuaris==null){
            prepareSaveUsuariStatement();
        }
        
        try {
            psSaveUsuaris.setString(1, usuari.getLogin());
            psSaveUsuaris.setString(2, usuari.getNom());
            psSaveUsuaris.setString(3, usuari.getPassword());
            
            return psSaveUsuaris.executeUpdate() > 0; 
        } catch (SQLException ex) {
            throw new GestorSportManagerException("error en guardar usuari amb login "+usuari.getLogin(), ex);
        }
    }

    @Override
    public boolean saveUsuaris(List<Usuari> usuaris) throws GestorSportManagerException {

        for (Usuari usuari : usuaris) {
            saveUsuari(usuari);
        }
        return true;
    }
    
    
    //Prepara PreparedStatement per el saveUsuari o saveUsuaris
    private void prepareSaveUsuariStatement() throws GestorSportManagerException {
        try {
            psSaveUsuaris = conn.prepareStatement("INSERT INTO usuari (login, nom, password) VALUES (?, ?, ?)");
        } catch (SQLException ex) {
            throw new GestorSportManagerException("Error en preparar la sentencia psSaveUsuaris", ex);
        }
    }

    @Override
    public Usuari loadUsuariLogin(String login) throws GestorSportManagerException {
        Usuari usuari=null;
                
        if (psLoadUsuari==null){
            try {
            psLoadUsuari = conn.prepareStatement("select login, nom, password from usuari where login = ?");
            } catch (SQLException ex) {
                throw new GestorSportManagerException("Error en preparar la sentencia psLoadUsuaris", ex);
            }
        }
        try {
            psLoadUsuari.setString(1, login);
            ResultSet rs = psLoadUsuari.executeQuery();
            if (rs.next()) {
                usuari = new Usuari(rs.getString("login"), rs.getString("password"), rs.getString("nom"),true);
            } else {
                throw new GestorSportManagerException("No s'ha trobat usuari amb login: "+login);
            }
            
        } catch (SQLException ex) {
            throw new GestorSportManagerException("Error en recuperar usuari", ex);
        }
        
        return usuari;
    }

    @Override
    public List<Usuari> loadUsuaris() throws GestorSportManagerException {
        List<Usuari> usuaris = new ArrayList<>();
        
        if (psLoadUsuaris==null){
            try {
                psLoadUsuaris = conn.prepareStatement("select login, nom, password from usuari");
            } catch (SQLException ex) {
                throw new GestorSportManagerException("Error en preparar la sentencia psLoadUsuaris", ex);
            }
        }
        
        ResultSet rs=null;
        try {
            rs = psLoadUsuaris.executeQuery();
            
            while (rs.next()) {
                try{
                    Usuari usuari = new Usuari(rs.getString("login"), rs.getString("password"), rs.getString("nom"),true);
                    usuaris.add(usuari);
                }catch (SQLException ex) {
                    throw new GestorSportManagerException("Error en recuperar usuari amb login "+ rs.getString("login"));
                }
            }
        } catch (SQLException ex) {
            throw new GestorSportManagerException("Error en recuperar usuaris");
        }

        return usuaris;
    }
    

    @Override
    public void modificarUsuari(String login, Usuari usuari) throws GestorSportManagerException {
        
            if (psUpdateUsuari==null){
                try {
                    psUpdateUsuari = conn.prepareStatement("UPDATE usuari SET nom = ?, password = ?, login=? WHERE login = ?");
                } catch (SQLException ex) {
                    throw new GestorSportManagerException("Error en preparar la sentencia psUpdateUsuari", ex);
                }
            }
            
            try {
                psUpdateUsuari.setString(1, usuari.getNom());
                psUpdateUsuari.setString(2, usuari.getPassword());
                psUpdateUsuari.setString(3, usuari.getLogin());
                psUpdateUsuari.setString(4, login);

                int rowUpdated = psUpdateUsuari.executeUpdate();
                
                if (rowUpdated==0){
                    throw new GestorSportManagerException("No s'ha trobat usuari amb login "+login);
                }
            } catch (SQLException ex) {
                throw new GestorSportManagerException("Error en assignar valors a la sentencia psUpdateUsuari", ex);
            }
    }

    @Override
    public boolean eliminarUsuari(String login) throws GestorSportManagerException {
        if (psDeleteUsuari==null){
            try {
                psDeleteUsuari = conn.prepareStatement("DELETE FROM usuari WHERE login=?");
            } catch (SQLException ex) {
                throw new GestorSportManagerException("Error en preparar statement psDeleteUsuari", ex);
            }
        }
        
        try {
            psDeleteUsuari.setString(1, login);
        } catch (SQLException ex) {
            throw new GestorSportManagerException("Error en assignar valor a la sentencia psDeleteUsuari "+login, ex);
        }
        
        int rowsDeleted;
        try {
            rowsDeleted = psDeleteUsuari.executeUpdate();
        } catch (SQLException ex) {
            throw new GestorSportManagerException("Error en eliminar usuari amb login: " + login, ex);
        }
        
        if (rowsDeleted == 0) {
            throw new GestorSportManagerException("No s'ha trobat usuari amb login: " + login);
        }
        
        return rowsDeleted != 0;
    }

    @Override
    public boolean estaRegistrat(String login, String contrassenya) throws GestorSportManagerException {
        if (psLoginCorrecte==null){
            try {
                psLoginCorrecte = conn.prepareStatement("SELECT login, password FROM usuari WHERE login=?");
            } catch (SQLException ex) {
                throw new GestorSportManagerException("Error en preparar statememtnt psLoginCorrecte", ex);
            }
        }
        
        try {
            psLoginCorrecte.setString(1, login);
            
            ResultSet rs = psLoginCorrecte.executeQuery();
            
            if (!rs.next()) {
                throw new GestorSportManagerException("No hi ha cap usuari registrat amb el login: " + login);
            } else {
                
                return contrassenya.equals(rs.getString("password"));

            }
            
        } catch (SQLException ex) {
            throw new GestorSportManagerException("Error en executar la query", ex);
        }
    }

    @Override
    public boolean loginRepetit(String login) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String encriptarContrassenya(String contrassenya) throws GestorSportManagerException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            // Convertir contrassenya a array de bytes i calular el hash
            byte[] hashBytes = md.digest(contrassenya.getBytes());

            // Convertir hash a hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new GestorSportManagerException("Error en encriptar la contrassenya", e);
        }
    }

    
    
    
    
    
    
    
    
    //***********************************************************TEMPORADA*************************************************************
    
    
    
    
    
    
    
    
    

    @Override
    public boolean saveTemporada(Temporada temporada) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Temporada> loadTemporades() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminarTemporada(Temporada temporada) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean temporadaRepetida(Temporada temporada) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
    
    
    
    
    
    //***********************************************************CATEGORIA*************************************************************
    
    
    
    
    
    
    
    
    
    @Override
    public Categoria loadCategoria(String nom) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Categoria> loadCategories() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
    
    
    
    
    
    //***********************************************************JUGADORS*************************************************************
    
    
    
    
    
    
    
    
    
    @Override
    public boolean saveJugador(Jugador jugador) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean saveJugadors(List<Jugador> jugadors) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Jugador> loadJugadors() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Jugador loadJugadorIdLegal(String idLegal) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Jugador> loadJugadorNomCognom(String nom, String cognom) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean modificarJugador(String idLegal, Jugador jugador) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean jugadorRepetit(String idLegal) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminarJugador(Jugador jugador) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
    
    
    
    
    
    //***********************************************************EQUIP*************************************************************
    
    
    
    
    
    
    
    
    
    @Override
    public Equip loadEquipNom(String nom, int temporada) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Equip> loadEquips() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean saveEquip(Equip equip) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean saveEquips(List<Equip> equips) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean equipRepetit(String nom, int any) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean modificarEquip(String nom, int any, Equip equip) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminarEquip(Equip equip) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
    
    
    
    
    
    //***********************************************************MEMBRE*************************************************************
    
    
    
    
    
    
    
    
    
    @Override
    public boolean afegirJugadorEquip(EnumTitular titularitat, Equip equip, Jugador jugador) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean esTitular(Jugador jugador) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminarJugadorEquip(Jugador jugador, Equip equip) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
    
    
    
    
    
    //***********************************************************OTHERS*************************************************************
    
    
    
    
    
    
    
    
    
    @Override
    public void saveAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean tancarConnexio() throws GestorSportManagerException {
        if (conn != null) {
            
            desferCanvis();
            
            try {
                conn.close();
            } catch (SQLException ex) {
                throw new GestorSportManagerException("Error en tancar la connexió.\n", ex);
            }
        }
        return true;
    }

    @Override
    public void confirmarCanvis() throws GestorSportManagerException {
        try{
            conn.commit();
        } catch (SQLException ex) {
            throw new GestorSportManagerException("Error en fer commit", ex);
        }
    }

    @Override
    public void desferCanvis() throws GestorSportManagerException {
        try{
            conn.rollback();
        } catch (SQLException ex) {
            throw new GestorSportManagerException("Error en fer rollback", ex);
        }
    }
    
}
