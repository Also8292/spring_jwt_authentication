package net.atos.khayma.controllers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import net.atos.khayma.entities.Role;
import net.atos.khayma.entities.RoleName;
import net.atos.khayma.entities.Utilisateur;
import net.atos.khayma.repositories.RoleRepository;
import net.atos.khayma.repositories.UtilisateurRepository;
import net.atos.khayma.request.LoginUtilisateur;
import net.atos.khayma.request.SignupRequest;
import net.atos.khayma.response.AuthResponse;
import net.atos.khayma.security.JwtProvider;
import net.atos.khayma.services.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    IUtilisateurService utilisateurService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/connexion")
    public ResponseEntity<?> authenticateUtilisateur(@Valid @RequestBody LoginUtilisateur loginRequest) {

        System.out.println("Username : " + loginRequest.getUsername());
        System.out.println("Password : " + loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        System.out.println("TEST 123");

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Optional<Utilisateur> utilisateur = utilisateurService.findUtilisateurByEmail(loginRequest.getUsername());

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new AuthResponse(jwt, utilisateur));
    }

    @PostMapping("/inscription")
    public ResponseEntity<String> register(@RequestBody SignupRequest signupRequest) {
        Set<Role> roles = new HashSet<>();
        Role admin = new Role (RoleName.ROLE_ADMIN);
        Utilisateur utilisateur = new Utilisateur();

        roles.add(admin);
        utilisateur.setRoles(roles);
        utilisateur.setNom(signupRequest.getNom());
        utilisateur.setPrenom(signupRequest.getPrenom());
        utilisateur.setEmail(signupRequest.getEmail());
//        utilisateur.setProfil(signupRequest.getProfil());
        utilisateur.setPassword(encoder.encode(signupRequest.getPassword()));

        utilisateurRepository.save(utilisateur);
        return new ResponseEntity<String>("User succesfully created!", HttpStatus.OK);
    }

}
