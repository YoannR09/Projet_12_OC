package fr.yr.site.controller;

import fr.yr.site.dao.ContenuDao;
import fr.yr.site.model.Contenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContenuController {

    @Autowired
    private ContenuDao dao;

    /**
     * Méthode pour récupérer un
     * @param id
     * @return
     */
    @GetMapping(value = "/Contenu/{id}")
    public Contenu findById(@PathVariable int id){
        try {
            return dao.findById(id);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * Méthode pour récupérer une liste de contenus via l'id d'un panier
     * @param panierId
     * @return
     */
    @GetMapping(value = "/Contenu/Panier/{panierId}")
    public List<Contenu> findByPanierId(@PathVariable int panierId){
        try {
            return dao.findByPanierId(panierId);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * Méthode pour ajouter un contenu à la bdd
     * @param contenu
     */
    @PostMapping(value = "/Contenu")
    public void add(@RequestBody Contenu contenu){
        try {
            dao.save(contenu);
        }catch (Exception e){

        }
    }

    /**
     * Méthode pour mettre à jour un coutennu existant
     * @param contenu
     */
    @PutMapping(value = "/Contenu")
    public void update(@RequestBody Contenu contenu){
        try {
            dao.save(contenu);
        }catch (Exception e){

        }
    }

    /**
     * Méthode pour supprimer un contenu
     * @param id
     */
    @DeleteMapping(value = "/Contenu/{id}")
    public void delete(@PathVariable int id){
        try {
            dao.deleteById(id);
        }catch (Exception e){

        }
    }
}
