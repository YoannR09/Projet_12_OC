package fr.yr.site.controller;

import fr.yr.site.controller.CommandeController;
import fr.yr.site.dao.CommandeDao;
import fr.yr.site.model.Commande;
import javassist.NotFoundException;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CommandeControllerTest {

    private CommandeDao dao;
    private Logger logger;
    private CommandeController controller;

    @Test
    public void getCommande(){

        //GIVEN
        Commande commande = new Commande();
        commande.setId(3);
        commande.setNumero("Numero");
        when(dao.findById(anyInt())).thenReturn(commande);

        // WHEN
        Commande commandeTest = controller.getCommande(3);

        //THEN
        assertEquals(commandeTest.getId(),new Integer(3));
        assertEquals(commandeTest.getNumero(),"Numero");
        when(dao.findById(anyInt()))
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.getCommande(3));
    }

    @Test
    public void getCommandeByNumero(){

        //GIVEN
        Commande commande = new Commande();
        commande.setId(3);
        commande.setNumero("Numero");
        when(dao.findByNumero(anyString())).thenReturn(commande);

        // WHEN
        Commande commandeTest = controller.getCommandeByNumero("Numero");

        //THEN
        assertEquals(commandeTest.getId(),new Integer(3));
        assertEquals(commandeTest.getNumero(),"Numero");
        when(dao.findByNumero(anyString()))
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.getCommandeByNumero("Numero"));
    }

    @Test
    public void findByNomPrenomEmail(){

        //GIVEN
        List<Commande> vList = new ArrayList<>();
        initList(vList);
        when(dao.findByNomPrenomEmail(anyString()
                ,anyString()
                ,anyString())).thenReturn(vList);

        // WHEN
        List<Commande> vListTest = controller.findByNomPrenomEmail(
                "Nom"
                ,"Prenom"
                , "Email");

        //THEN
        assertEquals(vListTest.size(),2);
        when(dao.findByNomPrenomEmail(anyString()
                ,anyString()
                ,anyString()))
                .then((Answer<Void>) invocationOnMock -> {
            throw new NotFoundException("Erreur");
        });
        assertNull(controller.findByNomPrenomEmail("Nom","Prenom","Email"));
    }


    @Test
    public void findByNomPrenom(){

        //GIVEN
        List<Commande> vList = new ArrayList<>();
        initList(vList);
        when(dao.findByNomPrenom(anyString()
                ,anyString())).thenReturn(vList);

        // WHEN
        List<Commande> vListTest = controller.findByNomPrenom(
                "Nom"
                ,"Prenom");

        //THEN
        assertEquals(vListTest.size(),2);
        when(dao.findByNomPrenom(anyString()
                ,anyString()))
                .then((Answer<Void>) invocationOnMock -> {
            throw new NotFoundException("Erreur");
        });
        assertNull(controller.findByNomPrenom("Nom","Prenom"));
    }

    @Test
    public void findByNom(){
        //GIVEN
        List<Commande> vList = new ArrayList<>();
        initList(vList);
        when(dao.findByNom(anyString())).thenReturn(vList);

        // WHEN
        List<Commande> vListTest = controller.findByNom("Nom");

        //THEN
        assertEquals(vListTest.size(),2);
        when(dao.findByNom(anyString()))
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.findByNom("Nom"));
    }


    @Test
    public void findByNomEmail(){

        //GIVEN
        List<Commande> vList = new ArrayList<>();
        initList(vList);
        when(dao.findByNomEmail(anyString()
                ,anyString())).thenReturn(vList);

        // WHEN
        List<Commande> vListTest = controller.findByNomEmail(
                "Nom"
                ,"Email");

        //THEN
        assertEquals(vListTest.size(),2);
        when(dao.findByNomEmail(anyString()
                ,anyString()))
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.findByNomEmail("Nom","Email"));
    }


    @Test
    public void findByPrenomEmail(){

        //GIVEN
        List<Commande> vList = new ArrayList<>();
        initList(vList);
        when(dao.findByPrenomEmail(anyString()
                ,anyString())).thenReturn(vList);

        // WHEN
        List<Commande> vListTest = controller.findByPrenomEmail(
                "Prenom"
                ,"Email");

        //THEN
        assertEquals(vListTest.size(),2);
        when(dao.findByPrenomEmail(anyString()
                ,anyString()))
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.findByPrenomEmail("Prenom","Email"));
    }

    @Test
    public void findByPrenom(){

        //GIVEN
        List<Commande> vList = new ArrayList<>();
        initList(vList);
        when(dao.findByPrenom(anyString())).thenReturn(vList);

        // WHEN
        List<Commande> vListTest = controller.findByPrenom("Prenom");

        //THEN
        assertEquals(vListTest.size(),2);
        when(dao.findByPrenom(anyString()))
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.findByPrenom("Prenom"));
    }

    @Test
    public void findByEmail(){
        //GIVEN
        List<Commande> vList = new ArrayList<>();
        initList(vList);
        when(dao.findByEmail(anyString())).thenReturn(vList);

        // WHEN
        List<Commande> vListTest = controller.findByEmail("Email");

        //THEN
        assertEquals(vListTest.size(),2);
        when(dao.findByEmail(anyString()))
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.findByEmail("Email"));
    }

    @Test
    public void findCommandeByNumeroContaining(){
        //GIVEN
        List<Commande> vList = new ArrayList<>();
        initList(vList);
        when(dao.findByNumeroContaining(anyString())).thenReturn(vList);

        // WHEN
        List<Commande> vListTest = controller.findCommandeByNumeroContaining("Numero");

        //THEN
        assertEquals(vListTest.size(),2);
        when(dao.findByNumeroContaining(anyString()))
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.findCommandeByNumeroContaining("Numero"));
    }


    @Test
    public void getCommandeByCompteId(){

        //GIVEN
        List<Commande> vList = new ArrayList<>();
        initList(vList);
        when(dao.findByCompteId(anyInt())).thenReturn(vList);

        // WHEN
        List<Commande> vListTest = controller.getCommandeByCompteId(1);

        //THEN
        assertEquals(vListTest.size(),2);
        when(dao.findByCompteId(anyInt()))
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.getCommandeByCompteId(1));
    }


    @Test
    public void getCommandeByStatutId(){

        //GIVEN
        List<Commande> vList = new ArrayList<>();
        initList(vList);
        when(dao.findByStatutId(anyInt())).thenReturn(vList);

        // WHEN
        List<Commande> vListTest = controller.getCOmmandeByStatutId(1);

        //THEN
        assertEquals(vListTest.size(),2);
        when(dao.findByStatutId(anyInt()))
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.getCOmmandeByStatutId(1));
    }

    @Test
    public void getListCommande(){
        //GIVEN
        List<Commande> vList = new ArrayList<>();
        initList(vList);
        when(dao.findAll()).thenReturn(vList);

        // WHEN
        List<Commande> vListTest = controller.getListCommande();

        //THEN
        assertEquals(vListTest.size(),2);
        when(dao.findAll())
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.getListCommande());
    }

    @Test
    public void add(){

        // GIVEN
        Commande commande = new Commande();
        commande.setNumero("Numero");
        List<Commande> vList = new ArrayList<>();
        reset(dao);
        when(dao.save(any(Commande.class))).then((Answer<Void>) invocationOnMock -> {
            vList.add(commande);
            return null;
        });

        // WHEN
        controller.add(commande);

        // THEN
        assertEquals(vList.size(),1);
        when(dao.save(any(Commande.class))).then((Answer<Void>) invocationOnMock -> {
            throw new NotFoundException("Erreur");
        });
        controller.add(commande);
        assertEquals(vList.size(),1);
    }

    @Test
    public void update(){

        // GIVEN
        Commande commande = new Commande();
        commande.setNumero("Numero");
        reset(dao);
        when(dao.save(any(Commande.class))).then((Answer<Void>) invocationOnMock -> {
            commande.setNumero("updateCommande");
            return null;
        });

        // WHEN
        controller.update(commande);

        // THEN
        assertEquals(commande.getNumero(),"updateCommande");
        commande.setNumero("testThrow");
        when(dao.save(any(Commande.class))).then((Answer<Void>) invocationOnMock -> {
            throw new NotFoundException("Erreur");
        });
        controller.update(commande);
        assertEquals(commande.getNumero(),"testThrow");
    }

    public void initList(List<Commande> list){
        Commande commande1 = new Commande();
        commande1.setNumero("Numero");
        commande1.setId(3);
        Commande commande2 = new Commande();
        commande2.setNumero("Numero");
        commande2.setId(1);
        list.add(commande1);
        list.add(commande2);
    }


    @Before
    public void init(){
        controller = new CommandeControllerFake();
        dao = mock(CommandeDao.class);
        logger = mock(Logger.class);
        doNothing().when(logger).warn(anyString());
        doNothing().when(logger).error(anyString());
    }

    public class CommandeControllerFake extends CommandeController {
        @Override
        protected Logger getLogger() {
            return logger;
        }

        @Override
        protected CommandeDao getCommandeDao() {
            return dao;
        }
    }
}
