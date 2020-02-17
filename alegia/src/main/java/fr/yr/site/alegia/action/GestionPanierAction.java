package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.*;
import fr.yr.site.alegia.configuration.Factory;
import fr.yr.site.alegia.configuration.GenerateMethod;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GestionPanierAction extends ActionSupport {

    // Microseervices
    @Autowired
    Factory factory;

    private GenerateMethod gm = new GenerateMethod();

    private Integer quantite;
    private Integer articleId;
    private Article article;
    private String taille;
    private Integer countArticle;
    private String totalPrix;
    private List<Contenu> contenuList;
    private List<Categorie> categorieList;
    private Compte compte;
    private Panier panier;

    public String doAddPanier(){
        try {
            generateCompteAndPanier();
            List<Contenu> contenuExist = factory.getContenuProxy()
                    .findByPanierId(panier.getId());
            boolean existe = false;
            Contenu contenu = new Contenu();
            for (Contenu c:contenuExist){
                if(c.getArticleId() == articleId
                        && c.getTailleId() == factory.getTailleProxy()
                        .findByTaille(taille).getId()){
                    existe = true;
                    contenu.setId(c.getId());
                    quantite = quantite+c.getQuantite();
                }
            }
            contenu.setArticleId(articleId);
            contenu.setPanierId(panier.getId());
            contenu.setTailleId(factory.getTailleProxy()
                    .findByTaille(taille).getId());
            contenu.setQuantite(quantite);
            article = factory.getArticleProxy().getArticle(articleId);
            gm.completeArticle(factory,article);
            if (!existe){
                factory.getContenuProxy().add(contenu);
            }else {
                factory.getContenuProxy().update(contenu);
            }
            categorieList = factory.getCategorieProxy().findAll();
            this.addActionMessage(" Article ajouté à votre panier");
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = factory.getCategorieProxy().findByDispo(true);
            e.printStackTrace();
            return ActionSupport.ERROR;
        }
    }

    public String doConsulterPanier(){
        try{
            generateCompteAndPanier();
            contenuList = factory.getContenuProxy().findByPanierId(panier.getId());
            float totalContenu = 0;
            countArticle = 0;
            for(Contenu contenu:contenuList){
                contenu.setArticle(factory.getArticleProxy().getArticle(contenu.getArticleId()));
                gm.completeArticle(factory,contenu.getArticle());
                contenu.setTaille(factory.getTailleProxy().findById(contenu.getTailleId()));
                totalContenu = totalContenu+(contenu.getArticle().getPrixTtc()*contenu.getQuantite());
                countArticle = countArticle+contenu.getQuantite();
            }
            totalPrix = Float.toString(totalContenu);
            categorieList = factory.getCategorieProxy().findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = factory.getCategorieProxy().findByDispo(true);
            e.printStackTrace();
            return ActionSupport.ERROR;
        }
    }

    private void generateCompteAndPanier() {
        String email = (String) ActionContext.getContext().getSession().get("email");
        compte = factory.getCompteProxy().findByEmail(email.toLowerCase());
        panier = factory.getPanierProxy().getPanierByCompteId(compte.getId());
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
}
