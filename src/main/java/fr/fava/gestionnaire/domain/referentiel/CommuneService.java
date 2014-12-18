package fr.fava.gestionnaire.domain.referentiel;

import fr.fava.gestionnaire.domain.model.Commune;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author paoesco
 */
@Stateless
@LocalBean
public class CommuneService {

    @Inject
    private EntityManager entityManager;

    public String create(CommuneDTO request) {
        Commune entity = new Commune(request.getCode(), request.getVille());
        entityManager.persist(entity);
        return entity.getCode();
    }

    public void delete(String code) {
        Commune entity = entityManager.find(Commune.class, code);
        entityManager.remove(entity);
    }

    public void update(CommuneDTO request) {
        Commune entity = entityManager.find(Commune.class, request.getCode());
        entity.setVille(request.getVille());
        entityManager.merge(entity);
    }

    public CommuneDTO retrieve(String code) {
        Commune entity = entityManager.find(Commune.class, code);
        CommuneDTO dto = new CommuneDTO();
        dto.setCode(entity.getCode());
        dto.setVille(entity.getVille());
        return dto;
    }

    public List<CommuneDTO> retrieve() {
        List<Commune> communes = entityManager.createNamedQuery(Commune.QUERY_RETRIEVE_ALL, Commune.class).getResultList();
        return communes.stream().map((Commune c) -> {
            CommuneDTO dto = new CommuneDTO();
            dto.setCode(c.getCode());
            dto.setVille(c.getVille());
            return dto;
        }).collect(Collectors.toList());

    }

}
