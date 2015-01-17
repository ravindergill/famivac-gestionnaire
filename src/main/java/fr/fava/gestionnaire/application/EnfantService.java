package fr.fava.gestionnaire.application;

import fr.fava.gestionnaire.domain.model.enfant.Enfant;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
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
    
    public Long create(Enfant enfant) {
        if (!enfant.getInscripteur().isResponsableLegal()) {
            entityManager.persist(enfant.getResponsableLegal());
        }
        entityManager.persist(enfant);
        return enfant.getId();
    }
    
}
