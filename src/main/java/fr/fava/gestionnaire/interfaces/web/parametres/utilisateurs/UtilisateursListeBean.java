package fr.fava.gestionnaire.interfaces.web.parametres.utilisateurs;

import fr.fava.gestionnaire.domain.utilisateur.UtilisateurService;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Backing bean des enfants.
 *
 * @author paoesco
 */
@Named
@ViewScoped
public class UtilisateursListeBean implements Serializable {

    /**
     * Liste des utilistaeurs.
     */
    private LazyUtilisateurDataModel lazyModel;

    @Inject
    private UtilisateurService utilisateurService;

    /**
     * Initialisation du bean par la vue.
     */
    public void init() {
        lazyModel = new LazyUtilisateurDataModel(utilisateurService.retrieve());
    }

    public void delete(String login) {
        utilisateurService.delete(login);
        init(); // recharge des utilisateurs
    }

    public LazyUtilisateurDataModel getLazyModel() {
        return lazyModel;
    }

}
