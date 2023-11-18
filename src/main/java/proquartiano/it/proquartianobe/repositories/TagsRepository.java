package proquartiano.it.proquartianobe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import proquartiano.it.proquartianobe.entities.Tag;

import java.util.UUID;

public interface TagsRepository extends JpaRepository<Tag, UUID> {
}
