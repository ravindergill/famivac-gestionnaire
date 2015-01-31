package fr.fava.gestionnaire.application.enfant;

import fr.fava.gestionnaire.domain.EnfantRepository;
import fr.fava.gestionnaire.domain.model.enfant.Enfant;
import fr.fava.gestionnaire.domain.model.enfant.ResponsableLegal;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.ws.rs.PathParam;
import net.bull.javamelody.MonitoringInterceptor;

/**
 *
 * @author paoesco
 */
@Stateless
@Interceptors({MonitoringInterceptor.class})
public class EnfantService {

    @Inject
    private EntityManager entityManager;

    @Inject
    private EnfantRepository enfantRepository;

    public Long create(Enfant enfant) {
        if (enfant.getInscripteur().isResponsableLegal()) {
            enfant.setResponsableLegal(new ResponsableLegal(enfant.getInscripteur()));
        }
        entityManager.persist(enfant);
        return enfant.getId();
    }

    public Enfant retrieve(long id) {
        return entityManager.find(Enfant.class, id);
    }

    public void update(Enfant enfant) {
        entityManager.merge(enfant);
    }

    public List<RetrieveEnfantsDTO> retrieve(String nomEnfant, String prenomEnfant) {
        List<Enfant> entities = enfantRepository.retrieve(nomEnfant, prenomEnfant);
        return entities.stream().map((Enfant entity) -> {
            RetrieveEnfantsDTO dto = new RetrieveEnfantsDTO();
            dto.setId(entity.getId());
            dto.setNomEnfant(entity.getNom());
            dto.setPrenomEnfant(entity.getPrenom());
            return dto;
        }).collect(Collectors.toList());
    }

    public void delete(@PathParam("id") long id) {
        Enfant entity = entityManager.find(Enfant.class, id);
        if (entity == null) {
            throw new IllegalArgumentException("L'enfant n'existe pas");
        }
        entityManager.remove(entity);
    }

}
