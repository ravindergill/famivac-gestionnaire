package fr.fava.gestionnaire.domain.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * @author paoesco
 */
@Entity
public class Inscription implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Embedded
    private Periode periode;

    @NotNull
    @OneToOne
    @JoinColumn(name = "FK_FAMILLE_ID", nullable = false)
    private Famille famille;

    @NotNull
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Enfant> enfants;

    public Inscription() {
        enfants = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public Periode getPeriode() {
        return periode;
    }

    public Famille getFamille() {
        return famille;
    }

    public Set<Enfant> getEnfants() {
        return Collections.unmodifiableSet(enfants);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.id);
        hash = 11 * hash + Objects.hashCode(this.periode);
        hash = 11 * hash + Objects.hashCode(this.famille);
        hash = 11 * hash + Objects.hashCode(this.enfants);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Inscription)) {
            return false;
        }
        Inscription other = (Inscription) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Inscription{" + "id=" + id + ", periode=" + periode + ", famille=" + famille + ", enfants=" + enfants + '}';
    }

}
