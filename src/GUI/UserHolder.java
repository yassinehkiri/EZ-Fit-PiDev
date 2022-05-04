/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author omen
 */
public class UserHolder {
    static int id;
    static String nom,prenom, mail;

    public static String getMail() {
        return mail;
    }

    public static void setMail(String mail) {
        UserHolder.mail = mail;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        UserHolder.id = id;
    }

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        UserHolder.nom = nom;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        UserHolder.prenom = prenom;
    }
    
    

}
