package fr.yr.site.alegia.configuration;

import fr.yr.site.alegia.proxies.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Factory {

    @Autowired
    private MicroserviceLigneDeCommandeProxy microserviceLigneDeCommandeProxy;
    @Autowired
    private MicroserviceCommandeProxy microserviceCommandeProxy;
    @Autowired
    private MicroserviceArticleProxy microserviceArticleProxy;
    @Autowired
    private MicroserviceCompteProxy microserviceCompteProxy;
    @Autowired
    private MicroservicePanierProxy microservicePanierProxy;
    @Autowired
    private MicroserviceContenuPanierProxy microserviceContenuPanierProxy;
    @Autowired
    MicroserviceCategorie microserviceCategorie;
    @Autowired
    private MicroserviceImageProxy microserviceImageProxy;
    @Autowired
    private MicroserviceAdresseProxy microserviceAdresseProxy;
    @Autowired
    private MicroserviceTailleProxy microserviceTailleProxy;
    @Autowired
    private MicroserviceListTaille microserviceListTaille;
    @Autowired
    private MicroserviceNiveauAccesProxy microserviceNiveauAccesProxy;

    public MicroserviceLigneDeCommandeProxy getLigneProxy() {
        return microserviceLigneDeCommandeProxy;
    }

    public MicroserviceCommandeProxy getCommandeProxy() {
        return microserviceCommandeProxy;
    }

    public MicroserviceArticleProxy getArticleProxy() {
        return microserviceArticleProxy;
    }

    public MicroserviceCompteProxy getCompteProxy() {
        return microserviceCompteProxy;
    }

    public MicroservicePanierProxy getPanierProxy() {
        return microservicePanierProxy;
    }

    public MicroserviceContenuPanierProxy getContenuProxy() {
        return microserviceContenuPanierProxy;
    }

    public MicroserviceCategorie getCategorieProxy() {
        return microserviceCategorie;
    }

    public MicroserviceImageProxy getImageProxy() {
        return microserviceImageProxy;
    }

    public MicroserviceAdresseProxy getAdresseProxy() {
        return microserviceAdresseProxy;
    }

    public MicroserviceTailleProxy getTailleProxy() {
        return microserviceTailleProxy;
    }

    public MicroserviceListTaille getListTailleProxy() {
        return microserviceListTaille;
    }

    public MicroserviceNiveauAccesProxy getNiveauAccesProxy() {
        return microserviceNiveauAccesProxy;
    }
}
