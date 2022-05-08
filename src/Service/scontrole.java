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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author lamis
 */
public class scontrole {
    Connection cnx= MaConnexion.getInstance().getCnx();
    public static boolean Controlechar(Salle s) {
		String str = (s.getNom_s()).toLowerCase();
                if (str.length() == 0)
                    return false;
		char[] charArray = str.toCharArray();
                
		for (int i = 0; i < charArray.length; i++) {
			char ch = charArray[i];
			if (!((ch >= 'a' && ch <= 'z') || (String.valueOf(ch)).equals(" "))) {
				return false;
			}
		}
		return true;
	}
      public static boolean Controlechar1(Salle s) {
		String str = (s.getVille()).toLowerCase();
                if (str.length() == 0)
                    return false;
		char[] charArray = str.toCharArray();
                
		for (int i = 0; i < charArray.length; i++) {
			char ch = charArray[i];
			if (!((ch >= 'a' && ch <= 'z') || (String.valueOf(ch)).equals(" "))) {
				return false;
			}
		}
		return true;
	}
       public  boolean Controlechar2(personnel p) {
		String str = (p.getNom_p()).toLowerCase();
                if (str.length() == 0)
                    return false;
		char[] charArray = str.toCharArray();
                
		for (int i = 0; i < charArray.length; i++) {
			char ch = charArray[i];
			if (!((ch >= 'a' && ch <= 'z') || (String.valueOf(ch)).equals(" "))) {
				return false;
			}
		}
		return true;
	}
       
         public  boolean Controlechar3(personnel p) {
		String str = (p.getPrenom_p()).toLowerCase();
                if (str.length() == 0)
                    return false;
		char[] charArray = str.toCharArray();
                
		for (int i = 0; i < charArray.length; i++) {
			char ch = charArray[i];
			if (!((ch >= 'a' && ch <= 'z') || (String.valueOf(ch)).equals(" "))) {
				return false;
			}
		}
		return true;
	}
         
         
    public int existe(personnel p) throws SQLException {
        Statement s = cnx.createStatement();
        ResultSet rs = s.executeQuery("SELECT COUNT(*) from personnel WHERE email_p = '" + p.getEmail_p()+"'");
        int size = 0;
        rs.next();
        size=rs.getInt(1);
        return size;
    }
     public int existe1(Salle s) throws SQLException {
        Statement s1 = cnx.createStatement();
        ResultSet rs = s1.executeQuery("SELECT COUNT(*) from salle WHERE adresse_s = '" + s.getAdresse_s()+"'");
        int size = 0;
        rs.next();
        size=rs.getInt(1);
        return size;
    }
      
   
   public boolean controlmail(String text) {
////  if(text == null || text.isEmpty()){ return false ;}
////  String emailr="^ [a-zA-Z0-9 _ + & * -] + (?: \\\\. [A-zA-Z0-9 _ + & * -] +) * @ (?: [A- zA-Z0-9 -] + \\\\.) + [a-zA-Z] {2,7} $";
////  Pattern p = Pattern.compile(emailr);
////        return Pattern.matcher(text).matches();
////  
//  }      
String emailr = "^ [A-Z0-9 ._% + -] + @ [A-Z0-9 .-] + \\.[A-Z] {2,6} $";
//String emailr = "^ [a-zA-Z0-9 _ + & * -] + (?: \\\\\\\\. [A-zA-Z0-9 _ + & * -] +) * @ (?: [A- zA-Z0-9 -] + \\\\\\\\.) + [a-zA-Z] {2,7} $";
Pattern emailp = Pattern.compile(emailr,Pattern.CASE_INSENSITIVE);
Matcher matcher =emailp.matcher(text);
return matcher.find();
//String masque = "^&#91;a-zA-Z&#93;+&#91;a-zA-Z0-9\\._-&#93;*&#91;a-zA-Z0-9&#93;@&#91;a-zA-Z&#93;+"
//                        + "&#91;a-zA-Z0-9\\._-&#93;*&#91;a-zA-Z0-9&#93;+\\.&#91;a-zA-Z&#93;&#123;2,4&#125;$";
//Pattern pattern = Pattern.compile(masque);
//Matcher controler = pattern.matcher&#40;laSaisieDeLutilisateur&#41;;
//if &#40;controler.matches&#40;&#41;&#41;&#123;
  //return text;
  }
    public boolean isNumeric(String text) {
             if (text == null || text.trim().equals("")) {
                 return false;
             }
             for (int iCount = 0; iCount < text.length(); iCount++) {
                 if (!Character.isDigit(text.charAt(iCount))) {
                    return false;
               }
           }
            return true;
        }
     public boolean isNumeric1(String text) {
             if (text == null || text.trim().equals("")) {
                 return false;
             }
             for (int iCount = 0; iCount < text.length(); iCount++) {
                 if (!Character.isDigit(text.charAt(iCount))) {
                    return false;
               }
           }
            return true;
        }
}
