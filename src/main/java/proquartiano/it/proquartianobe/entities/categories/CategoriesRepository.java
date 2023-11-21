package proquartiano.it.proquartianobe.entities.categories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoriesRepository extends JpaRepository<Category, UUID> {
    Optional<Category> findByName(String name);
}
