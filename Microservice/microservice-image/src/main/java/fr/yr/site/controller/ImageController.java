package fr.yr.site.controller;

import fr.yr.site.dao.ImageDao;
import fr.yr.site.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImageController {

    @Autowired
    private ImageDao dao;

    @GetMapping(value = "/Image/Article/{articleId}")
    public List<Image> findByArticleId(@PathVariable int articleId){
        try {
            return dao.findByArticleId(articleId);
        }catch (Exception e){
            return null;
        }
    }
}
