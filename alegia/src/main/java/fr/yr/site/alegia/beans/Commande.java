package fr.yr.site.alegia.beans;

import java.util.List;

public class Commande {

    private Integer id;
    private List<LigneDeCommande> ligneDeCommandeList;
    private String numero;
    private StatutCommande statutCommande;
    private Integer statutId;


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
}
