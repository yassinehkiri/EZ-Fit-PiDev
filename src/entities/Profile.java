/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 *
 * @author omen
 */
public class Profile {
    int id,num_tel,user_id;
    String nom,prenom,ddn,adresse,sexe;
    InputStream pdp;
    long fileSize;
    public Profile() {
    } 

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public Profile(int id, int num_tel, String nom, String prenom, String ddn, InputStream pdp, String adresse, String sexe,int user_id, long fileSize) {
        this.id = id;
        this.num_tel = num_tel;
        this.nom = nom;
        this.prenom = prenom;
        this.ddn = ddn;
        this.pdp = pdp;
        this.adresse = adresse;
        this.sexe = sexe;
        this.user_id = user_id;
        this.fileSize = fileSize;
    }
        public Profile(int id, int num_tel, String nom, String prenom, String ddn, String adresse, String sexe,int user_id) {
        this.id = id;
        this.num_tel = num_tel;
        this.nom = nom;
        this.prenom = prenom;
        this.ddn = ddn;
        this.adresse = adresse;
        this.sexe = sexe;
        this.user_id = user_id;
    }
        public Profile(int id, int num_tel, String nom, String prenom, String ddn, InputStream pdp, String adresse, String sexe,int user_id) {
        this.id = id;
        this.num_tel = num_tel;
        this.nom = nom;
        this.prenom = prenom;
        this.ddn = ddn;
        this.pdp = pdp;
        this.adresse = adresse;
        this.sexe = sexe;
        this.user_id = user_id;
    }
        public Profile(int num_tel, String nom, String prenom, String ddn, InputStream pdp, String adresse, String sexe,int user_id,long fileSize) {
        this.num_tel = num_tel;
        this.nom = nom;
        this.prenom = prenom;
        this.ddn = ddn;
        this.pdp = pdp;
        this.adresse = adresse;
        this.sexe = sexe;
        this.user_id = user_id;
        this.fileSize = fileSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDdn() {
        return ddn;
    }

    public void setDdn(String ddn) {
        this.ddn = ddn;
    }

    public InputStream getPdp() {
        return pdp;
    }

    public void setPdp(InputStream pdp) {
        this.pdp = pdp;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setAmis(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Profile{" + "id=" + id + ", num_tel=" + num_tel + ", nom=" + nom + ", prenom=" + prenom + ", ddn=" + ddn + ", pdp=" + pdp + ", adresse=" + adresse + ", sexe=" + sexe + ", id utilisateur=" + user_id + '}';
    }
    
}
