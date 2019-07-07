package net.atos.khayma.controllers;

import net.atos.khayma.entities.Utilisateur;
import net.atos.khayma.request.SignupRequest;
import net.atos.khayma.services.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/test")
public class UtilisateurController {

    @Autowired
    private IUtilisateurService utilisateurService;

    @PostMapping("/utilisateur/ajouter")
    @ResponseBody
    public Utilisateur ajouterUtilisateur(@Valid @RequestBody SignupRequest signupRequest) {
        return utilisateurService.ajouterUtilisateur(signupRequest);
    }

    @GetMapping("/utilisateurs")
    @ResponseBody
    public List<Utilisateur> getUtilisateurs() {
        return utilisateurService.getUtilisateurs();
    }

    @GetMapping("/email/{email}")
    @ResponseBody
    public Optional<Utilisateur> getUtilisateur(@PathVariable("email") String email) {
        return utilisateurService.getUtilisateurByEmail(email);
    }
}
