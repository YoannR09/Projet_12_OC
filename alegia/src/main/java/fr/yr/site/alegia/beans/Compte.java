package fr.yr.site.alegia.beans;

public class Compte {

    private NiveauAcces niveauAcces;
    private String email;
    private Integer id;
    private String motDePasse;
    private String nom;
    private String prenom;
    private String numeroTelephone;
    private Adresse adresse;
    private Integer adresseId;
    private Integer niveauId;

    public NiveauAcces getNiveauAcces() {
        return niveauAcces;
    }

    public void setNiveauAcces(NiveauAcces niveauAcces) {
        this.niveauAcces = niveauAcces;
    }

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

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Integer getAdresseId() {
        return adresseId;
    }

    public void setAdresseId(Integer adresseId) {
        this.adresseId = adresseId;
    }

    public Integer getNiveauId() {
        return niveauId;
    }

    public void setNiveauId(Integer niveauId) {
        this.niveauId = niveauId;
    }
}
