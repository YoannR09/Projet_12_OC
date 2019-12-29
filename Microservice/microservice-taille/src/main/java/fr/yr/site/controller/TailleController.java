package fr.yr.site.controller;

import fr.yr.site.dao.TailleDao;
import fr.yr.site.model.Taille;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TailleController {

    @Autowired
    TailleDao dao;

    @GetMapping(value = "/Taille/{id}")
    public Taille findById(@PathVariable int id){
        try {
            return dao.findById(id);
        }catch (Exception e){
            return null;
        }
    }
    @GetMapping(value = "/Taille/Taille/{taille}")
    public Taille findByTaille(@PathVariable String taille){
        try {
            return dao.findByTaille(taille);
        }catch (Exception e){
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
            return dao.findAll();
        }catch (Exception e){
            return null;
        }
    }
}
