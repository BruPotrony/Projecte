/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru.SportManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * Constructor: li arriben totes les dades dels parametres i una mes: 
 * estaEncriptada la qual ens dira si li estem passant la contrassenya encriptada
 * (com quan la recuperem de la base de dades) o si no esta encriptada i per tant ha 
 * de passar un filtre de contrassenya
 * 
 * @author Bru Potrony
 */
public class Usuari {
    private String login;
    private String password;
    private String nom;

    
    public Usuari(String login, String password, String nom, boolean estaEncriptada) {
        this.setLogin(login);
        if (estaEncriptada){
            this.password=password;
        }else{
            this.setPassword(password);
        }
        
        this.setNom(nom);
    }

    
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (this.isLoginValid(login)){
           this.login = login; 
        }else{
            throw new SportModelException("Error en assignar el Login de l'usuari");
        }
        
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (this.isPasswordValid(password)){
           this.password = encriptarContrassenya(password); 
        }else{
            throw new SportModelException("Error en assignar el Password de l'usuari");
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if(this.isNomValid(nom)){
            this.nom = nom;
        }else{
            throw new SportModelException("Error en assignar el Nom de l'usuari");
        }
        
    }
    
    
    
    public String encriptarContrassenya(String contrassenya) throws SportModelException {
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
            throw new SportModelException("Error en encriptar la contrassenya", e);
        }
    }
    
    
    
    //Valida si la contrassenya te mes de 7 caracters i que contingui numeros i majuscules
    //Aquí la contrassenya encara no esta encriptada
    public boolean isPasswordValid(String password){
        if (password==null){
            throw new SportModelException("El password no pot ser null");
        }
        
        boolean llargada = password.length()>7;
        boolean majuscula = password.matches(".*[A-Z].*");
        boolean numero = password.matches(".*[0-9].*");
        
        
        
        if (!llargada){
            throw new SportModelException("La llargada del password ha de ser major a 7");
        }
        if (!majuscula){
            throw new SportModelException("El password ha de contenir alguna majuscula");
        }
        if (!numero){
            throw new SportModelException("El password ha de contenir alguna numero");
        }
        
        return llargada && majuscula && numero;
    }
    
    
    //Valida que el login tingui entre 4 i 30 caracters o numeros
    public boolean isLoginValid(String login){
        if (login == null) throw new SportModelException("S'ha passat un login null");
        
        boolean llargada = login.length() >= 4 && login.length() <= 30;
        
        if (!llargada){
            throw new SportModelException("La llargada del login ha de ser major a 3 i menor que 31");
        }

        return true;
    }
    
    
    
    //Valida que el nom tingui entre 1 i 40 lletres
    public boolean isNomValid(String nom){
        if (nom == null) throw new SportModelException("S'ha passat un nom null");
        boolean llargada = nom.length() > 1 && nom.length() <= 40;
        boolean contingut = nom.toUpperCase().matches("[a-zA-ZÀÁÈÉÍÏÒÓÚÜÇÑ]*");
        
        if (!llargada){
            throw new SportModelException("La llargada del nom ha de ser major a 1 i menor que 41");
        }else if (!contingut){
            throw new SportModelException("El nom nomes soporta majúscules i minúscules");
        }
        
        return true;
    }

    
    @Override
    public String toString() {
        return "Usuari{" + "login=" + login + ", password=" + password + ", nom=" + nom + '}';
    }
}
