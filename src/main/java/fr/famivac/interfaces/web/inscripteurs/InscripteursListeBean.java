package fr.famivac.interfaces.web.inscripteurs;

import fr.famivac.gestionnaire.enfants.control.InscripteurService;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Backing bean des familles.
 *
 * @author paoesco
 */
@Named
@ViewScoped
public class InscripteursListeBean implements Serializable {

    /**
     * Liste.
     */
    private LazyInscripteurDataModel lazyModel;

    @Inject
    private InscripteurService inscripteurService;

    @Inject
    private RechercherInscripteursForm rechercheForm;

    /**
     * Initialisation du bean.
     */
    public void init() {
        rechercher();
    }

    public void rechercher() {
        lazyModel = new LazyInscripteurDataModel(inscripteurService.search(rechercheForm.getNom(), rechercheForm.getPrenom(), rechercheForm.getOrganisme()));
    }

    public void supprimer(Long id) {
        inscripteurService.delete(id);
        init(); // recharge des inscripteurs
    }

    public LazyInscripteurDataModel getLazyModel() {
        return lazyModel;
    }

}
