package fr.yr.site.alegia.action;


import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.Article;
import fr.yr.site.alegia.beans.Categorie;
import fr.yr.site.alegia.beans.Image;
import fr.yr.site.alegia.beans.ListTaille;
import fr.yr.site.alegia.configuration.Factory;
import fr.yr.site.alegia.proxies.*;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class GestionArticleActionTest {

    private GestionArticleAction action;

    private Factory factory;
    private Logger logger;

    @Test
    public void doDetailArticle() {

        // GIVEN
        Article article = new Article();
        article.setId(1);
        when(factory.getArticleProxy().getArticle(anyInt())).thenReturn(article);
        List<ListTaille> listTailleList = new ArrayList<>();
        ListTaille lt = new ListTaille();
        lt.setId(2);
        lt.setTailleId(1);
        listTailleList.add(lt);
        when(factory.getListTailleProxy().findByArticleId(anyInt())).thenReturn(listTailleList);
        Image image = new Image();
        List<Image> imageList = new ArrayList<>();
        imageList.add(image);
        when(factory.getImageProxy().findByArticleId(anyInt())).thenReturn(imageList);
        Categorie categorie = new Categorie();
        List<Categorie> categorieList = new ArrayList<>();
        categorieList.add(categorie);
        when(factory.getCategorieProxy().findByDispo(anyBoolean())).thenReturn(categorieList);
        action.setArticleId(2);

        // WHEN
        String result = action.doDetailArticle();

        // THEN
        assertEquals(result, ActionSupport.SUCCESS);
        assertNotNull(action.getArticle());
        assertNotNull(action.getImageList());
        assertNotNull(action.getListTailles());
        assertNotNull(action.getCategorieList());
    }


    @Test
    public void doDetailArticleException(){

        // GIVEN
        when(factory.getArticleProxy().getArticle(anyInt())).thenReturn(null);

        // WHEN
        String result = action.doDetailArticle();

        // THEN
        assertEquals(result, ActionSupport.ERROR);
    }

    @Test
    public void formArticleModif(){

        // GIVEN
        action.setArticleId(3);
        Article article = new Article();
        article.setId(2);
        when(factory.getArticleProxy().getArticle(anyInt())).thenReturn(article);
        Categorie categorie = new Categorie();
        List<Categorie> categorieList = new ArrayList<>();
        categorieList.add(categorie);
        when(factory.getCategorieProxy().findByDispo(anyBoolean())).thenReturn(categorieList);
        when(factory.getImageProxy().findByArticleId(anyInt())).thenReturn(new ArrayList<>());
        factory.getImageProxy().findByArticleId(2).add(new Image());

        // WHEN
        String result = action.formModifArticle();

        // THEN
        assertEquals(result, ActionSupport.SUCCESS);
        assertNotNull(action.getCategorieList());
    }

    @Test
    public void formArticleModifException(){

        // GIVEN
        when(factory.getArticleProxy().getArticle(anyInt())).thenReturn(null);

        // WHEN
        String result = action.formModifArticle();

        // THEN
        assertEquals(result, ActionSupport.ERROR);
    }

    @Before
    public void init(){
        action = new GestionArticleActionFake();
        factory = mock(Factory.class);
        when(factory.getCategorieProxy()).thenReturn(mock(MicroserviceCategorie.class));
        when(factory.getImageProxy()).thenReturn(mock(MicroserviceImageProxy.class));
        when(factory.getListTailleProxy()).thenReturn(mock(MicroserviceListTaille.class));
        when(factory.getArticleProxy()).thenReturn(mock(MicroserviceArticleProxy.class));
        when(factory.getCommandeProxy()).thenReturn(mock(MicroserviceCommandeProxy.class));
        when(factory.getCompteProxy()).thenReturn(mock(MicroserviceCompteProxy.class));
        when(factory.getAdresseProxy()).thenReturn(mock(MicroserviceAdresseProxy.class));
        when(factory.getTailleProxy()).thenReturn(mock(MicroserviceTailleProxy.class));
        when(factory.getContenuProxy()).thenReturn(mock(MicroserviceContenuPanierProxy.class));
        when(factory.getLigneProxy()).thenReturn(mock(MicroserviceLigneDeCommandeProxy.class));
        when(factory.getPanierProxy()).thenReturn(mock(MicroservicePanierProxy.class));
        logger = mock(Logger.class);
        doNothing().when(logger).error(anyString());
        doNothing().when(logger).warn(anyString());
    }

    public class GestionArticleActionFake extends GestionArticleAction {
        @Override
        protected Factory getFactory() {
            return factory;
        }

        @Override
        protected Logger getLogger() {
            return logger;
        }
    }
}
