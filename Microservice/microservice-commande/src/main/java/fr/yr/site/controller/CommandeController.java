package fr.yr.site.controller;

import fr.yr.site.dao.CommandeDao;
import fr.yr.site.model.Commande;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(value = "/Commande/{numero}")
    public Commande getCommandeByNumero(@PathVariable String numero){
        try {
            return commandeDao.findByNumero(numero);
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
}
