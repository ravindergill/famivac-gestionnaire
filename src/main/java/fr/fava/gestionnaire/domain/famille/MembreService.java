package fr.fava.gestionnaire.domain.famille;

import fr.fava.gestionnaire.domain.famille.dto.MembreDTO;
import fr.fava.gestionnaire.domain.model.Commune;
import fr.fava.gestionnaire.domain.model.MembreFamille;
import fr.fava.gestionnaire.domain.model.Sexe;
import fr.fava.gestionnaire.domain.referentiel.CommuneDTO;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.PathParam;

/**
 *
 * @author paoesco
 */
@Stateless
@LocalBean
public class MembreService {

    @Inject
    private EntityManager entityManager;

    public MembreDTO retrieve(@PathParam("id") long id) {
        MembreFamille entity = entityManager.find(MembreFamille.class, id);
        MembreDTO dto = new MembreDTO();
        dto.setId(entity.getId());
        CommuneDTO commune = new CommuneDTO();
        commune.setCode(entity.getCommuneDeNaissance().getCode());
        commune.setVille(entity.getCommuneDeNaissance().getVille());
        dto.setCommuneDeNaissance(commune);
        dto.setDateNaissance(entity.getDateNaissance());
        dto.setLienDeParente(entity.getLienDeParente());
        dto.setNom(entity.getNom());
        dto.setNomDeNaissance(entity.getNomDeNaissance());
        dto.setPrenom(entity.getPrenom());
        dto.setProfession(entity.getProfession());
        dto.setReferent(entity.isReferent());
        dto.setSexe(entity.getSexe().name());
        return dto;

    }

    public void update(MembreDTO request) {
        MembreFamille entity = entityManager.find(MembreFamille.class, request.getId());
        Commune commune = new Commune(request.getCommuneDeNaissance().getCode(), request.getCommuneDeNaissance().getVille());
        entity.setCommuneDeNaissance(commune);
        entity.setDateNaissance(request.getDateNaissance());
        entity.setLienDeParente(request.getLienDeParente());
        entity.setNom(request.getNom());
        entity.setNomDeNaissance(request.getNomDeNaissance());
        entity.setPrenom(request.getPrenom());
        entity.setProfession(request.getProfession());
        entity.setReferent(request.isReferent());
        entity.setSexe(Sexe.valueOf(request.getSexe()));
        entityManager.merge(entity);
    }

    public void delete(@PathParam("id") long id) {
        MembreFamille entity = entityManager.find(MembreFamille.class, id);
        entity.getFamille().retirerMembre(entity);
        entityManager.remove(entity);
    }

}
