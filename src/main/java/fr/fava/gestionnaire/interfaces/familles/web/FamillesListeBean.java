package fr.fava.gestionnaire.interfaces.familles.web;

import fr.fava.gestionnaire.application.FamilleService;
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
public class FamillesListeBean implements Serializable {

    /**
     * Liste des enfants.
     */
    private LazyFamilleDataModel lazyModel;

    @Inject
    private FamilleService familleService;

    @Inject
    private RechercherFamillesForm rechercherForm;

    /**
     * Initialisation du bean.
     */
    public void init() {
        rechercher();
    }

    public void rechercher() {
        lazyModel = new LazyFamilleDataModel(familleService.retrieve(rechercherForm.getNomReferent(), rechercherForm.getPrenomReferent()));
    }

    public void supprimer(Long id) {
        familleService.delete(id);
        init(); // recharge des familles
    }

    public LazyFamilleDataModel getLazyModel() {
        return lazyModel;
    }

}