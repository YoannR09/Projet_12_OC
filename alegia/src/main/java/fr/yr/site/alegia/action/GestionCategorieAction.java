package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.Article;
import fr.yr.site.alegia.beans.Categorie;
import fr.yr.site.alegia.beans.Image;
import fr.yr.site.alegia.configuration.Factory;
import fr.yr.site.alegia.configuration.GenerateMethod;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * Classe qui gère l'affichage du contenu d'une catégories.
 */
public class GestionCategorieAction extends ActionSupport {

    // --- Microservices ---
    @Autowired
    Factory factory;

    private GenerateMethod gm = new GenerateMethod();

    private         Integer                 categorieId;
    private         List<Article>           articleList;
    private         List<Categorie>         categorieList;
    private         List<Categorie>         categories;
    private         String                  nom;
    private         String                  labelle;
    private         String                  radio;
    private         List<String>            radioList = Arrays.asList("Disponible","Indisponible");

    /**
     * Méthode pour afficher une liste d'articles via l'id d'une catégorie
     * @return
     */
    public String doListArticleByCategorieId(){
        try {
            articleList = factory.getArticleProxy()
                    .findByCategorieIdAndDisponible(categorieId,true);
            gm.completeArticleList(factory,articleList);
            categorieList = factory.getCategorieProxy().findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = factory.getCategorieProxy().findByDispo(true);
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour afficher la page d'ajout d'une categorie
     * @return
     */
    public String addCategorie(){
        try {
            categorieList = factory.getCategorieProxy().findByDispo(true);
            if (nom != null && labelle != null) {
                Categorie categorie = new Categorie();
                categorie.setNom(nom);
                categorie.setLabelle(labelle);
                categorie.setDisponible(false);
                if (factory.getCategorieProxy().findByNom(nom) == null) {
                    factory.getCategorieProxy().add(categorie);
                    this.addActionMessage("La catégorie a bien été crée");
                    return ActionSupport.SUCCESS;
                } else {
                    this.addActionMessage("Ce nom de catégorie est déjà utilisé");
                    return ActionSupport.INPUT;
                }
            }else {
                this.addActionMessage("Un problème est survenu... ");
                return ActionSupport.ERROR;
            }
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = factory.getCategorieProxy().findByDispo(true);
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour définir une catégorie comme disponible
     * @return
     */
    public String doDisponible(){
        try {
            Categorie categorie = factory.getCategorieProxy().findById(categorieId);
            categorie.setDisponible(true);
            factory.getCategorieProxy().update(categorie);
            categories = factory.getCategorieProxy().findByDispo(false);
            categorieList = factory.getCategorieProxy().findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = factory.getCategorieProxy().findByDispo(true);
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour définir une catégorie comme indisponible
     * @return
     */
    public String doIndisponible(){
        try {
            Categorie categorie = factory.getCategorieProxy().findById(categorieId);
            categorie.setDisponible(false);
            factory.getCategorieProxy().update(categorie);
            categories = factory.getCategorieProxy().findByDispo(true);
            categorieList = factory.getCategorieProxy().findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = factory.getCategorieProxy().findByDispo(true);
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

    public List<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
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
}
