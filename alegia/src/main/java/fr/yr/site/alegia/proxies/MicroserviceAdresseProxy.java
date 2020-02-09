package fr.yr.site.alegia.proxies;

import fr.yr.site.alegia.beans.Adresse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe pour récupérer les données du MicroserviceAdresse
 */
@FeignClient(value = "microservice-adresse", url = "localhost:9004")
public interface MicroserviceAdresseProxy {

    /**
     * Méthode pour rechercher ka liste des adresses
     * @return
     */
    @GetMapping(value = "/Adresse")
    List<Adresse> findAll();

    /**
     * Méthode pour récupérer une adresse en fonction de toutes les valeurs de l'objet Adresse
     * @param ville
     * @param codePostal
     * @param numero
     * @param rue
     * @return
     */
    @GetMapping(value = "Adresse/All/{ville},{codePostal},{numero},{rue}")
    Adresse findByVilleAndCodePostalAndNumeroAndRue(
            @PathVariable("ville") String ville,@PathVariable("codePostal") String codePostal,
            @PathVariable("numero") String numero,@PathVariable("rue") String rue);

    /**
     * Méthode pour récupérer une adresse via un id
     * @param id
     * @return
     */
    @GetMapping(value = "/Adresse/{id}")
    Adresse getAdresse(@PathVariable("id") int id);


    /**
     * Méthode pour ajouter une adresse
     * @param adresse
     */
    @PostMapping(value = "/Adresse")
    void add(@RequestBody Adresse adresse);

    /**
     * Méthode pour mettre à jour une adresse existante
     * @param adresse
     */
    @PutMapping(value = "/Adresse")
    void update(@RequestBody Adresse adresse);
}
