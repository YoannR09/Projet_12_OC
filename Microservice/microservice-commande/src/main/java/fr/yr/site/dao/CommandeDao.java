package fr.yr.site.dao;

import fr.yr.site.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeDao extends JpaRepository<Commande, Integer> {

    Commande findById(int id);

    Commande findByNumero(String numero);

    List<Commande> findByCompteId(int compteId);

    List<Commande> findByStatutId(int statutId);

    List<Commande> findAll();
}
