package net.atos.khayma.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.atos.khayma.entities.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UtilisateurPrinciple implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String nom;
    private String prenom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

//    public String getProfil() {
//        return profil;
//    }

//    public void setProfil(String profil) {
//        this.profil = profil;
//    }

    private String email;
//    private String profil;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UtilisateurPrinciple(Long id, String nom, String prenom, String email, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;;
        this.password = password;
//        this.profil = profil;
        this.email = email;
        this.authorities = authorities;
    }

    public static UtilisateurPrinciple build(Utilisateur utilisateur) {
        List<GrantedAuthority> authorities = utilisateur.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new UtilisateurPrinciple(
                utilisateur.getId(),
                utilisateur.getNom(),
                utilisateur.getPrenom(),
                utilisateur.getEmail(),
                utilisateur.getPassword()
        );
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UtilisateurPrinciple user = (UtilisateurPrinciple) o;
        return Objects.equals(id, user.id);
    }
}
