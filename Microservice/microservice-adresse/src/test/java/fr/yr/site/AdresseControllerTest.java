package fr.yr.site;

import fr.yr.site.controller.AdresseController;
import fr.yr.site.dao.AdresseDao;
import fr.yr.site.model.Adresse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AdresseControllerTest {

    private AdresseDao dao = mock(AdresseDao.class);
    private AdresseController controller = new AdresseController(dao);

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
}
