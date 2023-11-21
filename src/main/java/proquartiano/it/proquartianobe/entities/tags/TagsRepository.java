package proquartiano.it.proquartianobe.entities.tags;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TagsRepository extends JpaRepository<Tag, UUID> {
    Optional<Tag> findByName(String name);
}
