package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.Article;
import fr.yr.site.alegia.beans.Categorie;
import fr.yr.site.alegia.beans.Image;
import fr.yr.site.alegia.beans.Taille;
import fr.yr.site.alegia.configuration.Factory;
import fr.yr.site.alegia.configuration.GenerateMethod;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class GestionSiteAction extends ActionSupport {

    // Microseervices
    @Autowired
    Factory factory;

    private     GenerateMethod          gm = new GenerateMethod();

    private     List<Categorie>         categorieList;
    private     List<Categorie>         categories;
    private     List<Article>           articleList;
    private     List<Taille>            tailleList;
    private     Article                 article;
    private     Integer                 categorieId;
    private     Integer                 articleId;
    private     String                  categorieSelect;
    private     File                    file;
    private     String                  contentType;
    private     String                  filename;
    private     String                  descriptionImage;
    private     String                  radio;
    private     List<String>            radioList = Arrays.asList("Disponible","Indisponible");
    private     String                  filePath = "C:/Users/El-ra/Documents" +
            "/Projet_12_OC/alegia/src/main/webapp/image/";


    /**
     * Méthode pour permet de récupérer la liste des catégories
     * Quand l'administrateur consulte les pages de gestions
     * @return
     */
    public String doGestion(){
        String vResult;
        try {
            categorieList = factory.getCategorieProxy().findAll();
            vResult = ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = factory.getCategorieProxy().findByDispo(true);
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
            tailleList = factory.getTailleProxy().findAll();
            categorieList = factory.getCategorieProxy().findAll();
            vResult = ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = factory.getCategorieProxy().findByDispo(true);
            e.printStackTrace();
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
                Categorie categorie = factory.getCategorieProxy().findByNom(categorieSelect);
                if (radio.equals("Disponible")){
                    articleList = factory.getArticleProxy()
                            .findByCategorieIdAndDisponible(categorie.getId(),true);
                }else if (radio.equals("Indisponible")){
                    articleList = factory.getArticleProxy()
                            .findByCategorieIdAndDisponible(categorie.getId(),false);
                }
                for (Article article:articleList){
                    gm.completeArticle(factory,article);
                }
            }
            categorieList = factory.getCategorieProxy().findAll();
            vResult = ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = factory.getCategorieProxy().findByDispo(true);
            vResult = ActionSupport.ERROR;
        }
        return vResult;
    }

    /**
     * Méthode pour la gestion d'une catégorie
     * Affiche une liste de catégories disponibles ou indisponible
     * En focntion du choix de l'administrateur
     * @return
     */
    public String gestionCategorie(){
        String vResult;
        try {
            if (radio != null){
                if (radio.equals("Disponible")){
                    categories = factory.getCategorieProxy().findByDispo(true);
                }else if (radio.equals("Indisponible")){
                    categories = factory.getCategorieProxy().findByDispo(false);
                }
            }
            if (categories != null){
                for (Categorie cat:categories){
                    cat.setCountArticle(factory.getArticleProxy()
                            .getArticleByCategorieId(cat.getId()).size());
                }
            }
            categorieList = factory.getCategorieProxy().findAll();
            vResult = ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = factory.getCategorieProxy().findByDispo(true);
            vResult = ActionSupport.ERROR;
        }
        return vResult;
    }

    public String doAjoutImage() {
        try {
            File fileToCreate = new File(filePath,file.getName());
            FileUtils.copyFile(file, fileToCreate);
            Image image = new Image();
            image.setUrl(file.getName());
            image.setArticleId(articleId);
            image.setLabelle("Image de l'article à l'id suivant : " + articleId);
            factory.getImageProxy().add(image);
            article = factory.getArticleProxy().getArticle(articleId);
            gm.completeArticle(factory,article);
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = factory.getCategorieProxy().findByDispo(true);
            e.printStackTrace();
            return ActionSupport.ERROR;
        }
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

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getDescriptionImage() {
        return descriptionImage;
    }

    public void setDescriptionImage(String descriptionImage) {
        this.descriptionImage = descriptionImage;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
