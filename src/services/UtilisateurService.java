/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.sun.org.apache.bcel.internal.classfile.Utility;
import entities.Utilisateur;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.MyConnexion;


public class UtilisateurService implements iservice<Utilisateur>{
    Connection cnx;
     public UtilisateurService() {
         cnx = MyConnexion.getInstance().getCnx();
    }
   public String[] connexion(String mail,String mdp){
       try{
         String query="SELECT id,code,role FROM utilisateur WHERE mail = ? AND mdp = ?";
         PreparedStatement smt = cnx.prepareStatement(query);
                smt.setString(1, mail);
                smt.setString(2, mdp);
    ResultSet rs= smt.executeQuery();
                while(rs.next()){
                    String[] r = new String[3];
                    r[0]= Integer.toString(rs.getInt("id"));
                    r[1]= rs.getString("code");
                    r[2]=rs.getString("role");
return  r;
    }       } catch(SQLException ex){
           System.out.println(ex.getMessage());
       }
       return null;
   }
    @Override
    public int ajouter(Utilisateur t) {
        
            try {
          
           String query="INSERT INTO utilisateur(mail,mdp,role) values(?,?,?)";
                PreparedStatement smt = cnx.prepareStatement(query);
                smt.setString(1, t.getEmail());
                smt.setString(2, t.getMdp());
                smt.setString(3, t.getRole());

                int i = smt.executeUpdate();
                System.out.println(i);
                return i;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
           
       }
            return 0;
    }
        public int ajouter(Utilisateur t,String code) {
        
            try {
          
           String query="INSERT INTO utilisateur(mail,mdp,role,code) values(?,?,?,?)";
                PreparedStatement smt = cnx.prepareStatement(query);
                smt.setString(1, t.getEmail());
                smt.setString(2, t.getMdp());
                smt.setString(3, t.getRole());
                smt.setString(4, code);
                int i = smt.executeUpdate();
                System.out.println(i);
                return i;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
           
       }
            return 0;
    }

   
   
    @Override
    public int modifier(Utilisateur t) {
         try 
            {
                  MessageDigest md = MessageDigest.getInstance("SHA-256");
    byte[] hash = md.digest(t.getMdp().getBytes());
    String mdpHash = Utility.toHexString(hash);  
                String query2="update utilisateur set mail=?, mdp=?, role=? where id=?";
                PreparedStatement smt = cnx.prepareStatement(query2); 
                smt.setString(1, t.getEmail());
                smt.setString(2, mdpHash);
                smt.setString(3, t.getRole());
                smt.setInt(4, t.getId());
                smt.executeUpdate();
                System.out.println("modification avec succee");
                return 1;
            } 
         catch (Exception ex) 
            {
                System.out.println(ex.getMessage());
                return 0;
            }
         
    }
    
    @Override
    public void supprimer(Utilisateur t) {
         try 
            {
                String query2="delete from utilisateur where id=?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                smt.setInt(1, t.getId());
                smt.executeUpdate();
                System.out.println("suppression avec succee");
            } 
         catch (SQLException ex) 
            {
                System.out.println(ex.getMessage());
            }
    }

    @Override
    public List<Utilisateur> find() {
        ArrayList l=new ArrayList(); 
        
        try {
       String query2="select * from utilisateur";
                PreparedStatement smt = cnx.prepareStatement(query2);
                Utilisateur p;
                ResultSet rs= smt.executeQuery();
                while(rs.next()){
                   p=new Utilisateur(rs.getInt("id"),rs.getString("mail"),rs.getString("mdp"),rs.getString("role"));
                   l.add(p);
                }
                System.out.println(l);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }

        return l;
    }
    public int confirmer(String mail,int id,String code){
              try {
       String query2="select code from utilisateur where id = ?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                                smt.setInt(1, id);
                  System.out.println(id);
                ResultSet rs= smt.executeQuery();
                while(rs.next()){
                    System.out.println(rs.getString("code")+" = "+code);
                   if (rs.getString("code").equals(code)){
                        String query="UPDATE `utilisateur` SET `code` = '0' WHERE `utilisateur`.`id` = ?";
                        PreparedStatement ps = cnx.prepareStatement(query);
                        ps.setInt(1, id);
                        ps.executeUpdate();
                       return 1;
                   }
                   else{
                        String gs = generateString();
                        String query="UPDATE `utilisateur` SET `code` = ? WHERE `utilisateur`.`id` = ?";
                        PreparedStatement ps = cnx.prepareStatement(query);
                        ps.setString(1,gs);
                        ps.setInt(2, id);
                        ps.executeUpdate();
                        Sendmail sm = new Sendmail();
                        sm.envoyer(mail, "ESPRIT-CONNECT Confirmation E-mail", "Your confirmation code is : \n"+gs);
                        JOptionPane.showMessageDialog(null,"Code renvoyer");
return 0;
                   }
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }
              return 0;
    }
    public int resetPassword(String mail){
String gs=generateString();
try{
              MessageDigest md = MessageDigest.getInstance("SHA-256");
    byte[] hash = md.digest(gs.getBytes());
    String mdpHash = Utility.toHexString(hash);  
    String query="UPDATE `utilisateur` SET `mdp` = ? WHERE `mail` = ?";
                        PreparedStatement ps = cnx.prepareStatement(query);
                        ps.setString(1,mdpHash);
                        ps.setString(2, mail);
                        ps.executeUpdate();
                          Sendmail sm = new Sendmail();
                        sm.envoyer(mail, "ESPRIT-CONNECT réinitialisation du mot de passe", "Votre mot de passe est: \n"+gs);
                       return 1;
                       
}
catch(Exception e){
    System.out.println(e.getMessage());
                  return 0;

}
                        
                   
      
                
        
    }
    
public String generateString() {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 8;
    Random random = new Random();

    String generatedString = random.ints(leftLimit, rightLimit + 1)
      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
      .limit(targetStringLength)
      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
      .toString();


    return generatedString;
}
    
}
