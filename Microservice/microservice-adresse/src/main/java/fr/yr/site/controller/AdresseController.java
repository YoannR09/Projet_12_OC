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

    /**
     * Méthode pour rechercher ka liste des adresses
     * @return
     */
    @GetMapping(value = "/Adresse")
    public List<Adresse> findAll(){
        try {
            return getDao().findAll();
        }catch (Exception e){
            getLogger().error(e);
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
            return getDao().findById(id);
        }catch (Exception e){
            getLogger().error(e);
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
            getDao().save(adresse);
        }catch (Exception e){
            getLogger().error(e);
        }
    }

    /**
     * Méthode pour récupérer une adresse en fonction de toutes les valeurs de l'objet Adresse
     * @param ville
     * @param codePostal
     * @param numero
     * @param rue
     * @return
     */
    @GetMapping(value = "/Adresse/All/{ville},{codePostal},{numero},{rue}")
    public Adresse findByVilleAndCodePostalAndNumeroAndRue(
            @PathVariable String ville,@PathVariable String codePostal,
            @PathVariable String numero,@PathVariable String rue){
        try {
            return getDao().findByVilleAndCodePostalAndNumeroAndRue(ville, codePostal, numero, rue);
        }catch (Exception e){
            return null;
        }
    }

    @GetMapping(value = "/Adresse/OldCommande/{compteId}")
    public List<Adresse> findOldByCompteId(@PathVariable int compteId){
        try {
            return getDao().findOldAdresseByCompteIdAnd(compteId);
        }catch (Exception e){
            logger.error(e);
            return null;
        }
    }


    /**
     * Méthode pour mettre à jour une adresse existante
     * @param adresse
     */
    @PutMapping(value = "/Adresse")
    public void update(@RequestBody Adresse adresse){
        try {
            getDao().save(adresse);
        }catch (Exception e){
            getLogger().error(e);
        }
    }

    protected AdresseDao getDao() {
        return dao;
    }

    protected Logger getLogger() {
        return logger;
    }
}
