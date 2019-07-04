package net.atos.khayma.controller;

import net.atos.khayma.model.Utilisateur;
import net.atos.khayma.repository.UtilisateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
//@RequestMapping("/register")
public class AccountController {

    private final Logger log = LoggerFactory.getLogger(UtilisateurController.class);

    private final UtilisateurRepository utilisateurRepository;

    public AccountController(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

//    @PostMapping("/inscription")
//    public ResponseEntity<Utilisateur> inscription(@RequestBody Utilisateur utilisateur) throws URISyntaxException {
//        log.debug("Ajout d'un utilisateur", utilisateur);
//
//        if (utilisateur.getId() != null) {
//            System.out.println("ID must be null");
//        }
//
//        Utilisateur newUtilisateur = utilisateurRepository.save(utilisateur);
//
//        return ResponseEntity.created(new URI("/account/register" + newUtilisateur.getId().toString())).body(newUtilisateur);
//    }
}
