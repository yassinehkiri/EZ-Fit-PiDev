/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Salle;
import Entities.personnel;
import Tools.MaConnexion;
import java.sql.Connection;
import java.sql.Date;
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
public class personnelservice implements Iservice<personnel>{
  Connection cnx= MaConnexion.getInstance().getCnx();
   scontrole sc= new scontrole();
    @Override
    
    
     public void ajouter(personnel t) {
         try{
            String sql="insert into personnel(salle_id,nom_p,prenom_p,tel_p,email_p,mdp,salaire_p,poste,h_travail,h_absence,date_embauche)"+"Values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ste=cnx.prepareStatement(sql);
            ste.setInt(1, t.getSalle_id());
            ste.setString(2, t.getNom_p());
            ste.setString(3, t.getPrenom_p());
            ste.setInt(4, t.getTel_p());
            ste.setString(5,t.getEmail_p());
            ste.setString(6,t.getMdp());
            ste.setDouble(7,t.getSalaire_p());
            ste.setString(8,t.getPoste());
            ste.setInt(9, t.getH_travail());
            ste.setInt(10, t.getH_absence());
            ste.setDate(11, t.getDate_embauche());
            ste.executeUpdate();
            System.out.println("Personnel ajouté");
        }catch(SQLException ex){
             System.out.println(ex.getMessage());
        }

    }
    

    @Override
    public List<personnel> afficher() {
       
        ObservableList<personnel> personnel = FXCollections.observableArrayList();
        String sql ="select personnel.id,salle.nom_s,personnel.nom_p,personnel.prenom_p,personnel.tel_p,personnel.email_p,personnel.mdp,personnel.salaire_p,personnel.poste,personnel.h_travail,personnel.h_absence,personnel.date_embauche from personnel  INNER JOIN  salle  on personnel.salle_id=salle.id  ";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            personnel p = new personnel();
            while(rs.next()){
                
                p.setId(rs.getInt("id"));
                p.setNom_s(rs.getString("nom_s"));
                p.setNom_p(rs.getString("nom_p"));
                p.setPrenom_p(rs.getString("prenom_p"));
                p.setTel_p(rs.getInt("tel_p"));
                p.setEmail_p(rs.getString("email_p"));
                p.setMdp(rs.getString("mdp"));
                p.setSalaire_p(rs.getDouble("salaire_p"));
                p.setPoste(rs.getString("poste"));
                p.setH_travail(rs.getInt("h_travail"));
                p.setH_absence(rs.getInt("h_absence"));
                p.setDate_embauche(rs.getDate("date_embauche"));
                   
                 
                personnel.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return personnel;
    }

    @Override
    public void supprimer(personnel t) {
      
        String sql="delete from personnel where id = '"+t.getId()+"'";
        try {            
            PreparedStatement ste =cnx.prepareStatement(sql);           
            ste.executeUpdate(sql);
            System.out.println("personnel supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
    @Override
    public void modifier(personnel t) {
        String sql="update personnel set nom_p= ? , prenom_p=?,tel_p=?,email_p=?, mdp=?, salaire_p=?, poste=?, h_travail=?, h_absence=?,date_embauche=? where id='"+t.getId()+"'";
            try {
            PreparedStatement ste =cnx.prepareStatement(sql);
      
            ste.setString(1, t.getNom_p());
            ste.setString(2, t.getPrenom_p());
            ste.setInt(3, t.getTel_p());
            ste.setString(4, t.getEmail_p());
            ste.setString(5, t.getMdp());
            ste.setDouble(6, t.getSalaire_p());
            ste.setString(7, t.getPoste());
            ste.setInt(8, t.getH_travail());
            ste.setInt(9, t.getH_absence());
            ste.setDate(10, t.getDate_embauche());
           // ste.setDate(11, t.getDate_embauche());
            
           ste.executeUpdate();
            System.out.println("personnel Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }  
    public List<String> getAll() {
        List<String> list = new ArrayList<String>();
        try {
            String requetee = "SELECT nom_s FROM Salle";
            PreparedStatement pst = cnx.prepareStatement(requetee);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs.toString());

            while (rs.next()) {
                list.add(rs.getString("nom_s"));
            }

            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
      public int chercherIds(String nom_s) throws SQLException{
         int idd=0;
         String requetee = "SELECT id FROM salle where nom_s='"+nom_s+"';";
            PreparedStatement pst = cnx.prepareStatement(requetee);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {idd= rs.getInt("id");}
            return idd;
         }
            
    }
    
