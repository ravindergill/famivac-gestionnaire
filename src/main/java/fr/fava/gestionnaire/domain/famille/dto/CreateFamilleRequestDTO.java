package fr.fava.gestionnaire.domain.famille.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author paoesco
 */
@XmlRootElement
public class CreateFamilleRequestDTO {

    private AdresseDTO adresse;

    private String projet;

    private MembreDTO membrePrincipal;
    
    public CreateFamilleRequestDTO(){
        adresse = new AdresseDTO();
        membrePrincipal = new MembreDTO();
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

    public MembreDTO getMembrePrincipal() {
        return membrePrincipal;
    }

    public void setMembrePrincipal(MembreDTO membrePrincipal) {
        this.membrePrincipal = membrePrincipal;
    }

}
