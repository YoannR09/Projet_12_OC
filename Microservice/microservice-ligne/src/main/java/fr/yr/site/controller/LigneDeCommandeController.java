package fr.yr.site.controller;

import fr.yr.site.dao.LigneDeCommandeDao;
import fr.yr.site.model.LigneDeCommande;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LigneDeCommandeController {

    @Autowired
    private LigneDeCommandeDao dao;

    private static final Logger logger = LogManager.getLogger();

    @GetMapping(value = "/Ligne/{id}")
    public LigneDeCommande findById(@PathVariable int id){
        try {
            return getDao().findById(id);
        }catch (Exception e){
            getLogger().error(e);
            return null;
        }
    }

    @GetMapping(value = "/Ligne")
    public List<LigneDeCommande> findAll(){
        try {
            return getDao().findAll();
        }catch (Exception e){
            getLogger().error(e);
            return null;
        }
    }

    @GetMapping(value = "/Ligne/Commande/{commandeId}")
    public List<LigneDeCommande> findByCommandeId(@PathVariable int commandeId){
        try {
            return getDao().findByCommandeId(commandeId);
        }catch (Exception e){
            getLogger().error(e);
            return null;
        }
    }

    @PostMapping(value = "/Ligne")
    public void add(@RequestBody LigneDeCommande ldc){
        try {
            getDao().save(ldc);
        }catch (Exception e){
            getLogger().error(e);
        }
    }

    protected LigneDeCommandeDao getDao() {
        return dao;
    }

    protected Logger getLogger() {
        return logger;
    }
}
