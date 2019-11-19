package fr.yr.site;

import fr.yr.site.controller.PanierController;
import fr.yr.site.dao.PanierDao;
import fr.yr.site.model.Panier;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PanierControllerTest {

    private PanierDao dao = mock(PanierDao.class);
    private PanierController controller = new PanierController(dao);

    @Test
    public void findById(){

        // GIVEN
        Panier panier = new Panier();
        panier.setCompteId(4);
        when(dao.findById(anyInt())).thenReturn(panier);

        // WHEN
        Panier panierTest = controller.getPanier(2);

        // THEN
        assertEquals(panierTest.getCompteId(),new Integer(4));
    }

    @Test
    public void findByCompteId(){

        // GIVEN
        Panier panier = new Panier();
        panier.setId(4);
        when(dao.findByCompteId(anyInt())).thenReturn(panier);

        // WHEN
        Panier panierTest = controller.getPanierByCompteId(2);

        // THEN
        assertEquals(panierTest.getCompteId(),new Integer(4));
    }
}
