package net.atos.khayma.repositories;

import net.atos.khayma.entities.Role;
import net.atos.khayma.entities.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Optional<Role> findByName(RoleName roleName);
}