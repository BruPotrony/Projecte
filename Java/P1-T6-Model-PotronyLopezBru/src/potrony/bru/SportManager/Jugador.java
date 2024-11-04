/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru.SportManager;

import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author Vago
 */
public class Jugador {
    private long id;//Aqui nomes tindre id pel get, ja que ID ho assigna automaticament la bd
    private String nom;
    private String cognom;
    private EnumSexe sexe;
    private Date data_naix;
    private String foto;
    private String adreca;
    private String iban;
    private String idLegal;
    private int any_fi_revisio_medica;

    public Jugador(String nom, String cognom, EnumSexe sexe, Date data_naix, String foto, String adreca, String iban, String id_Legal, int any_fi_revisio_medica) {
        setNom(nom);
        setCognom(cognom);
        setSexe(sexe);
        setData_naix(data_naix);
        setFoto(foto);
        setAdreca(adreca);
        setIban(iban);
        setId_Legal(id_Legal);
        this.any_fi_revisio_medica = any_fi_revisio_medica;
    }


    
    
    
    
    
    
    
    
    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getCognom() {
        return cognom;
    }

    public EnumSexe getSexe() {
        return sexe;
    }

    public Date getData_naix() {
        return data_naix;
    }

    public String getFoto() {
        return foto;
    }

    public String getAdreca() {
        return adreca;
    }

    public int getAny_fi_revisio_medica() {
        return any_fi_revisio_medica;
    }

    public String getIban() {
        return iban;
    }

    public String getId_Legal() {
        return idLegal;
    }
    
    

    
    
    
    
    
    
    
    public void setNom(String nom) {
        if (nom == null) throw new SportModelException("S'ha passat un nom null");

        boolean alfabetic = nom.matches("[a-zA-Z]+");
        boolean llargada = nom.length() > 1 && nom.length() < 40;

        if (!alfabetic) {
            throw new SportModelException("El nom només pot contenir caràcters alfabètics");
        } else if (!llargada) {
            throw new SportModelException("La llargada del nom ha de ser major a 1 i menor que 40");
        }else{
            this.nom = nom;
        }
        
    }

    public void setCognom(String cognom) {
        if (cognom == null) throw new SportModelException("S'ha passat un cognom null");

        boolean alfabetic = cognom.matches("[a-zA-Z]+");
        boolean llargada = cognom.length() > 1 && cognom.length() < 40;

        if (!alfabetic) {
            throw new SportModelException("El cognom només pot contenir caràcters alfabètics");
        } else if (!llargada) {
            throw new SportModelException("La llargada del cognom ha de ser major a 1 i menor que 40");
        }else{
            this.cognom=cognom;
        }
    }

    public void setSexe(EnumSexe sexe) {
        if (sexe == null || (!sexe.equals(EnumSexe.H) && !sexe.equals(EnumSexe.D))) {
            throw new SportModelException("El sexe ha de ser 'H' o 'D'");
        }
        this.sexe = sexe;
    }

    public void setData_naix(Date data_naix) {
        //comprobar que tingui mes de 6 anys
        if (calcularEdatIniciAnyActual(data_naix)>6){
            this.data_naix = data_naix;
        }else{
            throw new SportModelException("L'edat minima son 6 anys");
        }
        
    }
    
     public int calcularEdatIniciAnyActual(Date data_naixament) {
         //Ho converteixo a localdate ja que date te molts metodes deprecated com el getYear
        LocalDate dataNaix = data_naixament.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        //Obtinc 1 de gener de l'any actual
        LocalDate iniciAnyAct = LocalDate.now().withDayOfYear(1);

        // Calcular edat
        int any = iniciAnyAct.getYear() - dataNaix.getYear();

        //Resta 1 si el cumple encara no ha passat
        if (dataNaix.isAfter(iniciAnyAct)) {
            any--;
        }

        return any;
    }
    
    public void setFoto(String foto) {
        if (foto == null) {
        throw new SportModelException("La foto no pot ser null");
        }
        if (foto.length() > 2500 && foto.length()>1) {
            throw new SportModelException("La longitud de la foto no pot ser superior a 2500 caràcters");
        }

        this.foto = foto;
    }

    public void setAdreca(String adreca) {
        if (adreca == null) {
            throw new SportModelException("S'ha passat una adreça null");
        }

        boolean llargada = adreca.length() > 5 && adreca.length() < 150;

        if (!llargada) {
            throw new SportModelException("La llargada de l'adreça ha de ser major a 5 i menor que 150");
        }

        this.adreca = adreca;
    }

    public void setAny_fi_revisio_medica(int any_fi_revisio_medica) {
        int anyActual = Year.now().getValue();

        if (any_fi_revisio_medica < anyActual) {
            throw new SportModelException("L'any de fi de revisió mèdica ha de ser igual o major a l'any actual");
        }

    this.any_fi_revisio_medica = any_fi_revisio_medica;
    }

    public void setIban(String iban) {
        if (iban == null) {
            throw new SportModelException("S'ha passat un IBAN null");
        }

        boolean formatCorrecte = iban.matches("^[A-Za-z]{2}\\d{15,34}$");

        if (!formatCorrecte) {
            throw new SportModelException("L'IBAN ha de començar amb 2 caràcters alfabètics seguits de 15 a 34 dígits");
        }

        this.iban = iban;
    }

    public void setId_Legal(String id_Legal) {
        if (id_Legal == null) {
        throw new SportModelException("L'ID legal no pot ser null");
        }
        if (id_Legal.length() > 40) {
            throw new SportModelException("La longitud de l'ID legal no pot ser superior a 40 caràcters");
        }

        this.idLegal = id_Legal;
    }
    
    
}
