/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Profile;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.MyConnexion;

/**
 *
 * @author omen
 */
public class ProfileService implements iservice<Profile>{
     Connection cnx;
     public ProfileService() {
         cnx = MyConnexion.getInstance().getCnx();
    }
   
    @Override
    public int ajouter(Profile t) {
        
            try {
          
           String query="INSERT INTO `profil` (`nom`, `prenom`, `ddn`, `pdp`, `num_tel`, `adresse`, `sexe`, `id_utilisateur`) VALUES (?, ?, ?, ?, ?, ?, ?, ?); ";
                PreparedStatement smt = cnx.prepareStatement(query);
                smt.setString(1, t.getNom());
                smt.setString(2, t.getPrenom());
                smt.setString(3, t.getDdn());
                
                smt.setBinaryStream(4,(InputStream) t.getPdp(),(int) t.getFileSize());
            
                smt.setInt(5, t.getNum_tel());
                smt.setString(6, t.getAdresse());
                smt.setString(7, t.getSexe());
                smt.setInt(8, t.getUser_id());
                int i=smt.executeUpdate();
return i;            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
           
       }
            return 0;
    }

   
   
    @Override
    public int modifier(Profile t) {
        System.out.println(t);
         try {
             if (t.getPdp()!=null){
       String query2="UPDATE `profil` SET `nom` = ?, `prenom` = ?, `ddn` = ?, `pdp` = ?, `num_tel` = ?, `adresse` = ?, `sexe` = ?, `id_utilisateur`= ? WHERE `profil`.`id` = ?; ";
                PreparedStatement smt = cnx.prepareStatement(query2);
                
                smt.setString(1, t.getNom());
                smt.setString(2, t.getPrenom());
                smt.setString(3, t.getDdn());
   
                               smt.setBinaryStream(4,(InputStream) t.getPdp(),(int) t.getFileSize());

                smt.setInt(5, t.getNum_tel());
                smt.setString(6, t.getAdresse());
                smt.setString(7, t.getSexe());
                smt.setInt(8, t.getUser_id());
                smt.setInt(9, t.getId());

                smt.executeUpdate();
return 1; }
             else {
                 String query2="UPDATE `profil` SET `nom` = ?, `prenom` = ?, `ddn` = ?, `num_tel` = ?, `adresse` = ?, `sexe` = ?, `id_utilisateur`= ? WHERE `profil`.`id` = ?; ";
                PreparedStatement smt = cnx.prepareStatement(query2);
                
                smt.setString(1, t.getNom());
                smt.setString(2, t.getPrenom());
                smt.setString(3, t.getDdn());
   

                smt.setInt(4, t.getNum_tel());
                smt.setString(5, t.getAdresse());
                smt.setString(6, t.getSexe());
                smt.setInt(7, t.getUser_id());
                smt.setInt(8, t.getId());

                smt.executeUpdate();
             return 1;}} catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return 0;
    }}
    
    @Override
    public void supprimer(Profile t) {
         try {
       String query2="delete from profil where id=?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                smt.setInt(1, t.getId());
                smt.executeUpdate();
                System.out.println("suppression avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}

    @Override
    public List<Profile> find() {
        ArrayList l=new ArrayList(); 
        
        try {
       String query2="select * from profil";
                PreparedStatement smt = cnx.prepareStatement(query2);
                Profile p;
                ResultSet rs= smt.executeQuery();
                while(rs.next()){
                   p=new Profile(rs.getInt("id"),rs.getInt("num_tel"),rs.getString("nom"),rs.getString("prenom"),rs.getString("ddn"),rs.getBlob("pdp").getBinaryStream(),rs.getString("adresse"),rs.getString("sexe"),rs.getInt("id_utilisateur"));
                   l.add(p);
                }
                System.out.println(l);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }

        return l;   
    }
    public Profile findByUserId(int id){
              try {
       String query2="select * from profil where id_utilisateur=?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                smt.setInt(1, id);
                Profile p;
                ResultSet rs= smt.executeQuery();
                while(rs.next()){
                   p=new Profile(rs.getInt("id"),rs.getInt("num_tel"),rs.getString("nom"),rs.getString("prenom"),rs.getString("ddn"),rs.getBlob("pdp").getBinaryStream(),rs.getString("adresse"),rs.getString("sexe"),rs.getInt("id_utilisateur"));
                             return p;

                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                }
              return null;
    }
}
