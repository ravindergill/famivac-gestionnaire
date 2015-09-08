package fr.fava.gestionnaire.test.integration.application;

import fr.fava.gestionnaire.application.AjouterUtilisateurDTO;
import fr.fava.gestionnaire.application.UtilisateurService;
import fr.fava.gestionnaire.domain.common.Utilisateur;
import fr.fava.gestionnaire.test.integration.AbstractIntegrationTest;
import java.util.Collections;
import javax.inject.Inject;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author pescobar
 */
public class UtilisateurServiceIT extends AbstractIntegrationTest {

    @Inject
    private UtilisateurService utilisateurService;

    @Test
    public void testCreateAndRetrieve() {
        AjouterUtilisateurDTO request = new AjouterUtilisateurDTO();
        request.setEmail("email@domain.com");
        request.setLogin("login");
        request.setNom("Nom");
        request.setPrenom("Prénom");
        request.setGroupes(Collections.EMPTY_LIST);
        String password = utilisateurService.create(request);
        Assert.assertNotNull(password);

        Utilisateur utilisateur = utilisateurService.retrieve("login");
        Assert.assertNotNull(utilisateur);
        Assert.assertEquals("login", utilisateur.getLogin());
        Assert.assertEquals("email@domain.com", utilisateur.getEmail());
        Assert.assertEquals("Nom", utilisateur.getNom());
        Assert.assertEquals("Prénom", utilisateur.getPrenom());
        Assert.assertNotNull(utilisateur.getPassword());
    }

}
