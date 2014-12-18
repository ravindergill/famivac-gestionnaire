package fr.fava.gestionnaire.domain.famille.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author paoesco
 */
@XmlRootElement
public class RetrieveFamillesResponseDTO {

    private Long id;

    private String nomReferent;

    private String prenomReferent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomReferent() {
        return nomReferent;
    }

    public void setNomReferent(String nomReferent) {
        this.nomReferent = nomReferent;
    }

    public String getPrenomReferent() {
        return prenomReferent;
    }

    public void setPrenomReferent(String prenomReferent) {
        this.prenomReferent = prenomReferent;
    }

}
