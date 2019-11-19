package fr.yr.site.controller;

import fr.yr.site.dao.CategorieDao;
import fr.yr.site.model.Categorie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategorieController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private CategorieDao dao;

    @GetMapping(value = "/Categorie/{id}")
    public Categorie findById(@PathVariable int id){
        try {
            return dao.findById(id);
        }catch (Exception e){
            logger.error(e);
            return null;
        }
    }

    @GetMapping(value = "/Categorie")
    public List<Categorie> findAll(){
        try {
            return dao.findAll();
        }catch (Exception e){
            logger.error(e);
            return null;
        }
    }
}
