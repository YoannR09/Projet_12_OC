package fr.yr.site.dao;

import fr.yr.site.model.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseDao extends JpaRepository<Adresse,Integer> {

    Adresse findById(int id);

    Adresse findByVilleAndCodePostalAndNumeroAndRue(String ville,String codePostal,String numero,String rue);
}
