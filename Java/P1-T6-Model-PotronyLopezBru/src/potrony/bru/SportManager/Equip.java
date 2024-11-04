/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru.SportManager;

/**
 *
 * @author Vago
 */
public class Equip {
    private long id;//Aqui nomes tindre id pel get, ja que ID ho assigna automaticament la bd
    private Categoria categoria;
    private Temporada temporada;
    private String nom;
    private EnumTipus tipus;  

    public Equip(Categoria categoria, Temporada temporada, String nom, EnumTipus tipus) {
        setCategoria(categoria);
        setTemporada(temporada);
        setNom(nom);
        setTipus(tipus);
    }

    public long getId() {
        return id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Temporada getTemporada() {
        return temporada;
    }

    public String getNom() {
        return nom;
    }

    public EnumTipus getTipus() {
        return tipus;
    }

    
    
    
    public void setCategoria(Categoria categoria) {
        if (categoria==null)throw new SportModelException("S'ha passat una categoria null");
        this.categoria = categoria;
    }

    public void setTemporada(Temporada temporada) {
        if (temporada==null)throw new SportModelException("S'ha passat una temporada null");
        this.temporada = temporada;
    }

    public void setNom(String nom) {
        if (nom == null) throw new SportModelException("S'ha passat un nom null");

        boolean llargadaValida = nom.length() > 1 && nom.length() < 40;

        if (!llargadaValida) {
            throw new SportModelException("El nom ha de tenir més de 1 caracter i menys de 40");
        }

        this.nom = nom;
    }

    public void setTipus(EnumTipus tipus) {
        if (!(tipus.equals(EnumTipus.D)||tipus.equals(EnumTipus.H)||tipus.equals(EnumTipus.M))){
            throw new SportModelException("El tipus ha de ser 'H' o 'D' o 'M'");
        }
        this.tipus = tipus;
    }
    
}
