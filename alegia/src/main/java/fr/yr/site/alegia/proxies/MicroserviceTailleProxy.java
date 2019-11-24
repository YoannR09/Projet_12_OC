package fr.yr.site.alegia.proxies;

import fr.yr.site.alegia.beans.Taille;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Classe pour récupérer les données du MicroserviceTaille
 */
@FeignClient(value = "microservice-taille", url = "192.168.1.61:9009")
public interface MicroserviceTailleProxy {

    @GetMapping(value = "/Taille/{id}")
    Taille findById(@PathVariable("id") int id);

    @GetMapping(value = "/Taille/Taille/{taille}")
    Taille findByTaille(@PathVariable("taille") String taille);
}
