package fr.yr.site.controller;

import fr.yr.site.dao.ListTailleDao;
import fr.yr.site.model.ListTaille;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListTailleController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private ListTailleDao dao;

    @GetMapping(value = "/ListTaille/{id}")
    public ListTaille findById(@PathVariable int id){
        try {
            return dao.findById(id);
        }catch (Exception e){
            logger.error(e);
            return null;
        }
    }


    @GetMapping(value = "/ListTaille/Categorie/{categorieId}")
    public List<ListTaille> findByCategorieId(@PathVariable int categorieId){
        try {
            return dao.findByCategorieId(categorieId);
        }catch (Exception e){
            logger.error(e);
            return null;
        }
    }
}
