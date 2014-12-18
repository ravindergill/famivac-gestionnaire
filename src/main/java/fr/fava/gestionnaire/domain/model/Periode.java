package fr.fava.gestionnaire.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author paoesco
 */
@Embeddable
public class Periode implements Serializable {

    @Column(name = "DATE_DEBUT")
    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Column(name = "DATE_FIN")
    @Temporal(TemporalType.DATE)
    private Date dateFin;

    protected Periode() {
    }

    public Periode(final Date dateDebut, final Date dateFin) {
        if (dateDebut == null || dateFin == null) {
            throw new IllegalArgumentException("Les dates de début et de fin sont obligatoires");
        }
        if (dateDebut.after(dateFin)) {
            Logger.getAnonymousLogger().log(Level.FINE, "Date d\u00e9but = {0}", dateDebut);
            Logger.getAnonymousLogger().log(Level.FINE, "Date fin = {0}", dateFin);
            throw new IllegalArgumentException("La date de début doit être antérieure à la date de fin");
        }
        this.dateDebut = (Date) dateDebut.clone();
        this.dateFin = (Date) dateFin.clone();
    }

    public boolean chevauche(Periode periode) {
        if (periode == null) {
            throw new IllegalArgumentException("Paramètre obligatoire");
        }
        // Période en paramètre contenue
        if (dateDebut.compareTo(periode.dateDebut) <= 0
                && dateFin.compareTo(periode.dateFin) >= 0) {
            return true;
        }
        // Période en paramètre commence avant, termine avant
        if (dateDebut.compareTo(periode.dateDebut) >= 0
                && dateFin.compareTo(periode.dateFin) >= 0
                && dateDebut.compareTo(periode.dateFin) <= 0) {
            return true;
        }
        // Période en paramètre commence après, termine après
        if (dateDebut.compareTo(periode.dateDebut) <= 0
                && dateFin.compareTo(periode.dateFin) <= 0
                && dateFin.compareTo(periode.dateDebut) >= 0) {
            return true;
        }
        if (periode.dateDebut.compareTo(dateDebut) <= 0
                && periode.dateFin.compareTo(dateFin) >= 0) {
            return true;
        }
        return false;
    }

}
