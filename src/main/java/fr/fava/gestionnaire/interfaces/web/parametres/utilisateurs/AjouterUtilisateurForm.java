package fr.fava.gestionnaire.interfaces.web.parametres.utilisateurs;

import fr.fava.gestionnaire.utils.Email;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author paoesco
 */
@Named
@RequestScoped
@XmlRootElement
public class AjouterUtilisateurForm {

    @NotNull
    private String login;

    @NotNull
    @Size(min = 1, max = 2000)
    private String password;

    @NotNull
    @Size(min = 1, max = 2000)
    private String confirmPassword;

    @NotNull
    @Size(min = 1, max = 255)
    private String nom;

    @NotNull
    @Size(min = 1, max = 255)
    private String prenom;

    @NotNull
    @Size(min = 1, max = 255)
    private String groupe;

    @NotNull
    @Email
    @Size(min = 1, max = 2000)
    private String email;

    @XmlElement(required = true)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @XmlElement(required = true)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlElement(required = true)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlElement(required = true)
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @XmlElement(required = true)
    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    @XmlElement(required = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
