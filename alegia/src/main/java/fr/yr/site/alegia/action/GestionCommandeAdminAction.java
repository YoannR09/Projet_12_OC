package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.Categorie;
import fr.yr.site.alegia.beans.Commande;
import fr.yr.site.alegia.beans.LigneDeCommande;
import fr.yr.site.alegia.configuration.Factory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class GestionCommandeAdminAction extends ActionSupport {

    // Microseervices
    @Autowired
    Factory factory;

    private List<Commande> commandeList;
    private List<Categorie> categorieList;
    private String statut;
    private Integer commandeId;
    private String numero;
    private Commande commande;


    public String doCommandeChangeStatut(){
        try {
            if (statut != null && commandeId != null){
                commande = factory.getCommandeProxy().getCommande(commandeId);
                if(statut.equals("1")){
                    commande.setStatutId(1);
                }else if(statut.equals("2")){
                    commande.setStatutId(2);
                }else if(statut.equals("3")){
                    commande.setStatutId(3);
                }else if(statut.equals("4")){
                    commande.setStatutId(4);
                }
                factory.getCommandeProxy().update(commande);
            }
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            return ActionSupport.ERROR;
        }
    }

    public String doRechercheCommande(){
        try {
            if (numero != null) {
                commande = factory.getCommandeProxy().getCommandeByNumero(numero);
            }
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            return ActionSupport.SUCCESS;
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
            commandeList = factory.getCommandeProxy().getCOmmandeByStatutId(Integer.parseInt(statut));
            for (Commande c:commandeList){
                generateCommande(c);
            }
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ActionSupport.ERROR;
        }
    }


    public String doDetailCommande(){
        try {
            if (commandeId != null){
                commande = factory.getCommandeProxy().getCommande(commandeId);
                generateCommande(commande);
            }
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            return ActionSupport.SUCCESS;
        }
    }

    private void generateCommande(Commande c) {
        c.setStatut(c.generateStatut());
        c.setDate(new Date()); // A CHANGER !!!!!!!!
        int count = 0;
        float total = 0;
        c.setLigneDeCommandeList(factory.getLigneProxy().findByCommandeId(c.getId()));
        for (LigneDeCommande lc : c.getLigneDeCommandeList()){
            total = total+lc.getMontantTtc();
            count = count+lc.getQuantite();
        }
        c.setCountArticle(count);
        c.setPrixTotal(Float.toString(total));
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
}
