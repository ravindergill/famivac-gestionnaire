package fr.fava.gestionnaire.domain.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * @author paoesco
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Famille.QUERY_LISTE_ALL, query = "select f from Famille f")
})
public class Famille implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String QUERY_LISTE_ALL = "listFamilles";

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.REMOVE)
    private final Set<MembreFamille> membres;

    @Embedded
    private final Adresse adresse;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<PeriodeAccueil> periodesSouhaitees;

    @Column(nullable = false)
    private boolean vacancesCompletes;

    private String precisionSiVacancesNonCompletes;

    @Column(nullable = false)
    private String projet;

    @OneToMany
    private Set<Chambre> chambres;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<TrancheAgeEnfant> tranchesAges;

    private String connaissanceAssociation;

    private Integer nombreFillesSouhaitees;

    private Integer nombreGarconsSouhaites;

    protected Famille() {
        adresse = new Adresse();
        membres = new HashSet<>();
        chambres = new HashSet<>();
        tranchesAges = new HashSet<>();
        periodesSouhaitees = new HashSet<>();
    }

    public Famille(Adresse adresse, String projet) {
        if (adresse == null
                || projet == null
                || projet.isEmpty()) {
            throw new IllegalArgumentException("Tous les paramètres sont obligatoires");
        }
        this.adresse = adresse;
        membres = new HashSet<>();
        this.projet = projet;
        chambres = new HashSet<>();
    }

    public void ajouterChambre(Chambre chambre) {
        if (chambre == null) {
            throw new IllegalArgumentException("Renseigner la chambre");
        }
        chambres.add(chambre);
    }

    public void retirerChambre(Chambre chambre) {
        if (chambre == null) {
            throw new IllegalArgumentException("Renseigner la chambre");
        }
        chambres.remove(chambre);
    }

    public void ajouterMembre(MembreFamille membre) {
        if (membre == null) {
            throw new IllegalArgumentException("Tous les paramètre sont obligatoires");
        }
        this.membres.add(membre);
    }

    public void retirerMembre(MembreFamille membre) {
        if (membre == null) {
            throw new IllegalArgumentException("Tous les paramètre sont obligatoires");
        }
        this.membres.remove(membre);
    }

    public Long getId() {
        return id;
    }

    public Set<MembreFamille> getMembres() {
        return Collections.unmodifiableSet(membres);
    }

    public MembreFamille getMembreReferent() {
        return membres.stream().filter((MembreFamille m) -> {
            return m.isReferent();
        }).findFirst().get();
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public Set<PeriodeAccueil> getPeriodesSouhaitees() {
        return Collections.unmodifiableSet(periodesSouhaitees);
    }

    public boolean isVacancesCompletes() {
        return vacancesCompletes;
    }

    public String getPrecisionSiVacancesNonCompletes() {
        return precisionSiVacancesNonCompletes;
    }

    public String getProjet() {
        return projet;
    }

    public void setVacancesCompletes(boolean vacancesCompletes) {
        this.vacancesCompletes = vacancesCompletes;
    }

    public void setPrecisionSiVacancesNonCompletes(String precisionSiVacancesNonCompletes) {
        this.precisionSiVacancesNonCompletes = precisionSiVacancesNonCompletes;
    }

    public void setProjet(String projet) {
        if (projet == null || projet.isEmpty()) {
            throw new IllegalArgumentException("Le projet est obligatoire");
        }
        this.projet = projet;
    }

    public void setLigneAdresseUne(String ligneAdresseUne) {
        if (ligneAdresseUne == null || ligneAdresseUne.isEmpty()) {
            throw new IllegalArgumentException("La ligne adresse une est obligatoire");
        }
        this.adresse.setLigneAdresseUne(ligneAdresseUne);
    }

    public void setLigneAdresseDeux(String ligneAdresseDeux) {
        if (ligneAdresseDeux == null || ligneAdresseDeux.isEmpty()) {
            throw new IllegalArgumentException("La ligne adresse deux est obligatoire");
        }
        this.adresse.setLigneAdresseDeux(ligneAdresseDeux);
    }

    public void setCommune(Commune commune) {
        if (commune == null) {
            throw new IllegalArgumentException("La commune est obligatoire");
        }
        this.adresse.setCommune(commune);
    }

    public void ajouterPeriodeSouhaitee(PeriodeAccueil periode) {
        this.periodesSouhaitees.add(periode);
    }

    public void clearPeriodesSouhaitees() {
        this.periodesSouhaitees.clear();
    }

    public Set<Chambre> getChambres() {
        return Collections.unmodifiableSet(chambres);
    }

    public Set<TrancheAgeEnfant> getTranchesAges() {
        return Collections.unmodifiableSet(tranchesAges);
    }

    public void ajouterTrancheAge(TrancheAgeEnfant tranche) {
        this.tranchesAges.add(tranche);
    }

    public void clearTranchesAges() {
        this.tranchesAges.clear();
    }

    public String getConnaissanceAssociation() {
        return connaissanceAssociation;
    }

    public void setConnaissanceAssociation(String connaissanceAssociation) {
        this.connaissanceAssociation = connaissanceAssociation;
    }

    public Integer getNombreFillesSouhaitees() {
        return nombreFillesSouhaitees;
    }

    public void setNombreFillesSouhaitees(Integer nombreFillesSouhaitees) {
        this.nombreFillesSouhaitees = nombreFillesSouhaitees;
    }

    public Integer getNombreGarconsSouhaites() {
        return nombreGarconsSouhaites;
    }

    public void setNombreGarconsSouhaites(Integer nombreGarconsSouhaites) {
        this.nombreGarconsSouhaites = nombreGarconsSouhaites;
    }

}
