package fr.yr.site.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LigneDeCommande {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private Integer commandeId;
    private String designation;
    private Float prixUnitHt;
    private Float prixUnitTtc;
    private Float montantTtc;
    private Float montantHt;
    private Integer quantite;
    private String taille;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Integer commandeId) {
        this.commandeId = commandeId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Float getPrixUnitHt() {
        return prixUnitHt;
    }

    public void setPrixUnitHt(Float prixUnitHt) {
        this.prixUnitHt = prixUnitHt;
    }

    public Float getPrixUnitTtc() {
        return prixUnitTtc;
    }

    public void setPrixUnitTtc(Float prixUnitTtc) {
        this.prixUnitTtc = prixUnitTtc;
    }

    public Float getMontantTtc() {
        return montantTtc;
    }

    public void setMontantTtc(Float montantTtc) {
        this.montantTtc = montantTtc;
    }

    public Float getMontantHt() {
        return montantHt;
    }

    public void setMontantHt(Float montantHt) {
        this.montantHt = montantHt;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }
}
