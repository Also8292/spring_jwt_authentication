package net.atos.khayma.repository;

import net.atos.khayma.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UtilisateurRepository extends JpaRepository <Utilisateur, Long> {

    public UserDetails findOneByEmail(String email);
}
