package fr.yr.site.controller;

import fr.yr.site.dao.ImageDao;
import fr.yr.site.model.Image;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ImageController {

    @Autowired
    private ImageDao dao;

    private static final Logger logger = LogManager.getLogger();

    @GetMapping(value = "/Image/Article/{articleId}")
    public List<Image> findByArticleId(@PathVariable int articleId){
        try {
            return getDao().findByArticleId(articleId);
        }catch (Exception e){
            getLogger().error(e);
            return null;
        }
    }

    /**
     * Méthode pour ajouter une image
     * @param image
     */
    @PostMapping(value = "/Image")
    public void add(@RequestBody Image image){
        try {
            getDao().save(image);
        }catch (Exception e){
            getLogger().error(e);
        }
    }

    /**
     * Méthode pour supprimer une image
     * @param id
     */
    @DeleteMapping(value = "/Image/{id}")
    public void delete(@PathVariable int id){
        try {
            getDao().deleteById(id);
        }catch (Exception e){
            getLogger().error(e);
        }
    }

    protected Logger getLogger() {
        return logger;
    }

    protected ImageDao getDao() {
        return dao;
    }
}
