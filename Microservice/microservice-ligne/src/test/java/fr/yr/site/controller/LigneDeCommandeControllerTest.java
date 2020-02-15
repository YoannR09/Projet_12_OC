package fr.yr.site.controller;

import fr.yr.site.dao.LigneDeCommandeDao;
import fr.yr.site.model.LigneDeCommande;
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

public class LigneDeCommandeControllerTest {

    private Logger logger;
    private LigneDeCommandeController controller;
    private LigneDeCommandeDao dao;


    @Test
    public void findById(){
        //GIVEN
        LigneDeCommande ldc = new LigneDeCommande();
        ldc.setId(1);
        when(dao.findById(anyInt())).thenReturn(ldc);

        // WHEN
        LigneDeCommande ldcTest = controller.findById(1);

        //THEN
        assertEquals(ldc.getId(),new Integer(1));
        when(dao.findById(anyInt()))
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.findById(1));
    }

    @Test
    public void findAll(){
        //GIVEN
        List<LigneDeCommande> vList = new ArrayList<>();
        LigneDeCommande ldc1 = new LigneDeCommande();
        ldc1.setId(1);
        LigneDeCommande ldc2 = new LigneDeCommande();
        ldc2.setId(2);
        vList.add(ldc1);
        vList.add(ldc2);
        when(dao.findAll()).thenReturn(vList);

        // WHEN
        List<LigneDeCommande> vListTest = controller.findAll();

        //THEN
        assertEquals(vListTest.size(),2);
        when(dao.findAll())
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.findAll());
    }

    @Test
    public void findByCommandeId(){
        //GIVEN
        List<LigneDeCommande> vList = new ArrayList<>();
        LigneDeCommande ldc1 = new LigneDeCommande();
        ldc1.setId(1);
        LigneDeCommande ldc2 = new LigneDeCommande();
        ldc2.setId(2);
        vList.add(ldc1);
        vList.add(ldc2);
        when(dao.findByCommandeId(anyInt())).thenReturn(vList);

        // WHEN
        List<LigneDeCommande> vListTest = controller.findByCommandeId(2);

        //THEN
        assertEquals(vListTest.size(),2);
        when(dao.findByCommandeId(anyInt()))
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.findByCommandeId(2));
    }

    @Test
    public void add(){
        // GIVEN
        LigneDeCommande ldc = new LigneDeCommande();
        ldc.setId(2);
        List<LigneDeCommande> vList = new ArrayList<>();
        reset(dao);
        when(dao.save(any(LigneDeCommande.class))).then((Answer<Void>) invocationOnMock -> {
            vList.add(ldc);
            return null;
        });

        // WHEN
        controller.add(ldc);

        // THEN
        assertEquals(vList.size(),1);
        when(dao.save(any(LigneDeCommande.class))).then((Answer<Void>) invocationOnMock -> {
            throw new NotFoundException("Erreur");
        });
        controller.add(ldc);
        assertEquals(vList.size(),1);
    }

    @Before
    public void init(){
        controller = new LigneDeCommandeControllerFake();
        dao = mock(LigneDeCommandeDao.class);
        logger = mock(Logger.class);
        doNothing().when(logger).warn(anyString());
        doNothing().when(logger).error(anyString());
    }


    public class LigneDeCommandeControllerFake extends LigneDeCommandeController {
        @Override
        protected LigneDeCommandeDao getDao() {
            return dao;
        }

        @Override
        protected Logger getLogger() {
            return logger;
        }
    }
}
