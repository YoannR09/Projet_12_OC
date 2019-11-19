package fr.yr.site.model;

public class Compte {

    private Integer id;
    private String email;
    private String motDePasse;
    private String nom;
    private String prenom;
    private String numeroTelephone;
    private Integer adresseId;
    private Integer niveauAccesId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public Integer getAdresseId() {
        return adresseId;
    }

    public void setAdresseId(Integer adresseId) {
        this.adresseId = adresseId;
    }

    public Integer getNiveauAccesId() {
        return niveauAccesId;
    }

    public void setNiveauAccesId(Integer niveauAccesId) {
        this.niveauAccesId = niveauAccesId;
    }
}
