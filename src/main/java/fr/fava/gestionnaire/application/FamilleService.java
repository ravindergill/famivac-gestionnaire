package fr.fava.gestionnaire.application;

import fr.fava.gestionnaire.application.dto.MembreDTO;
import fr.fava.gestionnaire.application.dto.CreateFamilleRequestDTO;
import fr.fava.gestionnaire.application.dto.RetrieveFamillesResponseDTO;
import fr.fava.gestionnaire.domain.famille.FamilleRepository;
import fr.fava.gestionnaire.domain.model.Adresse;
import fr.fava.gestionnaire.domain.model.Chambre;
import fr.fava.gestionnaire.domain.model.Commune;
import fr.fava.gestionnaire.domain.model.Famille;
import fr.fava.gestionnaire.domain.model.MembreFamille;
import fr.fava.gestionnaire.domain.model.Sexe;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import net.bull.javamelody.MonitoringInterceptor;

/**
 * @author paoesco
 */
@Stateless
@Interceptors({MonitoringInterceptor.class})
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

        Commune communeMembre = request.getMembrePrincipal().getCommuneDeNaissance();
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

    public Famille retrieve(@PathParam("id") long id) {
        return entityManager.find(Famille.class, id);
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

    public void update(Famille entity) {
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

    public Chambre addChambre(@PathParam("familleId") long familleId, Chambre entity) {
        entityManager.persist(entity);
        Famille famille = entityManager.find(Famille.class, familleId);
        famille.ajouterChambre(entity);
        return entity;
    }

}
