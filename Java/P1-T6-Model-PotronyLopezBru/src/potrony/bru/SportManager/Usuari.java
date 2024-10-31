/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru.SportManager;

/**
 *
 * @author Vago
 */
public class Usuari {
    private String login;
    private String password;
    private String nom;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if ()
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    //Valida si la contrassenya te mes de 7 caracters i que contingui numeros, majuscules i caracters especials
    //Aquí la contrassenya encara no esta encriptada
    public boolean isPasswordValid(String password){
        boolean llargada = password.length()>7;
        boolean majuscula = password.matches(".*[A-Z].*");
        boolean numero = password.matches(".*[0-9].*");
        boolean especial = password.matches(".*[_\\-\\.\"·$%&/()]+.*");
        
        return llargada && majuscula && numero && especial;
    }
    
    
    //Valida que el login tingui entre 4 i 30 caracters o numeros
    public boolean isLoginValid(String login){
        boolean llargada = login.length() >= 4 && login.length() <= 30;
        boolean contingut = login.matches("[a-zA-Z0-9]+");

        return llargada && contingut;
    }
    
    
    
    //Valida que el nom tingui entre 1 i 40 lletres
    public boolean isNomValid(String nom){
        boolean llargada = nom.length() > 1 && nom.length() <= 40;
        boolean contingut = login.matches("[a-zA-Z]+");

        return llargada && contingut;
    }

    
    @Override
    public String toString() {
        return "Usuari{" + "login=" + login + ", password=" + password + ", nom=" + nom + '}';
    }
    
    
    
}
