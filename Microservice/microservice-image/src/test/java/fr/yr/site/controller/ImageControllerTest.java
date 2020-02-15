package fr.yr.site.controller;

import fr.yr.site.dao.ImageDao;
import fr.yr.site.model.Image;
import javassist.NotFoundException;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class ImageControllerTest {

    private Logger logger;
    private ImageDao dao;
    private ImageController controller;

    @Test
    public void findByArticleId(){
        //GIVEN
        List<Image> vList = new ArrayList<>();
        Image image1 = new Image();
        image1.setId(1);
        Image image2 = new Image();
        image2.setId(2);
        vList.add(image1);
        vList.add(image2);
        when(dao.findByArticleId(anyInt())).thenReturn(vList);

        // WHEN
        List<Image> vListTest = controller.findByArticleId(2);

        //THEN
        assertEquals(vListTest.size(),2);
        when(dao.findByArticleId(anyInt()))
                .then((Answer<Void>) invocationOnMock -> {
                    throw new NotFoundException("Erreur");
                });
        assertNull(controller.findByArticleId(2));
    }


    @Test
    public void add(){
        // GIVEN
        Image image = new Image();
        image.setUrl("Url");
        List<Image> vList = new ArrayList<>();
        reset(dao);
        when(dao.save(any(Image.class))).then((Answer<Void>) invocationOnMock -> {
            vList.add(image);
            return null;
        });

        // WHEN
        controller.add(image);

        // THEN
        assertEquals(vList.size(),1);
        when(dao.save(any(Image.class))).then((Answer<Void>) invocationOnMock -> {
            throw new NotFoundException("Erreur");
        });
        controller.add(image);
        assertEquals(vList.size(),1);
    }

    @Before
    public void init(){
        controller = new ImageControllerFake();
        dao = mock(ImageDao.class);
        logger = mock(org.apache.logging.log4j.core.Logger.class);
        doNothing().when(logger).warn(anyString());
        doNothing().when(logger).error(anyString());
    }

    public class ImageControllerFake extends ImageController {
        @Override
        protected Logger getLogger() {
            return logger;
        }

        @Override
        protected ImageDao getDao() {
            return dao;
        }
    }
}
