/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author yassine
 */
public class Abonne {

    @Override
    public String toString() {
        return "Abonne{" + "id=" + id + ", age=" + age + ", tel=" + tel + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", email=" + email + ", adresse=" + adresse + ", image=" + image + ", mdp=" + mdp + ", message=" + message + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
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

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Abonne(int id, int age, int tel, String nom, String prenom, String sexe, String email, String adresse, String image, String mdp, String message) {
        this.id = id;
        this.age = age;
        this.tel = tel;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.email = email;
        this.adresse = adresse;
        this.image = image;
        this.mdp = mdp;
        this.message = message;
    }

    
    private int id,age,tel;

    public Abonne() {
    }
    private String nom,prenom,sexe,email,adresse,image,mdp,message;

    public String toString(int age) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setAge(String age) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
