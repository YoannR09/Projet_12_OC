package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.*;
import fr.yr.site.alegia.proxies.*;
import org.apache.commons.io.FileUtils;
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

    @Autowired
    MicroserviceArticleProxy microserviceArticleProxy;
    @Autowired
    MicroserviceListTaille microserviceListTaille;
    @Autowired
    MicroserviceTailleProxy microserviceTailleProxy;
    @Autowired
    MicroserviceImageProxy microserviceImageProxy;
    @Autowired
    MicroserviceCategorie microserviceCategorie;

    private      String      destPath = "C:/Users/El-ra/Documents/Projet_12_OC/alegia/src/main/webapp/image/";

    private Article article;
    private Integer articleId;
    private List<ListTaille> listTailles;
    private List<String>    tailleSelect;
    private List<Taille>    tailleList;
    private List<Article>   articleList;
    private List<Image> imageList;
    private List<Categorie> categorieList;
    private String categorieSelect;
    private String nom;
    private String prixTtc;
    private String prixHt;
    private String description;
    private String radio;
    private List<Taille> tailles;
    private     List<String>            radioList = Arrays.asList("Disponible","Indisponible");

    /**
     * Méthode pour afficher kes détails d'un article sélectionné
     * @return
     */
    public String doDetailArticle(){
        try {
            article = microserviceArticleProxy.getArticle(articleId);
            listTailles = microserviceListTaille.findByArticleId(articleId);
            imageList = microserviceImageProxy.findByArticleId(articleId);
            if (imageList.size() == 0 ){
                Image image = new Image();
                image.setUrl("indisponible.jpg");
                imageList.add(image);
            }
            for(ListTaille lt:listTailles){
                lt.setTaille(microserviceTailleProxy.findById(lt.getTailleId()));
            }
            categorieList = microserviceCategorie.findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            e.printStackTrace();
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour ajouter un article
     * @return
     */
    public String addArticle(){
        try {
            if (nom != null && description != null && description != null
                    && prixHt != null && prixTtc != null && categorieSelect != null){
                Article article = new Article();
                article.setNom(nom);
                article.setDescription(description);
                Integer categorieId = microserviceCategorie.findByNom(categorieSelect).getId();
                article.setCategorieId(categorieId);
                article.setPrixHt(Float.parseFloat(prixHt));
                article.setPrixTtc(Float.parseFloat(prixTtc));
                article.setDisponible(false);
                microserviceArticleProxy.add(article);
                List<Article> vList = microserviceArticleProxy.getArticleByCategorieId(categorieId);
                Article newArticle = vList.get(vList.size()-1);

                // Ajout des tailles
                if(tailleSelect != null){
                    for(String t: tailleSelect){
                        ListTaille lt = new ListTaille();
                        Taille taille = microserviceTailleProxy.findByTaille(t);
                        lt.setTailleId(taille.getId());
                        lt.setArticleId(newArticle.getId());
                        microserviceListTaille.add(lt);
                    }
                }
                // vResult = ActionSupport.SUCCESS;
            }else {
               // vResult = ActionSupport.ERROR;
            }
        }catch (Exception e){
            e.printStackTrace();
            // vResult = ActionSupport.ERROR;
        }
        tailleList = microserviceTailleProxy.findAll();
        categorieList = microserviceCategorie.findAll();
        return ActionSupport.SUCCESS;
    }

    /**
     * Méthode pour rendre disponible un article
     * @return
     */
    public String doDiponible(){
        try {
            Article article = microserviceArticleProxy.getArticle(articleId);
            article.setDisponible(true);
            updateArticle(article,false);
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            categorieList = microserviceCategorie.findAll();
            e.printStackTrace();
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour rendre indisponible un article
     * @return
     */
    public String doIndiponible(){
        try {
            Article article = microserviceArticleProxy.getArticle(articleId);
            article.setDisponible(false);
            updateArticle(article,true);
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            categorieList = microserviceCategorie.findAll();
            e.printStackTrace();
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour supprimer un article
     * @return
     */
    public String doSupprimer(){
        try {
            Article a = microserviceArticleProxy.getArticle(articleId);
            microserviceListTaille.deleteByArticleId(articleId);
            microserviceArticleProxy.delete(articleId);
            if (radio.equals("Disponible")){
                articleList = microserviceArticleProxy.findByCategorieIdAndDisponible(a.getCategorieId(),true);
            }else if(radio.equals("Indisponible")){
                articleList = microserviceArticleProxy.findByCategorieIdAndDisponible(a.getCategorieId(),false);
            }
            completeArticleList(articleList);
            categorieList = microserviceCategorie.findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            categorieList = microserviceCategorie.findAll();
            e.printStackTrace();
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour afficher le formulaire de modification d'un article
     * @return
     */
    public String formModifArticle(){
        try {
            article = microserviceArticleProxy.getArticle(articleId);
            completeArticle(article);
            categorieList = microserviceCategorie.findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            categorieList = microserviceCategorie.findAll();
            e.printStackTrace();
            return ActionSupport.ERROR;
        }
    }

    private void updateArticle(Article article, Boolean dispo) {
        microserviceArticleProxy.update(article);
        articleList = microserviceArticleProxy.findByCategorieIdAndDisponible(article.getCategorieId(),dispo);
        categorieList = microserviceCategorie.findAll();
        completeArticleList(articleList);
    }

    private void completeArticleList(List<Article> vList){
        for (Article a : vList) {
            completeArticle(a);
        }
    }

    private void completeArticle(Article a){
        a.setImageList(microserviceImageProxy.findByArticleId(a.getId()));
        a.setSupprimable(true);
        if (a.getImageList().size() == 0) {
            Image image = new Image();
            image.setUrl("indisponible.jpg");
            a.getImageList().add(image);
        }
    }


    public void ajoutImage(File file,Integer newArticleId) throws IOException {
        File destFile = new File(destPath+ "/", file.getName());
        FileUtils.copyFile(file, destFile);
        Image image = new Image();
        image.setUrl(file.getName());
        image.setArticleId(newArticleId);
        image.setLabelle("Image de l'article à l'id suivant : "+newArticleId);
        microserviceImageProxy.add(image);
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

    public String getPrixTtc() {
        return prixTtc;
    }

    public void setPrixTtc(String prixTtc) {
        this.prixTtc = prixTtc;
    }

    public String getPrixHt() {
        return prixHt;
    }

    public void setPrixHt(String prixHt) {
        this.prixHt = prixHt;
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
}

