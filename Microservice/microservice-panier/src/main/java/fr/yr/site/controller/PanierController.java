package fr.yr.site.controller;

import fr.yr.site.dao.PanierDao;
import fr.yr.site.model.Panier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PanierController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private PanierDao dao;

    public PanierController(PanierDao vPanierDao){
        this.dao = vPanierDao;
    }

    @GetMapping(value = "/Panier/{id}")
    public Panier getPanier(@PathVariable int id){
        try {
            return dao.findById(id);
        }catch (Exception e){
            logger.error(e);
            return null;
        }
    }

    @GetMapping(value = "/Panier/Compte/{compteId}")
    public Panier getPanierByCompteId(@PathVariable int compteId){
        try {
            return dao.findByCompteId(compteId);
        }catch (Exception e){
            logger.error(e);
            return null;
        }
    }

    /**
     * Méthode pour créer un panier
     * @param panier
     */
    @PostMapping(value = "/Panier")
    public void add(@RequestBody Panier panier){
        try {
            dao.save(panier);
        }catch (Exception e){
            logger.error(e);
        }
    }
}
