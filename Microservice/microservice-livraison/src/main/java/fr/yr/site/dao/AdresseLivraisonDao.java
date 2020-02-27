package fr.yr.site.dao;

import fr.yr.site.model.AdresseLivraison;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdresseLivraisonDao extends JpaRepository<AdresseLivraison, Integer> {

    AdresseLivraison findById(int id);
    List<AdresseLivraison> findByCompteId(int compteId);
}
