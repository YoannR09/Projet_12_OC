package fr.yr.site.alegia.configuration;

import fr.yr.site.alegia.beans.Article;
import fr.yr.site.alegia.beans.Image;

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
