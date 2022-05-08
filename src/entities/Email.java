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
public class Email {
    private int idemail;
    private String destinataire;
    private String objet;
    private String contenue;

    public int getIdemail() {
        return idemail;
    }

    public void setIdemail(int idemail) {
        this.idemail = idemail;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }



    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getContenue() {
        return contenue;
    }

    public void setContenue(String contenue) {
        this.contenue = contenue;
    }

    @Override
    public String toString() {
        return "Email{" + "idemail=" + idemail + ", destinataire=" + destinataire + ", objet=" + objet + ", contenue=" + contenue + '}';
    }

    

    public Email() {
    }

    public Email(String destinataire, String objet, String contenue) {
        this.destinataire = destinataire;
        this.objet = objet;
        this.contenue = contenue;
    }

    public Email(int idemail, String destinataire, String objet, String contenue) {
        this.idemail = idemail;
        this.destinataire = destinataire;
        this.objet = objet;
        this.contenue = contenue;
    }


    
}
