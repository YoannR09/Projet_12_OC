package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.Categorie;
import fr.yr.site.alegia.beans.Commande;
import fr.yr.site.alegia.beans.Compte;
import fr.yr.site.alegia.beans.LigneDeCommande;
import fr.yr.site.alegia.configuration.Factory;
import fr.yr.site.alegia.configuration.GenerateMethod;
import fr.yr.site.alegia.configuration.MailGestion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestionCommandeAdminAction extends ActionSupport {

    private static final Logger logger = LogManager.getLogger();

    // Microseervices
    @Autowired
    private Factory factory;

    private GenerateMethod gm = new GenerateMethod();

    private List<Commande> commandeList;
    private List<Commande> commandeListSize;
    private List<Categorie> categorieList;
    private boolean max;
    private String nom;
    private String prenom;
    private String email;
    private String statut;
    private String statutList;
    private Integer commandeId;
    private Integer countPanier;
    private Integer listSize;
    private String numero;
    private String infoMessage;
    private Compte compte;
    private Commande commande;

    public String doCommandeChangeStatut(){
        try {
            countPanier = gm.generateCountPanier(factory,getEmailContext());
            MailGestion mailGestion = new MailGestion();
            if (statut != null && commandeId != null){
                commande = getFactory().getCommandeProxy().getCommande(commandeId);
                String contenu;
                String objet = "Le statut de votre commande Alegia évolue !";
                if(commande.getStatutId() == 1){
                    commande.setStatutId(2);
                    contenu = "Votre commande est maintenant en cours de préparation";
                    mailGestion.sendMail(objet,contenu
                            , getFactory().getCompteProxy().findById(commande.getCompteId()));
                }else if(commande.getStatutId() == 2){
                    contenu = "Votre commande est maintenant en cours de livraison";
                    mailGestion.sendMail(objet,contenu
                            , getFactory().getCompteProxy().findById(commande.getCompteId()));
                    commande.setStatutId(3);
                }else if(commande.getStatutId() == 3){
                    contenu = "Votre commande a été livré à votre adresse de livraison";
                    mailGestion.sendMail(objet,contenu
                            , getFactory().getCompteProxy().findById(commande.getCompteId()));
                    commande.setStatutId(4);
                }
                generateCommande(commande);
                getFactory().getCommandeProxy().update(commande);
                infoMessage = "Le statut de la commande a bien été changé";
                categorieList = getFactory().getCategorieProxy().findAll();
            }
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour afficher la listes des commandes en fonction du statut.
     * l'id 1 affiche les commandes en attentes de validations de paiement.
     * l'id 2 affiche les commandes en cours de préparation.
     * l'id 3 affiche les commandes en cours de livraison.
     * l'id 4 affiche les commandes achevées.
     * @return
     */
    public String doListCommandeByStatut(){
        try {
            commandeListSize = new ArrayList<>();
            countPanier = gm.generateCountPanier(factory,getEmailContext());
            commandeList = getFactory().getCommandeProxy()
                    .getCOmmandeByStatutId(Integer.parseInt(statut));
            for (Commande c:commandeList){
                generateCommande(c);
            }

            if (commandeList.size() != 0){
                statutList = commandeList.get(0).getStatut();
            }else {
                if(Integer.parseInt(statut) == 2){
                    statutList = "EN COURS DE PREPARATION";
                }else if(Integer.parseInt(statut) == 3){
                    statutList = "EN COURS DE LIVRAISON";
                }else if(Integer.parseInt(statut) == 4){
                    statutList = "ACHEVEES";
                }
            }
            doListSize(commandeList);
            categorieList = getFactory().getCategorieProxy().findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }


    public String doDetailCommande(){
        try {
            countPanier = gm.generateCountPanier(factory,getEmailContext());
            if (commandeId != null){
                commande = getFactory().getCommandeProxy().getCommande(commandeId);
                generateCommande(commande);
            }
            categorieList = getFactory().getCategorieProxy().findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.SUCCESS;
        }
    }

    private void generateCommande(Commande c) {
        c.setStatut(c.generateStatut());
        compte = getFactory().getCompteProxy().findById(c.getCompteId());
        int count = 0;
        float total = 0;
        gm.generateCommande(c,count,total, getFactory());
    }

    public String doRechercheForm(){
        try {
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
     * Méthode pour afficher une liste de commande
     * la recherche se fait en fonction des textes rentrés
     * dans le formulaire de recherche.
     * @return
     */
    public String doRechercheCommande(){
        try {
            commandeListSize = new ArrayList<>();
            countPanier = gm.generateCountPanier(factory,getEmailContext());
            nom = nom.toUpperCase();
            prenom = prenom.toUpperCase();
            email = email.toLowerCase();
            if (numero != null || (prenom != null || nom != null || email != null)){
            if (!numero.equals("")) {
                rechercheByNumero();
            }
            if (!nom.equals("") || !prenom.equals("") || !email.equals("")) {
                rechercheByClient();
            }
                categorieList = getFactory().getCategorieProxy().findAll();
            }
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    public String doVoirPlus(){
        try {
            commandeListSize = new ArrayList<>();
            listSize += 10;
            if (statut != null && !statut.equals("")){
                commandeList = getFactory().getCommandeProxy()
                        .getCOmmandeByStatutId(Integer.parseInt(statut));
                doListSize(commandeList);
                if(Integer.parseInt(statut) == 2){
                    statutList = "EN COURS DE PREPARATION";
                }else if(Integer.parseInt(statut) == 4){
                    statutList = "ACHEVEES";
                }else if(Integer.parseInt(statut) == 3){
                    statutList = "EN COURS DE LIVRAISON";
                }
            }
            if (numero != null && (prenom != null || nom != null || email != null)) {
                if (!nom.equals("") || !prenom.equals("") || !email.equals("")) {
                    rechercheByClient();
                }
                if (!numero.equals("")) {
                    rechercheByNumero();
                }
            }
            countPanier = gm.generateCountPanier(factory,getEmailContext());
            categorieList = getFactory().getCategorieProxy().findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ActionSupport.ERROR;
        }
    }

    public void doListSize(List<Commande> commandeList){
        if (listSize == null){
            listSize = 3;
        }
        if (listSize > commandeList.size()){
            listSize = commandeList.size();
            max = true;
        }
        for(int i = 0;i<listSize;i++){
            commandeListSize.add(commandeList.get(i));
        }
    }

    public void rechercheByNumero(){
        commandeList = getFactory().getCommandeProxy().findCommandeByNumeroContaining(numero);
        statutList = "Numéro de la commande : "+numero;
        for (Commande c : commandeList) {
            generateCommande(c);
        }
        doListSize(commandeList);
    }

    public void rechercheByClient(){
        if (!nom.equals("") && !prenom.equals("") && !email.equals("")) {
            commandeList = getFactory().getCommandeProxy().findByNomPrenomEmail(nom, prenom, email);
            statutList = "Nom : " + nom + "/ Prénom : " + prenom + "/ Adresse éléctronique : " + email;
        } else if (nom.equals("") && !prenom.equals("") && !email.equals("")) {
            commandeList = getFactory().getCommandeProxy().findByPrenomEmail(prenom, email);
            statutList = "Prénom : " + prenom + "/ Adresse éléctronique : " + email;
        } else if (!nom.equals("") && !prenom.equals("") && email.equals("")) {
            commandeList = getFactory().getCommandeProxy().findByNomPrenom(nom, prenom);
            statutList = "Nom : " + nom + "/ Prénom : " + prenom;
        } else if (!nom.equals("") && prenom.equals("") && !email.equals("")) {
            commandeList = getFactory().getCommandeProxy().findByNomEmail(nom, email);
            statutList = "Nom : " + nom + "/ Adresse éléctronique : " + email;
        } else if (!nom.equals("") && prenom.equals("") && email.equals("")) {
            commandeList = getFactory().getCommandeProxy().findByNom(nom);
            statutList = "Nom : " + nom;
        } else if (nom.equals("") && !prenom.equals("") && email.equals("")) {
            commandeList = getFactory().getCommandeProxy().findByPrenom(prenom);
            statutList = "Prénom : " + prenom;
        } else if (nom.equals("") && prenom.equals("") && !email.equals("")) {
            commandeList = getFactory().getCommandeProxy().findByEmail(email);
            statutList = "Adresse éléctronique : " + email;
        }
        for (Commande c : commandeList) {
            generateCommande(c);
        }
        doListSize(commandeList);
    }

    protected Logger getLogger() {
        return logger;
    }

    protected Factory getFactory() {
        return factory;
    }


    protected String getEmailContext() {
        return (String) ActionContext.getContext().getSession().get("email");
    }

    public List<Commande> getCommandeList() {
        return commandeList;
    }

    public void setCommandeList(List<Commande> commandeList) {
        this.commandeList = commandeList;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Integer getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Integer commandeId) {
        this.commandeId = commandeId;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }


    public List<Categorie> getCategorieList() {
        return categorieList;
    }

    public void setCategorieList(List<Categorie> categorieList) {
        this.categorieList = categorieList;
    }

    public String getStatutList() {
        return statutList;
    }

    public void setStatutList(String statutList) {
        this.statutList = statutList;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<Commande> getCommandeListSize() {
        return commandeListSize;
    }

    public void setCommandeListSize(List<Commande> commandeListSize) {
        this.commandeListSize = commandeListSize;
    }

    public Integer getListSize() {
        return listSize;
    }

    public void setListSize(Integer listSize) {
        this.listSize = listSize;
    }

    public boolean isMax() {
        return max;
    }

    public void setMax(boolean max) {
        this.max = max;
    }
}
