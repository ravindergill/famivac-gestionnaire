package fr.fava.gestionnaire.interfaces.enfants.web;

import fr.fava.gestionnaire.application.CommuneService;
import fr.fava.gestionnaire.application.EnfantService;
import fr.fava.gestionnaire.domain.model.Commune;
import fr.fava.gestionnaire.domain.model.enfant.Enfant;
import fr.fava.gestionnaire.domain.model.enfant.TypeInscripteur;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author paoesco
 */
@Named
@ViewScoped
public class AjouterEnfantBean implements Serializable {

    private List<Commune> communes;

    private Enfant form;

    @Inject
    private CommuneService communeService;

    @Inject
    private EnfantService enfantService;

    public void init() {
        form = new Enfant();
        communes = communeService.retrieve();
    }

    public String ajouter() {
        Long id = enfantService.create(form);
        return "/enfant/details.xhtml?id=" + id + "&faces-redirect=true";
    }

    public List<Commune> completeCommune(String query) {
        if (query == null || query.isEmpty()) {
            return communes;
        } else {
            return communes.stream().filter((Commune t) -> {
                return t.getLabel().toLowerCase().trim().contains(query.toLowerCase().trim());
            }).collect(Collectors.toList());
        }
    }

    public boolean isTypeInscripteurParticulier() {
        return TypeInscripteur.PARTICULIER.equals(form.getInscripteur().getType());
    }

    public boolean isTypeServiceSocialOuAutre() {
        return TypeInscripteur.SERVICE_SOCIAL.equals(form.getInscripteur().getType())
                || TypeInscripteur.AUTRE.equals(form.getInscripteur().getType());
    }

    public List<Commune> getCommunes() {
        return communes;
    }

    public void setCommunes(List<Commune> communes) {
        this.communes = communes;
    }

    public CommuneService getCommuneService() {
        return communeService;
    }

    public void setCommuneService(CommuneService communeService) {
        this.communeService = communeService;
    }

    public boolean isInscripteurEstResponsableLegal() {
        return form.getInscripteur().isResponsableLegal();
    }

    public Enfant getForm() {
        return form;
    }

    public void setForm(Enfant form) {
        this.form = form;
    }

}
