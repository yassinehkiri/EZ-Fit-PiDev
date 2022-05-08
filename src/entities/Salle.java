/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author lamis
 */
public class Salle {
    private int id;
    private  String nom_s ;
    private  String adresse_s;
    private int code_postal;
    private String ville;
    private int nombre_p;
   private String image;
  
public Salle(){
    
}

    public Salle(int id, String nom_s, String adresse_s, int code_postal, String ville, int nombre_p, String image) {
        this.id = id;
        this.nom_s = nom_s;
        this.adresse_s = adresse_s;
        this.code_postal = code_postal;
        this.ville = ville;
        this.nombre_p = nombre_p;
        this.image = image;
    }

    public Salle(String nom_s, String adresse_s, int code_postal, String ville, int nombre_p, String image) {
        this.nom_s = nom_s;
        this.adresse_s = adresse_s;
        this.code_postal = code_postal;
        this.ville = ville;
        this.nombre_p = nombre_p;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getNom_s() {
        return nom_s;
    }

    public String getAdresse_s() {
        return adresse_s;
    }

    public int getCode_postal() {
        return code_postal;
    }

    public String getVille() {
        return ville;
    }

    public int getNombre_p() {
        return nombre_p;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom_s(String nom_s) {
        this.nom_s = nom_s;
    }

    public void setAdresse_s(String adresse_s) {
        this.adresse_s = adresse_s;
    }

    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setNombre_p(int nombre_p) {
        this.nombre_p = nombre_p;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Salle(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Salle{" + "id=" + id + ", nom_s=" + nom_s + ", adresse_s=" + adresse_s + ", code_postal=" + code_postal + ", ville=" + ville + ", nombre_p=" + nombre_p + ", image=" + image + '}';
    }

    
    
    
    
}
