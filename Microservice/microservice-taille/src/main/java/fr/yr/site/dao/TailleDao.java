package fr.yr.site.dao;

import fr.yr.site.model.Taille;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TailleDao extends JpaRepository<Taille,Integer> {

    Taille findById(int id);
    Taille findByTaille(String taille);
    List<Taille> findAll();
}
