package fr.yr.site.alegia.proxies;

import fr.yr.site.alegia.beans.Compte;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe pour récupérer les données du MicroserviceCompte
 */

@FeignClient(name = "zuul-server", url = "localhost:9004")
public interface MicroserviceCompteProxy {

    @GetMapping(value = "/microservice-compte/Compte/{id}")
    Compte findById(@PathVariable("id") int id);

    @GetMapping(value = "/microservice-compte/Compte/Email/{email}")
    Compte findByEmail(@PathVariable("email") String email);

    @GetMapping(value = "/microservice-compte/Compte")
    List<Compte> findAll();


    /**
     * Méthode pour ajouter un compte
     * @param compte
     */
    @PostMapping(value = "/microservice-compte/Compte")
    void add(@RequestBody Compte compte);

    /**
     * Méthode pour mettre à jour un compte
     * @param compte
     */
    @PutMapping(value = "/microservice-compte/Compte")
    void update(@RequestBody Compte compte);
}
