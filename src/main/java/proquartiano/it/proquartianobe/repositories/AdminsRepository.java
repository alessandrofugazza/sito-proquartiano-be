package proquartiano.it.proquartianobe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proquartiano.it.proquartianobe.entities.Admin;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminsRepository extends JpaRepository<Admin, UUID> {
    Optional<Admin> findByUsername(String username);

    Optional<Admin> findByEmail(String email);
}
