package fr.yr.site.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Facture {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private Integer adresseId;
    private Integer commandeId;
    private String numero;
    private String numeroSiren;
    private String tva;
    private String numeroTvaEntreprise;
    private String prixHt;
    private String prixTtc;

    public Integer getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Integer commandeId) {
        this.commandeId = commandeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdresseId() {
        return adresseId;
    }

    public void setAdresseId(Integer adresseId) {
        this.adresseId = adresseId;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumeroSiren() {
        return numeroSiren;
    }

    public void setNumeroSiren(String numeroSiren) {
        this.numeroSiren = numeroSiren;
    }

    public String getTva() {
        return tva;
    }

    public void setTva(String tva) {
        this.tva = tva;
    }

    public String getNumeroTvaEntreprise() {
        return numeroTvaEntreprise;
    }

    public void setNumeroTvaEntreprise(String numeroTvaEntreprise) {
        this.numeroTvaEntreprise = numeroTvaEntreprise;
    }

    public String getPrixHt() {
        return prixHt;
    }

    public void setPrixHt(String prixHt) {
        this.prixHt = prixHt;
    }

    public String getPrixTtc() {
        return prixTtc;
    }

    public void setPrixTtc(String prixTtc) {
        this.prixTtc = prixTtc;
    }
}
