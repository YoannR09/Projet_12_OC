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
            return dao.findById(id);
        }catch (Exception e){
            logger.error(e);
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
            return dao.findByNom(nom);
        }catch (Exception e){
            logger.error(e);
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
            return dao.findAll();
        }catch (Exception e){
            logger.error(e);
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
            return dao.findByDisponible(dispo);
        }catch (Exception e){
            logger.error(e);
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
            Categorie cat = dao.findByNom(categorie.getNom());
            if(cat == null){
                dao.save(categorie);
            }
        }catch (Exception e){
            logger.error(e);
        }
    }
}
