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
        setEquip(equip);
        setJugador(jugador);
        setTitularitat(titularitat);
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
        if (equip==null)throw new SportModelException("S'ha passat un equip null");
        this.equip = equip;
    }

    public void setJugador(Jugador jugador) {
        if (jugador==null)throw new SportModelException("S'ha passat un jugador null");
        this.jugador = jugador;
    }

    public void setTitularitat(EnumTitular titularitat) {
        if (titularitat==null)throw new SportModelException("S'ha passat titularitat null");
        
        if (!(titularitat.equals(EnumTitular.C)||titularitat.equals(EnumTitular.T))){
            throw new SportModelException("La titularitat pot ser 'C' o 'T'");
        }
        this.titularitat = titularitat;
    }
    
    
}
