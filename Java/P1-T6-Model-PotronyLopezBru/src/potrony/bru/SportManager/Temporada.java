/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru.SportManager;

import java.time.Year;

/**
 *
 * @author Vago
 */
public class Temporada {
    int any;

    public int getAny() {
        return any;
    }

    public void setAny(int any) {
        if (isAnyValid(any)){
            this.any = any;
        }
    }
    
    ///metode que valida si l'any es major del 1980 ja que el club es va crear aquell any i 
    //menor que d'aqui a 5 anys ja que no te sentit crear temporades d'aqui mes de 5 anys
    public boolean isAnyValid (int any){
        int currentYear = Year.now().getValue();
        if (any < 1980 || any >= (currentYear + 5)) {
            return false;
        }else return true;
    }

    @Override
    public String toString() {
        return "Temporada{" + "any=" + any + '}';
    }
    
    
    
}
