package net.atos.khayma.controller;

//import java.util.Objects;

import net.atos.khayma.config.JwtTokenUtil;
import net.atos.khayma.model.JwtRequest;
import net.atos.khayma.model.JwtResponse;
import net.atos.khayma.model.Utilisateur;
import net.atos.khayma.repository.UtilisateurRepository;
import net.atos.khayma.service.JwtUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.security.authentication.DisabledException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;


@RestController

@CrossOrigin
@RequestMapping("/account")
public class JwtAuthenticationController {

    private final Logger log = LoggerFactory.getLogger(UtilisateurController.class);

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtUserDetailsService userDetailsService;

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public JwtAuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService, UtilisateurRepository utilisateurRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.utilisateurRepository = utilisateurRepository;
    }

    @PostMapping("/authenticate")

    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        System.out.println("TEST : " + authenticationRequest.getUsername() + " - " + authenticationRequest.getPassword());

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String hashPassword = bCryptPasswordEncoder.encode(authenticationRequest.getPassword());

//        authenticate(authenticationRequest.getUsername(), hashPassword);
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService

                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));

    }

    @PostMapping("/register")
    public ResponseEntity<Utilisateur> inscription(@RequestBody Utilisateur utilisateur) throws URISyntaxException {
        log.debug("Ajout d'un utilisateur", utilisateur);

        if (utilisateur.getId() != null) {
            System.out.println("ID must be null");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String hashPassword = bCryptPasswordEncoder.encode(utilisateur.getPassword());

        utilisateur.setPassword(hashPassword);
        utilisateur.setUsername(utilisateur.getEmail());

        Utilisateur newUtilisateur = utilisateurRepository.save(utilisateur);

        return ResponseEntity.created(new URI("/account/register" + newUtilisateur.getId().toString())).body(newUtilisateur);
    }

    private void authenticate(String username, String password) throws Exception {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println("Authenticated");

        } catch (DisabledException e) {
            System.out.println("Catch - DisabledException");
            throw new Exception("USER_DISABLED", e);

        } catch (BadCredentialsException e) {
            System.out.println("Catch - BadCredentialsException");
            throw new Exception("INVALID_CREDENTIALS", e);

        }

    }

    @GetMapping("/test")
    public String test() {
        return "Hello World !!!";
    }

    @GetMapping("/user")
    public ResponseEntity<UserDetails> getUser() {
        UserDetails user = utilisateurRepository.findOneByEmail("sowalils1992@gmail.com");

        return ResponseEntity.ok().body(user);
    }

}