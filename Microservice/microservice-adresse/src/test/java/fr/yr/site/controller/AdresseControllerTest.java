package fr.yr.site.controller;

import fr.yr.site.controller.AdresseController;
import fr.yr.site.dao.AdresseDao;
import fr.yr.site.model.Adresse;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AdresseControllerTest {

    private AdresseDao dao;
    private AdresseController controller;
    private Logger log;


    @Test
    public void getAdresse(){

        // GIVEN
        Adresse adresse = new Adresse();
        adresse.setCodePostal("83110");
        when(dao.findById(anyInt())).thenReturn(adresse);

        // WHEN
        Adresse adresseTest = controller.getAdresse(4);

        // THEN
        assertEquals(adresseTest.getCodePostal(),83110);
    }

    @Test
    public void findByVilleAndCodePostalAndNumeroAndRue(){

        // GIVEN
        Adresse adresse = new Adresse();
        adresse.setCodePostal("83110");
        when(controller.findByVilleAndCodePostalAndNumeroAndRue(
                anyString()
                ,anyString()
                ,anyString()
                ,anyString())).thenReturn(adresse);

        // WHEN
        Adresse adresseTest = controller.findByVilleAndCodePostalAndNumeroAndRue(
                "Sanary",
                "83110",
                "24",
                "Rue"
        );

        // THEN
        assertEquals(adresse.getCodePostal(),adresseTest.getCodePostal());
    }

    @Before
    public void init() {
        controller = new AdresseControllerFake();
        dao = mock(AdresseDao.class);
        log = mock(Logger.class);
        doNothing().when(log).warn(anyString());
        doNothing().when(log).error(anyString());
    }

    private class AdresseControllerFake extends AdresseController{
        @Override
        protected AdresseDao getDao() {
            return dao;
        }

        @Override
        protected Logger getLogger() {
            return log;
        }
    }
}
