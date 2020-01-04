package fr.yr.site.alegia.proxies;

import fr.yr.site.alegia.beans.Article;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe pour récupérer les données du MicroserviceArticle
 */
@FeignClient(value = "microservice-article", url = "localhost:9002")
public interface MicroserviceArticleProxy {

    /**
     * Méthode pou récupérer un article via un id
     * @param id
     * @return
     */
    @GetMapping(value = "/Article/{id}")
    Article getArticle(@PathVariable("id") int id);


    /**
     * Méthode pour récupérer un article via l'id d'une catégorie
     * @param categorieId
     * @return
     */
    @GetMapping(value = "/Article/Categorie/{categorieId}")
    List<Article> getArticleByCategorieId(@PathVariable("categorieId") int categorieId);

    /**
     * Méthode pour récupérer la liste des articles
     * @return
     */
    @GetMapping(value = "/Article")
    List<Article> findAll();

    /**
     * Méthode pour récupérer une liste d'articles
     * en fonction de la disponibilité et l'id d'une categorie
     * @param categorieId
     * @param dispo
     * @return
     */
    @GetMapping(value = "/Article/CategorieAndDispo/{categorieId},{dispo}")
    List<Article> findByCategorieIdAndDisponible(@PathVariable int categorieId,
                                                  @PathVariable Boolean dispo);

    /**
     * Méthode pour récupérer tout les articles disponibles
     * @param dispo
     * @return
     */
    @GetMapping(value = "/Article/Dispo/{dispo}")
    List<Article> findByDisponibleOrderById(@PathVariable Boolean dispo);

    /**
     * Méthode pour ajouter un article
     * @param article
     */
    @PostMapping(value = "/Article")
    void add(@RequestBody Article article);

    /**
     * Méthode pour mettre à jour un article
     * @param article
     */
    @PutMapping(value = "/Article")
    void update(@RequestBody Article article);

    /**
     * %éthode pour supprimer un article
     * @param id
     */
    @DeleteMapping(value = "/Article/{id}")
    void delete(@PathVariable("id") Integer id);

}
