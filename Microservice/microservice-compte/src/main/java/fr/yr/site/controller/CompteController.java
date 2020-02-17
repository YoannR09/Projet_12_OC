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
            return getDao().findById(id);
        }catch (Exception e){
            getLogger().error(e);
            return null;
        }
    }

    @GetMapping(value = "/Compte/Email/{email}")
    public Compte findByEmail(@PathVariable String email){
        try {
            return getDao().findByEmail(email);
        }catch (Exception e){
            getLogger().error(e);
            return null;
        }
    }

    @GetMapping(value = "/Compte")
    public List<Compte> findAll(){
        try {
            return getDao().findAll();
        }catch (Exception e){
            getLogger().error(e);
            return null;
        }
    }

    /**
     * Méthode pour ajouter un compte
     * L'adresse éléctronique est mise en minuscule
     * Le nom et prénom sont mis en majuscule
     * Si c'est le premier compte créé dans la bdd
     * ce compte sera alors un compte administrateur
     * @param compte
     */
    @PostMapping(value = "/Compte")
    public void add(@RequestBody Compte compte){
        try {
            compte.setEmail(compte.getEmail().toLowerCase());
            compte.setNom(compte.getNom().toUpperCase());
            compte.setPrenom(compte.getPrenom().toUpperCase());
            if (findAll().size() == 0 && getDao().findByNiveauAccesId(2) == null){
                compte.setNiveauAccesId(2);
            }
            getDao().save(compte);
        }catch (Exception e){
            getLogger().error(e);
        }
    }

    /**
     * Méthode pour mettre à jour un compte
     * @param compte
     */
    @PutMapping(value = "/Compte")
    public void update(@RequestBody Compte compte){
        try {
            getDao().save(compte);
        }catch (Exception e){
            getLogger().error(e);
        }
    }

    protected CompteDao getDao() {
        return dao;
    }

    protected Logger getLogger() {
        return logger;
    }
}
