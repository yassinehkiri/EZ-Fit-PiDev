/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Fournisseur;
import Entities.Livraison;
import Tools.Maconnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class LivraisonService implements Iservice<Livraison>  {
 Connection cnx= Maconnexion.getInstance().getCnx();
    @Override
    public void ajouter(Livraison t) {
     try {
            Date adresse_livraison;
             Date date_arrive;
              String sql="insert into Livraison(id,num_l,nom_livreur,prenom_livreur,tel_livreur,adresse_livraison,date_livraison,date_arrive) values('"+t.getId()+"','"+t.getNum_l()+"','"+t.getNom_livreur()+"','"+t.getPrenom_livreur()+"','"+t.getTel_livreur()+"','"+t.getAdresse_livraison()+"','"+t.getDate_livraison()+"','"+t.getDate_arrive()+"')";
            Statement ste = cnx.createStatement();
           
            ste.executeUpdate(sql);
            System.out.println(" Livraison Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public List<Livraison> afficher() {
         List<Livraison> Livraison = new ArrayList<>();
        String sql ="select * from Livraison";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                Livraison l = new Livraison();
                l.setId(rs.getInt("id"));
                l.setNum_l(rs.getInt("num_l"));
                l.setNom_livreur(rs.getString("nom_livreur"));
                    l.setPrenom_livreur(rs.getString("prenom_livreur")); 
                    l.setTel_livreur(rs.getString("tel_livreur"));
                    l.setAdresse_livraison(rs.getString("adresse_livraison"));
                            l.setDate_livraison(rs.getDate("date_livraison"));
                                l.setDate_arrive(rs.getDate("date_arrive"));
                Livraison.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Livraison;
    }

    @Override
    public void supprimer(Livraison t) {
         String sql="delete from Livraison where id = '"+t.getId()+"'";
        try {            
            PreparedStatement ste =cnx.prepareStatement(sql);           
            ste.executeUpdate(sql);
            System.out.println("Livraison supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Livraison t) {
           String sql="update Livraison set  num_l=?, nom_livreur= ?, prenom_livreur= ?, tel_livreur= ?, adresse_livraison= ?, date_livraison= ?, date_arrive= ? where id='"+t.getId()+"'";
            try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setInt(1, t.getNum_l());
            ste.setString(2, t.getNom_livreur());
              ste.setString(3, t.getPrenom_livreur());
            ste.setString(4, t.getTel_livreur());
              ste.setString(5, t.getAdresse_livraison());
            ste.setDate(6, t.getDate_livraison());
              ste.setDate(7, t.getDate_arrive());
           
           ste.executeUpdate();
            System.out.println("Livraison Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    }
    
