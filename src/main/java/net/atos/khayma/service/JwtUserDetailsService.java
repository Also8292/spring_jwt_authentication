package net.atos.khayma.service;

import net.atos.khayma.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UtilisateurService utilisateurService;

    @Autowired
    public JwtUserDetailsService(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        Utilisateur utilisateur = utilisateurService.findUtilisateurByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return Utilisateur.build(utilisateur);
    }
}
