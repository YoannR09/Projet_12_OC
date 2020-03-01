package fr.yr.site.alegia.configuration;

import fr.yr.site.alegia.beans.*;

import java.text.NumberFormat;
import java.util.List;

public class GenerateMethod {

    final NumberFormat instance = NumberFormat.getNumberInstance();

    /**
     * Méthode pour remplire une liste d'articles
     * @param vList
     */
    public void completeArticleList(Factory factory,List<Article> vList){
        for (Article a : vList) {
            a.setImageList(factory.getImageProxy().findByArticleId(a.getId()));
            if (a.getImageList().size() == 0) {
                Image image = new Image();
                image.setUrl("indisponible.jpg");
                a.getImageList().add(image);
            }
        }
    }

    public void generateCommande(Commande commande,int count, float total, Factory factory){
        instance.setMinimumFractionDigits(2);
        instance.setMaximumFractionDigits(2);
        commande.setLigneDeCommandeList(factory.getLigneProxy().findByCommandeId(commande.getId()));
        for (LigneDeCommande lc : commande.getLigneDeCommandeList()){
            total = total+lc.getMontantTtc();
            count = count+lc.getQuantite();
        }
        commande.setTotal(total);
        String totalP = instance.format((total+10)*1.1);
        commande.setAdresse(factory.getAdresseProxy().getAdresse(commande.getAdresseId()));
        commande.setTotalPayer(totalP);
        commande.setTva(instance.format((total*1.1)-total));
        commande.setCountArticle(count);
        commande.setPrixTotal(Float.toString(total));
    }

    /**
     * Méthode pour completer un article
     * @param a
     */
    public void completeArticle(Factory factory,Article a){
        a.setImageList(factory.getImageProxy().findByArticleId(a.getId()));
        a.setSupprimable(true);
        if (a.getImageList().size() == 0) {
            Image image = new Image();
            image.setUrl("indisponible.jpg");
            a.getImageList().add(image);
        }
    }

    public Integer generateCountPanier(Factory factory,String mail){
        if (mail != null) {
            Compte compte = factory.getCompteProxy().findByEmail(mail.toLowerCase());
            return factory.getContenuProxy().findByPanierId(factory
                    .getPanierProxy()
                    .getPanierByCompteId(compte
                            .getId()).getId()).size();
        }else {
            return null;
        }

    }
}
