package fr.yr.site.controller;

import fr.yr.site.dao.ListTailleDao;
import fr.yr.site.model.ListTaille;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ListTailleController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private ListTailleDao dao;

    @GetMapping(value = "/ListTaille/{id}")
    public ListTaille findById(@PathVariable int id){
        try {
            return getDao().findById(id);
        }catch (Exception e){
            getLogger().error(e);
            return null;
        }
    }


    @GetMapping(value = "/ListTaille/Article/{articleId}")
    public List<ListTaille> findByCategorieId(@PathVariable int articleId){
        try {
            return getDao().findByArticleId(articleId);
        }catch (Exception e){
            getLogger().error(e);
            return null;
        }
    }

    /**
     * Méthode pour ajouter une listTaille
     * @param listTaille
     */
    @PostMapping(value = "/ListTaille")
    public void add(@RequestBody ListTaille listTaille){
        try {
            getDao().save(listTaille);
        }catch (Exception e){
            getLogger().error(e);
        }
    }

    /**
     * Méthode pour supprimer une liste de taille en focntion de l'article id
     * @param articleId
     */
    @DeleteMapping(value = "/ListTaille/Article/{articleId}")
    public void deleteByArticleId(@PathVariable Integer articleId){
        try {
            getDao().deleteByArticleId(articleId);
        }catch (Exception e){
            getLogger().error(e);
        }
    }

    protected ListTailleDao getDao() {
        return dao;
    }

    protected Logger getLogger() {
        return logger;
    }
}
