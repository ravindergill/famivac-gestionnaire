package fr.fava.gestionnaire.domain.famille;

import fr.fava.gestionnaire.domain.model.Chambre;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.PathParam;

/**
 *
 * @author Paolo
 */
@Stateless
@LocalBean
public class ChambreService {

    @Inject
    private EntityManager entityManager;

    public void delete(@PathParam("id") long id) {
        Chambre entity = entityManager.find(Chambre.class, id);
        entity.getFamille().retirerChambre(entity);
        entityManager.remove(entity);
    }

}
