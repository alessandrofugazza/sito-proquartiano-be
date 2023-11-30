package proquartiano.it.proquartianobe.entities.tags;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TagsRepository extends JpaRepository<Tag, UUID> {
    Optional<Tag> findByName(String name);

    //    @Query(value = "SELECT tags_id, COUNT(tags_id) AS frequency FROM articles_tags GROUP BY tags_id ORDER BY frequency DESC LIMIT 5", nativeQuery = true)
    // todo wtf?
    @Query(value = "SELECT t.name FROM articles_tags at JOIN tags t ON at.tags_id = t.id GROUP BY at.tags_id, t.name ORDER BY COUNT(at.tags_id) DESC LIMIT 5", nativeQuery = true)
    List<String> findMostUsed();
}
