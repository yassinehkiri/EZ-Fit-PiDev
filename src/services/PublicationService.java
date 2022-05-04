/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Publication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import util.MyConnexion;

/**
 *
 * @author yassine
 */
public class PublicationService implements iPublication {
    Connection cnx = MyConnexion.getInstance().getCnx();

    @Override
    public void AjouterPublication(Publication p) {
        String sql = "INSERT INTO publication (redacteur,date ,contenu  ) VALUES ('" + p.getRedacteur() + "','" + p.getDate() + "','" + p.getContenu() + "')";
       Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
         alert.setHeaderText("Publication deja existe");
        /* try {
             String req = "INSERT INTO publication (redacteur,date ,contenu  ) VALUES ('" + p.getRedacteur() + "','" + p.getDate() + "','" + p.getContenu() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Publication ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
        try {
     if(existe1(p)==0){
          
            Statement ste = cnx.createStatement();
           
            ste.executeUpdate(sql);
            System.out.println(" Publication Ajoutée");
     }
     else
     {
         System.out.println("Publication deja existe"); 
         alert.setContentText("Veuillez Saisir Une Nouvelle Publication");
            alert.showAndWait();
            
     }
     
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void SupprimerPublication(int id) {
String requete = "delete from publication where id=?";
        try {
          PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
           Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }    }

    @Override
    public List<Publication> afficher() {
        List<Publication> list = new ArrayList<>();
        
        try {
            String req = "SELECT id, redacteur, date, contenu from publication";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                list.add(new Publication( rs.getInt("id"),rs.getString("redacteur"),rs.getString("date"),rs.getString("contenu")));
                //System.out.println(list);
                            
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }

    /* modifier avec deux parametres
    @Override
    public void ModifierPublication(Publication p, int id) {

          String req2 = "UPDATE `publication` SET `redacteur`=?,`date`=?,`contenu`=?WHERE id =?";
        try{
            
       
                PreparedStatement st = cnx.prepareStatement(req2);
		
          st.setString(1, p.getRedacteur());
          st.setString(2, p.getDate());
          st.setString(3, p.getContenu());
            

          st.setInt(4,id);
          st.executeUpdate();
                
                 System.out.println("publication mis à jour avec succès !");
                 System.out.println(p.toString());
        }catch (SQLException ex) {
            System.out.println(p.toString());
            System.out.println("erreur lors de la modification " + ex.getMessage());
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
	}    */

    @Override
    public int modifier(Publication p) {
  
        String sq13="UPDATE `publication` SET `redacteur`=?,`date`=?,`contenu`=?WHERE id =?";
            
        try {
            PreparedStatement pst = cnx.prepareStatement(sq13);
            pst.setString(1, p.getRedacteur());
            pst.setString(2, p.getDate());
            pst.setString(3, p.getContenu());

            pst.setInt(4, p.getId());
            pst.executeUpdate();
            System.out.println("publication mis à jour avec succès !");
            System.out.println(p.toString());
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(PublicationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;    }
    
    
    
    
    public ObservableList<Publication> chercherTitreplat(String chaine){
          String sql="SELECT * FROM publication WHERE (redacteur LIKE ? or contenu LIKE ?)";
            
             Connection cnx= MyConnexion.getInstance().getCnx();
            String ch="%"+chaine+"%";
            ObservableList<Publication> myList= FXCollections.observableArrayList();
        try {
           
            Statement ste= cnx.createStatement();
           // PreparedStatement pst = myCNX.getCnx().prepareStatement(requete6);
            PreparedStatement stee =cnx.prepareStatement(sql);  
            stee.setString(1, ch);
            stee.setString(2, ch);
          //  stee.setString(3, ch);
            
            ResultSet rs = stee.executeQuery();
            while (rs.next()){
                
               
                Publication p = new Publication ();
                p.setId(rs.getInt(1));
                p.setRedacteur(rs.getString(2));
                p.setDate(rs.getString(3));
                p.setContenu(rs.getString(4));
             
                
                
                myList.add(p);
                System.out.println("titre trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
      }
    
    
     public int existe1(Publication s) throws SQLException {
          
        Statement s1 = cnx.createStatement();
        ResultSet rs = s1.executeQuery("SELECT COUNT(*) from publication WHERE contenu = '" + s.getContenu()+"'");
        int size = 0;
        rs.next();
        size=rs.getInt(1);
        return size;
    }
     
     
     
     public int NumberReacts(int id) throws SQLException {
        String req = "select * from publication where i  = ?";
       int nb = 0;
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
           
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                nb++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nb;
}
}
