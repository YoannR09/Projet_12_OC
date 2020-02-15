package fr.yr.site.controller;

import fr.yr.site.dao.CategorieDao;
import fr.yr.site.model.Categorie;
import javassist.NotFoundException;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

public class CategorieControllerTest {

    private CategorieController controller;
    private CategorieDao dao;
    private Logger logger;

    @Test
    public void findById(){

        // GIVEN
        Categorie categorie = new Categorie();
        categorie.setNom("CategorieTest");
        categorie.setId(1);
        when(dao.findById(anyInt())).thenReturn(categorie);

        // WHEN
        Categorie categorieTest = controller.findById(1);

        // THEN
        assertEquals(categorieTest.getId(),new Integer(1));
        assertEquals(categorieTest.getNom(),"CategorieTest");
    }


    @Test
    public void findByNom(){

        // GIVEN
        Categorie categorie = new Categorie();
        categorie.setNom("Test");
        categorie.setId(1);
        when(dao.findByNom(anyString())).thenReturn(categorie);

        // WHEN
        Categorie categorieTest = controller.findByNom("Test");

        // THEN
        assertEquals(categorieTest.getId(),new Integer(1));
        assertEquals(categorieTest.getNom(),"Test");
    }

    @Test
    public void findAll(){

        // GIVEN
        List<Categorie> vList = new ArrayList<>();
        initList(vList);
        when(dao.findByDisponible(anyBoolean())).thenReturn(vList);

        // WHEN
        List<Categorie> vListTest = controller.findAll();

        // THEN
        assertEquals(vListTest.size(),2);
        assertEquals(vListTest.get(0).getNom(),"Article1");
        when(dao.findByDisponible(anyBoolean())).then((Answer<Void>) invocationOnMock -> {
            throw new NotFoundException("Erreur");
        });
        assertNull(controller.findAll());
    }


    @Test
    public void findByDispo(){

        // GIVEN
        List<Categorie> vList = new ArrayList<>();
        initList(vList);
        when(dao.findByDisponible(anyBoolean())).thenReturn(vList);

        // WHEN
        List<Categorie> vListTest = controller.findByDispo(true);

        // THEN
        assertEquals(vListTest.size(),2);
        assertEquals(vListTest.get(0).getNom(),"Article1");
        when(dao.findByDisponible(anyBoolean())).then((Answer<Void>) invocationOnMock -> {
            throw new NotFoundException("Erreur");
        });
        assertNull(controller.findByDispo(true));
    }


    @Test
    public void add(){

        // GIVEN
        Categorie categorie = new Categorie();
        categorie.setNom("newCategorie");
        List<Categorie> vList = new ArrayList<>();
        reset(dao);
        when(dao.save(any(Categorie.class))).then((Answer<Void>) invocationOnMock -> {
            vList.add(categorie);
            return null;
        });

        // WHEN
        controller.add(categorie);

        // THEN
        assertEquals(vList.size(),1);
        when(dao.save(any(Categorie.class))).then((Answer<Void>) invocationOnMock -> {
            throw new NotFoundException("Erreur");
        });
        controller.add(categorie);
        assertEquals(vList.size(),1);
    }


    @Test
    public void update(){

        // GIVEN
        Categorie categorie = new Categorie();
        categorie.setNom("newCategorie");
        reset(dao);
        when(dao.save(any(Categorie.class))).then((Answer<Void>) invocationOnMock -> {
            categorie.setNom("updateCategorie");
            return null;
        });

        // WHEN
        controller.update(categorie);

        // THEN
        assertEquals(categorie.getNom(),"updateCategorie");
        categorie.setNom("testThrow");
        when(dao.save(any(Categorie.class))).then((Answer<Void>) invocationOnMock -> {
            throw new NotFoundException("Erreur");
        });
        controller.update(categorie);
        assertEquals(categorie.getNom(),"testThrow");
    }






    public void initList(List<Categorie> list){
        Categorie categorie1 = new Categorie();
        categorie1.setNom("Article1");
        Categorie categorie2 = new Categorie();
        categorie2.setNom("Article2");
        list.add(categorie1);
        list.add(categorie2);
    }

    @Before
    public void init(){
        controller = new CategorieControllerFake();
        dao = mock(CategorieDao.class);
        logger = mock(Logger.class);
        doNothing().when(logger).warn(anyString());
        doNothing().when(logger).error(anyString());
    }


    public class CategorieControllerFake extends CategorieController{
        @Override
        protected CategorieDao getDao() {
            return dao;
        }

        @Override
        protected Logger getLogger() {
            return logger;
        }
    }
}
