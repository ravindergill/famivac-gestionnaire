package fr.fava.gestionnaire.interfaces.inscripteurs.web;

import fr.fava.gestionnaire.application.enfant.InscripteurService;
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

    /**
     * Initialisation du bean.
     */
    public void init() {
        rechercher();
    }

    public void rechercher() {
        lazyModel = new LazyInscripteurDataModel(inscripteurService.retrieve());
    }

    public void supprimer(Long id) {
        inscripteurService.delete(id);
        init(); // recharge des inscripteurs
    }

    public LazyInscripteurDataModel getLazyModel() {
        return lazyModel;
    }

}
