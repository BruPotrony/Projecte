/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.lopez.bru.SportManager;

/**
 *
 * @author Vago
 */
public class Categoria {
    long id;
    String nom;
    int edat_min;
    int edat_max;

    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getEdat_min() {
        return edat_min;
    }

    public int getEdat_max() {
        return edat_max;
    }

    @Override
    public String toString() {
        return "Categoria{" + "id=" + id + ", nom=" + nom + ", edat_min=" + edat_min + ", edat_max=" + edat_max + '}';
    }
    
    
}
