package fr.yr.site.controller;

import fr.yr.site.dao.LigneDeCommandeDao;
import fr.yr.site.model.LigneDeCommande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/Ligne")
    public List<LigneDeCommande> findAll(){
        try {
            return dao.findAll();
        }catch (Exception e){
            return null;
        }
    }

    @GetMapping(value = "/Ligne/Commande/{commandeId}")
    public List<LigneDeCommande> findByCommandeId(@PathVariable int commandeId){
        try {
            return dao.findByCommandeId(commandeId);
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
