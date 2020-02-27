package fr.yr.site.controller;

import fr.yr.site.dao.AdresseLivraisonDao;
import fr.yr.site.model.AdresseLivraison;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdresseLivraisonController {

    @Autowired
    private AdresseLivraisonDao dao;

    @GetMapping(value = "/Livraison/{id}")
    public AdresseLivraison findById(@PathVariable int id){
        try {
            return dao.findById(id);
        }catch (Exception e){
            return null;
        }
    }

    @GetMapping(value = "/Livraison/Compte/{compteId}")
    public List<AdresseLivraison> findByCompteId(@PathVariable int compteId){
        try {
            return dao.findByCompteId(compteId);
        }catch (Exception e){
            return null;
        }
    }

    @PostMapping(value = "/Livraison")
    public void add(@RequestBody AdresseLivraison adresseLivraison){
        try {
            dao.save(adresseLivraison);
        }catch (Exception e){

        }
    }

    @PutMapping(value = "/Livraison")
    public void update(@RequestBody AdresseLivraison adresseLivraison){
        try {
            dao.save(adresseLivraison);
        }catch (Exception e){

        }
    }

    @DeleteMapping(value = "/Livraison/{id}")
    public void delete(@PathVariable int id){
        try {
            dao.deleteById(id);
        }catch (Exception e){

        }
    }
}
