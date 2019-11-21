package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.Categorie;
import fr.yr.site.alegia.proxies.MicroserviceCategorie;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GestionIndexAction extends ActionSupport {

    // --- Microservices ---
    @Autowired
    MicroserviceCategorie microserviceCategorie;

    private     List<Categorie>     categorieList;
    private     Integer             categorieId;

    /**
     * Méthode pour afficher la page d'accueil du site
     * On charge la liste des catégories existante.
     * @return
     */
    public String accueil(){
        String vResult;
        try {
            categorieList = microserviceCategorie.findAll();
            vResult = ActionSupport.SUCCESS;
        }catch (Exception e){
            vResult = ActionSupport.ERROR;
        }
        return vResult;
    }



    //----------- GETTERS ET SETTERS ----------------


    public List<Categorie> getCategorieList() {
        return categorieList;
    }

    public void setCategorieList(List<Categorie> categorieList) {
        this.categorieList = categorieList;
    }

    public Integer getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Integer categorieId) {
        this.categorieId = categorieId;
    }
}
