package fr.fava.gestionnaire.utils;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author paoesco
 */
@ApplicationScoped
public class EntityManagerProducer {

    @Produces
    @PersistenceContext(name = "fava-metier-pu")
    private EntityManager entityManager;

}
