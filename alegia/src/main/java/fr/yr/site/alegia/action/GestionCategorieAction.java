package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.Article;
import fr.yr.site.alegia.beans.Categorie;
import fr.yr.site.alegia.beans.Image;
import fr.yr.site.alegia.configuration.Factory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Classe qui gère l'affichage du contenu d'une catégories.
 */
public class GestionCategorieAction extends ActionSupport {

    // --- Microservices ---
    @Autowired
    Factory factory;

    private         Integer         categorieId;
    private         List<Article>   articleList;
    private         List<Categorie> categorieList;
    private         String          nom;
    private         String          labelle;

    /**
     * Méthode pour afficher une liste d'articles via l'id d'une catégorie
     * @return
     */
    public String doListArticleByCategorieId(){
        try {
            articleList = factory.getArticleProxy().findByCategorieIdAndDisponible(categorieId,true);
            for (Article article:articleList){
                article.setImageList(factory.getImageProxy().findByArticleId(article.getId()));
                if (article.getImageList().size() == 0 ){
                    Image image = new Image();
                    image.setUrl("indisponible.jpg");
                    article.getImageList().add(image);
                }
            }
            categorieList = factory.getCategorieProxy().findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour afficher la page d'ajout d'une categorie
     * @return
     */
    public String addCategorie(){
        String vResult = null;

        if(nom != null && labelle != null){
            Categorie categorie = new Categorie();
            categorie.setNom(nom);
            categorie.setLabelle(labelle);
            categorie.setDisponible(false);
            if(factory.getCategorieProxy().findByNom(nom) == null){
                factory.getCategorieProxy().add(categorie);
                this.addActionMessage("La catégorie a bien été crée");
                vResult = ActionSupport.SUCCESS;
            }else {
                this.addActionMessage("Ce nom de catégorie est déjà utilisé");
                vResult = ActionSupport.ERROR;
            }
        }
        categorieList = factory.getCategorieProxy().findAll();
        return vResult;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLabelle() {
        return labelle;
    }

    public void setLabelle(String labelle) {
        this.labelle = labelle;
    }
}
