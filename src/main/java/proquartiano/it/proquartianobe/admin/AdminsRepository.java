package proquartiano.it.proquartianobe.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminsRepository extends JpaRepository<Admin, UUID> {
}
