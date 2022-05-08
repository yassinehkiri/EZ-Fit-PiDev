


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Fournisseur;
import Tools.Maconnexion;
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
 * @author LENOVO
 */
public class FournisseurService implements Iservice<Fournisseur>{
  Connection cnx= Maconnexion.getInstance().getCnx();
    @Override
    public void ajouter(Fournisseur t) {
         
         try {
            
                String sql="insert into Fournisseur(id,nom_f,prenom_f,tel_f,email_f,adresse,rib_f,image) values('"+t.getId()+"','"+t.getNom_f()+"','"+t.getPrenom_f()+"','"+t.getTel_f()+"','"+t.getEmail_f()+"','"+t.getAdresse()+"','"+t.getRib_f()+"','"+t.getImage()+"')";
            Statement ste = cnx.createStatement();
           
            ste.executeUpdate(sql);
            System.out.println(" Fournisseur Ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public List<Fournisseur> afficher() {
           List<Fournisseur> Fournisseur = new ArrayList<>();
        String sql ="select * from Fournisseur";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                Fournisseur f = new Fournisseur();
                f.setId(rs.getInt("id"));
                f.setNom_f(rs.getString("nom_f"));
                f.setPrenom_f(rs.getString("prenom_f"));
                    f.setTel_f(rs.getInt("tel_f")); 
                    f.setEmail_f(rs.getString("email_f"));
                        f.setAdresse(rs.getString("adresse"));
                            f.setRib_f(rs.getString("rib_f"));
                                f.setImage(rs.getString("image"));
                Fournisseur.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Fournisseur;
    }

    @Override
    public void supprimer(Fournisseur t) {
         String sql="delete from Fournisseur where id = '"+t.getId()+"'";
        try {            
            PreparedStatement ste =cnx.prepareStatement(sql);           
            ste.executeUpdate(sql);
            System.out.println("Fournisseur supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Fournisseur t) {
        String sql="update Fournisseur set  nom_f=?, prenom_f= ?, tel_f= ?, email_f= ?, adresse= ?, rib_f= ?, image= ? where id='"+t.getId()+"'";
            try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setString(1, t.getNom_f());
            ste.setString(2, t.getPrenom_f());
              ste.setInt(3, t.getTel_f());
            ste.setString(4, t.getEmail_f());
              ste.setString(5, t.getAdresse());
            ste.setString(6, t.getRib_f());
              ste.setString(7, t.getImage());
           
           ste.executeUpdate();
            System.out.println("Fournisseur Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
       public ObservableList<Fournisseur> chercherTitreplat(String chaine){
          String sql="SELECT * FROM Fournisseur WHERE (nom_f LIKE ? or prenom_f LIKE ? or email_f LIKE ? )";
            
             Connection cnx= Maconnexion.getInstance().getCnx();
            String ch="%"+chaine+"%";
            ObservableList<Fournisseur> myList= FXCollections.observableArrayList();
        try {
           
            Statement ste= cnx.createStatement();
           // PreparedStatement pst = myCNX.getCnx().prepareStatement(requete6);
            PreparedStatement stee =cnx.prepareStatement(sql);  
            stee.setString(1, ch);
            stee.setString(2, ch);
            stee.setString(3, ch);
            ;
            
            ResultSet rs = stee.executeQuery();
            while (rs.next()){
                Fournisseur p = new Fournisseur ();
                p.setId(rs.getInt(1));
                p.setNom_f(rs.getString(2));
                p.setPrenom_f(rs.getString(3));
                p.setTel_f(rs.getInt(4));
             
                p.setEmail_f(rs.getString(6));
                p.setAdresse(rs.getString(7));
                p.setRib_f(rs.getString(8));
                //p.setImage(rs.getString(9)); 
                myList.add(p);
                System.out.println("Fournisseur trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
      }
}
