/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author lamis
 */
public class personnel {
      private int id;
      private int salle_id;
    private  String nom_p ;
    private  String prenom_p ; 
   private  int tel_p ;
   private  String email_p ;
   private  String mdp ;
   private  double salaire_p ;
    private  String poste ;
   private  int h_travail ;
   private  int h_absence ;
    private  Date date_embauche ;
     private String nom_s;

    public personnel(int id, int salle_id, String nom_p, String prenom_p, int tel_p, String email_p, String mdp, double salaire_p, String poste, int h_travail, int h_absence, Date date_embauche) {
        this.id = id;
        this.salle_id = salle_id;
        this.nom_p = nom_p;
        this.prenom_p = prenom_p;
        this.tel_p = tel_p;
        this.email_p = email_p;
        this.mdp = mdp;
        this.salaire_p = salaire_p;
        this.poste = poste;
        this.h_travail = h_travail;
        this.h_absence = h_absence;
        this.date_embauche = date_embauche;
    }
    

    public personnel(String nom_p, String prenom_p, int tel_p, String email_p, String mdp, double salaire_p, String poste, int h_travail, int h_absence, Date date_embauche, String nom_s) {
        this.nom_p = nom_p;
        this.prenom_p = prenom_p;
        this.tel_p = tel_p;
        this.email_p = email_p;
        this.mdp = mdp;
        this.salaire_p = salaire_p;
        this.poste = poste;
        this.h_travail = h_travail;
        this.h_absence = h_absence;
        this.date_embauche = date_embauche;
        this.nom_s = nom_s;
    }
   
              

    public personnel(int id, int salle_id, String nom_p, String prenom_p, int tel_p, String email_p, String mdp, double salaire_p, String poste, int h_travail, int h_absence) {
        this.id = id;
        this.salle_id = salle_id;
        this.nom_p = nom_p;
        this.prenom_p = prenom_p;
        this.tel_p = tel_p;
        this.email_p = email_p;
        this.mdp = mdp;
        this.salaire_p = salaire_p;
        this.poste = poste;
        this.h_travail = h_travail;
        this.h_absence = h_absence;
    }

    public personnel(int id, String nom_p, String prenom_p, int tel_p, String email_p, String mdp, double salaire_p, String poste, int h_travail, int h_absence, Date date_embauche, String nom_s) {
        this.id = id;
        this.nom_p = nom_p;
        this.prenom_p = prenom_p;
        this.tel_p = tel_p;
        this.email_p = email_p;
        this.mdp = mdp;
        this.salaire_p = salaire_p;
        this.poste = poste;
        this.h_travail = h_travail;
        this.h_absence = h_absence;
        this.date_embauche = date_embauche;
        this.nom_s = nom_s;
    }

    public personnel(int id, int salle_id, String nom_p, String prenom_p, int tel_p, String email_p, String mdp, double salaire_p, String poste, int h_travail, int h_absence, Date date_embauche, String nom_s) {
        this.id = id;
        this.salle_id = salle_id;
        this.nom_p = nom_p;
        this.prenom_p = prenom_p;
        this.tel_p = tel_p;
        this.email_p = email_p;
        this.mdp = mdp;
        this.salaire_p = salaire_p;
        this.poste = poste;
        this.h_travail = h_travail;
        this.h_absence = h_absence;
        this.date_embauche = date_embauche;
        this.nom_s = nom_s;
    }

    public personnel() {
    }

    public personnel(int salle_id, String nom_p, String prenom_p, int tel_p, String email_p, String mdp, double salaire_p, String poste, int h_travail, int h_absence, Date date_embauche) {
        this.salle_id = salle_id;
        this.nom_p = nom_p;
        this.prenom_p = prenom_p;
        this.tel_p = tel_p;
        this.email_p = email_p;
        this.mdp = mdp;
        this.salaire_p = salaire_p;
        this.poste = poste;
        this.h_travail = h_travail;
        this.h_absence = h_absence;
        this.date_embauche = date_embauche;
    }

    

   

    public String getNom_s() {
        return nom_s;
    }

    public void setNom_s(String nom_s) {
        this.nom_s = nom_s;
    }

   

    public personnel(int id) {
        this.id = id;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSalle_id() {
        return salle_id;
    }

    public void setSalle_id(int salle_id) {
        this.salle_id = salle_id;
    }

    public String getNom_p() {
        return nom_p;
    }

    public void setNom_p(String nom_p) {
        this.nom_p = nom_p;
    }

    public String getPrenom_p() {
        return prenom_p;
    }

    public void setPrenom_p(String prenom_p) {
        this.prenom_p = prenom_p;
    }

    public int getTel_p() {
        return tel_p;
    }

    public void setTel_p(int tel_p) {
        this.tel_p = tel_p;
    }

    public String getEmail_p() {
        return email_p;
    }

    public void setEmail_p(String email_p) {
        this.email_p = email_p;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public double getSalaire_p() {
        return salaire_p;
    }

    public void setSalaire_p(double salaire_p) {
        this.salaire_p = salaire_p;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public int getH_travail() {
        return h_travail;
    }

    public void setH_travail(int h_travail) {
        this.h_travail = h_travail;
    }

    public int getH_absence() {
        return h_absence;
    }

    public void setH_absence(int h_absence) {
        this.h_absence = h_absence;
    }

    public Date getDate_embauche() {
        return date_embauche;
    }

    public void setDate_embauche(Date date_embauche) {
        this.date_embauche = date_embauche;
    }
     @Override
    public String toString() {
        return "personnel{" + "id=" + id + ", salle_id=" + salle_id + ", nom_p=" + nom_p + ", prenom_p=" + prenom_p + ", tel_p=" + tel_p + ", email_p=" + email_p + ", mdp=" + mdp + ", salaire_p=" + salaire_p + ", poste=" + poste + ", h_travail=" + h_travail + ", h_absence=" + h_absence + ", date_embauche=" + date_embauche + '}';
    }                
                    
}
