package fr.yr.site.alegia.proxies;

import fr.yr.site.alegia.beans.AdresseLivraison;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe pour récupérer les données du MicroserviceLivraison
 */
@FeignClient(name = "zuul-server", url = "192.168.1.61:9004")
public interface MicroserviceAdresseLivraisonProxy {

    @GetMapping(value = "/microservice-livraison/Livraison/{id}")
    AdresseLivraison findById(@PathVariable("id") int id);

    @GetMapping(value = "/microservice-livraison/Livraison/Compte/{compteId}")
    List<AdresseLivraison> findByCompteId(@PathVariable("compteId") int compteId);

    @PostMapping(value = "/microservice-livraison/Livraison")
    void add(@RequestBody AdresseLivraison adresseLivraison);

    @PutMapping(value = "/microservice-livraison/Livraison")
    void update(@RequestBody AdresseLivraison adresseLivraison);

    @DeleteMapping(value = "/microservice-livraison/Livraison/{id}")
    void delete(@PathVariable("id") int id);
}
