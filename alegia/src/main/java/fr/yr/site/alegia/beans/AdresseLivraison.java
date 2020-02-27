package fr.yr.site.alegia.beans;

public class AdresseLivraison {

    private Integer id;
    private Integer adresseId;
    private Adresse adresse;
    private Integer compteId;

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

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Integer getCompteId() {
        return compteId;
    }

    public void setCompteId(Integer compteId) {
        this.compteId = compteId;
    }
}
