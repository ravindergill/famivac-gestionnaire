package fr.fava.gestionnaire.interfaces.web.familles;

import fr.fava.gestionnaire.domain.famille.FamilleService;
import fr.fava.gestionnaire.domain.famille.dto.CreateFamilleRequestDTO;
import fr.fava.gestionnaire.domain.referentiel.CommuneDTO;
import fr.fava.gestionnaire.domain.referentiel.CommuneService;
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

    private List<CommuneDTO> communes;

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

    public List<CommuneDTO> completeCommune(String query) {
        if (query == null || query.isEmpty()) {
            return communes;
        } else {
            return communes.stream().filter((CommuneDTO t) -> {
                return t.getVille().toLowerCase().trim().contains(query.toLowerCase().trim());
            }).collect(Collectors.toList());
        }
    }

    public CreateFamilleRequestDTO getForm() {
        return form;
    }

    public void setForm(CreateFamilleRequestDTO form) {
        this.form = form;
    }

    public List<CommuneDTO> getCommunes() {
        return communes;
    }

    public void setCommunes(List<CommuneDTO> communes) {
        this.communes = communes;
    }

}
