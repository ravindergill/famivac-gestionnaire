package fr.fava.gestionnaire.domain.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 * @author paoesco
 */
@Entity
public class Enfant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 1, max = 255)
    @Column(nullable = false)
    private String nom;

    @Size(min = 1, max = 255)
    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    protected Enfant() {
    }

    public Enfant(String nom, String prenom, Sexe sexe) {
        if (nom == null || nom.isEmpty() || prenom == null || prenom.isEmpty() || sexe == null) {
            throw new IllegalArgumentException("Tous les paramètres sont obligatoires");
        }
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public boolean isFille() {
        return Sexe.FEMME.equals(sexe);
    }

    public boolean isGarcon() {
        return Sexe.HOMME.equals(sexe);
    }

}
