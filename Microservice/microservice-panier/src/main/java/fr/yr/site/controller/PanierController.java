package fr.yr.site.controller;

import fr.yr.site.dao.PanierDao;
import fr.yr.site.model.Panier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public Panier getPanierByCompteId(@PathVariable int compteId){
        try {
            return dao.findByCompteId(compteId);
        }catch (Exception e){
            logger.error(e);
            return null;
        }
    }
}
