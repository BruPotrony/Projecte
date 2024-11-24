/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package potrony.bru;

import java.util.ArrayList;
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

            
            
            
            
            
            /////Comprobar carregar categories correctes i erronies
            
            
            System.out.println("\n\n\n\nCarregar categories");
            try {
                
                List<Integer> ids = List.of(1, 2, 3, 4, 10);

                List<Categoria> categorias = new ArrayList<>();

                for (int id : ids) {
                    try {
                        Categoria categoria = gestor.loadCategoriaId(id);

                    
                        if (categoria != null) {
                            categorias.add(categoria);
                            System.out.println("\nCategoría cargada:");
                            System.out.println("ID: " + categoria.getId());
                            System.out.println("Nom: " + categoria.getNom());
                            System.out.println("Edad mínima: " + categoria.getEdat_min());
                            System.out.println("Edad màxima: " + categoria.getEdat_max());
                        }
                    } catch (Exception e) {
                        System.out.println("\nError en cargar categoria amb id "+id);

                    } 
                }

            } catch (Exception e) {
                System.out.println("\nError en carregar categories "+e.getMessage());
            }
            

            
            
            
            
            
            /////Comprovar cargar totes les categories que hi han a la bd
            
            
            System.out.println("\n\n\n\nCargar totes les categories");
            List <Categoria> categories = gestor.loadCategories();
            
            for (Categoria category : categories) {
                System.out.println(category);
            }
        } catch (GestorSportManagerException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
