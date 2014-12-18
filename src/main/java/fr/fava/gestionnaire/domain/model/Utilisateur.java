package fr.fava.gestionnaire.domain.model;

import fr.fava.gestionnaire.utils.Email;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author paoesco
 */
@Entity
@Table(name = "UTILISATEUR")
@NamedQueries({
    @NamedQuery(name = Utilisateur.QUERY_LISTE_ALL, query = "select u from Utilisateur u")
})
public class Utilisateur implements Serializable {

    public static final String QUERY_LISTE_ALL = "retrieveUtilisateurs";

    @Id
    @Column(name = "LOGIN", nullable = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String login;

    @Column(name = "PASSWORD", length = 2000, nullable = false)
    @NotNull
    @Size(min = 1, max = 2000)
    private String password;

    @Column(name = "NOM", nullable = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String nom;

    @Column(name = "PRENOM", nullable = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String prenom;

    @Column(name = "EMAIL", length = 2000, nullable = false)
    @NotNull
    @Email
    @Size(min = 1, max = 2000)
    private String email;

    @ManyToOne(optional = false)
    @JoinColumn(name = "GROUPE")
    @NotNull
    private Groupe groupe;

    protected Utilisateur() {
    }

    public Utilisateur(
            String login,
            String password,
            String nom,
            String prenom,
            String email,
            String groupe) {
        this.login = login;
        setPassword(password);
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.groupe = new Groupe(groupe);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        try {
            MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes("UTF-8"));
            byte[] passwordDigest = md.digest();
            this.password = Base64.getEncoder().encodeToString(passwordDigest);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = new Groupe(groupe);
    }

}
