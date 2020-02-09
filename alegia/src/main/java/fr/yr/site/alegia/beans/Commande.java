package fr.yr.site.alegia.beans;

import java.util.Date;
import java.util.List;

public class Commande {

    private Integer id;
    private Date date;
    private String statut;
    private List<LigneDeCommande> ligneDeCommandeList;
    private Adresse adresse;
    private String numero;
    private StatutCommande statutCommande;
    private Integer compteId;
    private Integer statutId;
    private Integer adresseId;
    private String  prixTotal;
    private Integer countArticle;

    public Integer getCountArticle() {
        return countArticle;
    }

    public void setCountArticle(Integer countArticle) {
        this.countArticle = countArticle;
    }

    public String getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(String prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getAdresseId() {
        return adresseId;
    }

    public void setAdresseId(Integer adresseId) {
        this.adresseId = adresseId;
    }

    public Integer getCompteId() {
        return compteId;
    }

    public void setCompteId(Integer compteId) {
        this.compteId = compteId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<LigneDeCommande> getLigneDeCommandeList() {
        return ligneDeCommandeList;
    }

    public void setLigneDeCommandeList(List<LigneDeCommande> ligneDeCommandeList) {
        this.ligneDeCommandeList = ligneDeCommandeList;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public StatutCommande getStatutCommande() {
        return statutCommande;
    }

    public void setStatutCommande(StatutCommande statutCommande) {
        this.statutCommande = statutCommande;
    }

    public Integer getStatutId() {
        return statutId;
    }

    public void setStatutId(Integer statutId) {
        this.statutId = statutId;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String generateStatut(){
        if(this.statutId == 1){
            this.statut = "EN ATTENTE DE VALIDATION";
        }else if (this.statutId == 2){
            this.statut = "EN COURS DE PREPARATION";
        }else if (this.statutId == 3){
            this.statut = "EXPEDIEE";
        }else if (this.statutId == 4){
            this.statut = "ACHEVEE";
        }
        return this.statut;
    }
}
