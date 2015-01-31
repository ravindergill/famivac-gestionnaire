package fr.fava.gestionnaire.interfaces.familles.web;

import fr.fava.gestionnaire.application.famille.FamilleService;
import fr.fava.gestionnaire.application.famille.CreateFamilleRequestDTO;
import fr.fava.gestionnaire.domain.model.Commune;
import fr.fava.gestionnaire.application.CommuneService;
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
public class AjouterFamilleBean implements Serializable {

    private CreateFamilleRequestDTO form;

    private List<Commune> communes;

    @Inject
    private FamilleService familleService;

    @Inject
    private CommuneService communeService;

    public void init() {
        form = new CreateFamilleRequestDTO();
        communes = communeService.retrieve();
    }

    public String ajouter() {
        Long familleId = familleService.create(form);
        return "/famille/details.xhtml?id=" + familleId + "&faces-redirect=true";
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

    public CreateFamilleRequestDTO getForm() {
        return form;
    }

    public void setForm(CreateFamilleRequestDTO form) {
        this.form = form;
    }

    public List<Commune> getCommunes() {
        return communes;
    }

    public void setCommunes(List<Commune> communes) {
        this.communes = communes;
    }

}
