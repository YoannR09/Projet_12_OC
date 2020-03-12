package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.Article;
import fr.yr.site.alegia.beans.Categorie;
import fr.yr.site.alegia.beans.Image;
import fr.yr.site.alegia.beans.Taille;
import fr.yr.site.alegia.configuration.Factory;
import fr.yr.site.alegia.configuration.GenerateMethod;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Part;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Classe qui permet la gestion du site via le role d'administrateur.
 */
public class GestionSiteAction extends ActionSupport {

    private static final Logger logger = LogManager.getLogger();

    // Microseervices
    @Autowired
    private Factory factory;

    private     GenerateMethod          gm = new GenerateMethod();

    private         List<Categorie>         categorieList;
    private         List<Categorie>         categories;
    private         List<Article>           articleList;
    private         List<Taille>            tailleList;
    private         Article                 article;
    private         Integer                 categorieId;
    private         Integer                 articleId;
    private         Integer                 imageId;
    private         Integer                 countPanier;
    private         String                  categorieSelect;
    private         File                    myFile;
    private         String                  myFileContentType;
    private         String                  myFileFileName;
    private         String                  descriptionImage;
    private         String                  radio;
    private         String                  infoMessage;
    private         Part                    ficher;
    private         List<String>            radioList = Arrays.asList("Disponible","Indisponible");
    private         String                  filePath = "C:/Users/El-ra/Documents" +
                                                        "/Projet_12_OC/alegia/src/main/webapp/image/";


    /**
     * Méthode pour permet de récupérer la liste des catégories
     * Quand l'administrateur consulte les pages de gestions
     * @return
     */
    public String doGestion(){
        String vResult;
        try {
            countPanier = gm.generateCountPanier(factory,getEmail());
            categorieList = getFactory().getCategorieProxy().findAll();
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
     * Méthode pour afficher le formulaire pour créer un article
     * @return
     */
    public String formAddArticle(){
        String vResult;
        try {
            countPanier = gm.generateCountPanier(factory,getEmail());
            tailleList = getFactory().getTailleProxy().findAll();
            categorieList = getFactory().getCategorieProxy().findAll();
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
     * Méthode pour gérer les articles.
     * Affiche le formulaire dans un premier temps
     * Affiche enssuite une liste d'articles à gérer
     * @return
     */
    public String gestionArticle(){
        String vResult;
        try {
            countPanier = gm.generateCountPanier(factory,getEmail());
            if (radio != null){
                Categorie categorie = getFactory().getCategorieProxy().findByNom(categorieSelect);
                if (radio.equals("Disponible")){
                    articleList = getFactory().getArticleProxy()
                            .findByCategorieIdAndDisponible(categorie.getId(),true);
                }else if (radio.equals("Indisponible")){
                    articleList = getFactory().getArticleProxy()
                            .findByCategorieIdAndDisponible(categorie.getId(),false);
                }
                for (Article article:articleList){
                    gm.completeArticle(getFactory(),article);
                }
            }
            categorieList = getFactory().getCategorieProxy().findAll();
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
     * Méthode pour la gestion d'une catégorie
     * Affiche une liste de catégories disponibles ou indisponible
     * En focntion du choix de l'administrateur
     * @return
     */
    public String gestionCategorie(){
        String vResult;
        try {
            countPanier = gm.generateCountPanier(factory,getEmail());
            if (radio != null){
                if (radio.equals("Disponible")){
                    categories = getFactory().getCategorieProxy().findByDispo(true);
                }else if (radio.equals("Indisponible")){
                    categories = getFactory().getCategorieProxy().findByDispo(false);
                }
            }
            if (categories != null){
                for (Categorie cat:categories){
                    cat.setCountArticle(getFactory().getArticleProxy()
                            .getArticleByCategorieId(cat.getId()).size());
                }
            }
            categorieList = getFactory().getCategorieProxy().findAll();
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
     * Méthode pour ajouter une image à un article.
     * @return
     */
    public String doAjoutImage(){
        try {
            File destFile = new File(filePath+ "/", myFileFileName);
            FileUtils.copyFile(myFile, destFile);
            Image image = new Image();
            image.setUrl(myFileFileName);
            image.setArticleId(articleId);
            image.setLabelle("Image de l'article à l'id suivant : " + articleId);
            getFactory().getImageProxy().add(image);
            article = getFactory().getArticleProxy().getArticle(articleId);
            gm.completeArticle(getFactory(),article);
            infoMessage = "L'image a bien été ajoutée à la base de données";
            countPanier = gm.generateCountPanier(factory,getEmail());
            categorieList = getFactory().getCategorieProxy().findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour supprimer une image.
     * Actuellement supprime seulement le lien dans la bdd
     * @return
     */
    public String doDeleteImage(){
        try {
            getFactory().getImageProxy().delete(imageId);
            article = getFactory().getArticleProxy().getArticle(articleId);
            gm.completeArticle(getFactory(),article);
            categorieList = getFactory().getCategorieProxy().findAll();
            countPanier = gm.generateCountPanier(factory,getEmail());
            infoMessage = "L'image a bien été supprimée de la base de données";
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            return ActionSupport.ERROR;
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

    public File getMyFile() {
        return myFile;
    }

    public void setMyFile(File myFile) {
        this.myFile = myFile;
    }

    public String getMyFileContentType() {
        return myFileContentType;
    }

    public void setMyFileContentType(String myFileContentType) {
        this.myFileContentType = myFileContentType;
    }

    public String getMyFileFileName() {
        return myFileFileName;
    }

    public void setMyFileFileName(String myFileFileName) {
        this.myFileFileName = myFileFileName;
    }

    public Integer getCountPanier() {
        return countPanier;
    }

    public void setCountPanier(Integer countPanier) {
        this.countPanier = countPanier;
    }

    public Part getFicher() {
        return ficher;
    }

    public void setFicher(Part ficher) {
        this.ficher = ficher;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }
}
