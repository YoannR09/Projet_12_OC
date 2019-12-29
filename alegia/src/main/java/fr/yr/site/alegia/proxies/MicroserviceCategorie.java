package fr.yr.site.alegia.proxies;

import fr.yr.site.alegia.beans.Categorie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Classe pour récupérer les données du MicroserviceCategorie
 */
@FeignClient(value = "microservice-categorie", url = "localhost:9015")
public interface MicroserviceCategorie {


    /**
     * Méthode pour rechercher une catégorie via un id
     * @param id
     * @return
     */
    @GetMapping(value = "/Categorie/{id}")
    Categorie findById(@PathVariable("id") int id);

    /**
     * Méthode pour récupérer une catégorie via un nom
     * @param nom
     * @return
     */
    @GetMapping(value = "/Categorie/Nom/{nom}")
    Categorie findByNom(@PathVariable("nom") String nom);

    /**
     * Méthode pour trouver toutes les catégories
     * @return
     */
    @GetMapping(value = "/Categorie")
    List<Categorie> findAll();

    /**
     * Méthode pour trouver toutes les catégories disponibles ou indisponibles
     * @return
     */
    @GetMapping(value = "/Categorie/Dispo/{dispo}")
    List<Categorie> findByDispo(@PathVariable("dispo") Boolean dispo);

    /**
     * Méthode pour ajouter une catégorie
     * @param categorie
     */
    @PostMapping(value = "/Categorie")
    void add(@RequestBody Categorie categorie);
}

