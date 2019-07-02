package net.atos.khayma.repository;

import net.atos.khayma.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository <Utilisateur, Long> {
}
