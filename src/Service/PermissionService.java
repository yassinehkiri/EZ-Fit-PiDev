/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Permission;
import Entities.Salle;
import Entities.personnel;
import Tools.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author lamis
 */
public class PermissionService implements Iservice<Permission>  {
 Connection cnx= MaConnexion.getInstance().getCnx();
 
    @Override
    public void ajouter(Permission t) {
      
        try {
           String sql="insert into Permission(id,type,reclamation,date_d,date_f,image,personnel_id) values('"+t.getId()+"','"+t.getType()+"','"+t.getReclamation()+"','"+t.getDate_d()+"','"+t.getDate_f()+"','"+t.getImage()+"','"+t.getPersonnel_id()+"')";
            Statement ste = cnx.createStatement();
           
            ste.executeUpdate(sql);
            System.out.println(" Permission Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public List<Permission> afficher() {
           ObservableList<Permission> Permission = FXCollections.observableArrayList();
        String sql ="select Permission.id,Permission.type,Permission.reclamation,Permission.date_d,Permission.date_f,Permission.image,personnel.nom_p from Permission inner join personnel on Permission.personnel_id=personnel.id ";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                Permission s = new Permission();
                s.setId(rs.getInt("id"));
                s.setType(rs.getString("type"));
                s.setReclamation(rs.getString("reclamation"));
                s.setDate_d(rs.getDate("date_d"));
                s.setDate_f(rs.getDate("date_f"));
                s.setImage(rs.getString("image"));
               s.setNom_p(rs.getString("nom_p"));
                Permission.add(s);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Permission;

    }

    @Override
    public void supprimer(Permission t) {
         String sql="delete from Permission where id = '"+t.getId()+"'";
        try {            
            PreparedStatement ste =cnx.prepareStatement(sql);           
            ste.executeUpdate(sql);
            System.out.println("Permission supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Permission t) {
         String sql="update Permission set  type=?, reclamation= ? , date_d=?,date_f=?,image=? where id='"+t.getId()+"'";
            try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setString(1, t.getType());
            ste.setString(2, t.getReclamation());
            ste.setDate(3, t.getDate_d());
            ste.setDate(4, t.getDate_f());
            
            ste.setString(5, t.getImage());
            
           ste.executeUpdate();
            System.out.println("Permission Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public List<String> getAll() {
        List<String> list = new ArrayList<String>();
        try {
            String requetee = "SELECT nom_p FROM personnel";
            PreparedStatement pst = cnx.prepareStatement(requetee);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs.toString());

            while (rs.next()) {
                list.add(rs.getString("nom_p"));
            }

            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
      public int chercherIds(String nom_p) throws SQLException{
         int idd=0;
         String requetee = "SELECT id FROM personnel where nom_p='"+nom_p+"';";
            PreparedStatement pst = cnx.prepareStatement(requetee);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {idd= rs.getInt("id");}
            return idd;
         }
    }
    

