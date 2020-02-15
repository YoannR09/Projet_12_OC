package fr.yr.site.controller;

import fr.yr.site.dao.CompteDao;
import fr.yr.site.model.Compte;
import javassist.NotFoundException;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class CompteControllerTest {

    private Logger logger;
    private CompteController controller;
    private CompteDao dao;


    @Test
    public void findById(){
        //GIVEN
        Compte compte = new Compte();
        compte.setNom("Compte");
        when(dao.findById(anyInt())).thenReturn(compte);

        // WHEN
        Compte compteTest = controller.findById(1);

        //THEN
        assertEquals(compteTest.getNom(),"Compte");
        when(dao.findById(anyInt()))
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.findById(1));
    }

    @Test
    public void findByEmail(){
        //GIVEN
        Compte compte = new Compte();
        compte.setNom("Compte");
        when(dao.findByEmail(anyString())).thenReturn(compte);

        // WHEN
        Compte compteTest = controller.findByEmail("Email");

        //THEN
        assertEquals(compteTest.getNom(),"Compte");
        when(dao.findByEmail(anyString()))
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.findByEmail("Email"));
    }

    @Test
    public void findAll(){
        //GIVEN
        List<Compte> vList = new ArrayList<>();
        Compte compte1 = new Compte();
        compte1.setNom("Compte1");
        Compte compte2 = new Compte();
        compte2.setNom("Compte2");
        vList.add(compte1);
        vList.add(compte2);
        when(dao.findAll()).thenReturn(vList);

        // WHEN
        List<Compte> vListTest = controller.findAll();

        //THEN
        assertEquals(vListTest.size(),2);
        when(dao.findAll())
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.findAll());
    }

    @Test
    public void add(){
        // GIVEN
        Compte compte = new Compte();
        compte.setNom("Nom");
        compte.setEmail("Email");
        compte.setPrenom("Prenom");
        List<Compte> vList = new ArrayList<>();
        reset(dao);
        when(dao.save(any(Compte.class))).then((Answer<Void>) invocationOnMock -> {
            vList.add(compte);
            return null;
        });

        // WHEN
        controller.add(compte);

        // THEN
        assertEquals(vList.size(),1);
        when(dao.save(any(Compte.class))).then((Answer<Void>) invocationOnMock -> {
            throw new NotFoundException("Erreur");
        });
        controller.add(compte);
        assertEquals(vList.size(),1);
    }


    @Test
    public void update(){
        // GIVEN
        Compte compte = new Compte();
        compte.setNom("Nom");
        reset(dao);
        when(dao.save(any(Compte.class))).then((Answer<Void>) invocationOnMock -> {
            compte.setNom("updateCompte");
            return null;
        });

        // WHEN
        controller.update(compte);

        // THEN
        assertEquals(compte.getNom(),"updateCompte");
        compte.setNom("testThrow");
        when(dao.save(any(Compte.class))).then((Answer<Void>) invocationOnMock -> {
            throw new NotFoundException("Erreur");
        });
        controller.update(compte);
        assertEquals(compte.getNom(),"testThrow");
    }


    @Before
    public void init(){
        controller = new CompteControllerFake();
        dao = mock(CompteDao.class);
        logger = mock(Logger.class);
        doNothing().when(logger).warn(anyString());
        doNothing().when(logger).error(anyString());
    }


    public class CompteControllerFake extends CompteController {
        @Override
        protected CompteDao getDao() {
            return dao;
        }

        @Override
        protected Logger getLogger() {
            return logger;
        }
    }
}
