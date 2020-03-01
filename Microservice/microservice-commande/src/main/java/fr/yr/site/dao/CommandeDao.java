package fr.yr.site.dao;

import fr.yr.site.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommandeDao extends JpaRepository<Commande, Integer> {

    Commande findById(int id);

    Commande findByNumero(String numero);

    List<Commande> findByCompteIdOrderByIdDesc(int compteId);

    List<Commande> findByStatutIdOrderByIdDesc(int statutId);

    List<Commande> findByNumeroContainingOrderByIdDesc(String numero);

    @Query(value = "SELECT * FROM commande,compte WHERE nom LIKE %:nom%"+
            " AND prenom LIKE %:prenom%" +
            " AND email LIKE %:email%" +
            " AND commande.compte_id = compte.id ", nativeQuery = true)
    List<Commande> findByNomPrenomEmail(@Param("nom") String nom,
                                        @Param("prenom") String prenom,
                                        @Param("email") String email);


    @Query(value = "SELECT * FROM commande,compte WHERE nom LIKE %:nom%"+
            " AND prenom LIKE %:prenom%" +
            " AND commande.compte_id = compte.id", nativeQuery = true)
    List<Commande> findByNomPrenom(@Param("nom") String nom,
                                        @Param("prenom") String prenom);


    @Query(value = "SELECT * FROM commande,compte WHERE nom LIKE %:nom%"+
            " AND commande.compte_id = compte.id", nativeQuery = true)
    List<Commande> findByNom(@Param("nom") String nom);

    @Query(value = "SELECT * FROM commande,compte WHERE prenom LIKE %:prenom%"+
            " AND commande.compte_id = compte.id", nativeQuery = true)
    List<Commande> findByPrenom(@Param("prenom") String nom);

    @Query(value = "SELECT * FROM commande,compte WHERE email LIKE %:email%"+
            " AND commande.compte_id = compte.id", nativeQuery = true)
    List<Commande> findByEmail(@Param("email") String email);


    @Query(value = "SELECT * FROM commande,compte WHERE nom LIKE %:nom%"+
            " AND email LIKE %:email%" +
            " AND commande.compte_id = compte.id ", nativeQuery = true)
    List<Commande> findByNomEmail(@Param("nom") String nom,
                                        @Param("email") String email);


    @Query(value = "SELECT * FROM commande,compte WHERE "+
            " prenom LIKE %:prenom%" +
            " AND email LIKE %:email%" +
            " AND commande.compte_id = compte.id ", nativeQuery = true)
    List<Commande> findByPrenomEmail(@Param("prenom") String prenom,
                                        @Param("email") String email);


    List<Commande> findCommandeByNumeroContaining(String numero);

    List<Commande> findAll();
}
