package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.*;
import fr.yr.site.alegia.configuration.Factory;
import fr.yr.site.alegia.configuration.GenerateMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.NumberFormat;
import java.util.List;

public class GestionPanierAction extends ActionSupport {

    private static final Logger logger = LogManager.getLogger();
    final NumberFormat instance = NumberFormat.getNumberInstance();

    // Microseervices
    @Autowired
    private Factory factory;

    private GenerateMethod gm = new GenerateMethod();

    private         Integer                 quantite;
    private         Integer                 articleId;
    private         Integer                 contenuId;
    private         Article                 article;
    private         String                  taille;
    private         String                  tva;
    private         String                  totalPayer;
    private         String                  livraison;
    private         Integer                 countArticle;
    private         Integer                 countPanier;
    private         String                  totalPrix;
    private         String                  infoMessage;
    private         List<Contenu>           contenuList;
    private         List<Categorie>         categorieList;
    private         List<ListTaille>        listTailles;
    private         List<Image>             imageList;
    private         Compte                  compte;
    private         Panier                  panier;

    public String doAddPanier(){
        try {
            generateCompteAndPanier();
            List<Contenu> contenuExist = getFactory().getContenuProxy()
                    .findByPanierId(panier.getId());
            boolean existe = false;
            Contenu contenu = new Contenu();
            for (Contenu c:contenuExist){
                if(c.getArticleId() == articleId
                        && c.getTailleId() == getFactory().getTailleProxy()
                        .findByTaille(taille).getId()){
                    existe = true;
                    contenu.setId(c.getId());
                    quantite = quantite+c.getQuantite();
                }
            }
            contenu.setArticleId(articleId);
            contenu.setPanierId(panier.getId());
            contenu.setTailleId(getFactory().getTailleProxy()
                    .findByTaille(taille).getId());
            contenu.setQuantite(quantite);
            article = getFactory().getArticleProxy().getArticle(articleId);
            gm.completeArticle(getFactory(),article);
            if (!existe){
                getFactory().getContenuProxy().add(contenu);
            }else {
                getFactory().getContenuProxy().update(contenu);
            }
            listTailles = getFactory().getListTailleProxy().findByArticleId(articleId);
            imageList = getFactory().getImageProxy().findByArticleId(articleId);
            gm.imageListSizeNull(imageList);
            gm.generateTaille(listTailles,factory);
            countPanier = gm.generateCountPanier(factory,getEmail());
            categorieList = getFactory().getCategorieProxy().findAll();
            infoMessage = "L'article a bien été ajouté à votre panier";
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    public String doConsulterPanier(){
        try{
            instance.setMinimumFractionDigits(2);
            instance.setMaximumFractionDigits(2);
            generateCompteAndPanier();
            contenuList = getFactory().getContenuProxy().findByPanierId(panier.getId());
            float totalContenu = 0;
            countArticle = 0;
            for(Contenu contenu:contenuList){
                contenu.setArticle(getFactory().getArticleProxy().getArticle(contenu.getArticleId()));
                gm.completeArticle(getFactory(),contenu.getArticle());
                contenu.setTaille(getFactory().getTailleProxy().findById(contenu.getTailleId()));
                totalContenu = totalContenu+(contenu.getArticle().getPrix()*contenu.getQuantite());
                countArticle = countArticle+contenu.getQuantite();
            }
            livraison = instance.format(10);
            countPanier = gm.generateCountPanier(factory,getEmail());
            totalPrix = instance.format(totalContenu);
            tva = instance.format((totalContenu*1.1)-totalContenu);
            totalPayer = instance.format((totalContenu*1.1)+10);
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
     * Méthode pour supprimer un contenu d'un panier
     * Récupére le panier du compte actif
     * @return
     */
    public String doDeleteContenu(){
        try {
            factory.getContenuProxy().delete(contenuId);
            infoMessage = "L'article a été retiré du panier";
            generateCompteAndPanier();
            contenuList = getFactory().getContenuProxy().findByPanierId(panier.getId());
            float totalContenu = 0;
            countArticle = 0;
            for(Contenu c:contenuList){
                c.setArticle(getFactory().getArticleProxy().getArticle(c.getArticleId()));
                gm.completeArticle(getFactory(), c.getArticle());
                c.setTaille(getFactory().getTailleProxy().findById(c.getTailleId()));
                totalContenu = totalContenu + (c.getArticle().getPrix() * c.getQuantite());
                countArticle = countArticle + c.getQuantite();
            }
            countPanier = gm.generateCountPanier(factory,getEmail());
            livraison = instance.format(10);
            categorieList = getFactory().getCategorieProxy().findAll();
            totalPrix = Float.toString(totalContenu);
            totalPrix = instance.format(totalContenu);

            tva = instance.format((totalContenu*1.1)-totalContenu);
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    public String doMoinsContenu(){
        try {
            Contenu contenu = getFactory().getContenuProxy().findById(contenuId);
            contenu.setQuantite(contenu.getQuantite() - 1);
            getFactory().getContenuProxy().update(contenu);
            instance.setMinimumFractionDigits(2);
            instance.setMaximumFractionDigits(2);
            generateCompteAndPanier();
            float totalContenu = 0;
            contenuList = getFactory().getContenuProxy().findByPanierId(panier.getId());
            countArticle = 0;
            for (Contenu c : contenuList) {
                c.setArticle(getFactory().getArticleProxy().getArticle(c.getArticleId()));
                gm.completeArticle(getFactory(), c.getArticle());
                c.setTaille(getFactory().getTailleProxy().findById(c.getTailleId()));
                totalContenu = totalContenu + (c.getArticle().getPrix() * c.getQuantite());
                countArticle = countArticle + c.getQuantite();
            }
            countPanier = gm.generateCountPanier(factory, getEmail());
            totalPrix = instance.format(totalContenu);
            livraison = instance.format(10);
            categorieList = getFactory().getCategorieProxy().findAll();
            tva = instance.format((totalContenu * 1.1) - totalContenu);
            totalPayer = instance.format((totalContenu * 1.1) + 10);
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    public String doPlusContenu(){
        try {
            Contenu contenu = getFactory().getContenuProxy().findById(contenuId);
            contenu.setQuantite(contenu.getQuantite() + 1);
            getFactory().getContenuProxy().update(contenu);
            instance.setMaximumFractionDigits(2);
            instance.setMinimumFractionDigits(2);
            generateCompteAndPanier();
            contenuList = getFactory().getContenuProxy().findByPanierId(panier.getId());
            countArticle = 0;
            float totalContenu = 0;
            for (Contenu c : contenuList) {
                c.setArticle(getFactory().getArticleProxy().getArticle(c.getArticleId()));
                gm.completeArticle(getFactory(), c.getArticle());
                c.setTaille(getFactory().getTailleProxy().findById(c.getTailleId()));
                totalContenu = totalContenu + (c.getArticle().getPrix() * c.getQuantite());
                countArticle = countArticle + c.getQuantite();
            }
            totalPrix = instance.format(totalContenu);
            countPanier = gm.generateCountPanier(factory, getEmail());
            livraison = instance.format(10);
            tva = instance.format((totalContenu * 1.1) - totalContenu);
            categorieList = getFactory().getCategorieProxy().findAll();
            totalPayer = instance.format((totalContenu * 1.1) + 10);
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }



    public void generateCompteAndPanier() {
        String email = getEmail();
        compte = getFactory().getCompteProxy().findByEmail(email.toLowerCase());
        panier = getFactory().getPanierProxy().getPanierByCompteId(compte.getId());
    }

    protected String getEmail() {
        return (String) ActionContext.getContext().getSession().get("email");
    }


    protected Logger getLogger() {
        return logger;
    }


    protected Factory getFactory() {
        return factory;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public List<Contenu> getContenuList() {
        return contenuList;
    }

    public void setContenuList(List<Contenu> contenuList) {
        this.contenuList = contenuList;
    }

    public List<Categorie> getCategorieList() {
        return categorieList;
    }

    public void setCategorieList(List<Categorie> categorieList) {
        this.categorieList = categorieList;
    }

    public String getTotalPrix() {
        return totalPrix;
    }

    public void setTotalPrix(String totalPrix) {
        this.totalPrix = totalPrix;
    }

    public Integer getCountArticle() {
        return countArticle;
    }

    public void setCountArticle(Integer countArticle) {
        this.countArticle = countArticle;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    public Integer getContenuId() {
        return contenuId;
    }

    public void setContenuId(Integer contenuId) {
        this.contenuId = contenuId;
    }

    public Integer getCountPanier() {
        return countPanier;
    }

    public void setCountPanier(Integer countPanier) {
        this.countPanier = countPanier;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public String getTva() {
        return tva;
    }

    public void setTva(String tva) {
        this.tva = tva;
    }

    public String getTotalPayer() {
        return totalPayer;
    }

    public void setTotalPayer(String totalPayer) {
        this.totalPayer = totalPayer;
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

    public String getLivraison() {
        return livraison;
    }

    public void setLivraison(String livraison) {
        this.livraison = livraison;
    }
}
