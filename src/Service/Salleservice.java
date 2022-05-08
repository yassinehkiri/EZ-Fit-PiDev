/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Salle;
import Tools.MaConnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author lamis
 */
public class Salleservice implements Iservice<Salle> {
  Connection cnx= MaConnexion.getInstance().getCnx();
   
scontrole sc= new scontrole();

    @Override
    public void ajouter(Salle t) {
         String sql="insert into salle(id,nom_s,adresse_s,code_postal,ville,nombre_p,image) values('"+t.getId()+"','"+t.getNom_s()+"','"+t.getAdresse_s()+"','"+t.getCode_postal()+"','"+t.getVille()+"','"+t.getNombre_p()+"','"+t.getImage()+"')";
 try {
     if(sc.existe1(t)==0){
          
            Statement ste = cnx.createStatement();
           
            ste.executeUpdate(sql);
            System.out.println(" salle Ajoutée");
     }
     else
     {
         System.out.println("salle deja existe");  
     }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
 
        }
    
    
    
//    public int existe(Salle t) throws SQLException {
//        Statement s = cnx.createStatement();
//        ResultSet rs = s.executeQuery("SELECT COUNT(*) from Salle WHERE nom_s = '" + t.getNom_s().toLowerCase() +"'");
//        int size = 0;
//        rs.next();
//        size=rs.getInt(1);
//        return size;
//    }

    @Override
    public List<Salle> afficher() {
    List<Salle> Salle = new ArrayList<>();
        String sql ="select * from Salle";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                Salle s = new Salle();
                s.setId(rs.getInt("id"));
                s.setNom_s(rs.getString("nom_s"));
                s.setAdresse_s(rs.getString("adresse_s"));
                s.setCode_postal(rs.getInt("code_postal"));
                s.setVille(rs.getString("ville"));
                s.setNombre_p(rs.getInt("nombre_p"));
                s.setImage(rs.getString("image"));
                Salle.add(s);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Salle;

    }

    @Override
    public void supprimer(Salle t) {
        
        String sql="delete from Salle where id = '"+t.getId()+"'";
        try {            
            PreparedStatement ste =cnx.prepareStatement(sql);           
            ste.executeUpdate(sql);
            System.out.println("salle supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Salle t) {
     String sql="update Salle set  nom_s=?, adresse_s= ? , code_postal=?,ville=?,nombre_p=?, image=? where id='"+t.getId()+"'";
            try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setString(1, t.getNom_s());
            ste.setString(2, t.getAdresse_s());
            ste.setInt(3, t.getCode_postal());
            ste.setString(4, t.getVille());
            ste.setInt(5, t.getNombre_p());
            ste.setString(6, t.getImage());
            
           ste.executeUpdate();
            System.out.println("salle Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
     public ObservableList<Salle> chercherTitreplat(String chaine){
          String sql="SELECT * FROM Salle WHERE (nom_s LIKE ? or adresse_s LIKE ? or ville LIKE ?)";
            
             Connection cnx= MaConnexion.getInstance().getCnx();
            String ch="%"+chaine+"%";
            ObservableList<Salle> myList= FXCollections.observableArrayList();
        try {
           
            Statement ste= cnx.createStatement();
           // PreparedStatement pst = myCNX.getCnx().prepareStatement(requete6);
            PreparedStatement stee =cnx.prepareStatement(sql);  
            stee.setString(1, ch);
            stee.setString(2, ch);
           stee.setString(3, ch);
            
            ResultSet rs = stee.executeQuery();
            while (rs.next()){
                
               
                Salle p = new Salle ();
                p.setId(rs.getInt(1));
                p.setNom_s(rs.getString(2));
                p.setAdresse_s(rs.getString(3));
                p.setCode_postal(rs.getInt(4));
             
                p.setVille(rs.getString(5));
                p.setNombre_p(rs.getInt(6));
                p.setImage(rs.getString(7));
                
                myList.add(p);
                System.out.println("titre trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
      }
}