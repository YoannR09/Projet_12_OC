package fr.yr.site.alegia.beans;

import java.util.List;

public class Panier {

    private Integer id;
    private List<Article> articleList;
    private Compte compte;
    private Integer compteId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public Integer getCompteId() {
        return compteId;
    }

    public void setCompteId(Integer compteId) {
        this.compteId = compteId;
    }
}
