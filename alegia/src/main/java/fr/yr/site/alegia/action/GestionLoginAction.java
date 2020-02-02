package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.Adresse;
import fr.yr.site.alegia.beans.Categorie;
import fr.yr.site.alegia.beans.Compte;
import fr.yr.site.alegia.beans.Panier;
import fr.yr.site.alegia.configuration.EncryptionUtil;
import fr.yr.site.alegia.configuration.Factory;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Classe qui gère la connexion en tant que visiteur ou administrateur
 */
public class GestionLoginAction extends ActionSupport implements SessionAware{

    // --- Microservices ---
    @Autowired
    Factory factory;

    final String secretKey = "ssshhhhhhhhhhh!!!!";

    private Map<String, Object> session;

    private List<Categorie> categorieList;
    private String email;
    private String motDePasse;
    private String nom;
    private String prenom;
    private String rue;
    private String numero;
    private String numeroTelephone;
    private String info;
    private String ville;
    private String codePostal;
    private Compte compte;



    public String pageLogin(){
        try {
            categorieList = factory.getCategorieProxy().findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour connecter à un compte client ou administrateur.
     * @return
     */
    public String doLogin() throws Exception {

        String vResult = ActionSupport.ERROR;

        categorieList = factory.getCategorieProxy().findAll();

        if (email != null) {
            compte = factory.getCompteProxy().findByEmail(email);
        }
        if (compte == null) {
            this.addActionMessage("Identifiant invalide");
            vResult = ActionSupport.ERROR;
        } else {
            if (compte != null) {
                if (motDePasse.equals(EncryptionUtil.decrypt(compte.getMotDePasse(),secretKey))) {
                    this.session.put("email", compte.getEmail());
                    if (compte.getNiveauAccesId() == 2) {
                        this.session.put("admin", compte);
                    } else {
                        this.session.put("user", compte);
                    }
                    vResult = ActionSupport.SUCCESS;
                } else {
                    this.addActionMessage("Mot de passe invalide");
                    vResult = ActionSupport.ERROR;
                }
            }
        }
        return vResult;
    }

    public String doInscription() {
        String vResult;
        categorieList = factory.getCategorieProxy().findAll();
        try {
            if (email != null){
                if(factory.getCompteProxy().findByEmail(email) != null){
                    this.addActionMessage("Adresse éléctronique déjà utilisée");
                    vResult = ActionSupport.ERROR;
                }else {
                    // Création de l'adresse
                    Adresse adresse = new Adresse();
                    adresse.setCodePostal(codePostal);
                    if (info.equals("") || info == null){
                        adresse.setInfo("Aucune information");
                    }else {
                        adresse.setInfo(info);
                    }
                    adresse.setNumero(numero);
                    adresse.setRue(rue);
                    adresse.setVille(ville);
                    factory.getAdresseProxy().add(adresse);

                    // Création du compte
                    Compte compte = new Compte();
                    compte.setEmail(email.toUpperCase());
                    compte.setMotDePasse(EncryptionUtil.encrypt(motDePasse, secretKey));
                    compte.setNiveauAccesId(1);
                    compte.setNom(nom.toUpperCase());
                    compte.setPrenom(prenom.toUpperCase());
                    compte.setNumeroTelephone(numeroTelephone);
                    List<Adresse> listAdresse = factory.getAdresseProxy().findAll();
                    compte.setAdresseId(listAdresse.get(listAdresse.size() - 1).getId());
                    factory.getCompteProxy().add(compte);

                    // Création du panier
                    Panier panier = new Panier();
                    List<Compte> listCompte = factory.getCompteProxy().findAll();
                    panier.setCompteId(listCompte.get(listCompte.size() - 1).getId());
                    factory.getPanierProxy().add(panier);

                    vResult = ActionSupport.SUCCESS;
                }
            }else {
                vResult = ActionSupport.INPUT;
            }
        }catch (Exception e){
            e.printStackTrace();
            vResult = ActionSupport.ERROR;
        }

        return vResult;
    }



    //----------- GETTERS ET SETTERS ----------------


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public List<Categorie> getCategorieList() {
        return categorieList;
    }

    public void setCategorieList(List<Categorie> categorieList) {
        this.categorieList = categorieList;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
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

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }


}
