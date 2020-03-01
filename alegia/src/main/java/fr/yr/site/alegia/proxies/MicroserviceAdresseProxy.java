package fr.yr.site.alegia.proxies;

import fr.yr.site.alegia.beans.Adresse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe pour récupérer les données du MicroserviceAdresse
 */
@FeignClient(name = "zuul-server", url = "192.168.1.61:9004")
public interface MicroserviceAdresseProxy {

    /**
     * Méthode pour rechercher ka liste des adresses
     * @return
     */
    @GetMapping(value = "/microservice-adresse/Adresse")
    List<Adresse> findAll();

    /**
     * Méthode pour récupérer une adresse en fonction de toutes les valeurs de l'objet Adresse
     * @param ville
     * @param codePostal
     * @param numero
     * @param rue
     * @return
     */
    @GetMapping(value = "/microservice-adresse/Adresse/All/{ville},{codePostal},{numero},{rue}")
    Adresse findByVilleAndCodePostalAndNumeroAndRue(
            @PathVariable("ville") String ville,@PathVariable("codePostal") String codePostal,
            @PathVariable("numero") String numero,@PathVariable("rue") String rue);

    /**
     * Méthode pour récupérer une adresse via un id
     * @param id
     * @return
     */
    @GetMapping(value = "/microservice-adresse/Adresse/{id}")
    Adresse getAdresse(@PathVariable("id") int id);


    /**
     * Méthode pour récupérer une liste d'adresse qui ont servies pour les commandes
     * du compte en question
     * @param compteId
     * @return
     */
    @GetMapping(value = "/microservice-adresse/Adresse/OldCommande/{compteId}")
    List<Adresse> findOldByCompteId(@PathVariable("compteId") int compteId);



    /**
     * Méthode pour ajouter une adresse
     * @param adresse
     */
    @PostMapping(value = "/microservice-adresse/Adresse")
    void add(@RequestBody Adresse adresse);

    /**
     * Méthode pour mettre à jour une adresse existante
     * @param adresse
     */
    @PutMapping(value = "/microservice-adresse/Adresse")
    void update(@RequestBody Adresse adresse);
}
