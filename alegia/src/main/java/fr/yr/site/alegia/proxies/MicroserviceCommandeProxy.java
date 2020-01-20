package fr.yr.site.alegia.proxies;

import fr.yr.site.alegia.beans.Commande;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(value = "/Commande/Numero/{numero}")
    Commande getCommandeByNumero(@PathVariable("numero") String numero);

    @GetMapping(value = "/Commande")
    List<Commande> getListCommande();

    /**
     * Méthode pour récupérer une commande via l'id d'un compte.
     * @param compteId
     * @return
     */
    @GetMapping(value = "/Commande/Compte/{compteId}")
    List<Commande> getCommandeByCompteId(@PathVariable("compteId") int compteId);


    /**
     * Méthode pour récupérer une liste de commandes via l'id d'un statut
     * @param statutId
     * @return
     */
    @GetMapping(value = "/Commande/Statut/{statutId}")
    List<Commande> getCOmmandeByStatutId(@PathVariable int statutId);

    @PostMapping(value = "/Commande")
    void add(@RequestBody Commande commande);

    @PutMapping(value = "/Commande")
    void update(@RequestBody Commande commande);
}
