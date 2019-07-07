package net.atos.khayma.response;

import net.atos.khayma.entities.Utilisateur;

import java.util.Optional;


public class AuthResponse {
    private String token;
    private String type = "Bearer";
    private Optional<Utilisateur> utilisateur;

    public AuthResponse(String accessToken, Optional<Utilisateur> utilisateur) {
        this.token = accessToken;
        this.utilisateur = utilisateur;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Optional<Utilisateur> getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Optional<Utilisateur> Utilisateur) {
        this.utilisateur = Utilisateur;
    }
}
