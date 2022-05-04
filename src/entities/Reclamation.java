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
public class Reclamation {

    @Override
    public String toString() {
        return "Reclamation{" + "idR=" + idR + ", redacteurR=" + redacteurR + ", dateR=" + dateR + ", contenuR=" + contenuR + '}';
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public String getRedacteurR() {
        return redacteurR;
    }

    public void setRedacteurR(String redacteurR) {
        this.redacteurR = redacteurR;
    }

    public String getDateR() {
        return dateR;
    }

    public void setDateR(String dateR) {
        this.dateR = dateR;
    }

    public String getContenuR() {
        return contenuR;
    }

    public void setContenuR(String contenuR) {
        this.contenuR = contenuR;
    }

    public Reclamation(int idR, String redacteurR, String dateR, String contenuR) {
        this.idR = idR;
        this.redacteurR = redacteurR;
        this.dateR = dateR;
        this.contenuR = contenuR;
    }

    public Reclamation(String redacteurR, String dateR, String contenuR) {
        this.redacteurR = redacteurR;
        this.dateR = dateR;
        this.contenuR = contenuR;
    }

    public Reclamation() {
    }
    private int idR;
    private String redacteurR,dateR,contenuR;
}
