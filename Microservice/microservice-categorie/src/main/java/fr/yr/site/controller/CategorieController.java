package fr.yr.site.controller;

import fr.yr.site.dao.CategorieDao;
import fr.yr.site.model.Categorie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategorieController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private CategorieDao dao;

    /**
     * Méthode pour rechercher une catégorie via un id
     * @param id
     * @return
     */
    @GetMapping(value = "/Categorie/{id}")
    public Categorie findById(@PathVariable int id){
        try {
            return getDao().findById(id);
        }catch (Exception e){
            getLogger().error(e);
            return null;
        }
    }

    /**
     * Méthode pour récupérer une catégorie via un nom
     * @param nom
     * @return
     */
    @GetMapping(value = "/Categorie/Nom/{nom}")
    public Categorie findByNom(@PathVariable String nom){
        try {
            return getDao().findByNom(nom);
        }catch (Exception e){
            getLogger().error(e);
            return null;
        }
    }

    /**
     * Méthode pour trouver toutes les catégories
     * @return
     */
    @GetMapping(value = "/Categorie")
    public List<Categorie> findAll(){
        try {
            return getDao().findByDisponible(true);
        }catch (Exception e){
            getLogger().error(e);
            return null;
        }
    }

    /**
     * Méthode pour trouver toutes les catégories disponibles ou indisponibles
     * @return
     */
    @GetMapping(value = "/Categorie/Dispo/{dispo}")
    public List<Categorie> findByDispo(@PathVariable Boolean dispo){
        try {
            return getDao().findByDisponible(dispo);
        }catch (Exception e){
            getLogger().error(e);
            return null;
        }
    }

    /**
     * Méthode pour ajouter une catégorie
     * Passe par une vérification du nom pour voir si une
     * catégorie existe déjà avec ce nom
     * @param categorie
     */
    @PostMapping(value = "/Categorie")
    public void add(@RequestBody Categorie categorie){
        try {
            Categorie cat = getDao().findByNom(categorie.getNom());
            if(cat == null){
                getDao().save(categorie);
            }
        }catch (Exception e){
            getLogger().error(e);
        }
    }

    /**
     * Méthode pour mettre à jour une catégorie
     * @param categorie
     */
    @PutMapping(value = "/Categorie")
    public void update(@RequestBody Categorie categorie){
        try {
            getDao().save(categorie);
        }catch (Exception e){
            getLogger().error(e);
        }
    }

    /**
     * Méthode pour supprimer une catégorie via un id
     * @param id
     */
    @DeleteMapping(value = "/Categorie/{id}")
    public void delete(@PathVariable int id){
        try {
            getDao().deleteById(id);
        }catch (Exception e){
            logger.error(e);
        }
    }

    protected CategorieDao getDao() {
        return dao;
    }


    protected Logger getLogger() {
        return logger;
    }
}
