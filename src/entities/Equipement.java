/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import javafx.scene.chart.PieChart;

/**
 *
 * @author LENOVO
 */
public class Equipement {
     private int id;
    private int fournisseur_id ;
     private String nom_e;
     private String type_e ;
    private String marque ;
     private String gamme ;
     private int quantite ;
       private Date date_commande ;
     private Double prix ;  
 private String nom_f;
    public Equipement() {
    }

    public Equipement(String nom_e, String type_e, String marque, String gamme, int quantite, Date date_commande, Double prix, String nom_f) {
        this.nom_e = nom_e;
        this.type_e = type_e;
        this.marque = marque;
        this.gamme = gamme;
        this.quantite = quantite;
        this.date_commande = date_commande;
        this.prix = prix;
        this.nom_f = nom_f;
    }

    public Equipement(int aInt, String string, String string0, String string1, String string2, String string3, int aInt0, Date date, double aDouble) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Equipement(int aInt, String string, String string0, String string1, String string2, int aInt0, Date date, double aDouble, String string3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    public String getNom_f() {
        return nom_f;
    }

    public void setNom_f(String nom_f) {
        this.nom_f = nom_f;
    }

    public Equipement(int id, int fournisseur_id, String nom_e, String type_e, String marque, String gamme, int quantite, Date date_commande, Double prix, String nom_f) {
        this.id = id;
        this.fournisseur_id = fournisseur_id;
        this.nom_e = nom_e;
        this.type_e = type_e;
        this.marque = marque;
        this.gamme = gamme;
        this.quantite = quantite;
        this.date_commande = date_commande;
        this.prix = prix;
        this.nom_f = nom_f;
    }

    public Equipement(int id,String nom_f, String nom_e, String type_e, String marque, String gamme, int quantite, Date date_commande, Double prix) {
        this.id = id;
         this.nom_f = nom_f;
        this.nom_e = nom_e;
        this.type_e = type_e;
        this.marque = marque;
        this.gamme = gamme;
        this.quantite = quantite;
        this.date_commande = date_commande;
        this.prix = prix;
       
    }

    public Equipement(int id, int fournisseur_id, String nom_e, String type_e, String marque, String gamme, int quantite, Date date_commande, Double prix) {
        this.id = id;
        this.fournisseur_id = fournisseur_id;
        this.nom_e = nom_e;
        this.type_e = type_e;
        this.marque = marque;
        this.gamme = gamme;
        this.quantite = quantite;
        this.date_commande = date_commande;
        this.prix = prix;
    }

    public Equipement(int fournisseur_id, String nom_e, String type_e, String marque, String gamme, int quantite, Date date_commande, Double prix) {
        this.fournisseur_id = fournisseur_id;
        this.nom_e = nom_e;
        this.type_e = type_e;
        this.marque = marque;
        this.gamme = gamme;
        this.quantite = quantite;
        this.date_commande = date_commande;
        this.prix = prix;
    }

    public Equipement(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFournisseur_id() {
        return fournisseur_id;
    }

    public void setFournisseur_id(int fournisseur_id) {
        this.fournisseur_id = fournisseur_id;
    }

    public String getNom_e() {
        return nom_e;
    }

    public void setNom_e(String nom_e) {
        this.nom_e = nom_e;
    }

    public String getType_e() {
        return type_e;
    }

    public void setType_e(String type_e) {
        this.type_e = type_e;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getGamme() {
        return gamme;
    }

    public void setGamme(String gamme) {
        this.gamme = gamme;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(Date date_commande) {
        this.date_commande = date_commande;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Equipement{" + "id=" + id + ", fournisseur_id=" + fournisseur_id + ", nom_e=" + nom_e + ", type_e=" + type_e + ", marque=" + marque + ", gamme=" + gamme + ", quantite=" + quantite + ", date_commande=" + date_commande + ", prix=" + prix + ", nom_f=" + nom_f + '}';
    }

  
     
    
}
