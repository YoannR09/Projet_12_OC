package fr.yr.site.controller;

import fr.yr.site.dao.CommandeDao;
import fr.yr.site.model.Commande;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@RestController
public class CommandeController {

    @Autowired
    private CommandeDao commandeDao;

    private static final Logger logger = LogManager.getLogger();

    /**
     * Méthode pour récupérer une commande via un id.
     * @param id
     * @return
     */
    @GetMapping(value = "/Commande/{id}")
    public Commande getCommande(@PathVariable int id){
        try {
            return commandeDao.findById(id);
        }catch (Exception e){
            logger.error(e);
            return null;
        }
    }

    /**
     * Méthode pour récupérer une commande via un numéro.
     * @param numero
     * @return
     */
    @GetMapping(value = "/Commande/Numero/{numero}")
    public Commande getCommandeByNumero(@PathVariable String numero){
        try {
            return commandeDao.findByNumero(numero);
        }catch (Exception e){
            logger.error(e);
            return null;
        }
    }

    @GetMapping(value = "/Commande/NomPrenomEmail/{nom},{prenom},{email}")
    public List<Commande> findByNomPrenomEmail(@PathVariable String nom,
                                        @PathVariable String prenom,
                                        @PathVariable String email){
        try {
            return commandeDao.findByNomPrenomEmail(nom, prenom, email);
        }catch (Exception e){
            return null;
        }
    }


    @GetMapping(value = "/Commande/NomPrenom/{nom},{prenom}")
    public List<Commande> findByNomPrenom(@PathVariable String nom,
                                   @PathVariable String prenom){
        try {
            return commandeDao.findByNomPrenom(nom,prenom);
        }catch (Exception e){
            return null;
        }
    }

    @GetMapping(value = "/Commande/Nom/{nom}")
    public List<Commande> findByNom(@PathVariable String nom){
        try {
            return commandeDao.findByNom(nom);
        }catch (Exception e){
            return null;
        }
    }


    @GetMapping(value = "/Commande/NomEmail/{nom},{email}")
    public List<Commande> findByNomEmail(@PathVariable String nom,@PathVariable String email){
     try {
         return commandeDao.findByNomEmail(nom,email);
     }catch (Exception e){
         return null;
     }
    }


    @GetMapping(value = "/Commande/PrenomEmail/{prenom},{email}")
    public List<Commande> findByPrenomEmail(@PathVariable String prenom,
                                     @PathVariable String email){
        try {
            return commandeDao.findByPrenomEmail(prenom, email);
        }catch (Exception e){
            return null;
        }
    }

    @GetMapping(value = "/Commande/Prenom/{prenom}")
    public List<Commande> findByPrenom(@PathVariable("prenom") String prenom){
        try {
            return commandeDao.findByPrenom(prenom);
        }catch (Exception e){
            return null;
        }
    }

    @GetMapping(value = "/Commande/Email/{email}")
    public List<Commande> findByEmail(@PathVariable("email") String email){
        try {
            return commandeDao.findByEmail(email);
        }catch (Exception e){
            return null;
        }
    }

    @GetMapping(value = "/Commande/NumeroContaining/{numero}")
    public List<Commande> findCommandeByNumeroContaining(@PathVariable String numero){
        try {
            return commandeDao.findByNumeroContaining(numero);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * Méthode pour récupérer une commande via l'id d'un compte.
     * @param compteId
     * @return
     */
    @GetMapping(value = "/Commande/Compte/{compteId}")
    public List<Commande> getCommandeByCompteId(@PathVariable int compteId){
        try {
            return commandeDao.findByCompteId(compteId);
        }catch (Exception e){
            logger.error(e);
            return null;
        }
    }

    /**
     * Méthode pour récupérer une liste de commandes via l'id d'un statut
     * @param statutId
     * @return
     */
    @GetMapping(value = "/Commande/Statut/{statutId}")
    public List<Commande> getCOmmandeByStatutId(@PathVariable int statutId){
        try {
            return commandeDao.findByStatutId(statutId);
        }catch (Exception e){
            logger.error(e);
            return null;
        }
    }

    @GetMapping(value = "/Commande")
    public List<Commande> getListCommande(){
        try {
            return commandeDao.findAll();
        }catch (Exception e){
            logger.error(e);
            return null;
        }
    }

    @PostMapping(value = "/Commande")
    public void add(@RequestBody Commande commande){
        try {
            commandeDao.save(commande);
        }catch (Exception e){
            logger.error(e);
        }
    }

    @PutMapping(value = "/Commande")
    public void update(@RequestBody Commande commande){
        try {
            commandeDao.save(commande);
        }catch (Exception e){
            logger.error(e);
        }
    }
}
