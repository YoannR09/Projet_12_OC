package fr.yr.site.alegia.proxies;

import fr.yr.site.alegia.beans.Commande;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe pour récupérer les données du MicroserviceCommande
 */
@FeignClient(name = "zuul-server", url = "localhost:9004")
public interface MicroserviceCommandeProxy {

    /**
     * Méthode pour récupérer une commande via un id.
     * @param id
     * @return
     */
    @GetMapping(value = "/microservice-commande/Commande/{id}")
    Commande getCommande(@PathVariable("id") int id);

    @GetMapping(value = "/microservice-commande/microservice-commande/Commande/NomPrenomEmail/{nom},{prenom},{email}")
    List<Commande> findByNomPrenomEmail(@PathVariable String nom,
                                               @PathVariable String prenom,
                                               @PathVariable String email);


    @GetMapping(value = "/microservice-commande/Commande/NomPrenom/{nom},{prenom}")
    List<Commande> findByNomPrenom(@PathVariable String nom,
                                          @PathVariable String prenom);

    @GetMapping(value = "/microservice-commande/Commande/Nom/{nom}")
    List<Commande> findByNom(@PathVariable String nom);


    @GetMapping(value = "/microservice-commande/Commande/NomEmail/{nom},{email}")
    List<Commande> findByNomEmail(@PathVariable String nom,@PathVariable String email);


    @GetMapping(value = "/microservice-commande/Commande/PrenomEmail/{prenom},{email}")
    List<Commande> findByPrenomEmail(@PathVariable String prenom,
                                            @PathVariable String email);

    @GetMapping(value = "/microservice-commande/Commande/Prenom/{prenom}")
    List<Commande> findByPrenom(@PathVariable("prenom") String prenom);

    @GetMapping(value = "/microservice-commande/Commande/Email/{email}")
    List<Commande> findByEmail(@PathVariable("email") String email);

    /**
     * Méthode pour récupérer une commande via un numéro.
     * @param numero
     * @return
     */
    @GetMapping(value = "/microservice-commande/Commande/Numero/{numero}")
    Commande getCommandeByNumero(@PathVariable("numero") String numero);

    @GetMapping(value = "/microservice-commande/Commande")
    List<Commande> getListCommande();

    @GetMapping(value = "/microservice-commande/Commande/NumeroContaining/{numero}")
    List<Commande> findCommandeByNumeroContaining(@PathVariable("numero") String numero);

    /**
     * Méthode pour récupérer une commande via l'id d'un compte.
     * @param compteId
     * @return
     */
    @GetMapping(value = "/microservice-commande/Commande/Compte/{compteId}")
    List<Commande> getCommandeByCompteId(@PathVariable("compteId") int compteId);


    /**
     * Méthode pour récupérer une liste de commandes via l'id d'un statut
     * @param statutId
     * @return
     */
    @GetMapping(value = "/microservice-commande/Commande/Statut/{statutId}")
    List<Commande> getCOmmandeByStatutId(@PathVariable int statutId);

    @PostMapping(value = "/microservice-commande/Commande")
    void add(@RequestBody Commande commande);

    @PutMapping(value = "/microservice-commande/Commande")
    void update(@RequestBody Commande commande);
}
