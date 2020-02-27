package fr.yr.site.alegia.beans;

public class CarteBancaire {

    private Integer id;
    private String numero;
    private Integer moisExpiration;
    private Integer anneeExpiration;
    private Integer compteId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getMoisExpiration() {
        return moisExpiration;
    }

    public void setMoisExpiration(Integer moisExpiration) {
        this.moisExpiration = moisExpiration;
    }

    public Integer getAnneeExpiration() {
        return anneeExpiration;
    }

    public void setAnneeExpiration(Integer anneeExpiration) {
        this.anneeExpiration = anneeExpiration;
    }

    public Integer getCompteId() {
        return compteId;
    }

    public void setCompteId(Integer compteId) {
        this.compteId = compteId;
    }
}
