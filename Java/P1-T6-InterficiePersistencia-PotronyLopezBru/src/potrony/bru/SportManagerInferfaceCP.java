/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package potrony.bru;

import java.util.List;
import potrony.bru.SportManager.Categoria;
import potrony.bru.SportManager.EnumTitular;
import potrony.bru.SportManager.Equip;
import potrony.bru.SportManager.Jugador;
import potrony.bru.SportManager.Temporada;
import potrony.bru.SportManager.Usuari;

/**
 *
 * @author Vago
 */
public interface SportManagerInferfaceCP {
    
    /********************************USUARI**************************************************/
    
    /*Per guardar un sol usuari o un List
    retorna un boolea si s'ha guardat o no*/
    boolean saveUsuari(Usuari usuari);
    boolean saveUsuaris(List<Usuari> usuaris);
    
    /*Per cargar un usuari si sabem el login
    o cargar tots els usuaris que tenim*/
    Usuari loadUsuari(String login);
    List<Usuari> loadUsuaris();
    
    /*Li passem el login d'un usuari, i un 
    usuari, buscara l'usuari pel login i
    el cambiara*/
    void modificarUsuari(String login, Usuari usuari);
    
    /*Li passem un usuari, el busca pel Login i el
    borra, retorna true si l'ha borrat o false si no*/
    boolean eliminarUsuari(Usuari usuari);
    
    /*Per saber si un usuari esta registrat
    i la contrassenya es correcte*/
    boolean estaRegistrat (String login, String contrassenya);
    
    /*Per saber si el login ja esta registrat
    ja que ha de ser unic*/
    boolean loginRepetit(String login);
    
    /*Encripta i desencripta la contrassenya amb SHA1*/
    String encriptarContrassenya(String contrassenya);
    String desencriptarContrassenya(String encriptada);

    @Override
    public boolean equals(Object obj);
    
    
    /********************************TEMPORADA**************************************************/   
    
    
    /*Per guardar una temporada retorna cert
    o false si s'ha guardat*/
    boolean saveTemporada(Temporada temporada);
    
    /*Per carregar totes les temporades*/
    List<Temporada> loadTemporades();
    
    /*Eliminar una temporada retorna cert o fals
    si l'ha eliminat correctament*/
    boolean eliminarTemporada(Temporada temporada);
    
    /*Serveix per saber si la temporada ja esta
    inserida per tant no es pot repetir*/
    boolean temporadaRepetida (Temporada temporada);
    
    
    /********************************CATEGORIA**************************************************/  
    
    
    /*Per cargar una sola categoria sabent nom
    o totes les categories que estan inserides*/
    Categoria loadCategoria(String nom);
    List<Categoria> loadCategories();
    
    
    /********************************Jugadors**************************************************/  

    
    
    /*Per guardar un jugador o una llista
    de jugadors, retorna cert o fals si ho 
    guarda correctament*/
    boolean saveJugador (Jugador jugador);
    boolean saveJugadors (List<Jugador> jugadors);
    
    /*Cargar tots els jugadors*/
    List<Jugador> loadJugadors();
    
    /*Cargar jugador per idLegal nomes en pot cargar
    un ja que idLegal es unic*/
    Jugador loadJugadorIdLegal(String idLegal);
    
    /*Carga una llista dels jugadors que tinguin el 
    nom o cognom que passem*/
    List<Jugador> loadJugadorNomCognom(String nom, String cognom);
    
    /*Modifica un jugador passant-li IdLegal i el 
    Jugador retorna cert si ho ha completat*/
    boolean modificarJugador(String idLegal, Jugador jugador);
    
    /*Busca si un jugador ja esta repetit,
    mitjançant idLegal ja que ha de ser unic*/
    boolean jugadorRepetit(String idLegal);
    
    /*Elimina un jugador retorna cert o fals
    si ho ha completat*/
    boolean eliminarJugador (Jugador jugador);
    
    
    

    /********************************EQUIP**************************************************/  

    
    /*Per a cargar un equip si sabem el nom
    i la temporada ja que sense la temporada
    pot ser que no sigui unic*/
    Equip loadEquipNom(String nom, int temporada);
    
    /*Per a cargar tots els equips*/
    List<Equip> loadEquips();
    
    /*Guardar un equip o una llista d'equips
    retorna cert o fals si ho ha guardat
    correctament*/
    boolean saveEquip(Equip equip);
    boolean saveEquips(List<Equip> equips);
    
    /*Retorna cert si l'equip esta repetit*/
    boolean equipRepetit(String nom, int any);
    
    /*modificar un equip si sabem el nom i l'any
    retorna cert si s'ha guardat correctament*/
    boolean modificarEquip (String nom, int any, Equip equip);
    
    /*eliminar un Equip, passem l'equip sencer*/
    boolean eliminarEquip (Equip equip);
    
    
    /********************************MEMBRE**************************************************/ 
    
    
    /*Afegir un jugador a un equip com a titular
    o convidat*/
    boolean afegirJugadorEquip(EnumTitular titularitat, Equip equip, Jugador jugador);
    
    /*Retorna cert si ja es titular en algun equip*/
    boolean esTitular(Jugador jugador);
    
    /*Eliminar jugador d'un equip*/
    boolean eliminarJugadorEquip(Jugador jugador, Equip equip);
    
    
    /********************************OTHERS**************************************************/  
    
    
    
    /*Serveix per a guardar-ho tot*/
    void saveAll();
    
    /*Serveix per tancar la connexió amb
    la capa de persistència retorna cert
    si ho aconsegueix*/
    boolean tancarConnexio();
    
    /*Per a fer commit o rollback*/
    void confirmarCanvis();
    void desferCanvis();
}
