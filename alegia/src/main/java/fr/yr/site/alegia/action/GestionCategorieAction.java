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

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe qui gère l'affichage du contenu d'une catégories.
 */
public class GestionCategorieAction extends ActionSupport {


    private static final Logger logger = LogManager.getLogger();

    // --- Microservices ---
    @Autowired
    private Factory factory;

    private GenerateMethod gm = new GenerateMethod();

    private         Integer                 categorieId;
    private         Integer                 countPanier;
    private         List<Article>           articleList;
    private         List<Categorie>         categorieList;
    private         List<Categorie>         categories;
    private         String                  nom;
    private         String                  labelle;
    private         String                  radio;
    private         String                  infoMessage;
    private         Integer                 listSize;
    private         Integer                 countPage;
    private         Integer                 page;
    private         Boolean                 max;
    private         List<String>            radioList = Arrays.asList("Disponible","Indisponible");

    /**
     * Méthode pour afficher une liste d'articles via l'id d'une catégorie
     * @return
     */
    public String doListArticleByCategorieId(){
        try {
            List<Article> vList = getFactory().getArticleProxy()
                    .findByCategorieIdAndDisponible(categorieId,true);
            double resultPage = (double) vList.size()/6;
            countPage = (int) Math.ceil(resultPage);
            listSize = 6;
            articleList = new ArrayList<>();
            if (listSize > vList.size()){
                listSize = vList.size();
                max = true;
            }else {
                max = false;
            }
            for(int i = 0;i<listSize;i++){
                articleList.add(vList.get(i));
            }
            categorieList = getFactory().getCategorieProxy().findAll();
            gm.completeArticleList(getFactory(),articleList);
            if (getEmail() != null){
                countPanier = gm.generateCountPanier(factory
                        ,(String) ActionContext.getContext().getSession().get("email"));
            }
            page = 1;
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    public String doMoinsList(){
        try {
            List<Article> vList = getFactory().getArticleProxy()
                    .findByCategorieIdAndDisponible(categorieId,true);
            articleList = new ArrayList<>();
            int oldListSize = listSize;
            listSize -= 12;
            double resultPage = (double) vList.size()/6;
            countPage = (int) Math.ceil(resultPage);
            for(int i = listSize;i<oldListSize-6;i++){
                articleList.add(vList.get(i));
            }
            listSize += 6;
            page -= 1;
            gm.completeArticleList(getFactory(),articleList);
            categorieList = getFactory().getCategorieProxy().findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    public String doPlusList(){
        try {
            List<Article> vList = getFactory().getArticleProxy()
                    .findByCategorieIdAndDisponible(categorieId,true);
            articleList = new ArrayList<>();
            int oldListSize = listSize;
            listSize += 6;
            if (listSize > vList.size()){
                listSize = vList.size();
                max = true;
            }else {
                max = false;
            }
            page += 1;
            double resultPage = (double) vList.size()/6;
            countPage = (int) Math.ceil(resultPage);
            for(int i = oldListSize;i<listSize;i++){
                articleList.add(vList.get(i));
            }
            if (max){
                listSize = oldListSize+6;
            }
            categorieList = getFactory().getCategorieProxy().findAll();
            gm.completeArticleList(getFactory(),articleList);
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour afficher la page d'ajout d'une categorie
     * @return
     */
    public String addCategorie(){
        try {
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            countPanier = gm.generateCountPanier(factory,getEmail());
            if (nom != null && labelle != null) {
                Categorie categorie = new Categorie();
                categorie.setNom(nom);
                categorie.setLabelle(labelle);
                categorie.setDisponible(false);
                if (getFactory().getCategorieProxy().findByNom(nom) == null) {
                    getFactory().getCategorieProxy().add(categorie);
                    infoMessage = "La catégorie a bien été créée";
                    return ActionSupport.SUCCESS;
                } else {
                    infoMessage = "Ce nom de catégorie est déjà utilisé";
                    return ActionSupport.INPUT;
                }
            }else {
                this.addActionMessage("Un problème est survenu... ");
                return ActionSupport.ERROR;
            }
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            getLogger().error(e);
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour définir une catégorie comme disponible
     * @return
     */
    public String doDisponible(){
        try {
            Categorie categorie = getFactory().getCategorieProxy().findById(categorieId);
            categorie.setDisponible(true);
            getFactory().getCategorieProxy().update(categorie);
            countPanier = gm.generateCountPanier(factory,getEmail());
            categories = getFactory().getCategorieProxy().findByDispo(false);
            generateCountArticle(categories);
            categorieList = getFactory().getCategorieProxy().findAll();
            infoMessage = " La catégorie est maintenant disponible ";
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            getLogger().error(e);
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour définir une catégorie comme indisponible
     * @return
     */
    public String doIndisponible(){
        try {
            Categorie categorie = getFactory().getCategorieProxy().findById(categorieId);
            categorie.setDisponible(false);
            getFactory().getCategorieProxy().update(categorie);
            countPanier = gm.generateCountPanier(factory,getEmail());
            categories = getFactory().getCategorieProxy().findByDispo(true);
            generateCountArticle(categories);
            categorieList = getFactory().getCategorieProxy().findAll();
            infoMessage = "La catégorie est maintenant indisponible";
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            getLogger().error(e);
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour supprimer une catégorie ne contenant pas d'article
     * @return
     */
    public String doSupprimer(){
        try {
            getFactory().getCategorieProxy().delete(categorieId);
            countPanier = gm.generateCountPanier(factory,getEmail());
            categories = getFactory().getCategorieProxy().findByDispo(true);
            infoMessage = "La catégorie a été supprimée";
            categorieList = getFactory().getCategorieProxy().findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            getLogger().error(e);
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            return ActionSupport.ERROR;
        }
    }

    private void generateCountArticle(List<Categorie> vList){
        for (Categorie cat:vList){
            cat.setCountArticle(getFactory().getArticleProxy()
                    .getArticleByCategorieId(cat.getId()).size());
        }
    }

    protected Logger getLogger() {
        return logger;
    }

    protected Factory getFactory() {
        return factory;
    }

    protected String getEmail() {
        return (String) ActionContext.getContext().getSession().get("email");
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

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    public Integer getCountPanier() {
        return countPanier;
    }

    public void setCountPanier(Integer countPanier) {
        this.countPanier = countPanier;
    }

    public Integer getListSize() {
        return listSize;
    }

    public void setListSize(Integer listSize) {
        this.listSize = listSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Boolean getMax() {
        return max;
    }

    public void setMax(Boolean max) {
        this.max = max;
    }

    public Integer getCountPage() {
        return countPage;
    }

    public void setCountPage(Integer countPage) {
        this.countPage = countPage;
    }
}
