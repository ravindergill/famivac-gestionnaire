package fr.famivac.gestionnaire.enfants.control;

import fr.famivac.gestionnaire.enfants.entity.TypeInscripteur;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author paoesco
 */
public class RetrieveInscripteursResponseDTO {

    private Long id;

    private String nom;

    private String prenom;

    private String organisme;

    private TypeInscripteur type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getOrganisme() {
        return organisme;
    }

    public void setOrganisme(String organisme) {
        this.organisme = organisme;
    }

    public TypeInscripteur getType() {
        return type;
    }

    public void setType(TypeInscripteur type) {
        this.type = type;
    }

    public boolean isParticulier() {
        return TypeInscripteur.PARTICULIER.equals(type);
    }

    public boolean isTypeServiceSocialOuAutre() {
        return TypeInscripteur.SERVICE_SOCIAL.equals(type) || TypeInscripteur.AUTRE.equals(type);
    }

    public String getLibelle() {
        if (isParticulier()) {
            return getPrenom() + " " + getNom();
        } else if (isTypeServiceSocialOuAutre()) {
            return getOrganisme();
        } else {
            Logger.getLogger(RetrieveInscripteursResponseDTO.class.getName()).log(Level.WARNING, "Impossible de déterminer le type inscripteur et donc son libellé");
            return "";
        }
    }

}
