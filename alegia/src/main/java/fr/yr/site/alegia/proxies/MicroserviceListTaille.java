package fr.yr.site.alegia.proxies;


import fr.yr.site.alegia.beans.ListTaille;
import fr.yr.site.alegia.beans.Taille;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe pour récupérer les données du MicroserviceListTaille
 */
@FeignClient(name = "zuul-server", url = "localhost:9004")
public interface MicroserviceListTaille {

    @GetMapping(value = "/microservice-list/ListTaille/{id}")
    ListTaille findById(@PathVariable("id") int id);


    @GetMapping(value = "/microservice-list/ListTaille/Article/{articleId}")
    List<ListTaille> findByArticleId(@PathVariable("articleId") int articleId);

    /**
     * Méthode pour ajouter une listTaille
     * @param listTaille
     */
    @PostMapping(value = "/microservice-list/ListTaille")
    void add(@RequestBody ListTaille listTaille);

    /**
     * Méthode pour supprimer une liste de taille en focntion de l'article id
     * @param articleId
     */
    @DeleteMapping(value = "/microservice-list/ListTaille/Article/{articleId}")
    void deleteByArticleId(@PathVariable("articleId") int articleId);
}
