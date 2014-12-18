package fr.fava.gestionnaire.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author paoesco
 */
@Entity
@Table(name = "GROUPE")
@NamedQueries({
    @NamedQuery(name = Groupe.QUERY_LISTE_ALL, query = "select g from Groupe g")
})
public class Groupe implements Serializable {

    public static final String QUERY_LISTE_ALL = "getGroupes";

    @Id
    @Column(name = "NOM", nullable = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String nom;

    @OneToMany(mappedBy = "groupe")
    private List<Utilisateur> utilisateurs;

    protected Groupe() {

    }

    public Groupe(String nom) {
        this.utilisateurs = new ArrayList<>();
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

}
