package fr.yr.site.dao;

import fr.yr.site.model.LigneDeCommande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneDeCommandeDao extends JpaRepository<LigneDeCommande,Integer> {

    LigneDeCommande findById(int id);

    List<LigneDeCommande> findAll();

    List<LigneDeCommande> findByCommandeId(int commandeId);
}
