package fr.yr.site.alegia.beans;

import java.util.List;

public class Article {

    private String nom;
    private String description;
    private String reference;
    private String prixAffichage;
    private Integer id;
    private Integer categorieId;
    private Float prix;
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

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getPrixAffichage() {
        return prixAffichage;
    }

    public void setPrixAffichage(String prixAffichage) {
        this.prixAffichage = prixAffichage;
    }
}
