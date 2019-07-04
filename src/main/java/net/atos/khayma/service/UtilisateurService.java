package net.atos.khayma.service;

import net.atos.khayma.model.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {

//    public List<Utilisateur> getAgents();
//    public Optional<Utilisateur> getAgent(Long id);
    public Optional<Utilisateur> findUtilisateurByEmail(String email);
    public List<Utilisateur> findAgentByProfil(String profil);
//    public Utilisateur updateAgent(Utilisateur agent);
//    public void supprimerAgent(Utilisateur agent);
//    public Utilisateur ajouterAgent(SignUpRequest signUpReq);

}

