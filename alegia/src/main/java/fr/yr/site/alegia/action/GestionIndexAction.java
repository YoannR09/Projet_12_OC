package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.Article;
import fr.yr.site.alegia.beans.Categorie;
import fr.yr.site.alegia.beans.Image;
import fr.yr.site.alegia.configuration.Factory;
import fr.yr.site.alegia.configuration.GenerateMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class GestionIndexAction extends ActionSupport {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private     Factory             factory;

    private     GenerateMethod      gm = new GenerateMethod();

    private     List<Categorie>     categorieList;
    private     Integer             categorieId;
    private     List<Article>       articleList;
    private     List<Article>       actuArticleList;

    /**
     * Méthode pour afficher la page d'accueil du site
     * On charge la liste des catégories existante, la liste
     * des 5 derniers articles disponible créé
     * @return
     */
    public String accueil(){
        String vResult;
        try {
            categorieList = getFactory().getCategorieProxy().findAll();
            vResult = ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            vResult = ActionSupport.ERROR;
        }
        return vResult;
    }

    protected Factory getFactory() {
        return factory;
    }

    protected Logger getLogger() {
        return logger;
    }


    //----------- GETTERS ET SETTERS ----------------

    public List<Categorie> getCategorieList() {
        return categorieList;
    }

    public void setCategorieList(List<Categorie> categorieList) {
        this.categorieList = categorieList;
    }

    public Integer getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Integer categorieId) {
        this.categorieId = categorieId;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public List<Article> getActuArticleList() {
        return actuArticleList;
    }

    public void setActuArticleList(List<Article> actuArticleList) {
        this.actuArticleList = actuArticleList;
    }
}
