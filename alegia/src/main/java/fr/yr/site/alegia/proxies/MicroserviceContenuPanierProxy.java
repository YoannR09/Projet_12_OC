package fr.yr.site.alegia.proxies;

import fr.yr.site.alegia.beans.Contenu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe pour récupérer les données du MicroserviceContenuPanier
 */
@FeignClient(name = "zuul-server", url = "192.168.1.61:9004")
public interface MicroserviceContenuPanierProxy {

    @GetMapping(value = "/microservice-contenu/Contenu/{id}")
    Contenu findById(@PathVariable int id);

    @GetMapping(value = "/microservice-contenu/Contenu/Panier/{panierId}")
    List<Contenu> findByPanierId(@PathVariable("panierId") int panierId);

    @PostMapping(value = "/microservice-contenu/Contenu")
    void add(@RequestBody Contenu contenu);

    @PostMapping(value = "/microservice-contenu/Contenu")
    void update(@RequestBody Contenu contenu);

    @DeleteMapping(value = "/microservice-contenu/Contenu/{id}")
    void delete(@PathVariable("id") int id);
}
