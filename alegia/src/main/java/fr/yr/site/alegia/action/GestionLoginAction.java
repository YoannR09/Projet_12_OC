package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.Adresse;
import fr.yr.site.alegia.beans.Categorie;
import fr.yr.site.alegia.beans.Compte;
import fr.yr.site.alegia.beans.Panier;
import fr.yr.site.alegia.configuration.EncryptionUtil;
import fr.yr.site.alegia.configuration.Factory;
import fr.yr.site.alegia.configuration.GenerateMethod;
import fr.yr.site.alegia.configuration.MailGestion;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Classe qui gère la connexion en tant que visiteur ou administrateur
 */
public class GestionLoginAction extends ActionSupport implements SessionAware, ServletResponseAware, ServletRequestAware {

    private static final Logger logger = LogManager.getLogger();

    private GenerateMethod gm = new GenerateMethod();

    // --- Microservices ---
    @Autowired
    private Factory factory;

    final String secretKey = "ssshhhhhhhhhhh!!!!";

    private Map<String, Object> session;

    private         List<Categorie>     categorieList;
    private         Integer             countPanier;
    private         String              email;
    private         String              motDePasse;
    private         String              nom;
    private         String              prenom;
    private         String              rue;
    private         String              numero;
    private         String              numeroTelephone;
    private         String              info;
    private         String              ville;
    private         String              verif;
    private         String              verifEmail;
    private         String              verifMdp;
    private         String              infoMessage;
    private         String              codePostal;
    private         Compte              compte;

    /**
     * Méthode pour afficher la page de connexion
     * @return
     */
    public String pageLogin(){
        try {
            categorieList = getFactory().getCategorieProxy().findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour connecter à un compte client ou administrateur.
     * @return
     */
    public String doLogin(){

        String vResult = ActionSupport.ERROR;

        categorieList = getFactory().getCategorieProxy().findAll();

        if (email != null) {
            compte = getFactory().getCompteProxy().findByEmail(email.toLowerCase());
        }
        if (compte == null) {
            infoMessage = "Identifiant invalide";
            vResult = ActionSupport.ERROR;
        } else {
            if (compte != null) {
                if (motDePasse.equals(EncryptionUtil.decrypt(compte.getMotDePasse(),secretKey))) {
                    this.session.put("email", compte.getEmail());
                    Cookie compteEmail = new Cookie("compteEmail", compte.getEmail());
                    servletResponse.addCookie(compteEmail);
                    if (compte.getNiveauAccesId() == 2) {
                        this.session.put("admin", compte);
                    } else {
                        this.session.put("user", compte);
                    }
                    countPanier = gm.generateCountPanier(factory,getEmail());
                    vResult = ActionSupport.SUCCESS;
                } else {
                    infoMessage = "Mot de passe invalide";
                    vResult = ActionSupport.ERROR;
                }
            }
        }
        return vResult;
    }

    /**
     * Méthode pour créer un compte utilisateur.
     * On vérifie les input du formulaire.
     * On vérifie que l'adresse électronique n'est psa déjà utilisée.
     * Création d'une adresse pour la lié au compte.
     * Création d'un panier pour le lié au compte.
     * Coté microservice le premier compte créé sera un compte adminstrateur.
     * @return
     */
    public String doInscription() {
        String vResult;
        categorieList = getFactory().getCategorieProxy().findAll();
        try {
            if (email != null) {
                if (email != null && email.equals(verifEmail) && motDePasse != null && motDePasse.equals(verifMdp)) {
                    if (getFactory().getCompteProxy().findByEmail(email.toLowerCase()) != null) {
                        infoMessage = "Adresse éléctronique déjà associée à un autre compte";
                        vResult = ActionSupport.ERROR;
                    } else {
                        if (info.equals("") || info == null) {
                            info = "Aucune information";
                        }
                        if (getFactory().getAdresseProxy()
                                .findByVilleAndCodePostalAndNumeroAndRueAndInfo(
                                        ville
                                        , codePostal
                                        , numero
                                        , rue,info) == null) {
                            // Création de l'adresse
                            Adresse adresse = new Adresse();
                            adresse.setCodePostal(codePostal);
                            adresse.setNumero(numero);
                            adresse.setRue(rue);
                            adresse.setVille(ville);
                            adresse.setInfo(info);
                            getFactory().getAdresseProxy().add(adresse);
                        }
                        // Création du compte
                        Compte compte = new Compte();
                        compte.setEmail(email.toLowerCase());
                        compte.setMotDePasse(EncryptionUtil.encrypt(motDePasse, secretKey));
                        compte.setNiveauAccesId(1);
                        compte.setNom(nom.toUpperCase());
                        compte.setPrenom(prenom.toUpperCase());
                        compte.setNumeroTelephone(numeroTelephone);
                        compte.setAdresseId(getFactory().getAdresseProxy()
                                .findByVilleAndCodePostalAndNumeroAndRueAndInfo(
                                        ville
                                        , codePostal
                                        , numero
                                        , rue
                                        , info).getId());
                        getFactory().getCompteProxy().add(compte);

                        // Création du panier
                        Panier panier = new Panier();
                        panier.setCompteId(getFactory().getCompteProxy()
                                .findByEmail(email.toLowerCase()).getId());
                        getFactory().getPanierProxy().add(panier);
                        infoMessage = "Votre compte a bien été créé";
                        vResult = ActionSupport.SUCCESS;
                    }
                }else {
                    infoMessage = "Les champs de confirmation ne corréspondent pas";
                    vResult = ActionSupport.INPUT;
                }
            } else {
                vResult = ActionSupport.INPUT;
            }

        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            vResult = ActionSupport.ERROR;
        }
        return vResult;
    }


    /**
     * Méthode pour réinitialiser son mot de passe
     * Envoie un mail contenant le nouveau mot de passe
     * @return
     */
    public String doNouveauMotDePasse(){
        try {
            if (email != null){
                compte = getFactory().getCompteProxy().findByEmail(email.toLowerCase());
                if (compte != null) {
                    String newMdp = RandomStringUtils.randomAlphanumeric(10);
                    MailGestion mailGestion = new MailGestion();
                    String contenu = "Votre nouveau mot de passe est : " + newMdp;
                    String objet = "Votre nouveau mot de passe";
                    mailGestion.sendMail(objet, contenu, compte);
                    compte.setMotDePasse(EncryptionUtil.encrypt(newMdp, secretKey));
                    getFactory().getCompteProxy().update(compte);
                    infoMessage = "Un email contenant le nouveau mot de passe vous a été transmis";
                }else {
                    infoMessage = "Il n'existe pas de compte avec cette adresse électronique";
                }
                categorieList = getFactory().getCategorieProxy().findAll();
                return ActionSupport.SUCCESS;
            }else {
                categorieList = getFactory().getCategorieProxy().findAll();
                return ActionSupport.INPUT;
            }
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour afficher les informations du compte
     * @return
     */
    public String doProfil(){
        try {
            String email = getEmailContext();
            compte = getFactory().getCompteProxy().findByEmail(email.toLowerCase());
            compte.setAdresse(getFactory().getAdresseProxy().getAdresse(compte.getAdresseId()));
            countPanier = gm.generateCountPanier(factory,getEmailContext());
            categorieList = getFactory().getCategorieProxy().findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour déconnecter l'abonné.
     * @return
     */
    public String doLogout(){
        try {
            this.session.remove("admin");
            this.session.remove("user");
            this.session.remove("email");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour changer de mot de passe
     * Via la page de profil
     * @return
     */
    public String doChangeMotDePasse(){
        try {
            countPanier = gm.generateCountPanier(factory,getEmailContext());
            if (motDePasse.equals(verif)) {
                String getEmail = getEmailContext();
                compte = getFactory().getCompteProxy().findByEmail(getEmail.toLowerCase());
                compte.setMotDePasse(EncryptionUtil.encrypt(motDePasse, secretKey));
                getFactory().getCompteProxy().update(compte);
                compte.setAdresse(getFactory().getAdresseProxy().getAdresse(compte.getAdresseId()));
                categorieList = getFactory().getCategorieProxy().findAll();
                infoMessage = "Votre mot de passe a été changé";
            }else {
                this.addActionMessage("La vérification du mot de passe est invalide");
            }
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour changer l'adresse électronique de l'utilisateur
     * @return
     */
    public String doChangeEmail(){
        try {
            if (email != null && email.equals(verifEmail)) {
                if (getFactory().getCompteProxy().findByEmail(email.toLowerCase()) != null) {
                    String getEmail = getEmailContext();
                    compte = getFactory().getCompteProxy().findByEmail(getEmail.toLowerCase());
                    compte.setEmail(email);
                    categorieList = getFactory().getCategorieProxy().findAll();
                    getFactory().getCompteProxy().update(compte);
                    ActionContext.getContext().getSession().put("email", email);
                    countPanier = gm.generateCountPanier(factory, getEmail());
                    infoMessage = "Votre adresse éléctronique a été changé";
                }else {
                    infoMessage = "Adresse électronique déjà associée à un autre compte";
                    categorieList = getFactory().getCategorieProxy().findAll();
                    countPanier = gm.generateCountPanier(factory, getEmail());
                }
            }else {
                this.addActionMessage("La vérification de l'adresse éléctronique est invalide");
            }
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour changer l'adresse lié au compte.
     * Si l'adresse est déjà existante on récupère l'id de celle-ci pour la lié au compte.
     * @return
     */
    public String doChangeAdresse(){
        try {
            countPanier = gm.generateCountPanier(factory,getEmailContext());
            if (motDePasse != null && motDePasse.equals(verif)) {
                String getEmail = getEmailContext();
                compte = getFactory().getCompteProxy().findByEmail(getEmail.toLowerCase());
                if (getFactory().getAdresseProxy()
                        .findByVilleAndCodePostalAndNumeroAndRueAndInfo(
                                ville
                                ,codePostal
                                ,numero
                                ,rue
                                ,info) == null) {
                    // Création de l'adresse
                    Adresse adresse = new Adresse();
                    adresse.setRue(rue);
                    if (info.equals("") || info == null){
                        adresse.setInfo("Aucune information");
                    }else {
                        adresse.setInfo(info);
                    }
                    adresse.setVille(ville);
                    adresse.setCodePostal(codePostal);
                    adresse.setNumero(numero);
                    getFactory().getAdresseProxy().add(adresse);
                }
                compte.setAdresseId(getFactory().getAdresseProxy()
                        .findByVilleAndCodePostalAndNumeroAndRueAndInfo(
                                ville
                                ,codePostal
                                ,numero
                                ,rue
                                ,info).getId());
                compte.setAdresse(getFactory().getAdresseProxy().getAdresse(compte.getAdresseId()));
                categorieList = getFactory().getCategorieProxy().findAll();
                getFactory().getCompteProxy().update(compte);
                infoMessage = "Votre adresse de livraison a été changée";
            }
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    protected String getEmailContext() {
        return (String) ActionContext.getContext().getSession().get("email");
    }


    protected Factory getFactory() {
        return factory;
    }

    protected Logger getLogger() {
        return logger;
    }

    protected HttpServletResponse servletResponse;
    @Override
    public void setServletResponse(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }

    protected HttpServletRequest servletRequest;
    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
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

    public String getVerif() {
        return verif;
    }

    public void setVerif(String verif) {
        this.verif = verif;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    public Integer getCountPanier() {
        return countPanier;
    }

    public void setCountPanier(Integer countPanier) {
        this.countPanier = countPanier;
    }

    public String getVerifEmail() {
        return verifEmail;
    }

    public void setVerifEmail(String verifEmail) {
        this.verifEmail = verifEmail;
    }

    public String getVerifMdp() {
        return verifMdp;
    }

    public void setVerifMdp(String verifMdp) {
        this.verifMdp = verifMdp;
    }


}
