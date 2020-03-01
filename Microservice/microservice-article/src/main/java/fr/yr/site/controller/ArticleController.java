package fr.yr.site.controller;

import fr.yr.site.dao.ArticleDao;
import fr.yr.site.model.Article;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private ArticleDao articleDao;

    /**
     * Méthode pou récupérer un article via un id
     * @param id
     * @return
     */
    @GetMapping(value = "/Article/{id}")
    public Article getArticle(@PathVariable int id){
        try {
            return getArticleDao().findById(id);
        }catch (Exception e){
            getLogger().error(e);
            return null;
        }
    }

    /**
     * Méthode pour récupérer un article via l'id d'une catégorie
     * @param categorieId
     * @return
     */
    @GetMapping(value = "/Article/Categorie/{categorieId}")
    public List<Article> getArticleByCategorieId(@PathVariable int categorieId){
        try {
            return getArticleDao().findByCategorieId(categorieId);
        }catch (Exception e){
            getLogger().error(e);
            return null;
        }
    }

    /**
     * Méthode pour récupérer la liste des articles
     * @return
     */
    @GetMapping(value = "/Article")
    public List<Article> findAll(){
        try {
            return getArticleDao().findAll();
        }catch (Exception e){
            getLogger().error(e);
            return null;
        }
    }

    /**
     * Méthode pour récupérer une liste d'articles
     * en fonction de la disponibilité et l'id d'une categorie
     * @param categorieId
     * @param dispo
     * @return
     */
    @GetMapping(value = "/Article/CategorieAndDispo/{categorieId},{dispo}")
    public List<Article> findByCategorieIdAndDisponible(@PathVariable int categorieId,@PathVariable Boolean dispo){
        try {
            return getArticleDao().findByCategorieIdAndDisponible(categorieId,dispo);
        }catch (Exception e){
            getLogger().error(e);
            return null;
        }
    }

    /**
     * Méthode pour récupérer tous les articles disponibles
     * @param dispo
     * @return
     */
    @GetMapping(value = "/Article/Dispo/{dispo}")
    public List<Article> findByDisponibleOrderById(@PathVariable Boolean dispo){
        try {
            return getArticleDao().findByDisponibleOrderById(dispo);
        }catch (Exception e){
            getLogger().error(e);
            return null;
        }
    }

    /**
     * Méthode pour récupérer un article via son nom
     * @param nom
     * @return
     */
    @GetMapping(value = "/Article/Nom/{nom}")
    public Article findByNom(@PathVariable String nom){
        try {
            return getArticleDao().findByNom(nom);
        }catch (Exception e){
            getLogger().error(e);
            return null;
        }
    }

    /**
     * Méthode pour ajouter un article
     * @param article
     */
    @PostMapping(value = "/Article")
    public void add(@RequestBody Article article){
        try {
                getArticleDao().save(article);
        }catch (Exception e){
            getLogger().error(e);
        }
    }

    /**
     * Méthode pour mettre à jour un article
     * @param article
     */
    @PutMapping(value = "/Article")
    public void update(@RequestBody Article article){
        try {
            getArticleDao().save(article);
        }catch (Exception e){
            getLogger().error(e);
        }
    }

    /**
     * %éthode pour supprimer un article
     * @param id
     */
    @DeleteMapping(value = "/Article/{id}")
    public void delete(@PathVariable Integer id){
        try {
            getArticleDao().deleteById(id);
        }catch (Exception e){
            getLogger().error(e);
        }
    }

    protected ArticleDao getArticleDao() {
        return articleDao;
    }

    protected Logger getLogger() {
        return logger;
    }
}
