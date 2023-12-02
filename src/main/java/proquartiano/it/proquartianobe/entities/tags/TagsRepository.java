package proquartiano.it.proquartianobe.entities.tags;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TagsRepository extends JpaRepository<Tag, UUID> {
    Optional<Tag> findByName(String name);

    // todo wtf?
    @Query(value = "SELECT t.name FROM articles_tags at JOIN tags t ON at.tag_id = t.id GROUP BY at.tag_id, t.name ORDER BY COUNT(at.tag_id) DESC LIMIT 5", nativeQuery = true)
    List<String> findMostUsed();
}
