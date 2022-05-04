/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Abonne;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.AbonneService;
import util.MyConnexion;
import util.javaMail;


/**
 * FXML Controller class
 *
 * @author yassine
 */
public class AbonneController implements Initializable {

    @FXML
    private TextField cina;
    @FXML
    private TextField abo;
    @FXML
    private TextField noma;
    @FXML
    private TextField prenoma;
    @FXML
    private TextField agea;
    @FXML
    private TextField sexea;
    @FXML
    private TextField emaila;
    @FXML
    private TextField adressea;
    @FXML
    private TextField imga;
    @FXML
    private TextField mdpa;
    @FXML
    private TextField msga;
    @FXML
    private TextField tela;
    @FXML
    private TableView<Abonne> tablea;
    @FXML
    private TableColumn<Abonne, String> CINA;
    @FXML
    private TableColumn<Abonne, String> ABOA;
    @FXML
    private TableColumn<Abonne, String> NOMA;
    @FXML
    private TableColumn<Abonne, String> PRENOMA;
    @FXML
    private TableColumn<Abonne, String> AGEA;
    @FXML
    private TableColumn<Abonne, String > SEXEA;
    @FXML
    private TableColumn<Abonne, String > MAILA;
    @FXML
    private TableColumn<Abonne, String > ADRESSEA;
    @FXML
    private TableColumn<Abonne, String > IMGA;
    @FXML
    private TableColumn<Abonne, String > MDPA;
    @FXML
    private TableColumn<Abonne, String > MSGA;
    @FXML
    private TableColumn<Abonne, String > TELA;
    @FXML
    private Button ajouta;
    @FXML
    private Button modifa;
    @FXML
    private Button suppa;
    
    int index=-1;
    @FXML
    private TextField objet;
    @FXML
    private TextField corps;
    @FXML
    private TextField id;
     

  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        affiche();
        ObservableList<Abonne>  list =  FXCollections.observableArrayList();
        
          try { 
            Connection cnx = MyConnexion.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT id, nom_a , prenom_a, age_a, sexe_a, email_a, mdp_a, tel_a, adresse_a, message, image  from abonne");
            while(rs.next())
            {
               list.add(new Abonne( rs.getInt("id"),rs.getInt("age_a"),rs.getInt("tel_a"),rs.getString("nom_a"),rs.getString("prenom_a")
                ,rs.getString("sexe_a"),rs.getString("email_a"),rs.getString("mdp_a")
                ,rs.getString("adresse_a"),rs.getString("message"),rs.getString("image")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AbonneController.class.getName()).log(Level.SEVERE, null, ex);
        }

     /*problem lahne khater string w attribut hedha integer
          CINA.setCellValueFactory(data ->
       {
            return new ReadOnlyStringWrapper(data.getValue().toString(getId()));
        });*/
    

    /* hedha cle etreng ena mna7ih
     ABOA.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getRedacteurR());
        });*/
    
    NOMA.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getNom());
        });
    
    PRENOMA.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getPrenom());
        });
    /*  problem lahne khater string w attribut hedha integer
    AGEA.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getAge());
        });*/
     
     SEXEA.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getSexe());
        });
      MAILA.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getEmail());
        });
      
       ADRESSEA.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getAdresse());
        });
       
       IMGA.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getImage());
        });
      
       MDPA.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getMdp());
        });
       MSGA.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getMessage());
        });
      /* problem lahne khater string w attribut hedha integer
       TELA.setCellValueFactory(data ->
        {
            return new ReadOnlyStringWrapper(data.getValue().getTel());
        });
         */
       
        // TODO
        tablea.setItems(list);
        tablea.refresh();
    // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        
        

                  AbonneService ts1 = new  AbonneService();
                  ts1.AjouterAbonne(new Abonne(Integer.parseInt(cina.getText()),Integer.parseInt(agea.getText()),Integer.parseInt(tela.getText())
                          ,noma.getText(),prenoma.getText(),sexea.getText(),emaila.getText(),adressea.getText()
                  ,imga.getText(),mdpa.getText(),msga.getText()));
                  try {
            String Object = objet.getText();
            String Corps = corps.getText();
            javaMail.sendMail("yassine.hkiri@esprit.tn", Object, Corps);
        } catch (Exception e) {
            e.printStackTrace();
        }
               affiche();
               
                   
    
 
    }

    @FXML
    private void modifier(ActionEvent event) {
        
        String id = cina.getText();
    String abonnement_id = abo.getText();
    String nom_a = noma.getText();
    String prenom_a = prenoma.getText();
    String age_a = agea.getText();
    String sexe_a =sexea.getText();
    String email_a = emaila.getText();
    String mdp_a = mdpa.getText();
    String tel_a = tela.getText();
    String adresse_a = adressea.getText();
    String message = msga.getText();
    String image = imga.getText();
    
    Abonne a = new Abonne();
    AbonneService ab = new AbonneService();
    a = (Abonne) tablea.getSelectionModel().getSelectedItem();
    a.setId(a.getId());
   // a.setIdab(a.getIdab());
    a.setNom(a.getNom());
    a.setPrenom(a.getPrenom());
    a.setAge(a.getAge());
    a.setSexe(a.getSexe());
    a.setEmail(a.getEmail());
    a.setMdp(a.getMdp());
    a.setTel(a.getTel());
    a.setAdresse(a.getAdresse());
    a.setMessage(a.getMessage());
    a.setImage(a.getImage());
    ab.modifier(a);
        affiche();
           
    
             
       }
        
        
        
    

    @FXML
    private void supprimer(ActionEvent event) {
         AbonneService r = new AbonneService();
        Abonne rl = new Abonne();
        rl = (Abonne) tablea.getSelectionModel().getSelectedItem();
                

        r.SupprimerAbonne(rl.getId());
         affiche();
        
        
    }

    @FXML
    private void selected(MouseEvent event) {
   Abonne evt = tablea.getSelectionModel().getSelectedItem();
       if (index<= -1)
        {return; } 
       noma.setText(evt.getNom());
       prenoma.setText(evt.getPrenom());
       sexea.setText(evt.getSexe());
       emaila.setText(evt.getEmail());
       adressea.setText(evt.getAdresse());
       imga.setText(evt.getImage());
       mdpa.setText(evt.getMdp());
       msga.setText(evt.getMessage());
        //String a = Integer.toString(evt.getIdab());
        //abo.setText(a);
        String b = Integer.toString(evt.getId());
        cina.setText(b);
        String c =Integer.toString(evt.getAge());
        agea.setText(c);
        String d =Integer.toString(evt.getTel());
        tela.setText(d);

    }
    
    
    
     public ObservableList<Abonne> show1()
    { 
       

           try {
               ObservableList<Abonne> obl =FXCollections.observableArrayList();
                             Connection cnx = MyConnexion.getInstance().getCnx();
 //exécution de la réquette et enregistrer le resultat dans le resultset
                  PreparedStatement pt= cnx.prepareStatement("SELECT id, nom_a, prenom_a, age_a, sexe_a, email_a, mdp_a, tel_a, adresse_a, message, image  from abonne ");
                  ResultSet rs = pt.executeQuery();
                  while(rs.next()){
                  //obl.add(new Note(rs.getFloat(1),rs.getFloat(2),rs.getFloat(3),rs.getInt(4),rs.getString(5)));
                 Abonne ls = new Abonne();
                 ls.setId(rs.getInt("id"));
                 ls.setNom(rs.getString("nom_a"));
                 ls.setPrenom(rs.getString("prenom_a"));
                 ls.setAge(rs.getInt("age_a"));
                 ls.setSexe(rs.getString("sexe_a"));
                 ls.setEmail(rs.getString("email_a"));
                 ls.setMdp(rs.getString("mdp_a"));
                 ls.setTel(rs.getInt("tel_a"));
                 ls.setAdresse(rs.getString("adresse_a"));
                 ls.setMessage(rs.getString("message"));
                 ls.setImage(rs.getString("image"));
                 
             

                  
                  System.out.println("");
                   obl.add(ls);
                  }
                  return obl;
                  
              } catch (SQLException ex) {
                System.out.println("Erreur"+ex);
              }
           return null;
    } 
    
    
     public void affiche() {
        
           
                         
      CINA.setCellValueFactory(new PropertyValueFactory<>("id"));
      //ABOA.setCellValueFactory(new PropertyValueFactory<>("abonnement_id"));
      NOMA.setCellValueFactory(new PropertyValueFactory<>("nom_a"));
      PRENOMA.setCellValueFactory(new PropertyValueFactory<>("prenom_a"));
      AGEA.setCellValueFactory(new PropertyValueFactory<>("age_a"));
      SEXEA.setCellValueFactory(new PropertyValueFactory<>("sexe_a"));
      MAILA.setCellValueFactory(new PropertyValueFactory<>("email_a"));
      MDPA.setCellValueFactory(new PropertyValueFactory<>("mdp_a"));
      TELA.setCellValueFactory(new PropertyValueFactory<>("tel_a"));
      ADRESSEA.setCellValueFactory(new PropertyValueFactory<>("adresse_a"));
      MSGA.setCellValueFactory(new PropertyValueFactory<>("message"));
      IMGA.setCellValueFactory(new PropertyValueFactory<>("image"));
      
     
      ObservableList<Abonne> obl =FXCollections.observableArrayList();
      obl=show1(); 
      tablea.setItems(obl);
      System.out.println(""+obl);

                      
    }
    
    public void mailfonction(MouseEvent event) {
        try {
            String Object = objet.getText();
            String Corps = corps.getText();
            javaMail.sendMail("yassine.hkiri@esprit.tn", Object, Corps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void populateTable(ObservableList<Abonne> branlist){
       tablea.setItems(branlist);
       }
    @FXML
    private void search(KeyEvent event) {
        AbonneService bs=new AbonneService(); 
        Abonne b= new Abonne();
        ObservableList<Abonne>filter= bs.chercherTitreplat(id.getText());
        populateTable(filter);
    } 



  
    
    
    
    
    
    
    
    
    
    
    
}
