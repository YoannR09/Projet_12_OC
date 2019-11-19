package fr.yr.site.alegia.beans;

public class Article {

    private String nom;
    private Integer id;
    private Integer categorieId;
    private Float prixHt;
    private Float prixTtc;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Integer categorieId) {
        this.categorieId = categorieId;
    }

    public Float getPrixHt() {
        return prixHt;
    }

    public void setPrixHt(Float prixHt) {
        this.prixHt = prixHt;
    }

    public Float getPrixTtc() {
        return prixTtc;
    }

    public void setPrixTtc(Float prixTtc) {
        this.prixTtc = prixTtc;
    }
}
