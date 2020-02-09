package fr.yr.site.controller;

import fr.yr.site.dao.FactureDao;
import fr.yr.site.model.Facture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FactureController {

    @Autowired
    private FactureDao dao;

    @GetMapping(value = "/Facture/{id}")
    public Facture getById(@PathVariable int id){
        try {
            return dao.getById(id);
        }catch (Exception e){
            return null;
        }
    }

    @GetMapping(value = "/Facture/Commande/{commandeId}")
    public Facture getByCoAndCommandeId(@PathVariable int commandeId){
        try {
            return dao.getByCoAndCommandeId(commandeId);
        }catch (Exception e){
            return null;
        }
    }

    @PostMapping(value = "/Facture")
    public void add(@RequestBody Facture facture){
        try {
            dao.save(facture);
        }catch (Exception e){

        }
    }

    @PutMapping(value = "/Facture")
    public void update(@RequestBody Facture facture){
        try {
            dao.save(facture);
        }catch (Exception e){

        }
    }
}
