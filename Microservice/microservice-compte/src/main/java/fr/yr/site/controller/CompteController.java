package fr.yr.site.controller;

import fr.yr.site.dao.CompteDao;
import fr.yr.site.model.Compte;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompteController {

    @Autowired
    private CompteDao dao;

    private static final Logger logger = LogManager.getLogger();


    @GetMapping(value = "/Compte/{id}")
    public Compte findById(@PathVariable int id){
        try {
            return dao.findById(id);
        }catch (Exception e){
            return null;
        }
    }

    @GetMapping(value = "/Compte/Email/{email}")
    public Compte findByEmail(@PathVariable String email){
        try {
            return dao.findByEmail(email);
        }catch (Exception e){
            return null;
        }
    }

    @GetMapping(value = "/Compte")
    public List<Compte> findAll(){
        try {
            return dao.findAll();
        }catch (Exception e){
            logger.error(e);
            return null;
        }
    }

    /**
     * Méthode pour ajouter un compte
     * @param compte
     */
    @PostMapping(value = "/Compte")
    public void add(@RequestBody Compte compte){
        try {
            compte.setEmail(compte.getEmail().toLowerCase());
            compte.setNom(compte.getNom().toUpperCase());
            compte.setPrenom(compte.getPrenom().toUpperCase());
            dao.save(compte);
        }catch (Exception e){
            logger.error(e);
        }
    }

    /**
     * Méthode pour mettre à jour un compte
     * @param compte
     */
    @PutMapping(value = "/Compte")
    public void update(@RequestBody Compte compte){
        try {
            dao.save(compte);
        }catch (Exception e){
            logger.error(e);
        }
    }
}
