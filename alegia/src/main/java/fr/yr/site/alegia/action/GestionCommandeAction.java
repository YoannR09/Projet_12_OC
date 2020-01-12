package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.*;
import fr.yr.site.alegia.configuration.Factory;
import fr.yr.site.alegia.proxies.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GestionCommandeAction extends ActionSupport {

    // Microseervices
    @Autowired
    Factory factory;


    private List<Contenu> contenuList;
    private List<Categorie> categorieList;


    public String doConfirmAdresse(){
        try {

            return ActionSupport.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ActionSupport.ERROR;
        }
    }

    public String doConfirmPaiement(){
        return ActionSupport.SUCCESS;
    }

    public String doCommande(){
        try {
            Compte compte = factory.getCompteProxy().findByEmail((String) ActionContext.getContext().getSession().get("email"));
            Panier panier = factory.getPanierProxy().getPanierByCompteId(compte.getId());
            contenuList = factory.getContenuProxy().findByPanierId(panier.getId());

            Commande commande = new Commande();
            commande.setStatutId(1);
            commande.setNumero("commandeTest");
            commande.setCompteId(compte.getId());
            factory.getCommandeProxy().add(commande);

            for(Contenu contenu:contenuList){
                contenu.setArticle(factory.getArticleProxy().getArticle(contenu.getArticleId()));
                LigneDeCommande ligneDeCommande = new LigneDeCommande();
                ligneDeCommande.setCommandeId(1); // A changer (Mettre l'id de la nouvelle commande)
                ligneDeCommande.setDesignation(contenu.getArticle().getNom());
                ligneDeCommande.setPrixUnitHt(contenu.getArticle().getPrixHt());
                ligneDeCommande.setPrixUnitTtc(contenu.getArticle().getPrixTtc());
                ligneDeCommande.setMontantHt(contenu.getArticle().getPrixHt()*contenu.getQuantite());
                ligneDeCommande.setMontantTtc(contenu.getArticle().getPrixTtc()*contenu.getQuantite());
                ligneDeCommande.setQuantite(contenu.getQuantite());
                ligneDeCommande.setTaille(contenu.getTaille().getTaille());
                factory.getLigneProxy().add(ligneDeCommande);
                factory.getContenuProxy().delete(contenu.getId());
            }
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            return ActionSupport.ERROR;
        }
    }
}
