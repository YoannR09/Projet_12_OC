package fr.yr.site.alegia.beans;

import java.util.List;

public class Article {

    private String nom;
    private String description;
    private Integer id;
    private Integer categorieId;
    private Float prixHt;
    private Float prixTtc;
    private Boolean disponible;
    private Boolean supprimable;
    private List<Image> imageList;

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

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Boolean getSupprimable() {
        return supprimable;
    }

    public void setSupprimable(Boolean supprimable) {
        this.supprimable = supprimable;
    }
}
