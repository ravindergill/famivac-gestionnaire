package fr.fava.gestionnaire.interfaces.web.parametres.utilisateurs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author paoesco
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class GroupeResponseDTO {

    @XmlElement(required = true)
    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
