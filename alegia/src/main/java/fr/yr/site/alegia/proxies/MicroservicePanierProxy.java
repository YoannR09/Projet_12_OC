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
@FeignClient(value = "microservice-panier", url = "localhost:9003")
public interface MicroservicePanierProxy {


    @GetMapping(value = "/Panier/{id}")
    Panier getPanier(@PathVariable int id);

    @GetMapping(value = "/Panier/Compte/{compteId}")
    Panier getPanierByCompteId(@PathVariable("compteId") int compteId);

    /**
     * Méthode pour créer un panier
     * @param panier
     */
    @PostMapping(value = "/Panier")
    void add(@RequestBody Panier panier);
}
