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
public class Publication {

    @Override
    public String toString() {
        return "Publication{" + "id=" + id + ", redacteur=" + redacteur + ", date=" + date + ", contenu=" + contenu + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRedacteur() {
        return redacteur;
    }

    public void setRedacteur(String redacteur) {
        this.redacteur = redacteur;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    private int id;
    private String redacteur,date,contenu;

    public Publication(int id, String redacteur, String date, String contenu) {
        this.id = id;
        this.redacteur = redacteur;
        this.date = date;
        this.contenu = contenu;
    }

    public Publication(String redacteur, String date, String contenu) {
        this.redacteur = redacteur;
        this.date = date;
        this.contenu = contenu;
    }

    public Publication() {
    }
}
