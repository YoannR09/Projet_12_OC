package fr.yr.site.controller;

import fr.yr.site.dao.ListTailleDao;
import fr.yr.site.model.ListTaille;
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

public class ListTailleControllerTest {


    private ListTailleController controller;
    private Logger logger;
    private ListTailleDao dao;

    @Test
    public void findById(){
        //GIVEN
        ListTaille lt = new ListTaille();
        lt.setId(1);
        when(dao.findById(anyInt())).thenReturn(lt);

        // WHEN
        ListTaille ltTest = controller.findById(1);

        //THEN
        assertEquals(ltTest.getId(),new Integer(1));
        when(dao.findById(anyInt()))
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.findById(2));
    }


    @Test
    public void findByCategorieId(){
        //GIVEN
        List<ListTaille> vList = new ArrayList<>();
        ListTaille lt1 = new ListTaille();
        lt1.setId(1);
        ListTaille lt2 = new ListTaille();
        lt2.setId(2);
        vList.add(lt1);
        vList.add(lt2);
        when(dao.findByArticleId(anyInt())).thenReturn(vList);

        // WHEN
        List<ListTaille> vListTest = controller.findByCategorieId(2);

        //THEN
        assertEquals(vListTest.size(),2);
        when(dao.findByArticleId(anyInt()))
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.findByCategorieId(2));
    }


    @Test
    public void add(){
        // GIVEN
        ListTaille lt = new ListTaille();
        lt.setId(4);
        List<ListTaille> vList = new ArrayList<>();
        reset(dao);
        when(dao.save(any(ListTaille.class))).then((Answer<Void>) invocationOnMock -> {
            vList.add(lt);
            return null;
        });

        // WHEN
        controller.add(lt);

        // THEN
        assertEquals(vList.size(),1);
        when(dao.save(any(ListTaille.class))).then((Answer<Void>) invocationOnMock -> {
            throw new NotFoundException("Erreur");
        });
        controller.add(lt);
        assertEquals(vList.size(),1);
    }


    @Test
    public void deleteByArticleId(){
        // GIVEN
        ListTaille lt = new ListTaille();
        lt.setId(4);
        List<ListTaille> vList = new ArrayList<>();
        vList.add(lt);
        reset(dao);
        doAnswer((Answer<Void>) invocationOnMock -> {
            vList.remove(0);
            return null;
        }).when(dao).deleteByArticleId(anyInt());

        // WHEN
        controller.deleteByArticleId(4);

        // THEN
        assertEquals(vList.size(),0);
        vList.add(lt);
        doAnswer((Answer<Void>) invocationOnMock -> {
            throw new NotFoundException("Erreur");
        }).when(dao).deleteById(anyInt());
        controller.add(lt);
        assertEquals(vList.size(),1);
    }


    @Before
    public void init(){
        controller = new ListTailleControllerFake();
        dao = mock(ListTailleDao.class);
        logger = mock(Logger.class);
        doNothing().when(logger).warn(anyString());
        doNothing().when(logger).error(anyString());
    }



    public class ListTailleControllerFake extends ListTailleController {
        @Override
        protected Logger getLogger() {
            return logger;
        }

        @Override
        protected ListTailleDao getDao() {
            return dao;
        }
    }
}
