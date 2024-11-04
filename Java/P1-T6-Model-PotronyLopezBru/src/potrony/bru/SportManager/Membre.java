/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru.SportManager;

/**
 *
 * @author Vago
 */
public class Membre {
    private Equip equip;
    private Jugador jugador;
    private EnumTitular titularitat;

    public Membre(Equip equip, Jugador jugador, EnumTitular titularitat) {
        this.equip = equip;
        this.jugador = jugador;
        this.titularitat = titularitat;
    }

    public Equip getEquip() {
        return equip;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public EnumTitular getTitularitat() {
        return titularitat;
    }

    public void setEquip(Equip equip) {
        this.equip = equip;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setTitularitat(EnumTitular titularitat) {
        this.titularitat = titularitat;
    }
    
    
}
