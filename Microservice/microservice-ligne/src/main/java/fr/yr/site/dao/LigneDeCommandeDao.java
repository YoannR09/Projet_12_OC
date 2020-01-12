package fr.yr.site.dao;

import fr.yr.site.model.LigneDeCommande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneDeCommandeDao extends JpaRepository<LigneDeCommandeDao,Integer> {

    LigneDeCommande findById(int id);

    void save(LigneDeCommande ldc);
}
