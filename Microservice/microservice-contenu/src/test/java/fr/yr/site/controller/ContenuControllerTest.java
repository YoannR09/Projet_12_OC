package fr.yr.site.controller;

import fr.yr.site.dao.ContenuDao;
import fr.yr.site.model.Contenu;
import javassist.NotFoundException;
import org.apache.logging.log4j.core.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class ContenuControllerTest {

    private Logger logger;
    private ContenuDao dao;
    private ContenuController controller;

    @Test
    public void findById(){
        //GIVEN
        Contenu contenu = new Contenu();
        contenu.setId(1);
        when(dao.findById(anyInt())).thenReturn(contenu);

        // WHEN
        Contenu contenuTest = controller.findById(1);

        //THEN
        assertEquals(contenuTest.getId(),new Integer(1));
        when(dao.findById(anyInt()))
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.findById(2));
    }


    @Test
    public void findByPanierId(){
        //GIVEN
        List<Contenu> vList = new ArrayList<>();
        Contenu contenu1 = new Contenu();
        contenu1.setId(1);
        Contenu contenu2 = new Contenu();
        contenu2.setId(2);
        vList.add(contenu1);
        vList.add(contenu2);
        when(dao.findByPanierId(anyInt())).thenReturn(vList);

        // WHEN
        List<Contenu> vListTest = controller.findByPanierId(2);

        //THEN
        assertEquals(vListTest.size(),2);
        when(dao.findByPanierId(anyInt()))
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.findByPanierId(2));
    }


    @Test
    public void add(){
        // GIVEN
        Contenu contenu = new Contenu();
        contenu.setId(4);
        List<Contenu> vList = new ArrayList<>();
        reset(dao);
        when(dao.save(any(Contenu.class))).then((Answer<Void>) invocationOnMock -> {
            vList.add(contenu);
            return null;
        });

        // WHEN
        controller.add(contenu);

        // THEN
        assertEquals(vList.size(),1);
        when(dao.save(any(Contenu.class))).then((Answer<Void>) invocationOnMock -> {
            throw new NotFoundException("Erreur");
        });
        controller.add(contenu);
        assertEquals(vList.size(),1);
    }

    @Test
    public void update(){
        // GIVEN
        Contenu contenu = new Contenu();
        contenu.setId(4);
        contenu.setQuantite(2);
        reset(dao);
        when(dao.save(any(Contenu.class))).then((Answer<Void>) invocationOnMock -> {
            contenu.setQuantite(5);
            return null;
        });

        // WHEN
        controller.update(contenu);

        // THEN
        assertEquals(contenu.getQuantite(),new Integer(5));
        contenu.setQuantite(7);
        when(dao.save(any(Contenu.class))).then((Answer<Void>) invocationOnMock -> {
            throw new NotFoundException("Erreur");
        });
        controller.update(contenu);
        assertEquals(contenu.getQuantite(),new Integer(7));
    }


    @Test
    public void delete(){
        // GIVEN
        Contenu contenu = new Contenu();
        contenu.setId(4);
        List<Contenu> vList = new ArrayList<>();
        vList.add(contenu);
        reset(dao);
        doAnswer((Answer<Void>) invocationOnMock -> {
            vList.remove(0);
            return null;
            }).when(dao).deleteById(anyInt());

        // WHEN
        controller.delete(2);

        // THEN
        assertEquals(vList.size(),0);
        vList.add(contenu);
        doAnswer((Answer<Void>) invocationOnMock -> {
            throw new NotFoundException("Erreur");
        }).when(dao).deleteById(anyInt());
        controller.add(contenu);
        assertEquals(vList.size(),1);
    }

    @Before
    public void init(){
        controller = new ContenuControllerFake();
        dao = mock(ContenuDao.class);
        logger = mock(Logger.class);
        doNothing().when(logger).warn(anyString());
        doNothing().when(logger).error(anyString());
    }

    public class ContenuControllerFake extends ContenuController {
        @Override
        protected ContenuDao getDao() {
            return dao;
        }

        @Override
        protected org.apache.logging.log4j.Logger getLogger() {
            return logger;
        }
    }
}
