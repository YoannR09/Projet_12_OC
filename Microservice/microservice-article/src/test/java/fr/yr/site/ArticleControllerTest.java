package fr.yr.site;

import fr.yr.site.controller.ArticleController;
import fr.yr.site.dao.ArticleDao;
import fr.yr.site.model.Article;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArticleControllerTest {

    private ArticleDao dao = mock(ArticleDao.class);
    private ArticleController controller = new ArticleController(dao);

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
    public void getArticleByNumero(){

        // GIVEN
        Article article = new Article();
        article.setNom("test");
        when(dao.findByNumero(anyString())).thenReturn(article);

        // WHEN
        Article articleTest = controller.getArticleByNumero("555e1qda6");

        // THEN
        assertEquals(articleTest.getNom(),"test");
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
}
