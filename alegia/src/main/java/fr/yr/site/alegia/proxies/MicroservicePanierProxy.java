package fr.yr.site.alegia.proxies;


import fr.yr.site.alegia.beans.Panier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Classe pour récupérer les données du MicroserviceAdresse
 */
@FeignClient(name = "zuul-server", url = "192.168.1.61:9004")
public interface MicroservicePanierProxy {


    @GetMapping(value = "/microservice-panier/Panier/{id}")
    Panier getPanier(@PathVariable int id);

    @GetMapping(value = "/microservice-panier/Panier/Compte/{compteId}")
    Panier getPanierByCompteId(@PathVariable("compteId") int compteId);

    /**
     * Méthode pour créer un panier
     * @param panier
     */
    @PostMapping(value = "/microservice-panier/Panier")
    void add(@RequestBody Panier panier);
}
