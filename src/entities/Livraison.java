/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author LENOVO
 */
public class Livraison {
     private int id;
          private int num_l;
    private String nom_livreur;
     private String prenom_livreur;
        private String tel_livreur;
        private String adresse_livraison;
        private Date date_livraison;
        private Date date_arrive;

    public Livraison() {
    }

    public Livraison(int id, int num_l, String nom_livreur, String prenom_livreur, String tel_livreur, String adresse_livraison, Date date_livraison, Date date_arrive) {
        this.id = id;
        this.num_l = num_l;
        this.nom_livreur = nom_livreur;
        this.prenom_livreur = prenom_livreur;
        this.tel_livreur = tel_livreur;
        this.adresse_livraison = adresse_livraison;
        this.date_livraison = date_livraison;
        this.date_arrive = date_arrive;
    }

    public Livraison(int num_l, String nom_livreur, String prenom_livreur, String tel_livreur, String adresse_livraison, Date date_livraison, Date date_arrive) {
        this.num_l = num_l;
        this.nom_livreur = nom_livreur;
        this.prenom_livreur = prenom_livreur;
        this.tel_livreur = tel_livreur;
        this.adresse_livraison = adresse_livraison;
        this.date_livraison = date_livraison;
        this.date_arrive = date_arrive;
    }

    public Livraison(int id) {
        this.id = id;
    }

    

   

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_l() {
        return num_l;
    }

    public void setNum_l(int num_l) {
        this.num_l = num_l;
    }

    public String getNom_livreur() {
        return nom_livreur;
    }

    public void setNom_livreur(String nom_livreur) {
        this.nom_livreur = nom_livreur;
    }

    public String getPrenom_livreur() {
        return prenom_livreur;
    }

    public void setPrenom_livreur(String prenom_livreur) {
        this.prenom_livreur = prenom_livreur;
    }

    public String getTel_livreur() {
        return tel_livreur;
    }

    public void setTel_livreur(String tel_livreur) {
        this.tel_livreur = tel_livreur;
    }

    public String getAdresse_livraison() {
        return adresse_livraison;
    }

    public void setAdresse_livraison(String adresse_livraison) {
        this.adresse_livraison = adresse_livraison;
    }

    public Date getDate_livraison() {
        return date_livraison;
    }

    public void setDate_livraison(Date date_livraison) {
        this.date_livraison = date_livraison;
    }

    public Date getDate_arrive() {
        return date_arrive;
    }

    public void setDate_arrive(Date date_arrive) {
        this.date_arrive = date_arrive;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id=" + id + ", num_l=" + num_l + ", nom_livreur=" + nom_livreur + ", prenom_livreur=" + prenom_livreur + ", tel_livreur=" + tel_livreur + ", adresse_livraison=" + adresse_livraison + ", date_livraison=" + date_livraison + ", date_arrive=" + date_arrive + '}';
    }
        
        
        
        
}
