package fr.fava.gestionnaire.domain.enfant;

import fr.fava.gestionnaire.domain.inscripteur.TypeInscripteur;
import fr.fava.gestionnaire.domain.common.Adresse;
import fr.fava.gestionnaire.domain.sejour.Sejour;
import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author paoesco
 */
@Entity
public class Payeur implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeInscripteur type;

    private String nom;

    private String prenom;

    @Embedded
    private Adresse adresse;

    @ManyToOne
    private Sejour sejour;

    public Payeur() {
        adresse = new Adresse();
    }

    public Long getId() {
        return id;
    }

    public TypeInscripteur getType() {
        return type;
    }

    public void setType(TypeInscripteur type) {
        this.type = type;
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

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Sejour getSejour() {
        return sejour;
    }

    public void setSejour(Sejour sejour) {
        this.sejour = sejour;
    }

}
