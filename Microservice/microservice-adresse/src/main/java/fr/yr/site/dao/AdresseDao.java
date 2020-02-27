package fr.yr.site.dao;

import fr.yr.site.model.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdresseDao extends JpaRepository<Adresse,Integer> {

    Adresse findById(int id);

    Adresse findByVilleAndCodePostalAndNumeroAndRue(String ville,String codePostal,String numero,String rue);

    @Query(value = "SELECT * FROM adresse,commande WHERE compte_id = :compteId"+
            " AND adresse.id = adresse_id", nativeQuery = true)
    List<Adresse> findOldAdresseByCompteIdAnd(@Param("compteId") int compteId);
}
