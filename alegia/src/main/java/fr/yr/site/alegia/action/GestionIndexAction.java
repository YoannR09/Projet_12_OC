package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.Article;
import fr.yr.site.alegia.beans.Categorie;
import fr.yr.site.alegia.beans.Image;
import fr.yr.site.alegia.configuration.Factory;
import fr.yr.site.alegia.configuration.GenerateMethod;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class GestionIndexAction extends ActionSupport {

    @Autowired
    Factory factory;

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
            articleList = factory.getArticleProxy().findByDisponibleOrderById(true);
            gm.completeArticleList(factory,articleList);
            actuArticleList = new ArrayList<>();
            if (articleList.size() < 5){
                for(int i=0;i<articleList.size();i++){
                    actuArticleList.add(articleList.get(i));
                }
            }else {
                for(int i=articleList.size()-5;i<articleList.size();i++){
                    actuArticleList.add(articleList.get(i));
                }
            }
            categorieList = factory.getCategorieProxy().findAll();
            vResult = ActionSupport.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            vResult = ActionSupport.ERROR;
        }
        return vResult;
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
