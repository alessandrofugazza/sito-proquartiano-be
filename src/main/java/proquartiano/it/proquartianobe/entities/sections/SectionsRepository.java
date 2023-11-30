package proquartiano.it.proquartianobe.entities.sections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proquartiano.it.proquartianobe.enums.ESection;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SectionsRepository extends JpaRepository<Section, UUID> {
    Optional<Section> findByName(ESection name);
}
