package fr.yr.site.controller;

import fr.yr.site.dao.LigneDeCommandeDao;
import fr.yr.site.model.LigneDeCommande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LigneDeCommandeController {

    @Autowired
    LigneDeCommandeDao dao;


    @GetMapping(value = "/Ligne/{id}")
    public LigneDeCommande findById(@PathVariable int id){
        try {
             return dao.findById(id);
        }catch (Exception e){
            return null;
        }
    }

    @PostMapping(value = "/Ligne")
    public void add(@RequestBody LigneDeCommande ldc){
        try {
            dao.save(ldc);
        }catch (Exception e){

        }
    }
}
