package fr.yr.site.alegia.proxies;

import fr.yr.site.alegia.beans.Image;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe pour récupérer les données du MicroserviceImage
 */
@FeignClient(name = "zuul-server", url = "localhost:9004")
public interface MicroserviceImageProxy {

    @GetMapping(value = "/microservice-image/Image/Article/{articleId}")
    List<Image> findByArticleId(@PathVariable("articleId") int articleId);

    /**
     * Méthode pour ajouter une image
     * @param image
     */
    @PostMapping(value = "/microservice-image/Image")
    void add(@RequestBody Image image);

    /**
     * Méthode pour supprimer une image
     * @param id
     */
    @DeleteMapping(value = "/microservice-image/Image/{id}")
    void delete(@PathVariable("id") int id);
}
