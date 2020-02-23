package fr.yr.site.controller;

import fr.yr.site.dao.TailleDao;
import fr.yr.site.model.Taille;
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

public class TailleControllerTest {

    private Logger logger;
    private TailleController controller;
    private TailleDao dao;

    @Test
    public void findById(){
        //GIVEN
        Taille taille = new Taille();
        taille.setId(1);
        when(dao.findById(anyInt())).thenReturn(taille);

        // WHEN
        Taille tailleTest = controller.findById(1);

        //THEN
        assertEquals(tailleTest.getId(),new Integer(1));
        when(dao.findById(anyInt()))
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.findById(1));
    }

    @Test
    public void findByTaille(){
        //GIVEN
        Taille taille = new Taille();
        taille.setId(1);
        when(dao.findByTaille(anyString())).thenReturn(taille);

        // WHEN
        Taille tailleTest = controller.findByTaille("XL");

        //THEN
        assertEquals(tailleTest.getId(),new Integer(1));
        when(dao.findByTaille(anyString()))
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.findByTaille("XL"));
    }


    @Test
    public void findAll(){
        //GIVEN
        List<Taille> vList = new ArrayList<>();
        Taille taille1 = new Taille();
        taille1.setId(1);
        Taille taille2 = new Taille();
        taille2.setId(2);
        vList.add(taille1);
        vList.add(taille2);
        when(dao.findAll()).thenReturn(vList);

        // WHEN
        List<Taille> vListTest = controller.findAll();

        //THEN
        assertEquals(vListTest.size(),2);
        when(dao.findAll())
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.findAll());
    }

    @Before
    public void init(){
        controller = new TailleControllerFake();
        dao = mock(TailleDao.class);
        logger = mock(Logger.class);
        doNothing().when(logger).warn(anyString());
        doNothing().when(logger).error(anyString());
    }

    public class TailleControllerFake extends TailleController {
        @Override
        protected TailleDao getDao() {
            return dao;
        }

        @Override
        protected Logger getLogger() {
            return logger;
        }
    }
}
