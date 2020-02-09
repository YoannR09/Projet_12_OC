package fr.yr.site.alegia.beans;

import java.util.Date;

public class Facture {

    private Commande commande;
    private Integer commandeId;
    private Integer adresseId;
    private Integer numero;
    private String numeroTvaEntreprise;
    private String numeroSiren;
    private Float prixHt;
    private Float prixTtc;
    private Date date;
    private Adresse adresse;

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNumeroTvaEntreprise() {
        return numeroTvaEntreprise;
    }

    public void setNumeroTvaEntreprise(String numeroTvaEntreprise) {
        this.numeroTvaEntreprise = numeroTvaEntreprise;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Integer getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Integer commandeId) {
        this.commandeId = commandeId;
    }

    public Integer getAdresseId() {
        return adresseId;
    }

    public void setAdresseId(Integer adresseId) {
        this.adresseId = adresseId;
    }

    public String getNumeroSiren() {
        return numeroSiren;
    }

    public void setNumeroSiren(String numeroSiren) {
        this.numeroSiren = numeroSiren;
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
