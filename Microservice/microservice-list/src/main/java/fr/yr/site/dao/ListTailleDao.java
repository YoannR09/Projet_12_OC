package fr.yr.site.dao;

import fr.yr.site.model.ListTaille;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListTailleDao extends JpaRepository<ListTaille,Integer> {

    ListTaille findById(int id);

    List<ListTaille> findByCategorieId(int categorieId);
}
