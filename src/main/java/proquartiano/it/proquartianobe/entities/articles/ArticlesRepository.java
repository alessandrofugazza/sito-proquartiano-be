package proquartiano.it.proquartianobe.entities.articles;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArticlesRepository extends JpaRepository<Article, UUID> {
    //    @Query("SELECT a FROM Article a JOIN a.categories c WHERE c.name = :categoryName")
//    public Set<Article> findByCategory(String categoryName);
    @Query("SELECT a FROM Article a WHERE LOWER(a.title) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Article> findByTitleContainingIgnoreCase(String query, Pageable pageable);

    Page<Article> findByCategories_Name(String category, Pageable pageable);

    Page<Article> findByTags_Name(String tag, Pageable pageable);

    @Query("SELECT a FROM Article a WHERE a.author.signature = :signature")
    Page<Article> findByAuthor_Username(String signature, Pageable pageable);
}
