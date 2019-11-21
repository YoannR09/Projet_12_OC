package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionSupport;
import fr.yr.site.alegia.beans.Categorie;
import fr.yr.site.alegia.proxies.MicroserviceCategorie;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GestionLoginAction extends ActionSupport {

    // --- Microservices ---
    @Autowired
    MicroserviceCategorie microserviceCategorie;

    private List<Categorie> categorieList;

    public String pageLogin(){
        try {
            categorieList = microserviceCategorie.findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            return ActionSupport.ERROR;
        }
    }

    //----------- GETTERS ET SETTERS ----------------


    public List<Categorie> getCategorieList() {
        return categorieList;
    }

    public void setCategorieList(List<Categorie> categorieList) {
        this.categorieList = categorieList;
    }
}
