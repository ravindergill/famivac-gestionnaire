package fr.fava.gestionnaire.application.dto;

import fr.fava.gestionnaire.domain.model.Groupe;
import fr.fava.gestionnaire.domain.utils.Email;

/**
 * @author paoesco
 */
public class AjouterUtilisateurDTO {

    private String login;

    @Email
    private String email;

    private String nom;

    private String prenom;

    private Groupe groupe;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

}
