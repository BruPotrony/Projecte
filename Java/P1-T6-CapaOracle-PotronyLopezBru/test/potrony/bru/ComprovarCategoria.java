/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru;

import potrony.bru.CapaPersistencia.SportManagerOracle;
import java.util.List;
import potrony.bru.Interface.GestorSportManagerException;
import potrony.bru.SportManager.Categoria;

/**
 *
 * @author Vago
 */
public class ComprovarCategoria {
    public static void main(String[] args) {
        
        
        try {
            SportManagerOracle gestor = new SportManagerOracle();

            /**
             * Cargar categoria amb id 1
             */
            try{
                Categoria categoria = gestor.loadCategoriaId(1);

                if (categoria != null) {
                    System.out.println("Categoría cargada:");
                    System.out.println("ID: " + categoria.getId());
                    System.out.println("Nom: " + categoria.getNom());
                    System.out.println("Edad mínima: " + categoria.getEdat_min());
                    System.out.println("Edad màxima: " + categoria.getEdat_max());
                }

            }catch (Exception e){
            System.out.println("Error en cargar la categoria"+e.getMessage());
            }
            
            /**
             * Cargar categoria amb id 9
             */
            try{
                Categoria categoria = gestor.loadCategoriaId(9);

                if (categoria != null) {
                    System.out.println("Categoría cargada:");
                    System.out.println("ID: " + categoria.getId());
                    System.out.println("Nom: " + categoria.getNom());
                    System.out.println("Edad mínima: " + categoria.getEdat_min());
                    System.out.println("Edad màxima: " + categoria.getEdat_max());
                }

            }catch (Exception e){
            System.out.println("Error en cargar la categoria Erronia"+e.getMessage());
            }
            
            
            System.out.println("\nCargar totes les categories");
            List <Categoria> categories = gestor.loadCategories();
            
            for (Categoria category : categories) {
                System.out.println(category);
            }
        } catch (GestorSportManagerException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
