package proquartiano.it.proquartianobe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import proquartiano.it.proquartianobe.entities.Category;

import java.util.UUID;

public interface CategoriesRepository extends JpaRepository<Category, UUID> {
}
