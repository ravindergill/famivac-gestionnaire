package fr.fava.gestionnaire.interfaces.web.familles;

import fr.fava.gestionnaire.domain.famille.ChambreService;
import fr.fava.gestionnaire.domain.famille.FamilleService;
import fr.fava.gestionnaire.domain.famille.MembreService;
import fr.fava.gestionnaire.domain.famille.dto.ChambreDTO;
import fr.fava.gestionnaire.domain.famille.dto.MembreDTO;
import fr.fava.gestionnaire.domain.famille.dto.RetrieveFamilleResponseDTO;
import fr.fava.gestionnaire.domain.famille.dto.UpdateFamilleRequestDTO;
import fr.fava.gestionnaire.domain.referentiel.CommuneDTO;
import fr.fava.gestionnaire.domain.referentiel.CommuneService;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.CellEditEvent;

/**
 * @author paoesco
 */
@Named
@ViewScoped
public class FamilleDetailsBean implements Serializable {

    private Long id;

    private UpdateFamilleRequestDTO form;

    private List<MembreDTO> membres;

    private List<CommuneDTO> communes;

    private List<ChambreDTO> chambres;

    private ChambreDTO nouvelleChambre;

    @Inject
    private FamilleService familleService;
    @Inject
    private MembreService membreService;
    @Inject
    private CommuneService communeService;
    @Inject
    private ChambreService chambreService;

    private boolean ajouterMembreEnCours;

    private int activeIndex;

    /**
     * Initialisation du bean.
     */
    public void init() {
        RetrieveFamilleResponseDTO bean = familleService.retrieve(id);
        form = new UpdateFamilleRequestDTO();
        form.setAdresse(bean.getAdresse());
        form.setId(bean.getId());
        form.setPrecisionSiVacancesNonCompletes(bean.getPrecisionSiVacancesNonCompletes());
        form.setProjet(bean.getProjet());
        form.setVacancesCompletes(bean.isVacancesCompletes());
        form.setPeriodesAccueil(bean.getPeriodesAccueil());
        form.setTranchesAges(bean.getTranchesAges());
        form.setConnaissanceAssociation(bean.getConnaissanceAssociation());
        form.setNombreFillesSouhaitees(bean.getNombreFillesSouhaitees());
        form.setNombreGarconsSouhaites(bean.getNombreGarconsSouhaites());
        communes = communeService.retrieve();
        ajouterMembreEnCours = false;
        activeIndex = 0;
        membres = new ArrayList<>();
        bean.getMembres().stream().map((dto) -> membreService.retrieve(dto.getId())).forEach((membre) -> {
            membres.add(membre);
        });
        membres.sort((MembreDTO m1, MembreDTO m2) -> {
            if (m1.isReferent()) {
                return -1;
            }
            if (m2.isReferent()) {
                return 1;
            }
            return m1.getId().compareTo(m2.getId());
        });
        chambres = bean.getChambres();
        nouvelleChambre = new ChambreDTO();
    }

    public void update() {
        familleService.update(form); 
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informations sur la famille sauvées", ""));
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

    public void ajouterMembre() {
        MembreDTO nouveau = new MembreDTO();
        membres.add(nouveau);
        ajouterMembreEnCours = true;
        activeIndex = membres.size() - 1;
    }

    public void updateMembre(MembreDTO membre) {
        membreService.update(membre);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informations sur le membre sauvées", ""));
    }

    public void ajouterMembre(MembreDTO membre) {
        Long membreId = familleService.addMembre(id, membre);
        membre.setId(membreId);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informations sur le membre sauvées", ""));
    }

    public void retirerMembre(MembreDTO membre) {
        membreService.delete(membre.getId());
        membres.remove(membre);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Membre retiré", ""));
    }

    public void definirReferent(MembreDTO membre) {
        membre.setReferent(true);
        membreService.update(membre);
        membres.stream().forEach((MembreDTO m) -> {
            if (m.isReferent() && !m.getId().equals(membre.getId())) {
                m.setReferent(false);
                membreService.update(m);
            }
        });
        membres.sort((MembreDTO m1, MembreDTO m2) -> {
            if (m1.isReferent()) {
                return -1;
            }
            if (m2.isReferent()) {
                return 1;
            }
            return m1.getId().compareTo(m2.getId());
        });
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, MessageFormat.format("{0} {1} est désormais le membre référent de la famille", membre.getPrenom(), membre.getNom()), ""));
    }

    public void annulerAjoutMembre(MembreDTO membre) {
        membres.remove(membre);
        ajouterMembreEnCours = false;
    }

    public void editChambre(CellEditEvent event) {
        // TODO finir
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void supprimerChambre(ChambreDTO chambre) {
        chambreService.delete(chambre.getId());
        chambres.remove(chambre);
    }

    public void ajouterChambre() {
        Long chambreId = familleService.addChambre(id, nouvelleChambre);
        nouvelleChambre.setId(chambreId);
        chambres.add(nouvelleChambre);
        nouvelleChambre = new ChambreDTO();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UpdateFamilleRequestDTO getForm() {
        return form;
    }

    public void setForm(UpdateFamilleRequestDTO form) {
        this.form = form;
    }

    public FamilleService getFamilleService() {
        return familleService;
    }

    public void setFamilleService(FamilleService familleService) {
        this.familleService = familleService;
    }

    public List<MembreDTO> getMembres() {
        return membres;
    }

    public void setMembres(List<MembreDTO> membres) {
        this.membres = membres;
    }

    public List<CommuneDTO> getCommunes() {
        return communes;
    }

    public void setCommunes(List<CommuneDTO> communes) {
        this.communes = communes;
    }

    public CommuneService getCommuneService() {
        return communeService;
    }

    public void setCommuneService(CommuneService communeService) {
        this.communeService = communeService;
    }

    public boolean isAjouterMembreEnCours() {
        return ajouterMembreEnCours;
    }

    public int getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }

    public List<ChambreDTO> getChambres() {
        return chambres;
    }

    public void setChambres(List<ChambreDTO> chambres) {
        this.chambres = chambres;
    }

    public ChambreDTO getNouvelleChambre() {
        return nouvelleChambre;
    }

    public void setNouvelleChambre(ChambreDTO nouvelleChambre) {
        this.nouvelleChambre = nouvelleChambre;
    }

}
