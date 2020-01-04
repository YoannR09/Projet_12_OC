package fr.yr.site.controller;

import fr.yr.site.dao.AdresseDao;
import fr.yr.site.model.Adresse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdresseController {

    @Autowired
    private AdresseDao dao;

    private static final Logger logger = LogManager.getLogger();

    public AdresseController(AdresseDao vAdresseDao){
        this.dao = vAdresseDao;
    }

    /**
     * Méthode pour rechercher ka liste des adresses
     * @return
     */
    @GetMapping(value = "/Adresse")
    public List<Adresse> findAll(){
        try {
            return dao.findAll();
        }catch (Exception e){
            logger.error(e);
            return null;
        }
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


    /**
     * Méthode pour ajouter une adresse
     * @param adresse
     */
    @PostMapping(value = "/Adresse")
    public void add(@RequestBody Adresse adresse){
        try {
            dao.save(adresse);
        }catch (Exception e){
            logger.error(e);
        }
    }

    /**
     * Méthode pour mettre à jour une adresse existante
     * @param adresse
     */
    @PutMapping(value = "/Adresse")
    public void update(@RequestBody Adresse adresse){
        try {
            dao.save(adresse);
        }catch (Exception e){
            logger.error(e);
        }
    }
}
