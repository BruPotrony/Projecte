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
    private long idEquip;
    private long idJugador;
    private EnumTitular titularitat;

    public Membre(long equip, long jugador, EnumTitular titularitat) {
        setIdEquip(equip);
        setIdJugador(jugador);
        setTitularitat(titularitat);
    }

    public long getIdEquip() {
        return idEquip;
    }

    public long getIdJugador() {
        return idJugador;
    }

    public EnumTitular getTitularitat() {
        return titularitat;
    }

    public void setIdEquip(long equip) {
        if (equip<=0)throw new SportModelException("S'ha passat un equip null");
        this.idEquip = equip;
    }

    public void setIdJugador(long jugador) {
        if (jugador<=0)throw new SportModelException("S'ha passat un jugador null");
        this.idJugador = jugador;
    }

    public void setTitularitat(EnumTitular titularitat) {
        if (titularitat==null)throw new SportModelException("S'ha passat titularitat null");
        
        if (!(titularitat.equals(EnumTitular.C)||titularitat.equals(EnumTitular.T))){
            throw new SportModelException("La titularitat pot ser 'C' o 'T'");
        }
        this.titularitat = titularitat;
    }
    
    
}
