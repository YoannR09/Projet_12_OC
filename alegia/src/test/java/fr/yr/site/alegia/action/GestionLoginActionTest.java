package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.Compte;
import fr.yr.site.alegia.configuration.Factory;
import fr.yr.site.alegia.proxies.*;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;

public class GestionLoginActionTest {

    private Factory factory;
    private Logger logger;
    private GestionLoginAction action;

    @Test
    public void doProfil(){
        // GIVEN
        Compte compte = new Compte();
        compte.setAdresseId(2);
        when(factory.getCompteProxy().findByEmail(anyString())).thenReturn(compte);
        when(factory.getCategorieProxy().findAll()).thenReturn(new ArrayList<>());

        // WHEN
        String result = action.doProfil();

        // THEN
        assertEquals(result,ActionSupport.SUCCESS);
        assertNotNull(action.getCategorieList());
        assertNotNull(action.getCompte());
    }

    @Test
    public void doProfilException(){
        // GIVEN
        when(factory.getCompteProxy().findByEmail(anyString())).thenReturn(null);

        // WHEN
        String result = action.doProfil();

        // THEN
        assertEquals(result,ActionSupport.ERROR);
    }


    @Before
    public void init(){
        action = new GestionLoginActionFake();
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
        when(factory.getNiveauAccesProxy()).thenReturn(mock(MicroserviceNiveauAccesProxy.class));
        logger = mock(Logger.class);
        doNothing().when(logger).error(anyString());
        doNothing().when(logger).warn(anyString());
    }

    public class GestionLoginActionFake extends GestionLoginAction {
        @Override
        protected Factory getFactory() {
            return factory;
        }

        @Override
        protected Logger getLogger() {
            return logger;
        }

        @Override
        protected String getEmailContext() {
            return "EMAIL";
        }
    }
}
