package fr.fava.gestionnaire.domain.enfant;

import fr.fava.gestionnaire.domain.inscripteur.Inscripteur;
import fr.fava.gestionnaire.domain.common.Sexe;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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

    @ManyToOne
    private Inscripteur inscripteur;

    @ManyToOne(cascade = CascadeType.ALL)
    private ResponsableLegal responsableLegal;

    @Embedded
    private final InformationsComplementairesEnfant informationsComplementairesEnfant;

    @Column(length = 2000)
    private String remarque;

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
        return dateNaissance == null ? null : (Date) dateNaissance.clone();
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = (dateNaissance == null ? null : (Date) dateNaissance.clone());
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

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

}
