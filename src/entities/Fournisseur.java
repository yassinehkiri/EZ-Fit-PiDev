/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author LENOVO
 */
public class Fournisseur {
 private int id;
 private String nom_f;
  private String prenom_f;
  private int tel_f;
  private String email_f;
  private String adresse;
  private String rib_f;
  private String image;

                
                
                
    public Fournisseur(int id, String nom_f, String prenom_f, int tel_f, String email_f, String adresse, String rib_f, String image) {
        this.id = id;
        this.nom_f = nom_f;
        this.prenom_f = prenom_f;
        this.tel_f = tel_f;
        this.email_f = email_f;
        this.adresse = adresse;
        this.rib_f = rib_f;
        this.image = image;
    }

    public Fournisseur() {
    }

    public Fournisseur(String nom_f, String prenom_f, int tel_f, String email_f, String adresse, String rib_f, String image) {
        this.nom_f = nom_f;
        this.prenom_f = prenom_f;
        this.tel_f = tel_f;
        this.email_f = email_f;
        this.adresse = adresse;
        this.rib_f = rib_f;
        this.image = image;
    }

    public Fournisseur(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_f() {
        return nom_f;
    }

    public void setNom_f(String nom_f) {
        this.nom_f = nom_f;
    }

    public String getPrenom_f() {
        return prenom_f;
    }

    public void setPrenom_f(String prenom_f) {
        this.prenom_f = prenom_f;
    }

    public int getTel_f() {
        return tel_f;
    }

    public void setTel_f(int tel_f) {
        this.tel_f = tel_f;
    }

    public String getEmail_f() {
        return email_f;
    }

    public void setEmail_f(String email_f) {
        this.email_f = email_f;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getRib_f() {
        return rib_f;
    }

    public void setRib_f(String rib_f) {
        this.rib_f = rib_f;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Fournisseur{" + "id=" + id + ", nom_f=" + nom_f + ", prenom_f=" + prenom_f + ", tel_f=" + tel_f + ", email_f=" + email_f + ", adresse=" + adresse + ", rib_f=" + rib_f + ", image=" + image + '}';
    }
                
                
    
}
