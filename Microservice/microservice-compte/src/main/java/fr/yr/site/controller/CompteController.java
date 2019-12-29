package fr.yr.site.controller;

import fr.yr.site.dao.CompteDao;
import fr.yr.site.model.Compte;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompteController {

    @Autowired
    private CompteDao dao;

    private static final Logger logger = LogManager.getLogger();


    @GetMapping(value = "/Compte/{id}")
    public Compte findById(@PathVariable int id){
        try {
            return dao.findById(id);
        }catch (Exception e){
            return null;
        }
    }

    @GetMapping(value = "/Compte/Email/{email}")
    public Compte findByEmail(@PathVariable String email){
        try {
            return dao.findByEmail(email);
        }catch (Exception e){
            return null;
        }
    }
}
