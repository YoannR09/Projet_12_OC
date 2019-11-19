package fr.yr.site.alegia.proxies;

import fr.yr.site.alegia.beans.Categorie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Classe pour récupérer les données du MicroserviceAdresse
 */
@FeignClient(value = "microservice-categorie", url = "localhost:9015")
public interface MicroserviceCategorie {

    @GetMapping(value = "/Categorie/{id}")
    Categorie findById(@PathVariable("id") int id);

    @GetMapping(value = "/Categorie")
    List<Categorie> findAll();
}

