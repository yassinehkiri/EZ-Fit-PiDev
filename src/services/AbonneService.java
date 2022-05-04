/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Abonne;
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
import util.MyConnexion;

/**
 *
 * @author yassine
 */
public class AbonneService implements iAbonne {
       
    Connection cnx = MyConnexion.getInstance().getCnx();
    
    @Override
    public void AjouterAbonne(Abonne a) {
try {
             String req = "INSERT INTO abonne ( id, nom_a , prenom_a, age_a, sexe_a, email_a, mdp_a, tel_a, adresse_a, message, image  ) VALUES ('" + a.getId() + "','" + a.getNom() + "','" + a.getPrenom() + "'"
             + ",'" + a.getAge() + "','" + a.getSexe() + "','" + a.getEmail() + "','" + a.getMdp() + "','" + a.getTel() + "','" + a.getAdresse() + "','" + a.getMessage() + "','" + a.getImage() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Abonne ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void SupprimerAbonne(int id) {
        String requete = "delete from abonne where id=?";
        try {
           PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
           Logger.getLogger(AbonneService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }       }

    @Override
    public List<Abonne> afficher() {
    List<Abonne> list = new ArrayList<>();
        
        try {
            String req = "SELECT id, nom_a , prenom_a, age_a, sexe_a, email_a, mdp_a, tel_a, adresse_a, message, image  from abonne";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                list.add(new Abonne( rs.getInt("id"),rs.getInt("age_a"),rs.getInt("tel_a"),rs.getString("nom_a"),rs.getString("prenom_a")
                ,rs.getString("sexe_a"),rs.getString("email_a"),rs.getString("mdp_a")
                ,rs.getString("adresse_a"),rs.getString("message"),rs.getString("image")));
                //System.out.println(list);
                            
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(list);
        return list;      }

    @Override
    public int modifier(Abonne a) {
 String sq13="UPDATE `abonne` SET  `nom_a`=?,`prenom_a`=?,`age_a`=?,`sexe_a`=?,`email_a`=?,`tel_a`=?,`adresse_a`=?,`image`=?,`mdp_a`=? WHERE id =?";
            
        try {
            PreparedStatement pst = cnx.prepareStatement(sq13);
            pst.setString(7, a.getAdresse());
            pst.setInt(3, a.getAge());
             pst.setString(5, a.getEmail()); 
              pst.setInt(6, a.getTel());
              pst.setString(9, a.getMdp()); 
              pst.setString(10, a.getImage());
               pst.setString(8, a.getMessage()); 
                pst.setString(1, a.getNom());
                 pst.setString(2, a.getPrenom()); 
                  pst.setString(4, a.getSexe());
                  // pst.setString(9, a.getImage());
           // pst.setInt(1, a.getIdab());
            pst.executeUpdate();
            System.out.println("abonne mis à jour avec succès !");
            System.out.println(a.toString());
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(AbonneService.class.getName()).log(Level.SEVERE, null, ex);
        }
         return 0;
    }
    public ObservableList<Abonne> chercherTitreplat(String chaine){
          String sql="SELECT * FROM abonne WHERE (nom_a LIKE ? or prenom_a LIKE ?)";
            
             Connection Cnx= MyConnexion.getInstance().getCnx();
            String ch="%"+chaine+"%";
            ObservableList<Abonne> myList= FXCollections.observableArrayList();
        try {
           
            Statement ste= Cnx.createStatement();
           // PreparedStatement pst = myCNX.getCnx().prepareStatement(requete6);
            PreparedStatement stee =Cnx.prepareStatement(sql);  
            stee.setString(1, ch);
            stee.setString(2, ch);
          //  stee.setString(3, ch);
            
            ResultSet rs = stee.executeQuery();
            while (rs.next()){
                
               
                Abonne p = new Abonne ();
                p.setId(rs.getInt(1));
                 p.setTel(rs.getInt(2));
                p.setAge(rs.getInt(3));
                p.setNom(rs.getString(4));
                p.setPrenom(rs.getString(5));
                p.setSexe(rs.getString(6));
                p.setEmail(rs.getString(7));
                p.setAdresse(rs.getString(8));
                p.setImage(rs.getString(9));
                p.setMdp(rs.getString(10));
                p.setMessage(rs.getString(11));
               
                
            /* p.setId(rs.getInt(1));
             p.setNom(rs.getString(2));
              p.setPrenom(rs.getString(3));
                p.setAge(rs.getInt(4));
                 p.setSexe(rs.getString(5));
                 p.setEmail(rs.getString(6));
                p.setMdp(rs.getString(7));
                p.setTel(rs.getInt(8));
                p.setAdresse(rs.getString(9));
                 p.setMessage(rs.getString(10));
                   p.setImage(rs.getString(11));*/
                
                myList.add(p);
                System.out.println("titre trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
      }
    
}
