package fr.fava.gestionnaire.domain.model;

import fr.fava.gestionnaire.domain.utils.Email;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author paoesco
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Utilisateur.QUERY_LISTE_ALL, query = "select u from Utilisateur u")
})
public class Utilisateur implements Serializable {

    public static final String QUERY_LISTE_ALL = "retrieveUtilisateurs";

    public static final Utilisateur fakeUtilisateur = new Utilisateur();

    @Id
    private String login;

    @Column(length = 2000, nullable = false)
    @Email
    private String email;

    @Column(length = 2000, nullable = false)
    private String password;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @ManyToOne(optional = false)
    private Groupe groupe;

    private boolean enabled;

    protected Utilisateur() {
    }

    public Utilisateur(
            String login,
            String email,
            String password,
            Groupe groupe,
            String nom,
            String prenom) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.groupe = groupe;
        this.nom = nom;
        this.prenom = prenom;
        this.enabled = true;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
