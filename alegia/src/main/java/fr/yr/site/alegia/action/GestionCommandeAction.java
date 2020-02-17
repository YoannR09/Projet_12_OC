package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.*;
import fr.yr.site.alegia.configuration.Factory;
import fr.yr.site.alegia.configuration.GenerateMethod;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class GestionCommandeAction extends ActionSupport {

    // Microseervices
    @Autowired
    Factory factory;

    private GenerateMethod gm = new GenerateMethod();

    private List<Contenu> contenuList;
    private List<Categorie> categorieList;
    private List<Commande> commandeList;
    private Adresse adresse;
    private Compte compte;
    private Commande commande;
    private String codePostal;
    private String rue;
    private String numero;
    private String info;
    private String ville;
    private String numeroCommande;
    private Integer commandeId;

    public String doConfirmNewAdresse(){
        try {
            commande = factory.getCommandeProxy().getCommande(commandeId);
            Adresse adresse = new Adresse();
            adresse.setInfo(info);
            adresse.setVille(ville);
            adresse.setRue(rue);
            adresse.setNumero(numero);
            adresse.setCodePostal(codePostal);
            if (factory.getAdresseProxy()
                    .findByVilleAndCodePostalAndNumeroAndRue(
                            ville
                            ,codePostal
                            ,numero
                            ,rue) == null) {
                factory.getAdresseProxy().add(adresse);
            }else {
                commande.setAdresseId(factory.getAdresseProxy()
                        .findByVilleAndCodePostalAndNumeroAndRue(
                                ville
                                ,codePostal
                                ,numero
                                ,rue).getId());
            }
            categorieList = factory.getCategorieProxy().findByDispo(true);
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = factory.getCategorieProxy().findByDispo(true);
            e.printStackTrace();
            return ActionSupport.ERROR;
        }
    }

    public String doConfirmAdresseCompte(){
        try {
            commande = factory.getCommandeProxy().getCommande(commandeId);
            categorieList = factory.getCategorieProxy().findByDispo(true);
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = factory.getCategorieProxy().findByDispo(true);
            e.printStackTrace();
            return ActionSupport.ERROR;
        }
    }

    public String doConfirmPaiement(){
        try {
            commande = factory.getCommandeProxy().getCommande(commandeId);
            commande.setStatutId(2);
            factory.getCommandeProxy().update(commande);
            categorieList = factory.getCategorieProxy().findByDispo(true);
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = factory.getCategorieProxy().findByDispo(true);
            e.printStackTrace();
            return ActionSupport.ERROR;
        }
    }

    public String doCommande(){
        try {
            String email = (String) ActionContext.getContext().getSession().get("email");
            compte = factory.getCompteProxy().findByEmail(email.toLowerCase());
            Panier panier = factory.getPanierProxy().getPanierByCompteId(compte.getId());
            contenuList = factory.getContenuProxy().findByPanierId(panier.getId());
            commande = new Commande();
            commande.setStatutId(1);
            Date date = new Date();
            commande.setDate(date);
            numeroCommande = compte.getId()+"-"
                    +date.getDate()+date.getMonth()+date.getYear()
                    +"-"+factory.getCommandeProxy().getListCommande().size();
            commande.setNumero(numeroCommande);
            commande.setCompteId(compte.getId());
            commande.setAdresseId(compte.getAdresseId());
            adresse = factory.getAdresseProxy().getAdresse(compte.getAdresseId());
            factory.getCommandeProxy().add(commande);
            for(Contenu contenu:contenuList){
                contenu.setArticle(factory.getArticleProxy().getArticle(contenu.getArticleId()));
                LigneDeCommande ligneDeCommande = new LigneDeCommande();
                List<Commande> vList = factory.getCommandeProxy().getCommandeByCompteId(compte.getId());
                ligneDeCommande.setCommandeId(vList.get(vList.size()-1).getId());
                ligneDeCommande.setDesignation(contenu.getArticle().getNom());
                ligneDeCommande.setPrixUnitHt(contenu.getArticle().getPrixHt());
                ligneDeCommande.setPrixUnitTtc(contenu.getArticle().getPrixTtc());
                ligneDeCommande.setMontantHt(contenu.getArticle().getPrixHt()*contenu.getQuantite());
                ligneDeCommande.setMontantTtc(contenu.getArticle().getPrixTtc()*contenu.getQuantite());
                ligneDeCommande.setQuantite(contenu.getQuantite());
                contenu.setTaille(factory.getTailleProxy().findById(contenu.getTailleId()));
                ligneDeCommande.setTaille(contenu.getTaille().getTaille());
                factory.getLigneProxy().add(ligneDeCommande);
                factory.getContenuProxy().delete(contenu.getId());
                commandeId = vList.get(vList.size()-1).getId();
            }
            categorieList = factory.getCategorieProxy().findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = factory.getCategorieProxy().findByDispo(true);
            e.printStackTrace();
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour intégrer une nouvelle adresse pour la livraison de la commande.
     * @return
     */
    public String doAdresseCommande(){
        try {
            if(codePostal != null && rue != null && numero != null && ville == null) {
                Adresse adresse = new Adresse();
                if (info.equals("") || info == null) {
                    adresse.setInfo("Aucune information");
                } else {
                    adresse.setInfo(info);
                }
                adresse.setCodePostal(codePostal);
                adresse.setRue(rue);
                adresse.setNumero(numero);
                adresse.setVille(ville);
                factory.getAdresseProxy().add(adresse);
                commande = factory.getCommandeProxy().getCommande(commandeId);
                commande.setAdresseId(factory.getAdresseProxy()
                        .findByVilleAndCodePostalAndNumeroAndRue(ville,codePostal,numero,rue).getId());
                factory.getCommandeProxy().update(commande);
            }else {
                commande = factory.getCommandeProxy().getCommande(commandeId);
            }
            categorieList = factory.getCategorieProxy().findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = factory.getCategorieProxy().findByDispo(true);
            return ActionSupport.ERROR;
        }
    }

    public String doRepriseCommande(){
        try {
            String email = (String) ActionContext.getContext().getSession().get("email");
            compte = factory.getCompteProxy().findByEmail(email.toLowerCase());
            commande = factory.getCommandeProxy().getCommande(commandeId);
            adresse = factory.getAdresseProxy().getAdresse(compte.getAdresseId());
            categorieList = factory.getCategorieProxy().findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = factory.getCategorieProxy().findByDispo(true);
            e.printStackTrace();
            return ActionSupport.ERROR;
        }
    }

    public String doConsulterCommande(){
        try {
            String email = (String) ActionContext.getContext().getSession().get("email");
            compte = factory.getCompteProxy().findByEmail(email.toLowerCase());
            commandeList = factory.getCommandeProxy().getCommandeByCompteId(compte.getId());
            for (Commande c:commandeList){
                c.setStatut(c.generateStatut());
                float total = 0;
                int count = 0;
                c.setLigneDeCommandeList(factory.getLigneProxy().findByCommandeId(c.getId()));
                for (LigneDeCommande lc : c.getLigneDeCommandeList()){
                    count = count+lc.getQuantite();
                    total = total+lc.getMontantTtc();
                }
                c.setAdresse(factory.getAdresseProxy().getAdresse(c.getAdresseId()));
                c.setPrixTotal(Float.toString(total));
                c.setCountArticle(count);
            }
            categorieList = factory.getCategorieProxy().findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            this.addActionMessage("Un problème est survenu... ");
            categorieList = factory.getCategorieProxy().findByDispo(true);
            e.printStackTrace();
            return ActionSupport.ERROR;
        }
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
}
