package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.Article;
import fr.yr.site.alegia.beans.Categorie;
import fr.yr.site.alegia.beans.Image;
import fr.yr.site.alegia.proxies.MicroserviceArticleProxy;
import fr.yr.site.alegia.proxies.MicroserviceCategorie;
import fr.yr.site.alegia.proxies.MicroserviceImageProxy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Classe qui gère l'affichage du contenu d'une catégories.
 */
public class GestionCategorieAction extends ActionSupport {

    // --- Microservices ---
    @Autowired
    MicroserviceArticleProxy microserviceArticleProxy;
    @Autowired
    MicroserviceCategorie microserviceCategorie;
    @Autowired
    MicroserviceImageProxy microserviceImageProxy;

    private         Integer         categorieId;
    private         List<Article>   articleList;
    private         List<Categorie> categorieList;

    /**
     * Méthode pour afficher une liste d'articles via l'id d'une catégorie
     * @return
     */
    public String doListArticleByCategorieId(){
        try {
            articleList = microserviceArticleProxy.getArticleByCategorieId(categorieId);
            for (Article article:articleList){
                article.setImageList(microserviceImageProxy.findByArticleId(article.getId()));
                if (article.getImageList().size() == 0 ){
                    Image image = new Image();
                    image.setUrl("indisponible.jpg");
                    article.getImageList().add(image);
                }
            }
            categorieList = microserviceCategorie.findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            return ActionSupport.ERROR;
        }
    }

    
    //----------- GETTERS ET SETTERS ----------------


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

    public List<Categorie> getCategorieList() {
        return categorieList;
    }

    public void setCategorieList(List<Categorie> categorieList) {
        this.categorieList = categorieList;
    }
}
