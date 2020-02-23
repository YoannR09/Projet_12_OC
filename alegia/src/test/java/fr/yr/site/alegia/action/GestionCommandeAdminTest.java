package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.Adresse;
import fr.yr.site.alegia.beans.Commande;
import fr.yr.site.alegia.beans.Compte;
import fr.yr.site.alegia.beans.LigneDeCommande;
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

public class GestionCommandeAdminTest {

    private Logger logger;
    private Factory factory;
    private GestionCommandeAdminAction action;


    @Test
    public void doListCommandeByStatut(){
        // GIVEN
        action.setStatut("1");
        Commande commande = new Commande();
        commande.setStatutId(2);
        commande.setId(2);
        commande.setCompteId(2);
        commande.setAdresseId(2);
        List<Commande> commandeList = new ArrayList<>();
        commandeList.add(commande);
        when(factory.getCommandeProxy().getCOmmandeByStatutId(anyInt())).thenReturn(commandeList);
        Compte compte = new Compte();
        compte.setId(2);
        when(factory.getCompteProxy().findById(anyInt())).thenReturn(compte);
        when(factory.getCategorieProxy().findByDispo(anyBoolean())).thenReturn(new ArrayList<>());
        when(factory.getAdresseProxy().getAdresse(anyInt())).thenReturn(new Adresse());
        LigneDeCommande ldc = new LigneDeCommande();
        ldc.setQuantite(3);
        ldc.setMontantTtc(new Float(3));
        List<LigneDeCommande> ligneDeCommandeList = new ArrayList<>();
        ligneDeCommandeList.add(ldc);
        when(factory.getLigneProxy().findByCommandeId(anyInt())).thenReturn(ligneDeCommandeList);

        // WHEN
        String result = action.doListCommandeByStatut();

        // THEN
        assertEquals(result,ActionSupport.SUCCESS);
        assertNotNull(action.getStatutList());
        assertNotNull(action.getCommandeList());
        assertNotNull(action.getCategorieList());
    }

    @Test
    public void doListCommandeByStatutException(){
        // GIVEN
        when(factory.getCommandeProxy().getCOmmandeByStatutId(anyInt())).thenReturn(null);

        // WHEN
        String result = action.doListCommandeByStatut();

        // THEN
        assertEquals(result, ActionSupport.ERROR);
    }

    @Test
    public void doDetailCommande(){
        // GIVEN
        Commande commande = new Commande();
        commande.setId(2);
        commande.setAdresseId(2);
        commande.setStatutId(2);
        commande.setCompteId(2);
        action.setCommandeId(2);
        when(factory.getCommandeProxy().getCommande(anyInt())).thenReturn(commande);

        // WHEN
        String result = action.doDetailCommande();

        // THEN
        assertEquals(result,ActionSupport.SUCCESS);
        assertNotNull(action.getCommande());
        assertNotNull(action.getCategorieList());
    }

    @Test
    public void doDetailCommandeException(){
        // GIVEN
        when(factory.getCommandeProxy().getCommande(anyInt())).thenReturn(null);

        // WHEN
        String result = action.doListCommandeByStatut();

        // THEN
        assertEquals(result, ActionSupport.ERROR);
    }


    @Before
    public void init(){
        action = new GestionCommandeAdminActionFake();
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

    public class GestionCommandeAdminActionFake extends GestionCommandeAdminAction {
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
