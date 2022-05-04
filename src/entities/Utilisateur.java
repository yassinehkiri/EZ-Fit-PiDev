/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ridha
 */
public class Utilisateur {
    private int id;
    private String email,mdp,role;

    public Utilisateur() {
    }

    public Utilisateur(int id, String email, String mdp,String role) {
        this.id = id;
        this.email = email;
        this.mdp = mdp;
        this.role = role;
    }
    
    public Utilisateur(String email, String mdp,String role) {
        
        this.email = email;
        this.mdp = mdp;
        this.role = role;
    }
    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getMdp() {
        return mdp;
    }
        public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setId(int id) {
        this.id = id;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return "personne{" + "id=" + id + ", nom=" + email + ", prenom=" + mdp + ", role="+ role +"}";
    }
    
    
}
