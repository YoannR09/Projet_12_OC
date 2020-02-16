package fr.yr.site.controller;

import fr.yr.site.controller.PanierController;
import fr.yr.site.dao.PanierDao;
import fr.yr.site.model.Panier;
import javassist.NotFoundException;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;

public class PanierControllerTest {

    private Logger logger;
    private PanierDao dao;
    private PanierController controller;

    @Test
    public void getPanier(){
        //GIVEN
        Panier panier = new Panier();
        panier.setId(1);
        when(dao.findById(anyInt())).thenReturn(panier);

        // WHEN
        Panier panierTest = controller.getPanier(2);

        //THEN
        assertEquals(panierTest.getId(),new Integer(1));
        when(dao.findById(anyInt()))
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.getPanier(2));
    }

    @Test
    public void getPanierByCompteId(){
        //GIVEN
        Panier panier = new Panier();
        panier.setId(1);
        when(dao.findByCompteId(anyInt())).thenReturn(panier);

        // WHEN
       Panier panierTest = controller.getPanierByCompteId(2);

        //THEN
        assertEquals(panierTest.getId(),new Integer(1));
        when(dao.findByCompteId(anyInt()))
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.getPanierByCompteId(2));
    }

    @Test
    public void add(){
        // GIVEN
        Panier panier = new Panier();
        panier.setId(4);
        List<Panier> vList = new ArrayList<>();
        reset(dao);
        when(dao.save(any(Panier.class))).then((Answer<Void>) invocationOnMock -> {
            vList.add(panier);
            return null;
        });

        // WHEN
        controller.add(panier);

        // THEN
        assertEquals(vList.size(),1);
        when(dao.save(any(Panier.class))).then((Answer<Void>) invocationOnMock -> {
            throw new NotFoundException("Erreur");
        });
        controller.add(panier);
        assertEquals(vList.size(),1);
    }

    @Before
    public void init(){
        controller = new PanierControllerFake();
        dao = mock(PanierDao.class);
        logger = mock(org.apache.logging.log4j.core.Logger.class);
        doNothing().when(logger).warn(anyString());
        doNothing().when(logger).error(anyString());
    }

    public class PanierControllerFake extends PanierController {
        @Override
        protected Logger getLogger() {
            return logger;
        }

        @Override
        protected PanierDao getDao() {
            return dao;
        }
    }
}
