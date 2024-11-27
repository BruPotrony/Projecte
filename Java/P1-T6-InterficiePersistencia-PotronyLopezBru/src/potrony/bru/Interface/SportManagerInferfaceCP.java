/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package potrony.bru.Interface;

import java.util.List;
import potrony.bru.SportManager.Categoria;
import potrony.bru.SportManager.EnumTitular;
import potrony.bru.SportManager.Equip;
import potrony.bru.SportManager.Jugador;
import potrony.bru.SportManager.Membre;
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
    boolean saveUsuari(Usuari usuari)throws GestorSportManagerException;
    boolean saveUsuaris(List<Usuari> usuaris)throws GestorSportManagerException;
    
    /*Per cargar un usuari si sabem el login
    o cargar tots els usuaris que tenim*/
    Usuari loadUsuariLogin(String login)throws GestorSportManagerException;
    List<Usuari> loadUsuaris()throws GestorSportManagerException;
    
    /*Li passem el login d'un usuari, i una nova contrassenya
    , buscara l'usuari pel login i
    el cambiara*/
    void modificarContrassenya(String login, String pswd)throws GestorSportManagerException;
    
    /*Li passem un usuari, el busca pel Login i el
    borra, retorna true si l'ha borrat o false si no*/
    boolean eliminarUsuari(String login)throws GestorSportManagerException;
    
    /*Per saber si un usuari esta registrat
    i la contrassenya es correcte*/
    boolean estaRegistrat (Usuari usuari)throws GestorSportManagerException;
    
    /*Per saber si el login ja esta registrat
    ja que ha de ser unic*/
    boolean loginRepetit(String login)throws GestorSportManagerException;
    
    /*Encripta la contrassenya amb SHA1*/
    String encriptarContrassenya(String contrassenya)throws GestorSportManagerException;


    
    
    /********************************TEMPORADA**************************************************/   
    
    
    /*Per guardar una temporada retorna cert
    o false si s'ha guardat*/
    boolean saveTemporada(Temporada temporada)throws GestorSportManagerException;
    
    /*Per carregar totes les temporades
    retorna una llista buida si no hi han
    temporades*/
    List<Temporada> loadTemporades()throws GestorSportManagerException;
    
    /*Eliminar una temporada retorna cert o fals
    si l'ha eliminat correctament*/
    boolean eliminarTemporada(int any)throws GestorSportManagerException;
    
    /*Serveix per saber si la temporada ja esta
    inserida per tant no es pot repetir*/
    boolean temporadaRepetida (int any)throws GestorSportManagerException;
    
    
    /********************************CATEGORIA**************************************************/  
    
    
    /*Per cargar una sola categoria sabent nom
    o totes les categories que estan inserides*/
    Categoria loadCategoriaId(long id)throws GestorSportManagerException;
    List<Categoria> loadCategories()throws GestorSportManagerException;
    
    
    /********************************Jugadors**************************************************/  

    
    
    /*Per guardar un jugador o una llista
    de jugadors, retorna cert o fals si ho 
    guarda correctament*/
    boolean saveJugador (Jugador jugador)throws GestorSportManagerException;
    boolean saveJugadors (List<Jugador> jugadors)throws GestorSportManagerException;
    
    /*Cargar tots els jugadors*/
    List<Jugador> loadJugadors()throws GestorSportManagerException;
    
    /*Cargar jugador per id nomes en pot cargar
    un ja que id es unic*/
    Jugador loadJugadorId(Long id)throws GestorSportManagerException;
    
    /*Cargar jugador per id Legal nomes en pot cargar
    un ja que idLegal es unic*/
    Jugador loadJugadorIdLegal(String dni)throws GestorSportManagerException;
    
    /*Carga una llista dels jugadors que tinguin el 
    nom o cognom que passem*/
    List<Jugador> loadJugadorNomCognom(String nom, String cognom)throws GestorSportManagerException;
    
    /*Modifica un jugador passant-li IdLegal i el 
    Jugador retorna cert si ho ha completat*/
    boolean modificarJugador(String idLegal, Jugador jugador)throws GestorSportManagerException;
    
    /*Busca si un jugador ja esta repetit,
    mitjançant idLegal ja que ha de ser unic*/
    boolean jugadorIdLegalRepetit(String idLegal)throws GestorSportManagerException;
    
    /*Elimina un jugador retorna cert o fals
    si ho ha completat*/
    boolean eliminarJugador (Jugador jugador)throws GestorSportManagerException;
    
    
    

    /********************************EQUIP**************************************************/  

    
    /*Per a cargar un equip si sabem el nom
    i la temporada ja que sense la temporada
    pot ser que no sigui unic*/
    Equip loadEquipNom(String nom, int temporada)throws GestorSportManagerException;
    
    /*Cargar un equip pel seu id*/
    Equip loadEquipId(long id) throws GestorSportManagerException;
    
    /*Per a cargar tots els equips*/
    List<Equip> loadEquips()throws GestorSportManagerException;
    
    /*Guardar un equip o una llista d'equips
    retorna cert o fals si ho ha guardat
    correctament*/
    boolean saveEquip(Equip equip)throws GestorSportManagerException;
    
    
    /*modificar un equip si sabem el nom i l'any
    retorna cert si s'ha guardat correctament*/
    boolean modificarEquip (String nom, int any, Equip equip)throws GestorSportManagerException;
    
    /*eliminar un Equip, passem l'equip sencer*/
    boolean eliminarEquip (Equip equip)throws GestorSportManagerException;
    
    
    /********************************MEMBRE**************************************************/ 
    
    
    /*Afegir un jugador a un equip com a titular
    o convidat*/
    boolean afegirJugadorEquip(Membre membre)throws GestorSportManagerException;
    
    /*Retorna cert si ja es titular en algun equip*/
    boolean esTitular(long idJugador)throws GestorSportManagerException;
    
    /*Eliminar jugador d'un equip*/
    boolean eliminarJugadorEquip(long idJugador, long idEquip)throws GestorSportManagerException;
    
    /*Carrega tots els jugadors que estan en equip amb idEquip*/
    List<Jugador> loadJugadorsIdEquip(long idEquip)throws GestorSportManagerException;
    
    /*Carrega tots els equips que hi ha el jugador amb idJugador*/
    List<Equip> loadEquipsIdJugador(long idJugador)throws GestorSportManagerException;
    
            
            
            
    
    /********************************OTHERS**************************************************/  
    
    
    

    
    /*Serveix per tancar la connexió amb
    la capa de persistència retorna cert
    si ho aconsegueix*/
    boolean tancarConnexio()throws GestorSportManagerException;
    
    /*Per a fer commit o rollback*/
    void confirmarCanvis()throws GestorSportManagerException;
    void desferCanvis()throws GestorSportManagerException;
}
