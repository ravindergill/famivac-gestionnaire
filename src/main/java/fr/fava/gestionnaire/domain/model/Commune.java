package fr.fava.gestionnaire.domain.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 *
 * @author paoesco
 */
@Entity
@NamedQuery(name = Commune.QUERY_RETRIEVE_ALL, query = "select c from Commune c order by ville")
public class Commune implements Serializable {

    public static final String QUERY_RETRIEVE_ALL = "communeRetrieveAll";

    @Id
    private String code;

    @Column(nullable = false)
    private String ville;

    protected Commune() {

    }

    public Commune(String code, String ville) {
        if (code == null || code.isEmpty() || ville == null || ville.isEmpty()) {
            throw new IllegalArgumentException("Tous les paramètres sont obligatoires");
        }
        this.code = code;
        this.ville = ville;
    }

    public String getCode() {
        return code;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        if (ville == null || ville.isEmpty()) {
            throw new IllegalArgumentException("La ville est obligatoire");
        }
        this.ville = ville;
    }

}
