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
        if (vList != null) {
            for (Article a : vList) {
                a.setImageList(factory.getImageProxy().findByArticleId(a.getId()));
                if (a.getImageList().size() == 0) {
                    Image image = new Image();
                    image.setUrl("indisponible.jpg");
                    a.getImageList().add(image);
                }
            }
        }
    }

    public void imageListSizeNull(List<Image> vList) {
        if (vList.size() == 0 ){
            Image image = new Image();
            image.setUrl("indisponible.jpg");
            vList.add(image);
        }
    }

    public void generateTaille(List<ListTaille> vList, Factory factory) {
        for(ListTaille lt:vList){
            lt.setTaille(factory.getTailleProxy().findById(lt.getTailleId()));
        }
    }

    public void generateCommande(Commande commande,int count, float total, Factory factory){
        instance.setMinimumFractionDigits(2);
        instance.setMaximumFractionDigits(2);
        commande.setLigneDeCommandeList(factory.getLigneProxy().findByCommandeId(commande.getId()));
        for (LigneDeCommande lc : commande.getLigneDeCommandeList()){
            total = total+lc.getMontant();
            count = count+lc.getQuantite();
            lc.setRef(factory.getArticleProxy().findByNom(lc.getDesignation()).getReference());
            lc.setPrixHt(instance.format(lc.getPrixUnit()));
            lc.setMontantHt(instance.format(lc.getMontant()));
        }
        commande.setTotal(total);
        commande.setAdresse(factory.getAdresseProxy().getAdresse(commande.getAdresseId()));
        commande.setTva(instance.format((total*1.1)-total));
        commande.setLivraison(instance.format(10));
        String totalP = instance.format(total+10+(total*1.1)-total);
        commande.setTotalPayer(totalP);
        commande.setCountArticle(count);
        commande.setPrixTotal(instance.format(total));
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
