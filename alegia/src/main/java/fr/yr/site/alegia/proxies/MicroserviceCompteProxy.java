package fr.yr.site.alegia.proxies;

import fr.yr.site.alegia.beans.Compte;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Classe pour récupérer les données du MicroserviceCompte
 */
@FeignClient(value = "microservice-compte", url = "localhost:9012")
public interface MicroserviceCompteProxy {

    @GetMapping(value = "/Compte/{id}")
    Compte findById(@PathVariable("id") int id);

    @GetMapping(value = "/Compte/Email/{email}")
    Compte findByEmail(@PathVariable("email") String email);
}
