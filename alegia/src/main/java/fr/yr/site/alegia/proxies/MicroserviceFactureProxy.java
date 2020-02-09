package fr.yr.site.alegia.proxies;

import fr.yr.site.alegia.beans.Facture;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Classe pour récupérer les données du MicroserviceFacture
 */
@FeignClient(value = "microservice-facture", url = "localhost:9018")
public interface MicroserviceFactureProxy {

    @GetMapping(value = "/Facture/{id}")
    Facture getById(@PathVariable("id") int id);

    @GetMapping(value = "/Facture/Commande/{commandeId}")
    Facture getByCoAndCommandeId(@PathVariable("commandeId") int commandeId);

    @PostMapping(value = "/Facture")
    void add(@RequestBody Facture facture);

    @PutMapping(value = "/Facture")
    void update(@RequestBody Facture facture);
}
