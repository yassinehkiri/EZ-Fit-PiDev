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
public class Permission {
    private int id;
    private String type;
     private String reclamation;
     private Date date_d;
    private Date date_f;
    private String image;
       private int personnel_id;
 private String nom_p;
    public Permission() {
    }

    public Permission(int id) {
        this.id = id;
    }

    public Permission(int id, String type, String reclamation, Date date_d, Date date_f, String image, int personnel_id) {
        this.id = id;
        this.type = type;
        this.reclamation = reclamation;
        this.date_d = date_d;
        this.date_f = date_f;
        this.image = image;
        this.personnel_id = personnel_id;
    }

    public Permission(String type, String reclamation, Date date_d, Date date_f, String image, int personnel_id) {
        this.type = type;
        this.reclamation = reclamation;
        this.date_d = date_d;
        this.date_f = date_f;
        this.image = image;
        this.personnel_id = personnel_id;
    }

    public Permission(int id, String type, String reclamation, Date date_d, Date date_f, String image, String nom_p) {
        this.id = id;
        this.type = type;
        this.reclamation = reclamation;
        this.date_d = date_d;
        this.date_f = date_f;
        this.image = image;
        this.nom_p = nom_p;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReclamation() {
        return reclamation;
    }

    public void setReclamation(String reclamation) {
        this.reclamation = reclamation;
    }

    public Date getDate_d() {
        return date_d;
    }

    public void setDate_d(Date date_d) {
        this.date_d = date_d;
    }

    public Date getDate_f() {
        return date_f;
    }

    public void setDate_f(Date date_f) {
        this.date_f = date_f;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPersonnel_id() {
        return personnel_id;
    }

    public void setPersonnel_id(int personnel_id) {
        this.personnel_id = personnel_id;
    }

    public String getNom_p() {
        return nom_p;
    }

    public void setNom_p(String nom_p) {
        this.nom_p = nom_p;
    }

    @Override
    public String toString() {
        return "Permission{" + "id=" + id + ", type=" + type + ", reclamation=" + reclamation + ", date_d=" + date_d + ", date_f=" + date_f + ", image=" + image + ", personnel_id=" + personnel_id + ", nom_p=" + nom_p + '}';
    }

    
    
    
    
       
       
}
