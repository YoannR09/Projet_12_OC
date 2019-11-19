package fr.yr.site.alegia.beans;

import java.util.Date;

public class Facture {

    private Commande commande;
    private Integer numero;
    private String numeroTvaEntreprise;
    private String numeroSIREN;
    private Float prixHT;
    private Float prixTTC;
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

    public String getNumeroSIREN() {
        return numeroSIREN;
    }

    public void setNumeroSIREN(String numeroSIREN) {
        this.numeroSIREN = numeroSIREN;
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
}
