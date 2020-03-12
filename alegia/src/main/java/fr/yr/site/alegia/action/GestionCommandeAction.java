package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.paypal.base.rest.PayPalRESTException;
import fr.yr.site.alegia.beans.*;
import fr.yr.site.alegia.configuration.Factory;
import fr.yr.site.alegia.configuration.GenerateMethod;
import fr.yr.site.alegia.paypal.OrderDetail;
import fr.yr.site.alegia.paypal.PaymentServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe qui gère les commandes coté utilisateur.
 * Gère l'affichage de ses commandes déjà réalisés
 * Gère la réalisation d'une commande.
 */
public class GestionCommandeAction extends ActionSupport {


    private static final Logger logger = LogManager.getLogger();
    private static final long serialVersionUID = 1L;

    // Microseervices
    @Autowired
    private Factory factory;

    private GenerateMethod gm = new GenerateMethod();


    private         List<Contenu>       contenuList;
    private         List<Categorie>     categorieList;
    private         List<Commande>      commandeList;
    private         List<Adresse>       adresseList;
    private         Adresse             adresse;
    private         Compte              compte;
    private         Commande            commande;
    private         String              codePostal;
    private         String              rue;
    private         String              numero;
    private         String              info;
    private         String              ville;
    private         String              numeroCommande;
    private         Integer             commandeId;
    private         Integer             adresseId;
    private         Integer             livraisonId;
    private         Integer             countPanier;


    /**
     * Méthode pour générer le formulaire de création d'une adresse de livraison
     * @return
     */
    public String doFormNewAdresse(){
        try {
            countPanier = gm.generateCountPanier(factory,getEmail());
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            commande = getFactory().getCommandeProxy().getCommande(commandeId);
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour supprimer une adresse de livraison.
     * @return
     */
    public String doSupprimerAdresseLivraison(){
        try {
            countPanier = gm.generateCountPanier(factory,getEmail());
            String mail = getEmail();
            compte = getFactory().getCompteProxy().findByEmail(mail);
            getFactory().getLivraisonProxy().delete(livraisonId);
            adresse = getFactory().getAdresseProxy().getAdresse(compte.getAdresseId());
            adresseList = new ArrayList<>();
            for (AdresseLivraison al:factory.getLivraisonProxy().findByCompteId(compte.getId())){
                Adresse adresseAdd = factory.getAdresseProxy().getAdresse(al.getAdresseId());
                adresseAdd.setAdresseLivraisonId(al.getId());
                adresseList.add(adresseAdd);
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
     * Méthode pour créer une nouvelle adresse de livraison.
     * Si celle-ci existe déjà on récupère l'id.
     * @return
     */
    public String doConfirmNewAdresse(){
        try {
            countPanier = gm.generateCountPanier(factory,getEmail());
            String mail = (String) ActionContext.getContext().getSession().get("email");
            compte = getFactory().getCompteProxy().findByEmail(mail);
            commande = getFactory().getCommandeProxy().getCommande(commandeId);
            Adresse newAdresse = new Adresse();
            if (info.equals("") || info == null) {
                newAdresse.setInfo("Aucune information");
            } else {
                newAdresse.setInfo(info);
            }
            newAdresse.setVille(ville);
            newAdresse.setRue(rue);
            newAdresse.setNumero(numero);
            newAdresse.setCodePostal(codePostal);
            if (getFactory().getAdresseProxy()
                    .findByVilleAndCodePostalAndNumeroAndRueAndInfo(
                            ville
                            ,codePostal
                            ,numero
                            ,rue
                            ,info) == null) {
                getFactory().getAdresseProxy().add(newAdresse);
            }
            AdresseLivraison al = new AdresseLivraison();
            al.setAdresseId(getFactory().getAdresseProxy()
                    .findByVilleAndCodePostalAndNumeroAndRueAndInfo(
                            ville
                            ,codePostal
                            ,numero
                            ,rue
                            ,info).getId());
            al.setCompteId(commande.getCompteId());
            getFactory().getLivraisonProxy().add(al);
            adresseList = new ArrayList<>();
            for (AdresseLivraison a:factory.getLivraisonProxy().findByCompteId(compte.getId())){
                Adresse adresseAdd = factory.getAdresseProxy().getAdresse(a.getAdresseId());
                adresseAdd.setAdresseLivraisonId(a.getId());
                adresseList.add(adresseAdd);
            }
            adresse = getFactory().getAdresseProxy().getAdresse(compte.getAdresseId());
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode qui définit l'adresse de la commande à l'adresse lié au compte
     * @return
     */
    public String doConfirmAdresseCompte(){
        try {
            countPanier = gm.generateCountPanier(factory,getEmail());
            commande = getFactory().getCommandeProxy().getCommande(commandeId);
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }


    /**
     * Méthode qui définit une nouvelle adresse de livraison pour la commande
     * @return
     */
    public String doConfirmAdresse(){
        try {
            countPanier = gm.generateCountPanier(factory,getEmail());
            commande = getFactory().getCommandeProxy().getCommande(commandeId);
            commande.setAdresseId(adresseId);
            getFactory().getCommandeProxy().update(commande);
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    public String doConfirmPaiement(){
        try {
            if (getEmail() != null){
                countPanier = gm.generateCountPanier(factory
                        ,(String) ActionContext.getContext().getSession().get("email"));
            }
            commande = getFactory().getCommandeProxy().getCommande(commandeId);
            int count = 0;
            float total = 0;
            gm.generateCommande(commande,count,total, getFactory());
            commande.setStatutId(2);
            getFactory().getCommandeProxy().update(commande);
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    /**
     * Création d'une commande à partir du contenu du panier.
     * On définit
     * @return
     */
    public String doCommande(){
        try {
            String email = getEmail();
            compte = getFactory().getCompteProxy().findByEmail(email.toLowerCase());
            Panier panier = getFactory().getPanierProxy().getPanierByCompteId(compte.getId());
            contenuList = getFactory().getContenuProxy().findByPanierId(panier.getId());
            commande = new Commande();
            commande.setStatutId(1);
            Date date = new Date();
            commande.setDate(date);
            numeroCommande = compte.getId()+"-"
                    +date.getDate()+date.getMonth()+date.getYear()
                    +"-"+ getFactory().getCommandeProxy().getListCommande().size();
            commande.setNumero(numeroCommande);
            commande.setCompteId(compte.getId());
            commande.setAdresseId(compte.getAdresseId());
            adresse = getFactory().getAdresseProxy().getAdresse(compte.getAdresseId());
            getFactory().getCommandeProxy().add(commande);
            for(Contenu contenu:contenuList){
                contenu.setArticle(getFactory().getArticleProxy().getArticle(contenu.getArticleId()));
                LigneDeCommande ligneDeCommande = new LigneDeCommande();
                ligneDeCommande.setCommandeId(factory.getCommandeProxy().getCommandeByNumero(numeroCommande).getId());
                ligneDeCommande.setDesignation(contenu.getArticle().getNom());
                ligneDeCommande.setPrixUnit(contenu.getArticle().getPrix());
                ligneDeCommande.setMontant(contenu.getArticle().getPrix()*contenu.getQuantite());
                ligneDeCommande.setQuantite(contenu.getQuantite());
                contenu.setTaille(getFactory().getTailleProxy().findById(contenu.getTailleId()));
                ligneDeCommande.setTaille(contenu.getTaille().getTaille());
                getFactory().getLigneProxy().add(ligneDeCommande);
                commandeId = factory.getCommandeProxy().getCommandeByNumero(numeroCommande).getId();
            }
            adresseList = new ArrayList<>();
            adresse = getFactory().getAdresseProxy().getAdresse(compte.getAdresseId());
            for (AdresseLivraison al:factory.getLivraisonProxy().findByCompteId(compte.getId())){
                Adresse adresseAdd = factory.getAdresseProxy().getAdresse(al.getAdresseId());
                adresseAdd.setAdresseLivraisonId(al.getId());
                adresseList.add(adresseAdd);
            }
            countPanier = gm.generateCountPanier(factory,getEmail());
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
     * Méthode pour afficher le résumé de la commande.
     * @return
     */
    public String doConsulterCommande(){
        try {
            String email = getEmail();
            compte = getFactory().getCompteProxy().findByEmail(email.toLowerCase());
            commandeList = getFactory().getCommandeProxy().getCommandeByCompteId(compte.getId());
            for (Commande c:commandeList){
                c.setStatut(c.generateStatut());
                float total = 0;
                int count = 0;
                gm.generateCommande(c,count,total,getFactory());
            }
            countPanier = gm.generateCountPanier(factory,getEmail());
            categorieList = getFactory().getCategorieProxy().findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = getFactory().getCategorieProxy().findByDispo(true);
            getLogger().error(e);
            return ActionSupport.ERROR;
        }
    }

    protected String getEmail() {
        return (String) ActionContext.getContext().getSession().get("email");
    }

    protected Factory getFactory() {
        return factory;
    }

    protected Logger getLogger() {
        return logger;
    }

    public List<Contenu> getContenuList() {
        return contenuList;
    }

    public void setContenuList(List<Contenu> contenuList) {
        this.contenuList = contenuList;
    }

    public List<Categorie> getCategorieList() {
        return categorieList;
    }

    public void setCategorieList(List<Categorie> categorieList) {
        this.categorieList = categorieList;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
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

    public String getNumeroCommande() {
        return numeroCommande;
    }

    public void setNumeroCommande(String numeroCommande) {
        this.numeroCommande = numeroCommande;
    }

    public List<Commande> getCommandeList() {
        return commandeList;
    }

    public void setCommandeList(List<Commande> commandeList) {
        this.commandeList = commandeList;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Integer getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Integer commandeId) {
        this.commandeId = commandeId;
    }

    public List<Adresse> getAdresseList() {
        return adresseList;
    }

    public void setAdresseList(List<Adresse> adresseList) {
        this.adresseList = adresseList;
    }

    public Integer getAdresseId() {
        return adresseId;
    }

    public void setAdresseId(Integer adresseId) {
        this.adresseId = adresseId;
    }

    public Integer getLivraisonId() {
        return livraisonId;
    }

    public void setLivraisonId(Integer livraisonId) {
        this.livraisonId = livraisonId;
    }

    public Integer getCountPanier() {
        return countPanier;
    }

    public void setCountPanier(Integer countPanier) {
        this.countPanier = countPanier;
    }
}
