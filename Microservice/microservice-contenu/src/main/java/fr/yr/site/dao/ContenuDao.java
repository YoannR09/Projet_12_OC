package fr.yr.site.dao;

import fr.yr.site.model.Contenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContenuDao extends JpaRepository<Contenu,Integer> {

    Contenu findById(int id);

    List<Contenu> findByPanierId(int panierId);
}
