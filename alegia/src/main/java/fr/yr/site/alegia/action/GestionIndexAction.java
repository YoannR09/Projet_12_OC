package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionContext;
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


/**
 * Classe qui gère l'affichage de la page d'acceuil et les pages vitrines.
 */
public class GestionIndexAction extends ActionSupport {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private     Factory             factory;

    private     GenerateMethod      gm = new GenerateMethod();

    private     List<Categorie>     categorieList;
    private     Integer             categorieId;
    private     Integer             countPanier;
    private     List<Article>       articleList;
    private     List<Article>       actuArticleList;

    /**
     * Méthode pour afficher la page d'accueil du site
     * @return
     */
    public String accueil(){
        String vResult;
        try {
            categorieList = getFactory().getCategorieProxy().findAll();
            countPanier = gm.generateCountPanier(factory,getEmail());
            vResult = ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            vResult = ActionSupport.ERROR;
        }
        return vResult;
    }

    /**
     * Méthode pour afficher la page qui décrit la marque.
     * @return
     */
    public String doConsulterLaMarque(){
        try {
            categorieList = getFactory().getCategorieProxy().findAll();
            countPanier = gm.generateCountPanier(factory,getEmail());
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            return ActionSupport.ERROR;
        }
    }

    protected Factory getFactory() {
        return factory;
    }

    protected Logger getLogger() {
        return logger;
    }

    protected String getEmail() {
        return (String) ActionContext.getContext().getSession().get("email");
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

    public Integer getCountPanier() {
        return countPanier;
    }

    public void setCountPanier(Integer countPanier) {
        this.countPanier = countPanier;
    }
}
