package fr.yr.site.dao;

import fr.yr.site.model.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanierDao extends JpaRepository<Panier, Integer> {

    Panier findById(int id);
    Panier findByCompteId(int compteId);
}
