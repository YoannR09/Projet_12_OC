package fr.yr.site.controller;

import fr.yr.site.dao.ArticleDao;
import fr.yr.site.model.Article;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController {

    private static final Logger logger = LogManager.getLogger();

    public ArticleController(ArticleDao vArticleDao){
        this.articleDao = vArticleDao;
    }

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
            return articleDao.findById(id);
        }catch (Exception e){
            logger.error(e);
            return null;
        }
    }

    /**
     * Méthode pour récupérer un article via un numéro
     * @param numero
     * @return
     */
    @GetMapping(value = "/Article/numero/{numero}")
    public Article getArticleByNumero(@PathVariable String numero){
        try {
            return articleDao.findByNumero(numero);
        }catch (Exception e){
            logger.error(e);
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
            return articleDao.findAll();
        }catch (Exception e){
            logger.error(e);
            return null;
        }
    }

}
