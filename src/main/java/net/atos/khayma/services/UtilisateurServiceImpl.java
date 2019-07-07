package net.atos.khayma.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import net.atos.khayma.entities.Role;
import net.atos.khayma.entities.RoleName;
import net.atos.khayma.entities.Utilisateur;
import net.atos.khayma.repositories.UtilisateurRepository;
import net.atos.khayma.request.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UtilisateurServiceImpl implements IUtilisateurService{

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    PasswordEncoder encoder;


    @Override
    public List<Utilisateur> getUtilisateurs() {
        List<Utilisateur> utilisateurs;
        utilisateurs = (List<Utilisateur>) utilisateurRepository.findAll();
        return utilisateurs;
    }

    @Override
    public Optional<Utilisateur> getUtilisateur(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Utilisateur> findUtilisateurByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public void supprimerUtilisateur(Utilisateur utilisateur) {

    }

    @Override
    public Utilisateur ajouterUtilisateur(SignupRequest signupRequest) {
        Set<Role> roles = new HashSet<>();
        Role role = new Role() ;
//        System.out.println("***profil: "+ signupRequest.getProfil()+"*************");
//        if (signupRequest.getProfil().equals("BACK_OFFICE")) {
//            role.setName(RoleName.ROLE_AGENT);
//        } else {
            role.setName(RoleName.ROLE_ADMIN);
//        }
        roles.add(role);
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setRoles(roles);
        utilisateur.setNom(signupRequest.getNom());
        utilisateur.setPrenom(signupRequest.getPrenom());
        utilisateur.setEmail(signupRequest.getEmail());
//        utilisateur.setProfil(signupRequest.getProfil());
        utilisateur.setPassword(encoder.encode("passer"));

        utilisateurRepository.save(utilisateur);
        return utilisateur;
    }

    @Override
    public Optional<Utilisateur> getUtilisateurByEmail(String email) {
        Optional<Utilisateur> opt = utilisateurRepository.findByEmail(email);
        return opt;
    }
}
