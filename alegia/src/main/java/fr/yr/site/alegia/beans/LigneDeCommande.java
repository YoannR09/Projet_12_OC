package fr.yr.site.alegia.beans;

public class LigneDeCommande {

    private Integer quantite;
    private String designation;
    private Commande commande;
    private Integer commandeId;
    private String taille;
    private Float montantHt;
    private Float montantTtc;
    private Float prixUnitHt;
    private Float prixUnitTtc;


    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
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

    public Float getMontantHt() {
        return montantHt;
    }

    public void setMontantHt(Float montantHt) {
        this.montantHt = montantHt;
    }

    public Float getMontantTtc() {
        return montantTtc;
    }

    public void setMontantTtc(Float montantTtc) {
        this.montantTtc = montantTtc;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Integer getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Integer commandeId) {
        this.commandeId = commandeId;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }
}
