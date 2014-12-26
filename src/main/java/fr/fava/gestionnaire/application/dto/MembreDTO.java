package fr.fava.gestionnaire.application.dto;

import fr.fava.gestionnaire.domain.model.Commune;
import fr.fava.gestionnaire.domain.utils.Email;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author paoesco
 */
@XmlRootElement
public class MembreDTO {

    private Long id;

    private String nom;

    private String nomDeNaissance;

    private String prenom;

    private String sexe;

    private Date dateNaissance;

    private boolean referent;

    private Commune communeDeNaissance;

    private String profession;

    private String lienDeParente;

    private String tel1;

    private String tel2;

    @Email
    private String email;

    public MembreDTO() {
        communeDeNaissance = new Commune();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomDeNaissance() {
        return nomDeNaissance;
    }

    public void setNomDeNaissance(String nomDeNaissance) {
        this.nomDeNaissance = nomDeNaissance;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public boolean isReferent() {
        return referent;
    }

    public void setReferent(boolean referent) {
        this.referent = referent;
    }

    public Commune getCommuneDeNaissance() {
        return communeDeNaissance;
    }

    public void setCommuneDeNaissance(Commune communeDeNaissance) {
        this.communeDeNaissance = communeDeNaissance;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getLienDeParente() {
        return lienDeParente;
    }

    public void setLienDeParente(String lienDeParente) {
        this.lienDeParente = lienDeParente;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
