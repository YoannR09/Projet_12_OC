package fr.yr.site.alegia.configuration;

import fr.yr.site.alegia.beans.Article;
import fr.yr.site.alegia.beans.Commande;
import fr.yr.site.alegia.beans.Image;
import fr.yr.site.alegia.beans.LigneDeCommande;

import java.util.List;

public class GenerateMethod {

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
        commande.setLigneDeCommandeList(factory.getLigneProxy().findByCommandeId(commande.getId()));
        for (LigneDeCommande lc : commande.getLigneDeCommandeList()){
            total = total+lc.getMontantTtc();
            count = count+lc.getQuantite();
        }
        commande.setAdresse(factory.getAdresseProxy().getAdresse(commande.getAdresseId()));
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
}
