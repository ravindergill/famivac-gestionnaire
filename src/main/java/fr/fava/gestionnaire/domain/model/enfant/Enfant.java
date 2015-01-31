package fr.fava.gestionnaire.domain.model.enfant;

import fr.fava.gestionnaire.domain.model.Sexe;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author paoesco
 */
@Entity
public class Enfant implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String nom;

    private String prenom;

    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    private String classeFrequentee;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Inscripteur inscripteur;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private ResponsableLegal responsableLegal;

    @Embedded
    private final InformationsComplementairesEnfant informationsComplementairesEnfant;

    public Enfant() {
        inscripteur = new Inscripteur();
        responsableLegal = new ResponsableLegal();
        informationsComplementairesEnfant = new InformationsComplementairesEnfant();
    }

    public Long getId() {
        return id;
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

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getClasseFrequentee() {
        return classeFrequentee;
    }

    public void setClasseFrequentee(String classeFrequentee) {
        this.classeFrequentee = classeFrequentee;
    }

    public Inscripteur getInscripteur() {
        return inscripteur;
    }

    public void setInscripteur(Inscripteur inscripteur) {
        this.inscripteur = inscripteur;
    }

    public ResponsableLegal getResponsableLegal() {
        return responsableLegal;
    }

    public void setResponsableLegal(ResponsableLegal responsableLegal) {
        this.responsableLegal = responsableLegal;
    }

    public InformationsComplementairesEnfant getInformationsComplementairesEnfant() {
        return informationsComplementairesEnfant;
    }

}
