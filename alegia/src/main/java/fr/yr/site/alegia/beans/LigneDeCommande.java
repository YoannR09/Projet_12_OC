package fr.yr.site.alegia.beans;

public class LigneDeCommande {

    private Article article;
    private Panier panier;
    private Integer quantite;
    private Float prixHT;
    private Float prixTTC;
    private String designation;
    private Commande commande;
    private Integer commandeId;
    private Integer panierId;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Float getPrixHT() {
        return prixHT;
    }

    public void setPrixHT(Float prixHT) {
        this.prixHT = prixHT;
    }

    public Float getPrixTTC() {
        return prixTTC;
    }

    public void setPrixTTC(Float prixTTC) {
        this.prixTTC = prixTTC;
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

    public Integer getPanierId() {
        return panierId;
    }

    public void setPanierId(Integer panierId) {
        this.panierId = panierId;
    }
}
