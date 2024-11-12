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
    private long idCategoria;
    private int anyTemporada;
    private String nom;
    private EnumTipus tipus;  

    public Equip(long idCategoria, int anyTemporada, String nom, EnumTipus tipus) {
        setIdCategoria(idCategoria);
        setAnyTemporada(anyTemporada);
        setNom(nom);
        setTipus(tipus);
    }

    public Equip(long id, long idCategoria, int anyTemporada, String nom, EnumTipus tipus) {
        this.id = id;
        this.idCategoria = idCategoria;
        this.anyTemporada = anyTemporada;
        this.nom = nom;
        this.tipus = tipus;
    }

    
    
    
    public void setId(long id) {
        this.id = id;
    }
      

    
    public long getId() {
        return id;
    }

    public long getIdCategoria() {
        return idCategoria;
    }

    public int getIdTemporada() {
        return anyTemporada;
    }

    public String getNom() {
        return nom;
    }

    public EnumTipus getTipus() {
        return tipus;
    }

    
    
    
    public void setIdCategoria(Long idCategoria) {
        if (idCategoria==null)throw new SportModelException("S'ha passat una categoria 0");
        this.idCategoria = idCategoria;
    }

    public void setAnyTemporada(int temporada) {
        this.anyTemporada = temporada;
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

    @Override
    public String toString() {
        return "Equip{" + "id=" + id + ", idCategoria=" + idCategoria + ", anyTemporada=" + anyTemporada + ", nom=" + nom + ", tipus=" + tipus + '}';
    }
    
    
    
    
}
