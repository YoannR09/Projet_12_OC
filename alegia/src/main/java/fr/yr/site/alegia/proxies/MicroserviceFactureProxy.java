package fr.yr.site.alegia.proxies;

import fr.yr.site.alegia.beans.Facture;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Classe pour récupérer les données du MicroserviceFacture
 */
@FeignClient(name = "zuul-server", url = "192.168.1.61:9004")
public interface MicroserviceFactureProxy {

    @GetMapping(value = "/microservice-facture/Facture/{id}")
    Facture getById(@PathVariable("id") int id);

    @GetMapping(value = "/microservice-facture/Facture/Commande/{commandeId}")
    Facture getByCoAndCommandeId(@PathVariable("commandeId") int commandeId);

    @PostMapping(value = "/microservice-facture/Facture")
    void add(@RequestBody Facture facture);

    @PutMapping(value = "/microservice-facture/Facture")
    void update(@RequestBody Facture facture);
}
