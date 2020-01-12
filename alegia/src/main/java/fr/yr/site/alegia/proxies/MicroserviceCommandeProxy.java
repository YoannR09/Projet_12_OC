package fr.yr.site.alegia.proxies;

import fr.yr.site.alegia.beans.Commande;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Classe pour récupérer les données du MicroserviceCommande
 */
@FeignClient(value = "microservice-commande", url = "localhost:9001")
public interface MicroserviceCommandeProxy {

    /**
     * Méthode pour récupérer une commande via un id.
     * @param id
     * @return
     */
    @GetMapping(value = "/Commande/{id}")
    Commande getCommande(@PathVariable("id") int id);


    /**
     * Méthode pour récupérer une commande via un numéro.
     * @param numero
     * @return
     */
    @GetMapping(value = "/Commande/{numero}")
    Commande getCommandeByNumero(@PathVariable("numero") String numero);

    @GetMapping(value = "/Commande")
    List<Commande> getListCommande();

    @PostMapping(value = "/Commande")
    void add(@RequestBody Commande commande);
}
