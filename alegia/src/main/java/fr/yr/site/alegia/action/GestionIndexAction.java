package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.Article;
import fr.yr.site.alegia.beans.Categorie;
import fr.yr.site.alegia.beans.Compte;
import fr.yr.site.alegia.beans.Image;
import fr.yr.site.alegia.configuration.Factory;
import fr.yr.site.alegia.configuration.GenerateMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Classe qui gère l'affichage de la page d'acceuil et les pages vitrines.
 */
public class GestionIndexAction extends ActionSupport implements SessionAware, ServletResponseAware, ServletRequestAware {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private     Factory             factory;

    private     Map<String, Object> session;

    private     GenerateMethod      gm = new GenerateMethod();

    private     List<Categorie>     categorieList;
    private     Integer             categorieId;
    private     Integer             countPanier;
    private     List<Article>       articleList;
    private     List<Article>       actuArticleList;
    private     String              token;

    /**
     * Méthode pour afficher la page d'accueil du site
     * @return
     */
    public String accueil(){
        String vResult;
        try {
            categorieList = getFactory().getCategorieProxy().findAll();
            countPanier = gm.generateCountPanier(factory,getEmail());
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
     * Méthode pour afficher la page qui décrit la marque.
     * @return
     */
    public String doConsulterLaMarque(){
        try {
            categorieList = getFactory().getCategorieProxy().findAll();
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
     * Méthode si l'utilisateur cancel sa commande
     * Méthode appelée après la redirection du service Paypal
     * Par sécurité le token de la transaction Paypal est demandé.
     * @return
     */
    public String redirectIndex(){
        try{
            if (token != null){
                for(Cookie c : servletRequest.getCookies()) {
                    if (c.getName().equals("compteEmail")) {
                        Compte compte = getFactory().getCompteProxy().findByEmail(c.getValue());
                        this.session.put("email", compte.getEmail());
                        if (compte.getNiveauAccesId() == 1) {
                            this.session.put("user", compte);
                        } else {
                            this.session.put("admin", compte);
                        }
                        countPanier = gm.generateCountPanier(factory, getEmail());
                    }
                }
            }
            categorieList = getFactory().getCategorieProxy().findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    protected Factory getFactory() {
        return factory;
    }

    protected Logger getLogger() {
        return logger;
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

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public List<Article> getActuArticleList() {
        return actuArticleList;
    }

    public void setActuArticleList(List<Article> actuArticleList) {
        this.actuArticleList = actuArticleList;
    }

    public Integer getCountPanier() {
        return countPanier;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setCountPanier(Integer countPanier) {
        this.countPanier = countPanier;
    }

    protected HttpServletResponse servletResponse;
    @Override
    public void setServletResponse(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }

    protected HttpServletRequest servletRequest;
    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
