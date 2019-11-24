package fr.yr.site.alegia.beans;


public class ListTaille {

    private Integer id;
    private Integer articleId;
    private Integer tailleId;
    private Taille  taille;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getTailleId() {
        return tailleId;
    }

    public void setTailleId(Integer tailleId) {
        this.tailleId = tailleId;
    }

    public Taille getTaille() {
        return taille;
    }

    public void setTaille(Taille taille) {
        this.taille = taille;
    }
}
