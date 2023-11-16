package proquartiano.it.proquartianobe.tag;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TagsRepository extends JpaRepository<Tag, UUID> {
}
