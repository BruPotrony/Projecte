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

    
    public Usuari(String login, String password, String nom) {
        this.setLogin(login);
        this.setPassword(password);
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
           this.password = password; 
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
    
    
    //Valida si la contrassenya te mes de 7 caracters i que contingui numeros, majuscules i caracters especials
    //Aquí la contrassenya encara no esta encriptada
    public boolean isPasswordValid(String password){
        if (password == null) throw new SportModelException("S'ha passat un password null");
        
        boolean llargada = password.length()>7;
        boolean majuscula = password.matches(".*[A-Z].*");
        boolean numero = password.matches(".*[0-9].*");
        boolean especial = password.matches(".*[_\\-\\.\"·$%&/()]+.*");
        
        if (!llargada){
            throw new SportModelException("La llargada del password ha de ser major a 7");
        }else if(!majuscula){
            throw new SportModelException("El password ha de contenir majúscules");
        }else if(!numero){
            throw new SportModelException("El password ha de contenir números");
        }else if(!especial){
            throw new SportModelException("El password ha de contenir algun caracter especial");
        }
        
        return true;
    }
    
    
    //Valida que el login tingui entre 4 i 30 caracters o numeros
    public boolean isLoginValid(String login){
        if (login == null) throw new SportModelException("S'ha passat un login null");
        
        boolean llargada = login.length() >= 4 && login.length() <= 30;
        boolean contingut = login.matches("[a-zA-Z0-9]+");
        
        if (!llargada){
            throw new SportModelException("La llargada del login ha de ser major a 3 i menor que 31");
        }else if (!contingut){
            throw new SportModelException("El login nomes soporta numeros majúscules i minúscules");
        }

        return true;
    }
    
    
    
    //Valida que el nom tingui entre 1 i 40 lletres
    public boolean isNomValid(String nom){
        if (nom == null) throw new SportModelException("S'ha passat un nom null");
        boolean llargada = nom.length() > 1 && nom.length() <= 40;
        boolean contingut = nom.matches("[a-zA-Z]*");
        
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
