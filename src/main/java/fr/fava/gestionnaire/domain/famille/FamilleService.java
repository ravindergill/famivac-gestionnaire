package fr.fava.gestionnaire.domain.famille;

import fr.fava.gestionnaire.domain.famille.dto.RetrieveFamilleResponseDTO;
import fr.fava.gestionnaire.domain.famille.dto.MembreDTO;
import fr.fava.gestionnaire.domain.famille.dto.AdresseDTO;
import fr.fava.gestionnaire.domain.famille.dto.CreateFamilleRequestDTO;
import fr.fava.gestionnaire.domain.famille.dto.ChambreDTO;
import fr.fava.gestionnaire.domain.famille.dto.RetrieveFamillesResponseDTO;
import fr.fava.gestionnaire.domain.famille.dto.UpdateFamilleRequestDTO;
import fr.fava.gestionnaire.domain.model.Adresse;
import fr.fava.gestionnaire.domain.model.Chambre;
import fr.fava.gestionnaire.domain.model.Commune;
import fr.fava.gestionnaire.domain.model.Famille;
import fr.fava.gestionnaire.domain.model.MembreFamille;
import fr.fava.gestionnaire.domain.model.PeriodeAccueil;
import fr.fava.gestionnaire.domain.model.Sexe;
import fr.fava.gestionnaire.domain.model.TrancheAgeEnfant;
import fr.fava.gestionnaire.domain.referentiel.CommuneDTO;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/**
 * @author paoesco
 */
@Stateless
@LocalBean
public class FamilleService {

    @Inject
    private EntityManager entityManager;

    @Inject
    private FamilleRepository repository;

    public Long create(CreateFamilleRequestDTO request) {
        Commune communeFamille = new Commune(request.getAdresse().getCommune().getCode(), request.getAdresse().getCommune().getVille());
        Adresse adresse = new Adresse(request.getAdresse().getLigneAdresseUne(), request.getAdresse().getLigneAdresseDeux(), communeFamille);
        Famille entity = new Famille(adresse, request.getProjet());
        entityManager.persist(entity);

        Commune communeMembre = new Commune(request.getMembrePrincipal().getCommuneDeNaissance().getCode(), request.getMembrePrincipal().getCommuneDeNaissance().getVille());
        MembreFamille membre = new MembreFamille(entity,
                request.getMembrePrincipal().getNom(),
                request.getMembrePrincipal().getNomDeNaissance(),
                request.getMembrePrincipal().getPrenom(),
                Sexe.valueOf(request.getMembrePrincipal().getSexe()),
                request.getMembrePrincipal().getDateNaissance(),
                request.getMembrePrincipal().getProfession(),
                true,
                communeMembre);
        entityManager.persist(membre);
        entity.ajouterMembre(membre);
        return entity.getId();
    }

    public RetrieveFamilleResponseDTO retrieve(@PathParam("id") long id) {
        Famille famille = entityManager.find(Famille.class, id);
        if (famille == null) {
            throw new IllegalArgumentException("Pas de famille");
        }
        RetrieveFamilleResponseDTO response = new RetrieveFamilleResponseDTO();
        AdresseDTO adresse = new AdresseDTO();
        CommuneDTO commune = new CommuneDTO();
        commune.setCode(famille.getAdresse().getCommune().getCode());
        commune.setVille(famille.getAdresse().getCommune().getVille());
        adresse.setCommune(commune);
        adresse.setLigneAdresseUne(famille.getAdresse().getLigneAdresseUne());
        adresse.setLigneAdresseDeux(famille.getAdresse().getLigneAdresseDeux());
        response.setAdresse(adresse);
        response.setId(famille.getId());
        response.setMembres(famille.getMembres().stream().map((MembreFamille t) -> {
            MembreDTO dto = new MembreDTO();
            dto.setId(t.getId());
            dto.setNom(t.getNom());
            dto.setPrenom(t.getPrenom());
            dto.setReferent(t.isReferent());
            return dto;
        }).collect(Collectors.toList()));
        response.setPrecisionSiVacancesNonCompletes(famille.getPrecisionSiVacancesNonCompletes());
        response.setProjet(famille.getProjet());
        response.setVacancesCompletes(famille.isVacancesCompletes());
        response.setConnaissanceAssociation(famille.getConnaissanceAssociation());
        response.setChambres(famille.getChambres().stream().map((Chambre c) -> {
            ChambreDTO dto = new ChambreDTO();
            dto.setId(c.getId());
            dto.setNombreLits(c.getNombreLits());
            return dto;
        }).collect(Collectors.toList()));
        List<String> periodes = famille.getPeriodesSouhaitees().stream().map((PeriodeAccueil p) -> {
            return p.name();
        }).collect(Collectors.toList());
        String[] tabPeriodes = new String[periodes.size()];
        response.setPeriodesAccueil(periodes.toArray(tabPeriodes));
        List<String> tranches = famille.getTranchesAges().stream().map((TrancheAgeEnfant p) -> {
            return p.name();
        }).collect(Collectors.toList());
        String[] tabTranches = new String[tranches.size()];
        response.setTranchesAges(tranches.toArray(tabTranches));
        response.setNombreFillesSouhaitees(famille.getNombreFillesSouhaitees());
        response.setNombreGarconsSouhaites(famille.getNombreGarconsSouhaites());
        return response;
    }

    public List<RetrieveFamillesResponseDTO> retrieve(String nomReferent, @QueryParam("param1") String prenomReferent) {
        List<Famille> beans = repository.retrieve(nomReferent, prenomReferent);
        List<RetrieveFamillesResponseDTO> dtos = beans.stream().map((Famille f) -> {
            RetrieveFamillesResponseDTO dto = new RetrieveFamillesResponseDTO();
            dto.setId(f.getId());
            dto.setNomReferent(f.getMembreReferent().getNom());
            dto.setPrenomReferent(f.getMembreReferent().getPrenom());
            return dto;
        }).collect(Collectors.toList());
        return dtos;
    }

    public void update(UpdateFamilleRequestDTO request) {
        Famille entity = entityManager.find(Famille.class, request.getId());
        entity.setProjet(request.getProjet());
        Commune commune = new Commune(request.getAdresse().getCommune().getCode(), request.getAdresse().getCommune().getVille());
        entity.setCommune(commune);
        entity.setLigneAdresseDeux(request.getAdresse().getLigneAdresseDeux());
        entity.setLigneAdresseUne(request.getAdresse().getLigneAdresseUne());
        entity.setPrecisionSiVacancesNonCompletes(request.getPrecisionSiVacancesNonCompletes());
        entity.setVacancesCompletes(request.isVacancesCompletes());
        entity.setConnaissanceAssociation(request.getConnaissanceAssociation());
        if (request.getPeriodesAccueil() != null) {
            entity.clearPeriodesSouhaitees();
            for (String periode : request.getPeriodesAccueil()) {
                entity.ajouterPeriodeSouhaitee(PeriodeAccueil.valueOf(periode));
            }
        }
        if (request.getTranchesAges() != null) {
            entity.clearTranchesAges();
            for (String tranche : request.getTranchesAges()) {
                entity.ajouterTrancheAge(TrancheAgeEnfant.valueOf(tranche));
            }
        }
        entity.setNombreFillesSouhaitees(request.getNombreFillesSouhaitees());
        entity.setNombreGarconsSouhaites(request.getNombreGarconsSouhaites());
        entityManager.merge(entity);
    }

    public void delete(@PathParam("id") long id) {
        Famille famille = entityManager.find(Famille.class, id);
        entityManager.remove(famille);
    }

    public Long addMembre(@PathParam("familleId") long familleId, MembreDTO request) {
        Famille famille = entityManager.find(Famille.class, familleId);
        Commune commune = new Commune(request.getCommuneDeNaissance().getCode(), request.getCommuneDeNaissance().getVille());
        MembreFamille membre = new MembreFamille(famille,
                request.getNom(),
                request.getNomDeNaissance(),
                request.getPrenom(),
                Sexe.valueOf(request.getSexe()),
                request.getDateNaissance(),
                request.getProfession(),
                commune);
        entityManager.persist(membre);
        famille.ajouterMembre(membre);
        return membre.getId();

    }

    public Long addChambre(@PathParam("familleId") long familleId, ChambreDTO request) {
        Famille famille = entityManager.find(Famille.class, familleId);
        Chambre chambre = new Chambre(request.getNombreLits(), famille);
        entityManager.persist(chambre);
        famille.ajouterChambre(chambre);
        return chambre.getId();
    }

}
