package fr.yr.site.dao;

import fr.yr.site.model.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureDao extends JpaRepository<Facture, Integer> {

    Facture getById(int id);

    Facture getByCoAndCommandeId(int commandeId);
}
