package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.Article;
import fr.yr.site.alegia.beans.Categorie;
import fr.yr.site.alegia.beans.Image;
import fr.yr.site.alegia.configuration.Factory;
import fr.yr.site.alegia.proxies.*;
import javassist.NotFoundException;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;

public class GestionCategorieActionTest {

    private Factory factory;
    private Logger logger;
    private GestionCategorieAction action;

    @Test
    public void doListArticleByCategorieId(){

        // GIVEN
        action.setCategorieId(3);
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
        String result = action.doListArticleByCategorieId();

        // THEN
        assertEquals(result, ActionSupport.SUCCESS);
        assertNotNull(action.getCategorieList());
        assertNotNull(action.getArticleList());
    }

    @Test
    public void doListArticleByCategorieIdException (){

            // GIVEN
            when(factory.getArticleProxy().getArticle(anyInt())).thenReturn(null);

            // WHEN
            String result = action.doListArticleByCategorieId();

            // THEN
            assertEquals(result, ActionSupport.ERROR);
    }

    @Test
    public void doDisponible(){

        // GIVEN
        action.setCategorieId(3);
        Categorie categorie = new Categorie();
        when(factory.getCategorieProxy().findById(anyInt())).thenReturn(categorie);
        List<Categorie> categorieList = new ArrayList<>();
        categorieList.add(categorie);
        when(factory.getCategorieProxy().findByDispo(anyBoolean())).thenReturn(categorieList);
        when(factory.getCategorieProxy().findAll()).thenReturn(categorieList);
        MicroserviceCategorie mc = factory.getCategorieProxy();
        doNothing().when(mc).update(any(Categorie.class));

        // WHEN
        String result = action.doDisponible();

        // THEN
        assertEquals(result, ActionSupport.SUCCESS);
        assertNotNull(action.getCategorieList());
        assertNotNull(action.getCategories());
    }

    @Test
    public void doDisponibleException(){

        // GIVEN
        when(factory.getCategorieProxy().findByDispo(anyBoolean())).thenReturn(null);

        // WHEN
        String result = action.doListArticleByCategorieId();

        // THEN
        assertEquals(result, ActionSupport.ERROR);
    }


    @Before
    public void init(){
        action = new GestionCategorieActionFake();
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

    public class GestionCategorieActionFake extends GestionCategorieAction {
        @Override
        protected Logger getLogger() {
            return logger;
        }

        @Override
        protected Factory getFactory() {
            return factory;
        }
    }
}
