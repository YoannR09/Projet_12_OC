package fr.yr.site.alegia.proxies;

import fr.yr.site.alegia.beans.Contenu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe pour récupérer les données du MicroserviceContenuPanier
 */
@FeignClient(value = "microservice-contenu", url = "localhost:9017")
public interface MicroserviceContenuPanierProxy {

    @GetMapping(value = "/Contenu/{id}")
    Contenu findById(@PathVariable int id);

    @GetMapping(value = "/Contenu/Panier/{panierId}")
    List<Contenu> findByPanierId(@PathVariable("panierId") int panierId);

    @PostMapping(value = "/Contenu")
    void add(@RequestBody Contenu contenu);

    @PostMapping(value = "/Contenu")
    void update(@RequestBody Contenu contenu);

    @DeleteMapping(value = "/Contenu/{id}")
    void delete(@PathVariable("id") int id);
}
