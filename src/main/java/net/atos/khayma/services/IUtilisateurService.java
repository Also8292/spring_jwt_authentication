package net.atos.khayma.services;

import net.atos.khayma.entities.Utilisateur;
import net.atos.khayma.request.SignupRequest;

import java.util.List;
import java.util.Optional;

public interface IUtilisateurService {

    public List<Utilisateur> getUtilisateurs();
    public Optional<Utilisateur> getUtilisateur(Long id);
    public Optional<Utilisateur> findUtilisateurByEmail(String email);
//    public List<Utilisateur> findUtilisateurByProfil(String profil);
    public Utilisateur updateUtilisateur(Utilisateur utilisateur);
    public void supprimerUtilisateur(Utilisateur utilisateur);
    public Utilisateur ajouterUtilisateur(SignupRequest signupReq);
    public Optional<Utilisateur> getUtilisateurByEmail(String email);

}