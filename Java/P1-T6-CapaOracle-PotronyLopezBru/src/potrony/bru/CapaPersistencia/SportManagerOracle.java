/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru.CapaPersistencia;

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
import java.sql.Date;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import potrony.bru.SportManager.Categoria;
import potrony.bru.SportManager.EnumSexe;
import potrony.bru.SportManager.EnumTipus;
import potrony.bru.SportManager.EnumTitular;
import potrony.bru.SportManager.Equip;
import potrony.bru.SportManager.Jugador;
import potrony.bru.SportManager.Membre;
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
    private PreparedStatement psLoginRepetit;
    
    private PreparedStatement psSaveTemporada;
    private PreparedStatement psLoadTemporades;
    private PreparedStatement psDeleteTemporada;
    private PreparedStatement psTemporadaRepetida;
    
    private PreparedStatement psLoadCategoria;
    private PreparedStatement psLoadCategories;
    
    private PreparedStatement psSaveJugador;
    private PreparedStatement psLoadJugadors;
    private PreparedStatement psLoadJugadorId;
    private PreparedStatement psLoadJugadorNomCognom;
    private PreparedStatement psIdLegalRepetit;
    private PreparedStatement psUpdateJugador;
    private PreparedStatement psDeleteJugador;
    private PreparedStatement psGetGeneratedJugadorId;
    
    private PreparedStatement psLoadEquipNom;
    private PreparedStatement psLoadEquipId;
    private PreparedStatement psLoadEquips;
    private PreparedStatement psSaveEquip;
    private PreparedStatement psDeleteEquip;
    private PreparedStatement psGetGeneratedEquipId;
    private PreparedStatement psUpdateEquip;
    
    private PreparedStatement psAddJugadorEquip;
    private PreparedStatement psEsTitular;
    private PreparedStatement psDeleteJugadorEquip;
    private PreparedStatement psLoadJugadorsEquip;
    private PreparedStatement psLoadEquipsJugador;
    
    
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
        } catch (Exception ex) {
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
        } catch (Exception ex) {
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
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en preparar la sentencia psSaveUsuaris", ex);
        }
    }

    @Override
    public Usuari loadUsuariLogin(String login) throws GestorSportManagerException {
        Usuari usuari=null;
                
        if (psLoadUsuari==null){
            try {
                psLoadUsuari = conn.prepareStatement("select login, nom, password from usuari where login = ?");
            } catch (Exception ex) {
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
            
        } catch (Exception ex) {
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
            } catch (Exception ex) {
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
                }catch (Exception ex) {
                    throw new GestorSportManagerException("Error en recuperar usuari amb login "+ rs.getString("login"));
                }
            }
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en recuperar usuaris");
        }

        return usuaris;
    }
    

    @Override
    public void modificarUsuari(String login, Usuari usuari) throws GestorSportManagerException {
        
            if (psUpdateUsuari==null){
                try {
                    psUpdateUsuari = conn.prepareStatement("UPDATE usuari SET nom = ?, password = ?, login=? WHERE login = ?");
                } catch (Exception ex) {
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
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en assignar valors a la sentencia psUpdateUsuari", ex);
            }
    }

    @Override
    public boolean eliminarUsuari(String login) throws GestorSportManagerException {
        if (psDeleteUsuari==null){
            try {
                psDeleteUsuari = conn.prepareStatement("DELETE FROM usuari WHERE login=?");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en preparar statement psDeleteUsuari", ex);
            }
        }
        
        try {
            psDeleteUsuari.setString(1, login);
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en assignar valor a la sentencia psDeleteUsuari "+login, ex);
        }
        
        int rowsDeleted;
        try {
            rowsDeleted = psDeleteUsuari.executeUpdate();
        } catch (Exception ex) {
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
            } catch (Exception ex) {
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
            
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en executar la query", ex);
        }
    }

    @Override
    public boolean loginRepetit(String login) throws GestorSportManagerException {
        if (psLoginRepetit==null){
            try {
                psLoginRepetit = conn.prepareStatement("SELECT login FROM usuari WHERE login=?");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en la consulta SQL psLoginRepetit", ex);
            }
        }
        
        try {
            psLoginRepetit.setString(1, login);
            ResultSet rs = psLoginRepetit.executeQuery();

            return rs.next();
            
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en executar la query psLoginRepetit", ex);
        }
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
    public boolean saveTemporada(Temporada temporada) throws GestorSportManagerException {
        if (psSaveTemporada==null){
            try {
                psSaveTemporada = conn.prepareStatement("Insert into temporada (anys) VALUES (?)");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en preparar la sentencia psSaveTemporada", ex);
            }
        }
        
        try {
            psSaveTemporada.setInt(1, temporada.getAny());
            
            return psSaveTemporada.executeUpdate() > 0; 
        } catch (Exception ex) {
            throw new GestorSportManagerException("error en guardar any: "+temporada.getAny(), ex);
        }
    }

    @Override
    public List<Temporada> loadTemporades() throws GestorSportManagerException {
        List<Temporada> temporades = new ArrayList<>();
        
        if (psLoadTemporades==null){
            try {
                psLoadTemporades = conn.prepareStatement("Select anys from temporada");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en preparar la sentencia psLoadTemporades", ex);
            }
        }
        
        try {
            ResultSet rs = psLoadTemporades.executeQuery();
            
            while(rs.next()){
                temporades.add(new Temporada(rs.getInt("anys")));
            }
            
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en executar la query per a recuperar temporades", ex);
        }
        
        return temporades;
    }

    @Override
    public boolean eliminarTemporada(int any) throws GestorSportManagerException {
        if (psDeleteTemporada==null){
            try {
                psDeleteTemporada = conn.prepareStatement("DELETE FROM temporada WHERE anys=?");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en preparar statement psDeleteTemporada", ex);
            }
        }
        
        try {
            psDeleteTemporada.setInt(1, any);
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en assignar valor a la sentencia psDeleteUsuari "+any, ex);
        }
        
        int rowsDeleted;
        try {
            rowsDeleted = psDeleteTemporada.executeUpdate();
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en eliminar temporada amb any: " + any, ex);
        }
        
        if (rowsDeleted == 0) {
            throw new GestorSportManagerException("No s'ha trobat temporada amb any: " + any);
        }
        
        return rowsDeleted != 0;
    }

    @Override
    public boolean temporadaRepetida(int any) throws GestorSportManagerException {
        if (psTemporadaRepetida==null){
            try {
                psTemporadaRepetida = conn.prepareStatement("SELECT anys FROM temporada WHERE anys=?");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en preparar statement psTemporadaRepetida", ex);
            }
        }
        
        try {
            psTemporadaRepetida.setInt(1, any);
            ResultSet rs = psTemporadaRepetida.executeQuery();

            return rs.next();
            
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en executar la query psTemporadaRepetida", ex);
        }
    }

    
    
    
    
    
    
    
    //***********************************************************CATEGORIA*************************************************************
    
    
    
    
    
    
    
    
    
    @Override
    public Categoria loadCategoriaId(long id) throws GestorSportManagerException {
        Categoria categoria=null;
                
        if (psLoadCategoria==null){
            try {
                psLoadCategoria = conn.prepareStatement("select id, nom,edat_min,edat_max from categoria where id = ?");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en preparar la sentencia psLoadCategoria", ex);
            }
        }
        try {
            Categoria cat=null;
            psLoadCategoria.setLong(1, id);
            ResultSet rs = psLoadCategoria.executeQuery();
            if (rs.next()) {
                cat = new Categoria(rs.getLong("id"), rs.getString("nom"), rs.getInt("edat_min"),rs.getInt("edat_max"));
            } else {
                throw new GestorSportManagerException("No s'ha trobat categoria amb id: "+cat.getId());
            }
            return cat;
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en recuperar categoria", ex);
        }
        
        
    }

    @Override
    public List<Categoria> loadCategories() throws GestorSportManagerException {
        List<Categoria> categories = new ArrayList<>();
        
        if (psLoadCategories==null){
            try {
                psLoadCategories = conn.prepareStatement("select id, nom,edat_min,edat_max from categoria");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en preparar la sentencia psLoadCategoria", ex);
            }
        }
        
        try {
            ResultSet rs = psLoadCategories.executeQuery();
            
            while (rs.next()){
                try{
                    categories.add(new Categoria(rs.getLong("id"),rs.getString("nom"),rs.getInt("edat_min"),rs.getInt("edat_max")));
                }catch(Exception ex){
                    throw new GestorSportManagerException("Error en inserir categoria a l'array amb nom: "+rs.getString("nom"), ex);
                }
            }
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en executar la query psLoadCategories", ex);
        }
        
        return categories;
    }

    
    
    
    
    
    
    
    //***********************************************************JUGADORS*************************************************************
    
    
    
    
    
    
    
    
    
    @Override
    public boolean saveJugador(Jugador jugador) throws GestorSportManagerException {
        if (psSaveJugador==null){
            try {
                psSaveJugador = conn.prepareStatement("INSERT INTO jugador (nom, cognom,sexe,foto,data_naix,adreca,any_fi_revisio_medica,iban,idlegal)\n" +
                                                      "VALUES (?,?,?,?,?,?,?,?,?)");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en preparar la sentencia psSaveJugador", ex);
            }
        }
        
        try {
            psSaveJugador.setString(1, jugador.getNom());
            psSaveJugador.setString(2, jugador.getCognom());
            psSaveJugador.setString(3, String.valueOf(jugador.getSexe()));
            psSaveJugador.setString(4, jugador.getFoto());
            psSaveJugador.setDate(5, java.sql.Date.valueOf(jugador.getData_naix()));
            psSaveJugador.setString(6, jugador.getAdreca());
            psSaveJugador.setInt(7, jugador.getAny_fi_revisio_medica());
            psSaveJugador.setString(8, jugador.getIban());
            psSaveJugador.setString(9, jugador.getId_Legal());
            
            boolean rowUpdated = psSaveJugador.executeUpdate()>0;
            
            jugador.setId(getGeneratedJugadorId(jugador.getId_Legal()));
            
            return rowUpdated;
            
        } catch (Exception ex) {
            throw new GestorSportManagerException("error en guardar jugador amb idLegal "+jugador.getId_Legal(), ex);
        }
    }

    @Override
    public boolean saveJugadors(List<Jugador> jugadors) throws GestorSportManagerException {
        for (Jugador jugador : jugadors) {
            saveJugador(jugador);
        }
        return true;
    }

    @Override
    public List<Jugador> loadJugadors() throws GestorSportManagerException {
        List<Jugador> jugadors = new ArrayList<>();
        
        if (psLoadJugadors==null){
            try {
                psLoadJugadors = conn.prepareStatement("select id, nom, cognom,sexe,foto,data_naix,adreca,any_fi_revisio_medica,iban,idlegal from jugador");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en preparar la sentencia psLoadJugadors", ex);
            }
        }
        
        ResultSet rs=null;
        try {
            rs = psLoadJugadors.executeQuery();
            
            
            while (rs.next()) {
                try{
                    jugadors.add(recuperarJugador(rs));
                }catch (Exception ex) {
                    throw new GestorSportManagerException("Error en recuperar jugador amb idLegal "+ rs.getString("idlegal"));
                }
            }
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en recuperar jugadors ",ex);
        }

        return jugadors;
    }

    @Override
    public Jugador loadJugadorId(Long id) throws GestorSportManagerException {
        Jugador jugador =null;

        if (psLoadJugadorId == null) {
            try {
                psLoadJugadorId = conn.prepareStatement("select id, nom, cognom,sexe,foto,data_naix,adreca,any_fi_revisio_medica,iban,idlegal from jugador where id = ?");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en preparar la sentencia psLoadJugadors", ex);
            }
        }

        
        
        try {
            psLoadJugadorId.setLong(1, id);
            ResultSet rs = psLoadJugadorId.executeQuery();

            if (rs.next()) {
                jugador = recuperarJugador(rs);
            }else{
                throw new GestorSportManagerException("no hi ha cap jugador amb id "+id);
            }

            return jugador;
            
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en recuperar jugadors ", ex);
        }
    }

    @Override
    public List<Jugador> loadJugadorNomCognom(String nom, String cognom) throws GestorSportManagerException {
        List<Jugador> jugadors=new ArrayList<>();

        if (psLoadJugadorNomCognom == null) {
            try {
                psLoadJugadorNomCognom = conn.prepareStatement("select id, nom, cognom,sexe,foto,data_naix,adreca,any_fi_revisio_medica,iban,idlegal from jugador where UPPER(nom) = ? OR UPPER(cognom)=?");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en preparar la sentencia psLoadJugadors", ex);
            }
        }

        
        
        try {
            psLoadJugadorNomCognom.setString(1, nom.toUpperCase());
            psLoadJugadorNomCognom.setString(2, cognom.toUpperCase());
            ResultSet rs = psLoadJugadorNomCognom.executeQuery();

            while (rs.next()){
                try{
                    jugadors.add(recuperarJugador(rs));
                }catch (Exception ex){
                throw new GestorSportManagerException("no hi ha cap jugador amb nom/cognom: "+nom+"/"+cognom);
                }
            }

            return jugadors;
            
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en recuperar jugadors ", ex);
        }
    }

    
    private Jugador recuperarJugador(ResultSet rs) throws Exception {
        Jugador jug = null;
        Long id = rs.getLong("id");
        String nom = rs.getString("nom");
        String cognom = rs.getString("cognom");
        EnumSexe sexe = rs.getString("sexe").equals("H") ? EnumSexe.H : EnumSexe.D;
        String foto = rs.getString("foto");

        java.sql.Date sqlDate = rs.getDate("data_naix");
        LocalDate dataNaix = sqlDate.toLocalDate();

        String adreca = rs.getString("adreca");
        int anyFiRevisioMedica = rs.getInt("any_fi_revisio_medica");
        String iban = rs.getString("iban");
        String idLegal = rs.getString("idlegal");
        
        return new Jugador (id, nom, cognom, sexe, dataNaix, foto, adreca, iban, idLegal, anyFiRevisioMedica);
    }
    
    @Override
    public boolean modificarJugador(String idLegal, Jugador jugador) throws GestorSportManagerException {
        if (psUpdateJugador == null) {
            try {
                psUpdateJugador = conn.prepareStatement("UPDATE jugador SET nom = ?, cognom = ?,sexe=?,foto=?,data_naix=?,adreca=?,any_fi_revisio_medica=?,iban=?,idLegal=?  WHERE idlegal = ?");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en preparar la sentencia psUpdateJugador", ex);
            }
        }

        try {
            psUpdateJugador.setString(1, jugador.getNom());
            psUpdateJugador.setString(2, jugador.getCognom());
            psUpdateJugador.setString(3,String.valueOf(jugador.getSexe()));
            psUpdateJugador.setString(4, jugador.getFoto());
            psUpdateJugador.setDate(5, java.sql.Date.valueOf(jugador.getData_naix()));
            psUpdateJugador.setString(6, jugador.getAdreca());
            psUpdateJugador.setInt(7, jugador.getAny_fi_revisio_medica());
            psUpdateJugador.setString(8, jugador.getIban());
            psUpdateJugador.setString(9, jugador.getId_Legal());
            psUpdateJugador.setString(10, idLegal);

            int rowUpdated = psUpdateJugador.executeUpdate();
            
            return rowUpdated >0;

        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en assignar valors a la sentencia psUpdateJugador", ex);
        }
        
    }

    @Override
    public boolean jugadorIdLegalRepetit(String idLegal) throws GestorSportManagerException {
        if (psIdLegalRepetit==null){
            try {
                psIdLegalRepetit = conn.prepareStatement("SELECT nom FROM jugador WHERE idlegal=?");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en la consulta SQL psIdLegalRepetit", ex);
            }
        }
        
        try {
            psIdLegalRepetit.setString(1, idLegal);
            ResultSet rs = psIdLegalRepetit.executeQuery();

            return rs.next();
            
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en executar la query psIdLegalRepetit", ex);
        }
    }

    
    
    @Override
    public boolean eliminarJugador(Jugador jugador) throws GestorSportManagerException {
        long id = jugador.getId();
        
        if (psDeleteJugador==null){
            try {
                psDeleteJugador = conn.prepareStatement("DELETE FROM jugador WHERE id=?");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en preparar statement psDeleteJugador", ex);
            }
        }
        
        try {
            psDeleteJugador.setLong(1, id);
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en assignar valor a la sentencia psDeleteJugador "+id, ex);
        }
        
        int rowsDeleted;
        try {
            rowsDeleted = psDeleteJugador.executeUpdate();
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en eliminar jugador amb id: " + id, ex);
        }
        
        if (rowsDeleted == 0) {
            throw new GestorSportManagerException("No s'ha trobat jugador amb id: " + id);
        }
        
        return rowsDeleted != 0;
    }

    
    ///Aquest metode recupera id del jugador amb idlegal que li passem
    private long getGeneratedJugadorId(String idLegal) throws GestorSportManagerException, Exception{
        if (psGetGeneratedJugadorId == null){
            try {
                psGetGeneratedJugadorId = conn.prepareStatement("Select id from jugador where idLegal=?");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en preparar statement psGetGeneratedJugadorId", ex);
            }
        }
        
        psGetGeneratedJugadorId.setString(1, idLegal);
        
        try (ResultSet rs = psGetGeneratedJugadorId.executeQuery()) {
            if (rs.next()) {
                return rs.getLong("id");  
            } else {
                throw new GestorSportManagerException("No s'ha trobat jugador amb idLegal: " + idLegal);
            }
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en executar la consulta per obtenir id al jugador amb idLegal: " + idLegal, ex);
        }
    }
    
    
    
    
    
    
    
    //***********************************************************EQUIP*************************************************************
    
    
    
    
    
    
    
    
    
    @Override
    public Equip loadEquipNom(String nom, int temporada) throws GestorSportManagerException {
        Equip equip = null;

        if (psLoadEquipNom == null) {
            try {
                psLoadEquipNom = conn.prepareStatement("select id, id_categoria, any_temporada,nom,tipus from equip where UPPER(nom) = ? AND any_temporada=?");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en preparar la sentencia psLoadEquipNom", ex);
            }
        }

        
        
        try {
            psLoadEquipNom.setString(1, nom.toUpperCase());
            psLoadEquipNom.setInt(2, temporada);
            ResultSet rs = psLoadEquipNom.executeQuery();

            if (rs.next()){
                long id = rs.getLong("id");
                long idCategoria = rs.getLong("id_categoria");
                String tipusStr = rs.getString("tipus");
                EnumTipus tipus = tipusStr.equals("D") ? EnumTipus.D : tipusStr.equals("H") ? EnumTipus.H : EnumTipus.M;

                equip = new Equip(id, idCategoria, temporada, nom, tipus);
                return equip;
            }else{
                throw new GestorSportManagerException("No hi ha cap equip amb nom: "+nom+ " i temporada: "+temporada);
            }

            
            
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en recuperar equip amb nom: "+nom, ex);
        }
    }
    
    
    @Override
    public Equip loadEquipId(long id)throws GestorSportManagerException {
        Equip equip = null;

        if (psLoadEquipId == null) {
            try {
                psLoadEquipId = conn.prepareStatement("select id, id_categoria, any_temporada,nom,tipus from equip where id=?");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en preparar la sentencia psLoadEquipId", ex);
            }
        }

        
        
         try {
            psLoadEquipId.setLong(1, id);
            ResultSet rs = psLoadEquipId.executeQuery();

            if (rs.next()) {
                long idCategoria = rs.getLong("id_categoria");
                int temporada = rs.getInt("any_temporada");
                String nom = rs.getString("nom");
                String tipusStr = rs.getString("tipus");
                EnumTipus tipus = tipusStr.equals("D") ? EnumTipus.D : tipusStr.equals("H") ? EnumTipus.H : EnumTipus.M;

                equip = new Equip(id, idCategoria, temporada, nom, tipus);
                return equip;
            } else {
                throw new GestorSportManagerException("No hi ha cap equip amb id: " + id);
            }

        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en recuperar equip amb id: " + id, ex);
        }
    }

    @Override
    public List<Equip> loadEquips() throws GestorSportManagerException {
        List<Equip> equips = new ArrayList<>();

        if (psLoadEquips == null) {
            try {
                psLoadEquips = conn.prepareStatement(
                    "SELECT id, id_categoria, any_temporada, nom, tipus FROM equip"
                );
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en preparar la sentencia psLoadEquips", ex);
            }
        }

        try {
            ResultSet rs = psLoadEquips.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                long idCategoria = rs.getLong("id_categoria");
                int temporada = rs.getInt("any_temporada");
                String nom = rs.getString("nom");
                String tipusStr = rs.getString("tipus");
                EnumTipus tipus = tipusStr.equals("D") ? EnumTipus.D : tipusStr.equals("H") ? EnumTipus.H : EnumTipus.M;

                Equip equip = new Equip(id, idCategoria, temporada, nom, tipus);
                equips.add(equip);
            }

            return equips;

        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en recuperar la llista d'equips", ex);
        }
    }

    @Override
    public boolean saveEquip(Equip equip) throws GestorSportManagerException {
        if (psSaveEquip==null){
            try {
                psSaveEquip = conn.prepareStatement("INSERT INTO equip (id_categoria, any_temporada,nom,Tipus)\n" +
                                                      "VALUES (?,?,?,?)");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en preparar la sentencia psSaveEquip", ex);
            }
        }
        
        try {
            psSaveEquip.setLong(1, equip.getIdCategoria());
            psSaveEquip.setInt(2, equip.getIdTemporada());
            psSaveEquip.setString(3, equip.getNom());
            psSaveEquip.setString(4, equip.getTipus().name());
            
            boolean rowUpdated = psSaveEquip.executeUpdate() > 0; 
                        
            equip.setId(getGeneratedEquipId(equip.getNom(), equip.getIdTemporada()));
            return rowUpdated;
        } catch (Exception ex) {
            throw new GestorSportManagerException("error en guardar equip amb id "+equip.getId(), ex);
        }
    }


    @Override
    public boolean modificarEquip(String nom, int any, Equip equip) throws GestorSportManagerException {
        if (psUpdateEquip == null) {
            try {
                psUpdateEquip = conn.prepareStatement("UPDATE equip SET id_categoria=?, any_temporada=?, nom=?,tipus=?  WHERE nom = ? AND any_temporada=?");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en preparar la sentencia psUpdateEquip", ex);
            }
        }

        try {
            psUpdateEquip.setLong(1, equip.getIdCategoria());
            psUpdateEquip.setInt(2, equip.getIdTemporada());
            psUpdateEquip.setString(3, equip.getNom());
            psUpdateEquip.setString(4, equip.getTipus().name());
            psUpdateEquip.setString(5, nom);
            psUpdateEquip.setInt(6, any);
            
            int rowUpdated = psUpdateEquip.executeUpdate();
                     
            return rowUpdated >0;

        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en assignar valors a la sentencia psUpdateEquip", ex);
        }
        
    }

    @Override
    public boolean eliminarEquip(Equip equip) throws GestorSportManagerException {
        long id = equip.getId();
        
        if (psDeleteEquip==null){
            try {
                psDeleteEquip = conn.prepareStatement("DELETE FROM equip WHERE id=?");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en preparar statement psDeleteEquip", ex);
            }
        }
        
        try {
            psDeleteEquip.setLong(1, id);
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en assignar valor a la sentencia psDeleteEquip "+id, ex);
        }
        
        int rowsDeleted;
        try {
            rowsDeleted = psDeleteEquip.executeUpdate();
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en eliminar equip amb id: " + id, ex);
        }
        
        if (rowsDeleted == 0) {
            throw new GestorSportManagerException("No s'ha trobat equip amb id: " + id);
        }
        
        return rowsDeleted != 0;
    }
    
    
    ///Aquest metode recupera id de l'equip que l'equip amb nom i any que li passem
    private long getGeneratedEquipId(String nom, int any) throws GestorSportManagerException, Exception{
        if (psGetGeneratedEquipId == null){
            try {
                psGetGeneratedEquipId = conn.prepareStatement("Select id from equip where nom=? AND any_temporada=?");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en preparar statement psGetGeneratedEquipId", ex);
            }
        }
        
        psGetGeneratedEquipId.setString(1, nom);
        psGetGeneratedEquipId.setInt(2, any);
        
        try (ResultSet rs = psGetGeneratedEquipId.executeQuery()) {
            if (rs.next()) {
                return rs.getLong("id");  
            } else {
                throw new GestorSportManagerException("No s'ha trobat equip amb nom/any: " + nom+"/"+any);
            }
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en executar la consulta per obtenir id a l'equip amb nom/any: " + nom+"/"+any, ex);
        }
    }

    
    
    
    
    
    
    
    //***********************************************************MEMBRE*************************************************************
    
    
    
    
    
    
    
    
    
    @Override
    public boolean afegirJugadorEquip(Membre membre) throws GestorSportManagerException {
        if (psAddJugadorEquip == null){
            try {
                psAddJugadorEquip = conn.prepareStatement("INSERT INTO membre (id_equip,id_jugador,Titular_convidat) VALUES (?, ?, ?)");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en preparar statement psAddJugadorEquip", ex);
            }
        }
        
        try {
            psAddJugadorEquip.setLong(1, membre.getIdEquip());
            psAddJugadorEquip.setLong(2, membre.getIdJugador());
            psAddJugadorEquip.setString(3, membre.getTitularitat().name());
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en assignar valors a psAddJugadorEquip\n", ex);
        }
        
        try {
            int rowUpdated = psAddJugadorEquip.executeUpdate();
            
            return rowUpdated>0;
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en executar la query\n", ex);
        }
    }

    @Override
    public boolean esTitular(long idJugador) throws GestorSportManagerException {
        if (psEsTitular == null){
            try {
                psEsTitular = conn.prepareStatement("select id_jugador from membre where id_jugador = ? AND titular_convidat='T'");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en preparar statement psEsTitular", ex);
            }
        }
        
        try {
            psEsTitular.setLong(1, idJugador);

            try (ResultSet rs = psEsTitular.executeQuery()) {
                return rs.next(); // Retorna true si existeix una fila, és a dir, el jugador és titular
            }
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en executar la query psEsTitular", ex);
        }
    }

    @Override
    public boolean eliminarJugadorEquip(long idJugador, long idEquip) throws GestorSportManagerException {
        if (psDeleteJugadorEquip==null){
            try {
                psDeleteJugadorEquip = conn.prepareStatement("DELETE FROM membre WHERE id_Jugador=? And id_Equip = ?");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en preparar statement psDeleteJugadorEquip", ex);
            }
        }
        
        try {
            psDeleteJugadorEquip.setLong(1, idJugador);
            psDeleteJugadorEquip.setLong(2, idEquip);
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en assignar valors a psDeleteJugadorEquip", ex);
        }

        try {
            int rowsDeleted = psDeleteJugadorEquip.executeUpdate();
            if (rowsDeleted == 0) {
                throw new GestorSportManagerException("No s'ha trobat cap jugador amb id: " + idJugador + " a l'equip amb id: " + idEquip);
            }
            return true;
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en executar la query psDeleteJugadorEquip", ex);
        }
    }

    public List<Jugador> loadJugadorsIdEquip(long idEquip)throws GestorSportManagerException{
        List<Jugador> jugadors=new ArrayList<>();
                
        if (psLoadJugadorsEquip==null){
            try {
                psLoadJugadorsEquip = conn.prepareStatement("select id_jugador from membre where id_equip = ?");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en preparar la sentencia psLoadJugadorsEquip", ex);
            }
        }
        try {
            psLoadJugadorsEquip.setLong(1, idEquip);
            ResultSet rs = psLoadJugadorsEquip.executeQuery();
            
            if (!rs.isBeforeFirst()) {
            throw new GestorSportManagerException("No hi ha cap equip amb id: " + idEquip);
            }

            while (rs.next()) {
                jugadors.add(loadJugadorId(rs.getLong("id_jugador")));
            }

            return jugadors;            
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en recuperar equip amb id "+idEquip, ex);
        }
    }
    
    public List<Equip> loadEquipsIdJugador(long idJugador)throws GestorSportManagerException{
        List<Equip> equips=new ArrayList<>();
                
        if (psLoadEquipsJugador==null){
            try {
                psLoadEquipsJugador = conn.prepareStatement("select id_equip from membre where id_jugador = ?");
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en preparar la sentencia psLoadEquipsJugador", ex);
            }
        }
        try {
            psLoadEquipsJugador.setLong(1, idJugador);
            ResultSet rs = psLoadEquipsJugador.executeQuery();
            
            if (!rs.isBeforeFirst()) {
            throw new GestorSportManagerException("No hi ha cap jugador amb id: " + idJugador);
            }

            while (rs.next()) {
                equips.add(loadEquipId(rs.getLong("id_equip")));
            }

            return equips;            
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en recuperar equip amb jugador amb id: " + idJugador, ex);
        }
    }
    
    
    
    
    
    
    
    
    
    //***********************************************************OTHERS*************************************************************
    
    
    
    
    
    
    
    

    @Override
    public boolean tancarConnexio() throws GestorSportManagerException {
        if (conn != null) {
            
            desferCanvis();
            
            try {
                conn.close();
            } catch (Exception ex) {
                throw new GestorSportManagerException("Error en tancar la connexió.\n", ex);
            }
        }
        return true;
    }

    @Override
    public void confirmarCanvis() throws GestorSportManagerException {
        try{
            conn.commit();
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en fer commit", ex);
        }
    }

    @Override
    public void desferCanvis() throws GestorSportManagerException {
        try{
            conn.rollback();
        } catch (Exception ex) {
            throw new GestorSportManagerException("Error en fer rollback", ex);
        }
    }

    
    
}
