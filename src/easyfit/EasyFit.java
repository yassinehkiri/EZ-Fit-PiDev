/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyfit;

import Entities.Equipement;
import Entities.Fournisseur;
import Entities.Livraison;
import Service.EquipemenetService;
import Service.FournisseurService;
import Service.LivraisonService;
import Tools.Maconnexion;
import java.sql.Date;

/**
 *
 * @author LENOVO
 */
public class EasyFit {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         Maconnexion mc = Maconnexion.getInstance();
         Date d1= Date.valueOf("2022-10-10");
          Date d2= Date.valueOf("2021-11-10");
         FournisseurService fss=new FournisseurService();
         Fournisseur f=new Fournisseur("lamis","boutheina",9989,"erza","kjiuy","uiyop","trfde");
          Fournisseur f1=new Fournisseur(1,"mpmlo","bou",10,"erza","kjiuy","uiyop","trfde");
          Fournisseur f2=new Fournisseur(2);
       // fss.supprimer(f1);
          //fss.modifier(f1);
        // fss.ajouter(f); 
       // System.out.println(fss.afficher());;
         
         
         LivraisonService ls =new LivraisonService();
         Livraison l =new Livraison(2,"ere","aze","pl","okl",d1,d2);
         Livraison l1 =new Livraison(1,4,"chaima","ben","pl","okl",d1,d2);
         Livraison l2=new Livraison(1);
        // ls.ajouter(l); 
    //     ls.modifier(l1);
          ls.supprimer(l2);
       //  System.out.println(ls.afficher());;
         
         EquipemenetService es =new EquipemenetService();
         Equipement e =new Equipement(1,"er","errr","azsq","acv",1,d1,7.1);
         Equipement e1 =new Equipement(1,1,"er","errr","azsq","acv",1,d1,7.1);
            Equipement e2 =new Equipement(1);
       es.ajouter(e);
       // es.supprimer(e2);
        // es.modifier(e1);
       System.out.println(es.afficher());;
    }
    
    
}
