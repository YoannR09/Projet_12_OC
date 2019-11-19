package fr.yr.site.alegia.proxies;

import fr.yr.site.alegia.beans.Article;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Classe pour récupérer les données du MicroserviceArticle
 */
@FeignClient(value = "microservice-article", url = "localhost:9095")
public interface MicroserviceArticleProxy {

    /**
     * Méthode pou récupérer un article via un id
     * @param id
     * @return
     */
    @GetMapping(value = "/Article/{id}")
    Article getArticle(@PathVariable("id") int id);


    /**
     * Méthode pour récupérer un article via un numéro
     * @param numero
     * @return
     */
    @GetMapping(value = "/Article/numero/{numero}")
    Article getArticleByNumero(@PathVariable("numero") String numero);

    /**
     * Méthode pour récupérer la liste des articles
     * @return
     */
    @GetMapping(value = "/Article")
    List<Article> findAll();

}
