package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.*;
import fr.yr.site.alegia.configuration.Factory;
import fr.yr.site.alegia.proxies.*;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;

public class GestionCommandeActionTest {

    private Logger logger;
    private Factory factory;
    private GestionCommandeAction action;



    @Test
    public void doConsulterCommande(){
        // GIVEN
        Compte compte = new Compte();
        compte.setId(3);
        when(factory.getCompteProxy().findByEmail(anyString())).thenReturn(compte);
        Commande commande = new Commande();
        commande.setId(2);
        commande.setAdresseId(2);
        commande.setStatutId(2);
        commande.setCompteId(2);
        List<Commande> commandeList = new ArrayList<>();
        commandeList.add(commande);
        when(factory.getCommandeProxy()
                .getCOmmandeByStatutId(anyInt())).thenReturn(commandeList);
        LigneDeCommande ldc = new LigneDeCommande();
        ldc.setQuantite(3);
        ldc.setMontant(new Float(3));
        List<LigneDeCommande> ligneDeCommandeList = new ArrayList<>();
        ligneDeCommandeList.add(ldc);
        when(factory.getLigneProxy().findByCommandeId(anyInt())).thenReturn(ligneDeCommandeList);
        when(factory.getAdresseProxy().getAdresse(anyInt())).thenReturn(new Adresse());
        when(factory.getCategorieProxy().findAll()).thenReturn(new ArrayList<>());

        // WHEN
        String result = action.doConsulterCommande();

        // THEN
        assertEquals(result, ActionSupport.SUCCESS);
        assertNotNull(action.getCommandeList());
        assertNotNull(action.getCategorieList());
    }

    @Test
    public void doConsulterCommandeException(){
        // GIVEN
        when(factory.getCompteProxy().findByEmail(anyString())).thenReturn(null);

        // WHEN
        String result = action.doConsulterCommande();

        // THEN
        assertEquals(result, ActionSupport.ERROR);
    }

    @Before
    public void init(){
        action = new GestionCommandeActionTest.GestionCommandeActionFake();
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


    public class GestionCommandeActionFake extends GestionCommandeAction {
        @Override
        protected Factory getFactory() {
            return factory;
        }

        @Override
        protected Logger getLogger() {
            return logger;
        }

        @Override
        protected String getEmail() {
            return "EMAIL";
        }
    }
}
