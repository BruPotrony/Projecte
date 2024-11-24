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
    private LocalDate data_naix;
    private String foto;
    private String adreca;
    private String codiPostal;
    private String poblacio;
    private String iban;
    private String idLegal;
    private int any_fi_revisio_medica;

    public Jugador(String nom, String cognom, EnumSexe sexe, LocalDate data_naix, String foto, String adreca, String codiPostal, String poblacio, String iban, String id_Legal, int any_fi_revisio_medica) {
        setNom(nom);
        setCognom(cognom);
        setSexe(sexe);
        setData_naix(data_naix);
        setFoto(foto);
        setAdreca(adreca);
        setCodiPostal(codiPostal);
        setPoblacio(poblacio);
        setIban(iban);
        setId_Legal(id_Legal);
        setAny_fi_revisio_medica(any_fi_revisio_medica);
    }

    public Jugador(long id, String nom, String cognom, EnumSexe sexe, LocalDate data_naix, String foto, String adreca,String codiPostal, String poblacio, String iban, String id_Legal, int any_fi_revisio_medica) {
        setId(id);
        setNom(nom);
        setCognom(cognom);
        setSexe(sexe);
        setData_naix(data_naix);
        setFoto(foto);
        setAdreca(adreca);
        setCodiPostal(codiPostal);
        setPoblacio(poblacio);
        setIban(iban);
        setId_Legal(id_Legal);
        setAny_fi_revisio_medica(any_fi_revisio_medica);
    }

    public void setId(long id) {
        this.id = id;
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

    public LocalDate getData_naix() {
        return data_naix;
    }

    public String getFoto() {
        return foto;
    }

    public String getAdreca() {
        return adreca;
    }

    public String getCodiPostal() {
        return codiPostal;
    }

    public String getPoblacio() {
        return poblacio;
    }

    public String getId_Legal() {
        return idLegal;
    }
    
    

    public int getAny_fi_revisio_medica() {
        return any_fi_revisio_medica;
    }

    public String getIban() {
        return iban;
    }

    
    

    
    
    
    
    
    
    
    public void setNom(String nom) {
        if (nom == null) throw new SportModelException("S'ha passat un nom null");

        boolean alfabetic = nom.toUpperCase().matches("[a-zA-ZÀÁÈÉÍÏÒÓÚÜÇÑ]+");
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
        
        boolean alfabetic = cognom.toUpperCase().matches("[A-ZÀÁÈÉÍÏÒÓÚÜÇÑ]+");
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
        if (sexe == null || !(sexe.equals(EnumSexe.D)||sexe.equals(EnumSexe.H))) {
            throw new SportModelException("El sexe ha de ser 'H' o 'D'");
        }
        this.sexe = sexe;
    }

    public void setData_naix(LocalDate data_naix) {
        if (data_naix == null){
            throw new SportModelException("L'edat passada es null");
        }
        //comprobar que tingui mes de 6 anys
        if (calcularEdatIniciAnyActual(data_naix)>6){
            this.data_naix = data_naix;
        }else{
            throw new SportModelException("L'edat minima son 6 anys");
        }
        
    }
    
     public int calcularEdatIniciAnyActual(LocalDate data_naixament) {
        // Obtenim el 1 de gener de l'any actual
        LocalDate iniciAnyAct = LocalDate.now().withDayOfYear(1);

        // Calculem l'edat
        int any = iniciAnyAct.getYear() - data_naixament.getYear();

        // Restem 1 si l'aniversari encara no ha passat aquest any
        if (data_naixament.isAfter(iniciAnyAct)) {
            any--;
        }

        return any;
    }
    
    public void setFoto(String foto) {
        if (foto.isEmpty()) {
        throw new SportModelException("La foto no pot ser null");
        }
        if (foto.length() > 2500 && foto.length()>1) {
            throw new SportModelException("La longitud de la foto no pot ser superior a 2500 caràcters");
        }

        this.foto = foto;
    }

    public void setAdreca(String adreca) {
        if (adreca.isEmpty()) {
            throw new SportModelException("S'ha passat una adreça null");
        }

        boolean llargada = adreca.length() > 5 && adreca.length() < 150;

        if (!llargada) {
            throw new SportModelException("La llargada de l'adreça ha de ser major a 5 i menor que 150");
        }

        this.adreca = adreca;
    }
    
    
    public void setCodiPostal(String codiPostal) {
        if (codiPostal.isEmpty() || codiPostal.length() != 5) {
            throw new SportModelException("El codi postal ha de tenir 5 dígits");
        }
        this.codiPostal = codiPostal;
    }

    public void setPoblacio(String poblacio) {
        if (poblacio.isEmpty()) {
            throw new SportModelException("La població no pot ser null");
        }
        boolean alfabetic = poblacio.toUpperCase().matches("[a-zA-ZÀÁÈÉÍÏÒÓÚÜÇÑ ]+");
        if (!alfabetic) {
            throw new SportModelException("La població només pot contenir caràcters alfabètics");
        }
        if (poblacio.length() < 3 || poblacio.length() > 50) {
            throw new SportModelException("La població ha de tenir entre 3 i 50 caràcters");
        }
        this.poblacio = poblacio;
    }

    public void setAny_fi_revisio_medica(int any_fi_revisio_medica) {
        int anyActual = Year.now().getValue();
        
        if (any_fi_revisio_medica < anyActual) {
            throw new SportModelException("L'any de fi de revisió mèdica ha de ser igual o major a l'any actual");
        }

        this.any_fi_revisio_medica = any_fi_revisio_medica;
    }

    public void setIban(String iban) {
        if (iban.isEmpty()) {
            throw new SportModelException("S'ha passat un IBAN null");
        }

        boolean formatCorrecte = iban.matches("^[A-Za-z]{2}\\d{15,34}$");

        if (!formatCorrecte) {
            throw new SportModelException("L'IBAN ha de començar amb 2 caràcters alfabètics seguits de 15 a 34 dígits");
        }

        this.iban = iban;
    }

    public void setId_Legal(String id_Legal) {
        if (id_Legal.isEmpty()) {
        throw new SportModelException("L'ID legal no pot ser null");
        }
        if (id_Legal.length() > 40) {
            throw new SportModelException("La longitud de l'ID legal no pot ser superior a 40 caràcters");
        }

        this.idLegal = id_Legal;
    }

    @Override
    public String toString() {
        return "Jugador{" + "id=" + id + ", nom=" + nom + ", cognom=" + cognom + ", sexe=" + sexe + ", data_naix=" + data_naix + ", foto=" + foto + ", adreca=" + adreca + ", iban=" + iban + ", idLegal=" + idLegal + ", any_fi_revisio_medica=" + any_fi_revisio_medica + '}';
    }

    
    
    
    
}
