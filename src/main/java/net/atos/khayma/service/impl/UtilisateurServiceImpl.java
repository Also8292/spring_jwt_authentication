package net.atos.khayma.service.impl;

import net.atos.khayma.model.Utilisateur;
import net.atos.khayma.service.UtilisateurService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {
    @Override
    public Optional<Utilisateur> findUtilisateurByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public List<Utilisateur> findAgentByProfil(String profil) {
        return null;
    }
}
