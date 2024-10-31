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
    static long idCounter=0; //per generar id automaticament
    long id;
    long idCategoria;
    int idTemporada;
    String nom;
    EnumTipus tipus;
    
    public void generarIdAutomatic(){
        this.id = idCounter++;
    }
}
