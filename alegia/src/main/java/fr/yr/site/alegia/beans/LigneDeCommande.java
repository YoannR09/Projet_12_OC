package fr.yr.site.alegia.beans;

public class LigneDeCommande {

    private Integer quantite;
    private String designation;
    private String ref;
    private Commande commande;
    private Integer commandeId;
    private String taille;
    private Float montant;
    private String montantHt;
    private Float prixUnit;
    private String prixHt;


    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Float getMontant() {
        return montant;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    public Float getPrixUnit() {
        return prixUnit;
    }

    public void setPrixUnit(Float prixUnit) {
        this.prixUnit = prixUnit;
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

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getMontantHt() {
        return montantHt;
    }

    public void setMontantHt(String montantHt) {
        this.montantHt = montantHt;
    }

    public String getPrixHt() {
        return prixHt;
    }

    public void setPrixHt(String prixHt) {
        this.prixHt = prixHt;
    }
}
