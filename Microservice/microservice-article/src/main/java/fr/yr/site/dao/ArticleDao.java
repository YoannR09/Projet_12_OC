package fr.yr.site.dao;

import fr.yr.site.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleDao extends JpaRepository<Article,Integer> {

    Article findById(int id);

    Article findByNumero(String numero);

    List<Article> findAll();
}
