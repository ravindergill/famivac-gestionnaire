package fr.fava.gestionnaire.domain.famille.dto;

import fr.fava.gestionnaire.domain.referentiel.CommuneDTO;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author paoesco
 */
@XmlRootElement
public class AdresseDTO {

    private String ligneAdresseUne;

    private String ligneAdresseDeux;

    private CommuneDTO commune;
    
    public AdresseDTO(){
        commune = new CommuneDTO();
    }

    public String getLigneAdresseUne() {
        return ligneAdresseUne;
    }

    public void setLigneAdresseUne(String ligneAdresseUne) {
        this.ligneAdresseUne = ligneAdresseUne;
    }

    public String getLigneAdresseDeux() {
        return ligneAdresseDeux;
    }

    public void setLigneAdresseDeux(String ligneAdresseDeux) {
        this.ligneAdresseDeux = ligneAdresseDeux;
    }

    public CommuneDTO getCommune() {
        return commune;
    }

    public void setCommune(CommuneDTO commune) {
        this.commune = commune;
    }

}
