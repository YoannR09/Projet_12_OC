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

    @GetMapping(value = "/Commande/NomPrenomEmail/{nom},{prenom},{email}")
    List<Commande> findByNomPrenomEmail(@PathVariable String nom,
                                               @PathVariable String prenom,
                                               @PathVariable String email);


    @GetMapping(value = "/Commande/NomPrenom/{nom},{prenom}")
    List<Commande> findByNomPrenom(@PathVariable String nom,
                                          @PathVariable String prenom);

    @GetMapping(value = "/Commande/Nom/{nom}")
    List<Commande> findByNom(@PathVariable String nom);


    @GetMapping(value = "/Commande/NomEmail/{nom},{email}")
    List<Commande> findByNomEmail(@PathVariable String nom,@PathVariable String email);


    @GetMapping(value = "/Commande/PrenomEmail/{prenom},{email}")
    List<Commande> findByPrenomEmail(@PathVariable String prenom,
                                            @PathVariable String email);

    @GetMapping(value = "/Commande/Prenom/{prenom}")
    List<Commande> findByPrenom(@PathVariable("prenom") String prenom);

    @GetMapping(value = "/Commande/Email/{email}")
    List<Commande> findByEmail(@PathVariable("email") String email);

    /**
     * Méthode pour récupérer une commande via un numéro.
     * @param numero
     * @return
     */
    @GetMapping(value = "/Commande/Numero/{numero}")
    Commande getCommandeByNumero(@PathVariable("numero") String numero);

    @GetMapping(value = "/Commande")
    List<Commande> getListCommande();

    @GetMapping(value = "/Commande/NumeroContaining/{numero}")
    List<Commande> findCommandeByNumeroContaining(@PathVariable("numero") String numero);

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
