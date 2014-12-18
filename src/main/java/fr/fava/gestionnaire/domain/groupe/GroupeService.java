package fr.fava.gestionnaire.domain.groupe;

import fr.fava.gestionnaire.domain.model.Groupe;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;

/**
 * @author paoesco
 */
@Stateless
@LocalBean
public class GroupeService {

    @Inject
    private EntityManager entityManager;

    @GET
    public List<GroupeResponseDTO> retrieve() {
        List<Groupe> beans = entityManager.createNamedQuery(Groupe.QUERY_LISTE_ALL).getResultList();
        return beans.stream().map((Groupe bean) -> {
            GroupeResponseDTO dto = new GroupeResponseDTO();
            dto.setNom(bean.getNom());
            return dto;
        }).collect(Collectors.toList());
    }

}
