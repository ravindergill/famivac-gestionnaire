package fr.fava.gestionnaire.interfaces.familles.web;

import fr.fava.gestionnaire.application.ChambreService;
import fr.fava.gestionnaire.application.FamilleService;
import fr.fava.gestionnaire.application.MembreService;
import fr.fava.gestionnaire.application.dto.MembreDTO;
import fr.fava.gestionnaire.domain.model.famille.Chambre;
import fr.fava.gestionnaire.domain.model.Commune;
import fr.fava.gestionnaire.domain.model.famille.Famille;
import fr.fava.gestionnaire.application.CommuneService;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.stream.Collectors;
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
public class FamilleDetailsBean implements Serializable {

    private Long id;

    private Famille form;

    private List<MembreDTO> membres;

    private List<Commune> communes;

    private List<Chambre> chambres;

    private Chambre nouvelleChambre;

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
        form = familleService.retrieve(id);
        communes = communeService.retrieve();
        ajouterMembreEnCours = false;
        activeIndex = 0;
        membres = new ArrayList<>();
        form.getMembres().stream().map((dto) -> membreService.retrieve(dto.getId())).forEach((membre) -> {
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
        chambres = form.getChambres();
        nouvelleChambre = new Chambre(0, form);
    }

    public void update() {
        familleService.update(form);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informations sur la famille sauvées", ""));
    }

    public List<Commune> completeCommune(String query) {
        if (query == null || query.isEmpty()) {
            return communes;
        } else {
            return communes.stream().filter((Commune t) -> {
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
        init();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informations sur le membre sauvées", ""));
    }

    public void ajouterMembre(MembreDTO membre) {
        familleService.addMembre(id, membre);
        init();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informations sur le membre sauvées", ""));
    }

    public void retirerMembre(MembreDTO membre) {
        membreService.delete(membre.getId());
        init();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Membre retiré", ""));
    }

    public void definirReferent(MembreDTO membre) {
        membreService.definirReferent(membre.getId());
        init();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, MessageFormat.format("{0} {1} est désormais le membre référent de la famille", membre.getPrenom(), membre.getNom()), ""));
    }

    public void annulerAjoutMembre(MembreDTO membre) {
        membres.remove(membre);
        ajouterMembreEnCours = false;
    }

    public void supprimerChambre(Chambre chambre) {
        chambreService.delete(chambre.getId());
        init();
    }

    public void ajouterChambre() {
        familleService.addChambre(id, nouvelleChambre);
        init();
    }

    public int getNombreTotalLits() {
        int nbLits = 0;
        return chambres.stream().map((c) -> c.getNombreLits()).reduce(nbLits, Integer::sum);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Famille getForm() {
        return form;
    }

    public void setForm(Famille form) {
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

    public boolean isAjouterMembreEnCours() {
        return ajouterMembreEnCours;
    }

    public int getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }

    public List<Chambre> getChambres() {
        return chambres;
    }

    public void setChambres(List<Chambre> chambres) {
        this.chambres = chambres;
    }

    public Chambre getNouvelleChambre() {
        return nouvelleChambre;
    }

    public void setNouvelleChambre(Chambre nouvelleChambre) {
        this.nouvelleChambre = nouvelleChambre;
    }

}
