package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.*;
import fr.yr.site.alegia.configuration.Factory;
import fr.yr.site.alegia.proxies.*;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;

public class GestionPanierActionTest {

    private Logger logger;
    private Factory factory;
    private GestionPanierAction action;

    @Test
    public void doConsulterPanier(){
        // GIVEN
        Compte compte = new Compte();
        compte.setId(2);
        when(factory.getCompteProxy().findByEmail(anyString())).thenReturn(compte);
        Panier panier = new Panier();
        panier.setId(2);
        when(factory.getPanierProxy().getPanierByCompteId(anyInt())).thenReturn(panier);
        Contenu contenu = new Contenu();
        contenu.setArticleId(3);
        contenu.setTailleId(2);
        contenu.setQuantite(3);
        contenu.setTailleId(2);
        List<Contenu> contenuList = new ArrayList<>();
        when(factory.getContenuProxy().findByPanierId(anyInt())).thenReturn(contenuList);
        Article article = new Article();
        article.setId(2);
        article.setPrix(new Float(2));
        when(factory.getArticleProxy().getArticle(anyInt())).thenReturn(article);
        List<Image> imageList = new ArrayList<>();
        when(factory.getImageProxy().findByArticleId(anyInt())).thenReturn(imageList);

        // WHEN
        String result = action.doConsulterPanier();

        // THEN
        assertEquals(result,ActionSupport.SUCCESS);
        assertNotNull(action.getTotalPrix());
        assertNotNull(action.getCategorieList());
    }

    @Test
    public void doConsulterPanierException(){
        when(factory.getImageProxy().findByArticleId(anyInt())).thenReturn(null);

        // WHEN
        String result = action.doConsulterPanier();

        // THEN
        assertEquals(result,ActionSupport.ERROR);
    }

    @Before
    public void init(){
        action = new GestionPanierActionFake();
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

    public class GestionPanierActionFake extends GestionPanierAction {
        @Override
        protected String getEmail() {
            return "EMAIL";
        }

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
