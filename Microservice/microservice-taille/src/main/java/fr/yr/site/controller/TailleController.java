package fr.yr.site.controller;

import fr.yr.site.dao.TailleDao;
import fr.yr.site.model.Taille;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TailleController {

    @Autowired
    private TailleDao dao;

    private static final Logger logger = LogManager.getLogger();

    @GetMapping(value = "/Taille/{id}")
    public Taille findById(@PathVariable int id){
        try {
            return getDao().findById(id);
        }catch (Exception e){
            getLogger().error(e);
            return null;
        }
    }
    @GetMapping(value = "/Taille/Taille/{taille}")
    public Taille findByTaille(@PathVariable String taille){
        try {
            return getDao().findByTaille(taille);
        }catch (Exception e){
            getLogger().error(e);
            return null;
        }
    }

    /**
     * Méthode pour récupérer la liste des tailles exitantes
     * @return
     */
    @GetMapping(value = "/Taille")
    public List<Taille> findAll(){
        try {
            return getDao().findAll();
        }catch (Exception e){
            getLogger().error(e);
            return null;
        }
    }

    protected TailleDao getDao() {
        return dao;
    }

    protected Logger getLogger() {
        return logger;
    }
}
