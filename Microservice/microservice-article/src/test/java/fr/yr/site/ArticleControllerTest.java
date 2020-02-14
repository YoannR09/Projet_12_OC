package fr.yr.site;

import fr.yr.site.controller.ArticleController;
import fr.yr.site.dao.ArticleDao;
import fr.yr.site.model.Article;
import javassist.NotFoundException;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ArticleControllerTest {

    private ArticleDao dao;
    private Logger logger;
    private ArticleController controller;

    @Test
    public void getArticle(){

        // GIVEN
        Article article = new Article();
        article.setNom("test");
        when(dao.findById(anyInt())).thenReturn(article);

        // WHEN
        Article articleTest = controller.getArticle(5);

        // THEN
        assertEquals(articleTest.getNom(),"test");
    }

    @Test
    public void getArticleByCategorieId(){

        // GIVEN
        Article article1 = new Article();
        article1.setNom("Article1");
        article1.setCategorieId(2);
        Article article2 = new Article();
        article2.setNom("Article2");
        article2.setCategorieId(2);
        List<Article> vList = new ArrayList<>();
        vList.add(article1);
        vList.add(article2);
        when(dao.findByCategorieId(anyInt())).thenReturn(vList);

        // WHEN
        List<Article> listTest = controller.getArticleByCategorieId(2);

        // THEN
        assertEquals(listTest.get(0).getCategorieId(),new Integer(2));
        assertEquals(listTest.size(),2);
        assertEquals(listTest.toString(),vList.toString());
    }



    @Test
    public void findAll(){

        // GIVEN
        Article article1 = new Article();
        Article article2 = new Article();
        List<Article> vList = new ArrayList<>();
        vList.add(article1);
        vList.add(article2);
        when(dao.findAll()).thenReturn(vList);

        // WHEN
        List<Article> list = controller.findAll();

        // THEN
        assertEquals(list.size(),2);
    }

    @Test
    public void findByCategorieIdAndDisponible(){

        // GIVEN
        Article article1 = new Article();
        Article article2 = new Article();
        List<Article> vList = new ArrayList<>();
        vList.add(article1);
        vList.add(article2);
        when(dao.findByCategorieIdAndDisponible(anyInt(),anyBoolean())).thenReturn(vList);

        // WHEN
        List<Article> list = controller.findByCategorieIdAndDisponible(3,true);

        // THEN
        assertEquals(list.size(),2);
    }

    @Test
    public void findByDisponibleOrderById(){

        // GIVEN
        Article article1 = new Article();
        Article article2 = new Article();
        List<Article> vList = new ArrayList<>();
        vList.add(article1);
        vList.add(article2);
        when(dao.findAll()).thenReturn(vList);

        // WHEN
        List<Article> list = controller.findAll();

        // THEN
        assertEquals(list.size(),2);
    }


    @Test
    public void add(){

        // GIVEN
        Article article = new Article();
        article.setNom("ArticleTest");
        List<Article> vList = new ArrayList<>();
        reset(dao);
        when(dao.save(any(Article.class))).then((Answer<Void>) invocationOnMock -> {
            vList.add(article);
            return null;
        });

        // WHEN
        controller.add(article);

        // THEN
        assertEquals(vList.size(),1);
        when(dao.save(any(Article.class))).then((Answer<Void>) invocationOnMock -> {
            throw new NotFoundException("Erreur");
        });
        controller.add(article);
        assertEquals(vList.size(),1);
    }

    @Test
    public void update(){
        // GIVEN
        Article article = new Article();
        article.setNom("ArticleTest");
        reset(dao);
        when(dao.save(any(Article.class))).then((Answer<Void>) invocationOnMock -> {
            article.setNom("UpdateArticle");
            return null;
        });

        // WHEN
        controller.update(article);

        // THEN
        assertEquals(article.getNom(),"UpdateArticle");
        when(dao.save(any(Article.class))).then((Answer<Void>) invocationOnMock -> {
            throw new NotFoundException("Erreur");
        });
        article.setNom("ExceptionArticle");
        controller.update(article);
        assertEquals(article.getNom(),"ExceptionArticle");
    }

    @Before
    public void init() {
        controller = new ArticleControllerFake();
        dao = mock(ArticleDao.class);
        logger = mock(Logger.class);
        doNothing().when(logger).warn(anyString());
    }

    public class ArticleControllerFake extends ArticleController {
        @Override
        protected ArticleDao getArticleDao() {
            return dao;
        }

        @Override
        protected Logger getLogger() {
            return logger;
        }
    }
}
