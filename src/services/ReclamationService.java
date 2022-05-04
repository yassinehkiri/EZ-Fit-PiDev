/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Publication;
import entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.MyConnexion;

/**
 *
 * @author yassine
 */
public class ReclamationService implements iReclamation {
  Connection cnx = MyConnexion.getInstance().getCnx();
    @Override
    public void AjouterReclamation(Reclamation r) {
        try {
             String req = "INSERT INTO reclamation (redacteurR,dateR ,contenuR  ) VALUES ('" + r.getRedacteurR() + "','" + r.getDateR() + "','" + r.getContenuR() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reclamation ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void SupprimerReclamation(int idR) {
      String requete = "delete from reclamation where idR=?";
        try {
           PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1,idR);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
           Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }      }

    @Override
    public List<Reclamation> afficher() {
     List<Reclamation> list = new ArrayList<>();
        
        try {
            String req = "SELECT idR, redacteurR, dateR, contenu from reclamation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                list.add(new Reclamation( rs.getInt("idR"),rs.getString("redacteurR"),rs.getString("dateR"),rs.getString("contenuR")));
                //System.out.println(list);
                            
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;    }

    @Override
    public int modifierR(Reclamation r) {
    String sq13="UPDATE `reclamation` SET `redacteurR`=?,`dateR`=?,`contenuR`=?WHERE idR=?";
            
        try {
            PreparedStatement pst = cnx.prepareStatement(sq13);
            pst.setString(1, r.getRedacteurR());
            pst.setString(2, r.getDateR());
            pst.setString(3, r.getContenuR());

            pst.setInt(4, r.getIdR());
            pst.executeUpdate();
            System.out.println("Reclamation mis à jour avec succès !");
            System.out.println(r.toString());
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;    
    }
        
}
    

