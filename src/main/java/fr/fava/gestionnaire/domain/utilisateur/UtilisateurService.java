package fr.fava.gestionnaire.domain.utilisateur;

import fr.fava.gestionnaire.domain.model.Utilisateur;
import fr.fava.gestionnaire.interfaces.web.parametres.utilisateurs.AjouterUtilisateurForm;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.POST;

/**
 * @author paoesco
 */
@Stateless
@LocalBean
public class UtilisateurService {

    @Inject
    private EntityManager entityManager;

    public String create(AjouterUtilisateurForm request) {
        Utilisateur entity = new Utilisateur(request.getLogin(),
                request.getPassword(),
                request.getNom(),
                request.getPrenom(),
                request.getEmail(),
                request.getGroupe());
        entityManager.persist(entity);
        return entity.getLogin();
    }

    public UtilisateurResponseDTO retrieve(String login) {
        Utilisateur bean = entityManager.find(Utilisateur.class, login);
        UtilisateurResponseDTO response = new UtilisateurResponseDTO();
        response.setEmail(bean.getEmail());
        response.setLogin(bean.getLogin());
        response.setNom(bean.getNom());
        response.setPrenom(bean.getPrenom());
        response.setGroupe(bean.getGroupe().getNom());
        return response;
    }

    public List<UtilisateurResponseDTO> retrieve() {
        List<Utilisateur> beans = entityManager.createNamedQuery(Utilisateur.QUERY_LISTE_ALL).getResultList();
        return beans.stream().map(bean -> {
            UtilisateurResponseDTO beanResponse = new UtilisateurResponseDTO();
            beanResponse.setLogin(bean.getLogin());
            beanResponse.setNom(bean.getNom());
            beanResponse.setPrenom(bean.getPrenom());
            return beanResponse;
        }).collect(Collectors.toList());
    }

    public void update(String login, ModifierUtilisateurRequestDTO request) {
        Utilisateur bean = entityManager.find(Utilisateur.class, login);
        bean.setEmail(request.getEmail());
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            bean.setPassword(request.getPassword());
        }
        bean.setNom(request.getNom());
        bean.setPrenom(request.getPrenom());
        bean.setGroupe(request.getGroupe());
        entityManager.merge(bean);
    }

    public void delete(String login) {
        Utilisateur bean = entityManager.find(Utilisateur.class, login);
        entityManager.remove(bean);
    }

}
