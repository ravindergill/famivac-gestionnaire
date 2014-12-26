package fr.fava.gestionnaire.domain.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * @author paoesco
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Groupe.QUERY_LISTE_ALL, query = "select g from Groupe g")
})
public class Groupe implements Serializable {

    public static final String QUERY_LISTE_ALL = "getGroupes";
    
    public static final String ROLE_GESTIONNAIRE = "ROLE_GESTIONNAIRE";

    @Id
    @Column(nullable = false)
    private String nom;

    private String libelle;

    @OneToMany(mappedBy = "groupe")
    private final Set<Utilisateur> utilisateurs;

    protected Groupe() {
        utilisateurs = new HashSet<>();
    }

    public Groupe(String nom, String libelle) {
        if (nom == null || nom.isEmpty()){
            throw new IllegalArgumentException("Le nom est obligatoire");
        }
        this.utilisateurs = new HashSet<>();
        this.nom = nom;
        this.libelle = libelle;
    }

    public String getNom() {
        return nom;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<Utilisateur> getUtilisateurs() {
        return Collections.unmodifiableSet(utilisateurs);
    }

}
