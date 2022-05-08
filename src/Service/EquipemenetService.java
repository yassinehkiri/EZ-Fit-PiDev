/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Equipement;
import Entities.Fournisseur;
import Tools.Maconnexion;
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
 * @author LENOVO
 */
public class EquipemenetService implements Iservice<Equipement> {
Connection cnx= Maconnexion.getInstance().getCnx();
    @Override
    public void ajouter(Equipement t) {
        
//            try {
//             Date date_commande;
//                String sql="insert into Equipement(id,fournisseur_id,nom_e,type_e,marque,gamme,quantite,date_commande,prix) values('"+t.getId()+"','"+t.getFournisseur_id()+"','"+t.getNom_e()+"','"+t.getType_e()+"','"+t.getMarque()+"','"+t.getGamme()+"','"+t.getQuantite()+"','"+t.getDate_commande()+"','"+t.getPrix()+"')";
//            Statement ste = cnx.createStatement();
//           
//            ste.executeUpdate(sql);
//            System.out.println(" Equipement Ajoutée");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        } 
   try{
            String sql="insert into Equipement(fournisseur_id,nom_e,type_e,marque,gamme,quantite,date_commande,prix) Values(?,?,?,?,?,?,?,?)";
            PreparedStatement ste=cnx.prepareStatement(sql);
            ste.setInt(1, t.getFournisseur_id());
            ste.setString(2, t.getNom_e()); 
            ste.setString(3, t.getType_e());
            ste.setString(4, t.getMarque());
            ste.setString(5,t.getGamme());
            ste.setInt(6,t.getQuantite());
            ste.setDate(7,t.getDate_commande());
            ste.setDouble(8,t.getPrix());
            
          
            ste.executeUpdate();
            System.out.println("Equipement ajouté");
        }catch(SQLException ex){
             System.out.println(ex.getMessage());
        }
            }

    @Override
    public ObservableList<Equipement> afficher() {
          // List<Equipement> Equipement = new ArrayList<>();
   ObservableList<Equipement> Equipement = FXCollections.observableArrayList();
        String sql ="select Equipement.id ,Fournisseur.nom_f ,Equipement.nom_e ,Equipement.type_e, Equipement.marque, Equipement.gamme ,Equipement.quantite, Equipement.date_commande,Equipement.prix from Equipement INNER JOIN Fournisseur where Equipement.fournisseur_id=Fournisseur.id ";
         
       
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                Equipement p = new Equipement();
               
                p.setId(rs.getInt("id"));
                p.setNom_f(rs.getString("nom_f"));
                p.setNom_e(rs.getString("nom_e"));
                p.setType_e(rs.getString("type_e"));
              
                p.setMarque(rs.getString("marque"));
                p.setGamme(rs.getString("gamme"));
                 p.setQuantite(rs.getInt("quantite"));
                 p.setDate_commande(rs.getDate("date_commande"));
                  p.setPrix(rs.getDouble("prix"));
                Equipement.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Equipement;

    }

    @Override
    public void supprimer(Equipement t) {
          String sql="delete from Equipement where id = '"+t.getId()+"'";
        try {            
            PreparedStatement ste =cnx.prepareStatement(sql);           
            ste.executeUpdate(sql);
            System.out.println("Equipement supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Equipement t) {
           String sql="update Equipement set   nom_e= ?, type_e= ?, marque= ?, gamme= ?, quantite= ?, date_commande= ?, prix= ? where id='"+t.getId()+"'";
            try {
            PreparedStatement ste =cnx.prepareStatement(sql);
          //  ste.setInt(1, t.getFournisseur_id());
            ste.setString(1, t.getNom_e());
              ste.setString(2, t.getType_e());
            ste.setString(3, t.getMarque());
              ste.setString(4, t.getGamme());
             ste.setInt(5, t.getQuantite());
              ste.setDate(6, t.getDate_commande());
                   ste.setDouble(7, t.getPrix());
           ste.executeUpdate();
            System.out.println("Equipement Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<String> getAll() {
        List<String> list = new ArrayList<String>();
        try {
            String requetee = "SELECT nom_f FROM Fournisseur";
            PreparedStatement pst = cnx.prepareStatement(requetee);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs.toString());

            while (rs.next()) {
                list.add(rs.getString("nom_f"));
            }

            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
      public int chercherIds(String nom_f) throws SQLException{
         int idd=0;
         String requetee = "SELECT id FROM Fournisseur where nom_f='"+nom_f+"';";
            PreparedStatement pst = cnx.prepareStatement(requetee);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {idd= rs.getInt("id");}
            return idd;
         }
      public int calculerequipement() throws SQLException{
  
        String sql="SELECT sum(quantite*prix) as somme FROM Equipement ";
        int a=0;
          
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
          
              
               
               
                
                
            
            a=rs.getInt("somme");     
        
    }return a;  
    }
      public List getById() throws SQLException{
         List<Equipement> res = new ArrayList<Equipement>();
        String sql = "SELECT * FROM reservation ORDER BY resId  DESC LIMIT 1";
           Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
           while(rs.next()){
               
        Equipement p = new Equipement();
               
                p.setId(rs.getInt("id"));
                p.setNom_f(rs.getString("nom_f"));
                p.setNom_e(rs.getString("nom_e"));
                p.setType_e(rs.getString("type_e"));
              
                p.setMarque(rs.getString("marque"));
                p.setGamme(rs.getString("gamme"));
                 p.setQuantite(rs.getInt("quantite"));
                 p.setDate_commande(rs.getDate("date_commande"));
                  p.setPrix(rs.getDouble("prix"));
                         
                         
                
                res.add(p);
                
                
               
                

           }

       
            return res;
    }
    }
    

