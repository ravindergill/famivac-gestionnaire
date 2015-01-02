package fr.fava.gestionnaire.application;

import fr.fava.gestionnaire.application.exceptions.WrongPasswordException;
import fr.fava.gestionnaire.application.dto.RetrieveUtilisateursDTO;
import fr.fava.gestionnaire.application.dto.AjouterUtilisateurDTO;
import fr.fava.gestionnaire.domain.model.Utilisateur;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author paoesco
 */
@Stateless
public class UtilisateurService {

    @Inject
    private EntityManager entityManager;

    public String create(AjouterUtilisateurDTO dto) {
        String password = generatePassword();
        Utilisateur entity = new Utilisateur(
                dto.getLogin(),
                dto.getEmail(),
                password,
                dto.getGroupe(),
                dto.getNom(),
                dto.getPrenom());
        entityManager.persist(entity);
        return password;
    }

    public Utilisateur retrieve(String login) {
        Utilisateur entity = entityManager.find(Utilisateur.class, login);
        return entity;
    }

    public List<RetrieveUtilisateursDTO> retrieve() {
        List<Utilisateur> entities = entityManager.createNamedQuery(Utilisateur.QUERY_LISTE_ALL).getResultList();
        return entities.stream().map(entity -> {
            RetrieveUtilisateursDTO dto = new RetrieveUtilisateursDTO();
            dto.setLogin(entity.getLogin());
            dto.setNom(entity.getNom());
            dto.setPrenom(entity.getPrenom());
            dto.setGroupe(entity.getGroupe().getLibelle());
            dto.setEmail(entity.getEmail());
            return dto;
        }).collect(Collectors.toList());
    }

    public void update(Utilisateur entity) {
        entityManager.merge(entity);
    }

    public void delete(String login) {
        Utilisateur bean = entityManager.find(Utilisateur.class, login);
        entityManager.remove(bean);
    }

    public void changePassword(String login, String actualPassword, String newPassword) throws WrongPasswordException {
        Utilisateur entity = entityManager.find(Utilisateur.class, login);
        if (!actualPassword.equals(entity.getPassword())) {
            throw new WrongPasswordException();
        }
        entity.setPassword(newPassword);
    }

    public String reinitPassword(String login) {
        String password = generatePassword();
        Utilisateur entity = entityManager.find(Utilisateur.class, login);
        entity.setPassword(password);
        // TODO PES : envoyer email
        return password;
    }

    private String generatePassword() {
        return UUID.randomUUID().toString();
    }

}
