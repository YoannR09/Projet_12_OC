package fr.yr.site.alegia.proxies;

import fr.yr.site.alegia.beans.LigneDeCommande;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Classe pour récupérer les données du MicroserviceLigneDeCommande
 */
@FeignClient(value = "microservice-ligne", url = "localhost:9033")
public interface MicroserviceLigneDeCommandeProxy {

    @GetMapping(value = "/Ligne/{id}")
    LigneDeCommande findById(@PathVariable int id);

    @PostMapping(value = "/Ligne")
    void add(@RequestBody LigneDeCommande ldc);
}
