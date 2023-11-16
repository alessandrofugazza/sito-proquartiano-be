package proquartiano.it.proquartianobe.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoriesRepository extends JpaRepository<Category, UUID> {
}
