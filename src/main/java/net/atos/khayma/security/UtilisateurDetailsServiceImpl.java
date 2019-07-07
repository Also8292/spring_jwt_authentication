package net.atos.khayma.security;

import net.atos.khayma.entities.Utilisateur;
import net.atos.khayma.services.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UtilisateurDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IUtilisateurService utilisateurService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Utilisateur utilisateur = utilisateurService.findUtilisateurByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Utilisateur Not Found with -> email : " + email)
                );

        return UtilisateurPrinciple.build(utilisateur);
    }
}
