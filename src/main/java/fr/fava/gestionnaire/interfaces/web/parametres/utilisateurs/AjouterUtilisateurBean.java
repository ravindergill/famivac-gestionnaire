package fr.fava.gestionnaire.interfaces.web.parametres.utilisateurs;

import fr.fava.gestionnaire.domain.groupe.GroupeResponseDTO;
import fr.fava.gestionnaire.domain.groupe.GroupeService;
import fr.fava.gestionnaire.domain.utilisateur.UtilisateurService;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author paoesco
 */
@Named
@ViewScoped
public class AjouterUtilisateurBean implements Serializable {

    @Inject
    private AjouterUtilisateurForm form;

    @Inject
    private UtilisateurService utilisateurService;
    @Inject
    private GroupeService groupeService;

    private List<String> groupes;

    public void init() {
        List<GroupeResponseDTO> groupesDTO = groupeService.retrieve();
        groupes = groupesDTO.stream().map(groupe -> groupe.getNom()).collect(Collectors.toList());
        form.setGroupe("gestionnaire");
    }

    public List<String> autocomplete(String query) {
        if (query == null || query.isEmpty()) {
            return groupes;
        }
        return groupes.stream().filter(nom -> query.equals(nom)).collect(Collectors.toList());
    }

    public String ajouter() {
        if (!form.getPassword().equals(form.getConfirmPassword())) {
            FacesContext.getCurrentInstance().addMessage("form:form_password", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Les mots de passe ne correspondent pas", ""));
            ((UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent("form:form_password")).setValid(false);
            ((UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent("form:form_confirm_password")).setValid(false);
            return null;
        }
        utilisateurService.create(form);
        return "/parametres/utilisateurs/liste.xhtml?faces-redirect=true";
    }

    public UtilisateurService getUtilisateurService() {
        return utilisateurService;
    }

    public void setUtilisateurService(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    public AjouterUtilisateurForm getForm() {
        return form;
    }

    public void setForm(AjouterUtilisateurForm form) {
        this.form = form;
    }

    public List<String> getGroupes() {
        return groupes;
    }

    public void setGroupes(List<String> groupes) {
        this.groupes = groupes;
    }

}
