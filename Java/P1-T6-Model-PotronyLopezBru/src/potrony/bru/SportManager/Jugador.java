/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru.SportManager;

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
    private int any_fi_revisio_medica;

    public Jugador(String nom, String cognom, EnumSexe sexe, Date data_naix, String foto, String adreca, int any_fi_revisio_medica) {
        this.nom = nom;
        this.cognom = cognom;
        this.sexe = sexe;
        this.data_naix = data_naix;
        this.foto = foto;
        this.adreca = adreca;
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

    
    
    
    
    
    
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public void setSexe(EnumSexe sexe) {
        this.sexe = sexe;
    }

    public void setData_naix(Date data_naix) {
        this.data_naix = data_naix;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    public void setAny_fi_revisio_medica(int any_fi_revisio_medica) {
        this.any_fi_revisio_medica = any_fi_revisio_medica;
    }
    
    
}
