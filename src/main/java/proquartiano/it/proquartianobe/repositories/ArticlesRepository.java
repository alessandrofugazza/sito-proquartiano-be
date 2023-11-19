package proquartiano.it.proquartianobe.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proquartiano.it.proquartianobe.entities.Article;

import java.util.List;
import java.util.UUID;

@Repository
public interface ArticlesRepository extends JpaRepository<Article, UUID> {
    //    @Query("SELECT a FROM Article a JOIN a.categories c WHERE c.name = :categoryName")
//    public Set<Article> findByCategory(String categoryName);
    @Query("SELECT a FROM Article a WHERE LOWER(a.title) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Article> findByTitleContainingIgnoreCase(String query, Pageable pageable);

}
