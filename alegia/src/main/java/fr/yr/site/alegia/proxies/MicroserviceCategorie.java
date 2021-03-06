package fr.yr.site.alegia.proxies;

import fr.yr.site.alegia.beans.Categorie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe pour récupérer les données du MicroserviceCategorie
 */
@FeignClient(name = "zuul-server", url = "localhost:9004")
public interface MicroserviceCategorie {


    /**
     * Méthode pour rechercher une catégorie via un id
     * @param id
     * @return
     */
    @GetMapping(value = "/microservice-categorie/Categorie/{id}")
    Categorie findById(@PathVariable("id") int id);

    /**
     * Méthode pour récupérer une catégorie via un nom
     * @param nom
     * @return
     */
    @GetMapping(value = "/microservice-categorie/Categorie/Nom/{nom}")
    Categorie findByNom(@PathVariable("nom") String nom);

    /**
     * Méthode pour trouver toutes les catégories
     * @return
     */
    @GetMapping(value = "/microservice-categorie/Categorie")
    List<Categorie> findAll();

    /**
     * Méthode pour trouver toutes les catégories disponibles ou indisponibles
     * @return
     */
    @GetMapping(value = "/microservice-categorie/Categorie/Dispo/{dispo}")
    List<Categorie> findByDispo(@PathVariable("dispo") Boolean dispo);

    /**
     * Méthode pour ajouter une catégorie
     * @param categorie
     */
    @PostMapping(value = "/microservice-categorie/Categorie")
    void add(@RequestBody Categorie categorie);

    /**
     * Méthode pour mettre à jour une catégorie
     * @param categorie
     */
    @PutMapping(value = "/microservice-categorie/Categorie")
    void update(@RequestBody Categorie categorie);

    @DeleteMapping(value = "/microservice-categorie/Categorie/{id}")
    void delete(@PathVariable("id") int id);
}

