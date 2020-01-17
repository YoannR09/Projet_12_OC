package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.*;
import fr.yr.site.alegia.configuration.Factory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class GestionCommandeAction extends ActionSupport {

    // Microseervices
    @Autowired
    Factory factory;


    private List<Contenu> contenuList;
    private List<Categorie> categorieList;
    private List<Commande> commandeList;
    private String codePostal;
    private String rue;
    private String numero;
    private String info;
    private String ville;
    private String numeroCommande;


    public String doConfirmAdresse(){

        try {
            // Création de l'adresse
            Adresse adresse = new Adresse();
            adresse.setCodePostal(codePostal);
            adresse.setCodePostal(codePostal);
            if (info.equals("") || info == null){
                adresse.setInfo("Aucune information");
            }else {
                adresse.setInfo(info);
            }
            adresse.setRue(rue);
            adresse.setVille(ville);
            adresse.setNumero(numero);
            factory.getAdresseProxy().add(adresse);
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ActionSupport.ERROR;
        }
    }

    public String doConfirmPaiement(){

        Commande commande = factory.getCommandeProxy().getCommandeByNumero(numeroCommande);
        commande.setStatutId(2);
        factory.getCommandeProxy().update(commande);

        return ActionSupport.SUCCESS;
    }

    public String doCommande(){
        try {
            Compte compte = factory.getCompteProxy().findByEmail((String) ActionContext.getContext().getSession().get("email"));
            Panier panier = factory.getPanierProxy().getPanierByCompteId(compte.getId());
            contenuList = factory.getContenuProxy().findByPanierId(panier.getId());

            Commande commande = new Commande();
            commande.setStatutId(1);
            Date date = new Date();
            numeroCommande = compte.getId()+"-"
                    +date.getDate()+date.getMonth()+date.getYear()
                    +"-"+factory.getCommandeProxy().getListCommande().size();
            commande.setNumero(numeroCommande);
            commande.setCompteId(compte.getId());
            commande.setAdresseId(compte.getAdresseId());
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
            }
            categorieList = factory.getCategorieProxy().findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ActionSupport.ERROR;
        }
    }

    public String doConsulterCommande(){
        try {
            Compte compte = factory.getCompteProxy().findByEmail((String) ActionContext.getContext().getSession().get("email"));
            commandeList = factory.getCommandeProxy().getCommandeByCompteId(compte.getId());
            for (Commande c:commandeList){
                c.setStatut("EN COURS DE PREPARATION");
                c.setDate(new Date()); // A CHANGER !!!!!!!!
                float total = 0;
                int count = 0;
                c.setLigneDeCommandeList(factory.getLigneProxy().findByCommandeId(c.getId()));
                for (LigneDeCommande lc : c.getLigneDeCommandeList()){
                    count = count+lc.getQuantite();
                    total = total+lc.getMontantTtc();
                }
                c.setPrixTotal(Float.toString(total));
                c.setCountArticle(count);
            }
            categorieList = factory.getCategorieProxy().findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
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
}
