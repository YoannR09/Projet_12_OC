package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.*;
import fr.yr.site.alegia.configuration.Factory;
import fr.yr.site.alegia.configuration.GenerateMethod;
import fr.yr.site.alegia.proxies.*;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Classe qui gère l'affichage pour les articles
 * Affichage des détails d'un article.
 */
public class GestionArticleAction extends ActionSupport {

    private static final Logger logger = LogManager.getLogger();

    // --- Microservices ---
    @Autowired
    private Factory factory;

    private         GenerateMethod      gm = new GenerateMethod();


    private         Article             article;
    private         Integer             articleId;
    private         Integer             countPanier;
    private         List<ListTaille>    listTailles;
    private         List<String>        tailleSelect;
    private         List<Taille>        tailleList;
    private         List<Article>       articleList;
    private         List<Image>         imageList;
    private         List<Categorie>     categorieList;
    private         String              categorieSelect;
    private         String              nom;
    private         String              prix;
    private         String              description;
    private         String              radio;
    private         String              reference;
    private         String              infoMessage;
    private         List<Taille>        tailles;
    private         List<String>        radioList = Arrays.asList("Disponible","Indisponible");

    /**
     * Méthode pour afficher kes détails d'un article sélectionné
     * @return
     */
    public String doDetailArticle(){
        try {
            article = getFactory().getArticleProxy().getArticle(articleId);
            listTailles = getFactory().getListTailleProxy().findByArticleId(articleId);
            imageList = getFactory().getImageProxy().findByArticleId(articleId);
            gm.imageListSizeNull(imageList);
            gm.generateTaille(listTailles,factory);
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            countPanier = gm.generateCountPanier(factory,getEmail());
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour ajouter un article
     * Ajout de la liste de tailles de l'article dans la bdd
     * @return
     */
    public String addArticle(){
        String vResult;
        try {
            if (nom != null && description != null && description != null
                    && prix != null && categorieSelect != null){
                Article article = new Article();
                article.setNom(nom);
                article.setDescription(description);
                Integer categorieId = getFactory().getCategorieProxy().findByNom(categorieSelect).getId();
                article.setCategorieId(categorieId);
                article.setPrix(Float.parseFloat(prix));
                article.setDisponible(false);
                article.setReference(reference);
                getFactory().getArticleProxy().add(article);
                List<Article> vList = getFactory().getArticleProxy().getArticleByCategorieId(categorieId);
                Article newArticle = vList.get(vList.size()-1);

                // Ajout des tailles
                if(tailleSelect != null){
                    for(String t: tailleSelect){
                        ListTaille lt = new ListTaille();
                        Taille taille = getFactory().getTailleProxy().findByTaille(t);
                        lt.setTailleId(taille.getId());
                        lt.setArticleId(newArticle.getId());
                        getFactory().getListTailleProxy().add(lt);
                    }
                    countPanier = gm.generateCountPanier(factory,getEmail());
                    infoMessage = " L'article a bien été créé ";
                    vResult = ActionSupport.SUCCESS;
                }else {
                    this.addActionMessage(" Vous devez choisir des tailles ");
                    if (getEmail() != null){
                        countPanier = gm.generateCountPanier(factory
                                ,(String) ActionContext.getContext().getSession().get("email"));
                    }
                    vResult = ActionSupport.ERROR;
                }
            }else {
                this.addActionMessage("Un problème est survenu... ");
                categorieList = getFactory().getCategorieProxy().findByDispo(true);
               vResult = ActionSupport.ERROR;
            }
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            vResult = ActionSupport.ERROR;
        }
        tailleList = getFactory().getTailleProxy().findAll();
        categorieList = getFactory().getCategorieProxy().findByDispo(true);
        return vResult;
    }

    /**
     * Méthode pour rendre disponible un article
     * @return
     */
    public String doDiponible(){
        try {
            Article article = getFactory().getArticleProxy().getArticle(articleId);
            article.setDisponible(true);
            updateArticle(article,false);
            infoMessage = "L'article est maintenant disponible";
            countPanier = gm.generateCountPanier(factory,getEmail());
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour rendre indisponible un article
     * @return
     */
    public String doIndiponible(){
        try {
            Article article = getFactory().getArticleProxy().getArticle(articleId);
            article.setDisponible(false);
            updateArticle(article,true);
            infoMessage = "L'article est maintenant indisponible";
            countPanier = gm.generateCountPanier(factory,getEmail());
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour supprimer un article
     * @return
     */
    public String doSupprimer(){
        try {
            Article a = getFactory().getArticleProxy().getArticle(articleId);
            getFactory().getListTailleProxy().deleteByArticleId(articleId);
            getFactory().getArticleProxy().delete(articleId);
            if (radio.equals("Disponible")){
                articleList = getFactory().getArticleProxy()
                        .findByCategorieIdAndDisponible(a.getCategorieId(),true);
            }else if(radio.equals("Indisponible")){
                articleList = getFactory().getArticleProxy()
                        .findByCategorieIdAndDisponible(a.getCategorieId(),false);
            }
            countPanier = gm.generateCountPanier(factory,getEmail());
            completeArticleList(articleList);
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour modifier la liste des tailles d'un article.
     * @return
     */
    public String doModifListTaille(){
        try {
            if (tailleSelect != null) {
                if (tailleSelect.size() != 0) {
                    getFactory().getListTailleProxy().deleteByArticleId(articleId);
                    for (String t : tailleSelect) {
                        ListTaille lt = new ListTaille();
                        Taille taille = getFactory().getTailleProxy().findByTaille(t);
                        lt.setTailleId(taille.getId());
                        lt.setArticleId(articleId);
                        getFactory().getListTailleProxy().add(lt);
                        infoMessage = "La liste de tailles a bien été modifiée";
                    }
                }else {
                    infoMessage = "Vous devez sélectionner au moins une taille";
                }
            }
            article = getFactory().getArticleProxy().getArticle(articleId);
            gm.completeArticle(getFactory(),article);
            listTailles = getFactory().getListTailleProxy().findByArticleId(article.getId());
            tailleList = getFactory().getTailleProxy().findAll();
            for (ListTaille lt : listTailles){
                lt.setTaille(getFactory().getTailleProxy().findById(lt.getTailleId()));
            }
            countPanier = gm.generateCountPanier(factory,getEmail());
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour afficher le formulaire de modification d'un article
     * @return
     */
    public String formModifArticle(){
        try {
            tailleList = getFactory().getTailleProxy().findAll();
            article = getFactory().getArticleProxy().getArticle(articleId);
            listTailles = getFactory().getListTailleProxy().findByArticleId(article.getId());
            for (ListTaille lt : listTailles){
                lt.setTaille(getFactory().getTailleProxy().findById(lt.getTailleId()));
            }
            gm.completeArticle(getFactory(),article);
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            countPanier = gm.generateCountPanier(factory,getEmail());
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }


    /**
     * Méthode pour modifier les informations d'un article déjà existant
     * @return
     */
    public String doModifArticle(){
        try {
            article = factory.getArticleProxy().getArticle(articleId);
            article.setNom(nom);
            article.setDescription(description);
            article.setPrix(Float.parseFloat(prix));
            getFactory().getArticleProxy().update(article);
            article = getFactory().getArticleProxy().getArticle(articleId);
            countPanier = gm.generateCountPanier(factory,getEmail());
            gm.completeArticle(getFactory(),article);
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            tailleList = getFactory().getTailleProxy().findAll();
            infoMessage = "L'article a bien été modifié";
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour mettre à jour un article dans la base de données.
     * @param article
     * @param dispo
     */
    private void updateArticle(Article article, Boolean dispo) {
        getFactory().getArticleProxy().update(article);
        articleList = getFactory().getArticleProxy()
                .findByCategorieIdAndDisponible(article.getCategorieId(),dispo);
        completeArticleList(articleList);
    }

    private void completeArticleList(List<Article> vList){
        for (Article a : vList) {
            gm.completeArticle(getFactory(),a);
        }
    }

    protected String getEmail() {
        return (String) ActionContext.getContext().getSession().get("email");
    }


    protected Factory getFactory() {
        return factory;
    }

    protected Logger getLogger() {
        return logger;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public List<ListTaille> getListTailles() {
        return listTailles;
    }

    public void setListTailles(List<ListTaille> listTailles) {
        this.listTailles = listTailles;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public List<Categorie> getCategorieList() {
        return categorieList;
    }

    public void setCategorieList(List<Categorie> categorieList) {
        this.categorieList = categorieList;
    }

    public List<Taille> getTailleList() {
        return tailleList;
    }

    public void setTailleList(List<Taille> tailleList) {
        this.tailleList = tailleList;
    }

    public String getCategorieSelect() {
        return categorieSelect;
    }

    public void setCategorieSelect(String categorieSelect) {
        this.categorieSelect = categorieSelect;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Taille> getTailles() {
        return tailles;
    }

    public void setTailles(List<Taille> tailles) {
        this.tailles = tailles;
    }

    public List<String> getTailleSelect() {
        return tailleSelect;
    }

    public void setTailleSelect(List<String> tailleSelect) {
        this.tailleSelect = tailleSelect;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public List<String> getRadioList() {
        return radioList;
    }

    public void setRadioList(List<String> radioList) {
        this.radioList = radioList;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
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
}

