package fr.fava.gestionnaire.interfaces.inscripteurs.web;

import fr.fava.gestionnaire.application.CommuneService;
import fr.fava.gestionnaire.application.enfant.InscripteurService;
import fr.fava.gestionnaire.domain.inscripteur.Inscripteur;
import fr.fava.gestionnaire.domain.inscripteur.TypeInscripteur;
import fr.fava.gestionnaire.interfaces.utils.web.CompleteCommune;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author paoesco
 */
@Named
@ViewScoped
public class InscripteurDetailsBean implements Serializable, CompleteCommune {

    private long id;

    private Inscripteur form;

    @Inject
    private CommuneService communeService;

    @Inject
    private InscripteurService inscripteurService;

    public void init() {
        form = inscripteurService.retrieve(id);
    }

    public void update() {
        inscripteurService.update(form);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informations sauvées", ""));
    }

    public void supprimerEnfant(long enfantId) {
        inscripteurService.retirerEnfant(enfantId);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Inscripteur getForm() {
        return form;
    }

    public void setForm(Inscripteur form) {
        this.form = form;
    }

    @Override
    public CommuneService getCommuneService() {
        return communeService;
    }

    public boolean isTypeInscripteurParticulier() {
        return TypeInscripteur.PARTICULIER.equals(form.getType());
    }

    public boolean isTypeServiceSocialOuAutre() {
        return TypeInscripteur.SERVICE_SOCIAL.equals(form.getType())
                || TypeInscripteur.AUTRE.equals(form.getType());
    }

    public boolean isInscripteurEstResponsableLegal() {
        return form.isResponsableLegal();
    }

}
