package fr.yr.site.dao;

import fr.yr.site.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageDao extends JpaRepository<Image,Integer> {

    List<Image> findByArticleId(int articleId);
}
