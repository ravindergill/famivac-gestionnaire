package fr.fava.gestionnaire.domain.famille.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author paoesco
 */
@XmlRootElement
public class UpdateFamilleRequestDTO {

    private Long id;

    private AdresseDTO adresse;

    private String projet;

    private String precisionSiVacancesNonCompletes;

    private boolean vacancesCompletes;

    private String[] periodesAccueil;

    private String[] tranchesAges;

    private String connaissanceAssociation;

    private Integer nombreFillesSouhaitees;

    private Integer nombreGarconsSouhaites;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdresseDTO getAdresse() {
        return adresse;
    }

    public void setAdresse(AdresseDTO adresse) {
        this.adresse = adresse;
    }

    public String getProjet() {
        return projet;
    }

    public void setProjet(String projet) {
        this.projet = projet;
    }

    public String getPrecisionSiVacancesNonCompletes() {
        return precisionSiVacancesNonCompletes;
    }

    public void setPrecisionSiVacancesNonCompletes(String precisionSiVacancesNonCompletes) {
        this.precisionSiVacancesNonCompletes = precisionSiVacancesNonCompletes;
    }

    public boolean isVacancesCompletes() {
        return vacancesCompletes;
    }

    public void setVacancesCompletes(boolean vacancesCompletes) {
        this.vacancesCompletes = vacancesCompletes;
    }

    public String[] getPeriodesAccueil() {
        return periodesAccueil;
    }

    public void setPeriodesAccueil(String[] periodesAccueil) {
        this.periodesAccueil = periodesAccueil;
    }

    public String[] getTranchesAges() {
        return tranchesAges;
    }

    public void setTranchesAges(String[] tranchesAges) {
        this.tranchesAges = tranchesAges;
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
