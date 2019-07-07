package net.atos.khayma.repositories;

import net.atos.khayma.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    public Optional<Utilisateur> findByEmail(String email);
    public Boolean existsByEmail(String email);
//    public List<Utilisateur> findByProfil(String profil);
}
