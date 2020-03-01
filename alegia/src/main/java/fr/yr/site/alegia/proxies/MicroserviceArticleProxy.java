package fr.yr.site.alegia.proxies;

import fr.yr.site.alegia.beans.Article;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe pour récupérer les données du MicroserviceArticle
 */
@FeignClient(name = "zuul-server", url = "192.168.1.61:9004")
public interface MicroserviceArticleProxy {

    /**
     * Méthode pou récupérer un article via un id
     * @param id
     * @return
     */
    @GetMapping(value = "/microservice-article/Article/{id}")
    Article getArticle(@PathVariable("id") int id);


    /**
     * Méthode pour récupérer un article via l'id d'une catégorie
     * @param categorieId
     * @return
     */
    @GetMapping(value = "/microservice-article/Article/Categorie/{categorieId}")
    List<Article> getArticleByCategorieId(@PathVariable("categorieId") int categorieId);

    /**
     * Méthode pour récupérer la liste des articles
     * @return
     */
    @GetMapping(value = "/microservice-article/Article")
    List<Article> findAll();

    /**
     * Méthode pour récupérer une liste d'articles
     * en fonction de la disponibilité et l'id d'une categorie
     * @param categorieId
     * @param dispo
     * @return
     */
    @GetMapping(value = "/microservice-article/Article/CategorieAndDispo/{categorieId},{dispo}")
    List<Article> findByCategorieIdAndDisponible(@PathVariable int categorieId,
                                                  @PathVariable Boolean dispo);

    /**
     * Méthode pour récupérer tout les articles disponibles
     * @param dispo
     * @return
     */
    @GetMapping(value = "/microservice-article/Article/Dispo/{dispo}")
    List<Article> findByDisponibleOrderById(@PathVariable Boolean dispo);

    /**
     * Méthode pour récupérer un article via son nom
     * @param nom
     * @return
     */
    @GetMapping(value = "/microservice-article/Article/Nom/{nom}")
    Article findByNom(@PathVariable("nom") String nom);

    /**
     * Méthode pour ajouter un article
     * @param article
     */
    @PostMapping(value = "/microservice-article/Article")
    void add(@RequestBody Article article);

    /**
     * Méthode pour mettre à jour un article
     * @param article
     */
    @PutMapping(value = "/microservice-article/Article")
    void update(@RequestBody Article article);

    /**
     * %éthode pour supprimer un article
     * @param id
     */
    @DeleteMapping(value = "/microservice-article/Article/{id}")
    void delete(@PathVariable("id") Integer id);

}
