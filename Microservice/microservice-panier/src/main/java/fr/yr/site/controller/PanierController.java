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

    @GetMapping(value = "/Panier/{id}")
    public Panier getPanier(@PathVariable int id){
        try {
            return getDao().findById(id);
        }catch (Exception e){
            getLogger().error(e);
            return null;
        }
    }

    @GetMapping(value = "/Panier/Compte/{compteId}")
    public Panier getPanierByCompteId(@PathVariable int compteId){
        try {
            return getDao().findByCompteId(compteId);
        }catch (Exception e){
            getLogger().error(e);
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
            getDao().save(panier);
        }catch (Exception e){
            getLogger().error(e);
        }
    }

    protected Logger getLogger() {
        return logger;
    }

    protected PanierDao getDao() {
        return dao;
    }
}
