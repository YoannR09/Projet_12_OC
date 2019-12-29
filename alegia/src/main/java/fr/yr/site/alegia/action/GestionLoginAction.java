package fr.yr.site.alegia.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.xml.internal.ws.wsdl.writer.document.Import;
import fr.yr.site.alegia.beans.Categorie;
import fr.yr.site.alegia.beans.Compte;
import fr.yr.site.alegia.proxies.MicroserviceCategorie;
import fr.yr.site.alegia.proxies.MicroserviceCompteProxy;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Classe qui gère la connexion en tant que visiteur ou administrateur
 */
public class GestionLoginAction extends ActionSupport implements SessionAware{

    // --- Microservices ---
    @Autowired
    MicroserviceCategorie microserviceCategorie;
    @Autowired
    MicroserviceCompteProxy microserviceCompteProxy;

    private Map<String, Object> session;

    private List<Categorie> categorieList;
    private String email;
    private String motDePasse;
    private Compte compte;

    public String pageLogin(){
        try {
            categorieList = microserviceCategorie.findAll();
            return ActionSupport.SUCCESS;
        }catch (Exception e){
            return ActionSupport.ERROR;
        }
    }

    /**
     * Méthode pour connecter à un compte client ou administrateur.
     * @return
     */
    public String doLogin() throws Exception {

        String vResult = ActionSupport.ERROR;
        categorieList = microserviceCategorie.findAll();

        if (email != null) {
            compte = microserviceCompteProxy.findByEmail(email);
        }
        if (compte == null) {
            this.addActionMessage("Identifiant invalide");
            vResult = ActionSupport.ERROR;
        } else {
            if (compte != null) {
                if (motDePasse.equals(compte.getMotDePasse())) {
                    this.session.put("email", compte.getEmail());
                    if (compte.getNiveauId() == 2) {
                        this.session.put("admin", compte);
                    } else {
                        this.session.put("user", compte);
                    }
                    vResult = ActionSupport.SUCCESS;
                } else {
                    this.addActionMessage("Mot de passe invalide");
                    vResult = ActionSupport.ERROR;
                }
            }

        }
        return vResult;
    }


    //----------- GETTERS ET SETTERS ----------------


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public List<Categorie> getCategorieList() {
        return categorieList;
    }

    public void setCategorieList(List<Categorie> categorieList) {
        this.categorieList = categorieList;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
