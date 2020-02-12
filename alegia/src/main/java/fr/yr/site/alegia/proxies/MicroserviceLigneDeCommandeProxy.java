package fr.yr.site.alegia.proxies;

import fr.yr.site.alegia.beans.LigneDeCommande;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Classe pour récupérer les données du MicroserviceLigneDeCommande
 */
@FeignClient(name = "zuul-server", url = "localhost:9004")
public interface MicroserviceLigneDeCommandeProxy {

    @GetMapping(value = "/microservice-ligne/Ligne/{id}")
    LigneDeCommande findById(@PathVariable int id);

    @GetMapping(value = "/microservice-ligne/Ligne")
    List<LigneDeCommande> findAll();

    @GetMapping(value = "/microservice-ligne/Ligne/Commande/{commandeId}")
    List<LigneDeCommande> findByCommandeId(@PathVariable("commandeId") int commandeId);

    @PostMapping(value = "/microservice-ligne/Ligne")
    void add(@RequestBody LigneDeCommande ldc);
}
