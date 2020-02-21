package fr.yr.site.alegia.proxies;

import fr.yr.site.alegia.beans.Taille;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Classe pour récupérer les données du MicroserviceTaille
 */
@FeignClient(name = "zuul-server", url = "localhost:9004")
public interface MicroserviceTailleProxy {

    @GetMapping(value = "/microservice-taille/Taille/{id}")
    Taille findById(@PathVariable("id") int id);

    @GetMapping(value = "/microservice-taille/Taille/Taille/{taille}")
    Taille findByTaille(@PathVariable("taille") String taille);

    /**
     * Méthode pour récupérer la liste des tailles exitantes
     * @return
     */
    @GetMapping(value = "/microservice-taille/Taille")
    List<Taille> findAll();
}
