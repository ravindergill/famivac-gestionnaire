package fr.fava.gestionnaire.application.enfant;

/**
 *
 * @author paoesco
 */
public class RetrieveEnfantsDTO {

    private long id;

    private String nomEnfant;

    private String prenomEnfant;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomEnfant() {
        return nomEnfant;
    }

    public void setNomEnfant(String nomEnfant) {
        this.nomEnfant = nomEnfant;
    }

    public String getPrenomEnfant() {
        return prenomEnfant;
    }

    public void setPrenomEnfant(String prenomEnfant) {
        this.prenomEnfant = prenomEnfant;
    }

}
