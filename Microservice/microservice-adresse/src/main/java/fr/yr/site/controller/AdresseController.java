package fr.yr.site.controller;

import fr.yr.site.dao.AdresseDao;
import fr.yr.site.model.Adresse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdresseController {

    @Autowired
    private AdresseDao dao;

    private static final Logger logger = LogManager.getLogger();

    public AdresseController(AdresseDao vAdresseDao){
        this.dao = vAdresseDao;
    }

    /**
     * Méthode pour récupérer une adresse via un id
     * @param id
     * @return
     */
    @GetMapping(value = "/Adresse/{id}")
    public Adresse getAdresse(@PathVariable int id){
        try {
            return dao.findById(id);
        }catch (Exception e){
            logger.error(e);
            return null;
        }
    }
}
