package fr.yr.site.dao;

import fr.yr.site.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorieDao extends JpaRepository<Categorie,Integer> {

    Categorie findById(int pId);
    Categorie findByNom(String nom);
    List<Categorie> findByDisponible(Boolean dispo);
    List<Categorie> findAll();
}
