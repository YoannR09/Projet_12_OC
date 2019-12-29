package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.Article;
import fr.yr.site.alegia.beans.Categorie;
import fr.yr.site.alegia.beans.Image;
import fr.yr.site.alegia.beans.Taille;
import fr.yr.site.alegia.proxies.MicroserviceArticleProxy;
import fr.yr.site.alegia.proxies.MicroserviceCategorie;
import fr.yr.site.alegia.proxies.MicroserviceImageProxy;
import fr.yr.site.alegia.proxies.MicroserviceTailleProxy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestionSiteAction extends ActionSupport {

    @Autowired
    MicroserviceCategorie microserviceCategorie;
    @Autowired
    MicroserviceTailleProxy microserviceTailleProxy;
    @Autowired
    MicroserviceArticleProxy microserviceArticleProxy;
    @Autowired
    MicroserviceImageProxy microserviceImageProxy;

    private     List<Categorie>         categorieList;
    private     List<Categorie>         categories;
    private     List<Article>           articleList;
    private     List<Taille>            tailleList;
    private     Integer                 categorieId;
    private     Integer                 articleId;
    private     String                  categorieSelect;
    private     String                  radio;
    private     List<String>            radioList = Arrays.asList("Disponible","Indisponible");


    /**
     * Méthode pour permet de récupérer la liste des catégories
     * Quand l'administrateur consulte les pages de gestions
     * @return
     */
    public String doGestion(){
        String vResult;
        try {
            categorieList = microserviceCategorie.findAll();
            vResult = ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Une erreur est survenu...");
            vResult = ActionSupport.ERROR;
        }
        return vResult;
    }

    /**
     * Méthode pour afficher le formulaire pour créer un article
     * @return
     */
    public String formAddArticle(){
        String vResult;
        try {
            tailleList = microserviceTailleProxy.findAll();
            categorieList = microserviceCategorie.findAll();
            vResult = ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Une erreur est survenu...");
            vResult = ActionSupport.ERROR;
        }
        return vResult;
    }

    /**
     * Méthode pour gérer les articles.
     * Affiche le formulaire dans un premier temps
     * Affiche enssuite une liste d'articles à gérer
     * @return
     */
    public String gestionArticle(){
        String vResult;
        try {
            if (radio != null){
                Categorie categorie = microserviceCategorie.findByNom(categorieSelect);
                if (radio.equals("Disponible")){
                    articleList = microserviceArticleProxy.findByCategorieIdAndDisponible(categorie.getId(),true);
                }else if (radio.equals("Indisponible")){
                    articleList = microserviceArticleProxy.findByCategorieIdAndDisponible(categorie.getId(),false);
                }
                for (Article article:articleList){
                    article.setImageList(microserviceImageProxy.findByArticleId(article.getId()));
                    article.setSupprimable(true);
                    if (article.getImageList().size() == 0 ){
                        Image image = new Image();
                        new Image();
                        image.setUrl("indisponible.jpg");
                        article.getImageList().add(image);
                    }
                }
            }
            categorieList = microserviceCategorie.findAll();
            vResult = ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Une erreur est survenu...");
            vResult = ActionSupport.ERROR;
        }
        return vResult;
    }

    public String gestionCategorie(){
        String vResult;
        try {
            if (radio != null){
                if (radio.equals("Disponible")){
                    categories = microserviceCategorie.findByDispo(true);
                }else if (radio.equals("Indisponible")){
                    categories = microserviceCategorie.findByDispo(false);
                }
            }
            if (categories != null){
                for (Categorie cat:categories){
                    cat.setCountArticle(microserviceArticleProxy
                            .getArticleByCategorieId(cat.getId()).size());
                }
            }
            categorieList = microserviceCategorie.findAll();
            vResult = ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Une erreur est survenu...");
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

    public List<Taille> getTailleList() {
        return tailleList;
    }

    public void setTailleList(List<Taille> tailleList) {
        this.tailleList = tailleList;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public List<String> getRadioList() {
        return radioList;
    }

    public void setRadioList(List<String> radioList) {
        this.radioList = radioList;
    }

    public List<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getCategorieSelect() {
        return categorieSelect;
    }

    public void setCategorieSelect(String categorieSelect) {
        this.categorieSelect = categorieSelect;
    }
}
